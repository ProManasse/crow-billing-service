package com.crow.billing.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.crow.billing.dto.BillDto;
import com.crow.billing.dto.PeriodBillDTO;
import com.crow.billing.model.Bill;
import com.crow.billing.model.BillState;
import com.crow.billing.model.EBillState;
import com.crow.billing.model.EValidity;
import com.crow.billing.model.Period;
import com.crow.billing.repository.BillRepository;
import com.crow.billing.repository.BillStateRepository;
import com.crow.billing.repository.PeriodRepository;

@Service
public class BillServiceImpl implements IBillService{
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillStateRepository billStateRepository;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	@Override
	public Bill create(BillDto billDto) {
		if(!periodRepository.findOpenPeriod().isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Open Billing Period");
		}
		if(billRepository.verifyBill(periodRepository.findOpenPeriod().get(),billDto.getStudentId()).size()!=0) {
			System.out.println("StudentId: "+billDto.getStudentId()+" has bill");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student has a bill already");
		}
		Bill bill=Bill.builder()
				.id(UUID.randomUUID())
				.studentId(billDto.getStudentId())
				.name(billDto.getName())
				.otherName(billDto.getOtherName())
				.branch(billDto.getBranch())
				.totalDue(new BigDecimal(billDto.getTotalDue()))
				.balance(new BigDecimal(billDto.getTotalDue()))
				.dateTime(LocalDateTime.now())
				.status(EBillState.NOT_PAID)
				.studentClass(billDto.getStudentClass())
				.period(periodRepository.findOpenPeriod().get())
				.build();
		bill=billRepository.save(bill);
		saveInitialState(bill);
		return bill;
	}

	@Override
	public List<Bill> getByBranch(String branch) {
		return billRepository.findAll();
	}

	@Override
	public List<Bill> getAll() {
		return billRepository.findAll();
	}
	
	public void saveInitialState(Bill bill) {
		BillState billState=BillState.builder()
				.bill(bill)
				.id(UUID.randomUUID())
				.dateTime(LocalDateTime.now())
				.comment("not yet paid")
				.amountPaid(new BigDecimal(0))
				.bankName("no bank")
				.receiptNo("no receipt")
				.status(EBillState.NOT_PAID)
				.build();
		billStateRepository.save(billState);
	}
	
	@Override
	public void bulkBilling(List<BillDto> billDtos)  {
		for(BillDto bd:billDtos) {
			create(bd);
		}
	}
	
	public void validateBill(PeriodBillDTO periodBillDTO) {
		Period p=periodRepository.findOpenPeriod().get();
		Bill b=billRepository.verifyBill(periodRepository.findOpenPeriod().get(),periodBillDTO.getStudentId()).get(0);
		b.setValidity(EValidity.VALID);
		billRepository.save(b);
		
	}
	


}
