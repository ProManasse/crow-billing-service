package com.crow.billing.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crow.billing.dto.BillDto;
import com.crow.billing.dto.BillPaymentRequestDto;
import com.crow.billing.dto.PaymentDto;
import com.crow.billing.model.Bill;
import com.crow.billing.model.BillState;
import com.crow.billing.model.EBillState;
import com.crow.billing.repository.BillRepository;
import com.crow.billing.repository.BillStateRepository;

@Service
public class BillStateServiceImpl implements IBillStateService{
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillStateRepository billStateRepository;

	
	@Override
	public BillState pay(PaymentDto paymentDto) {
		Optional<Bill> bill=billRepository.findById(UUID.fromString(paymentDto.getBillId()));
		BigDecimal sum=new BigDecimal(0);
		BigDecimal tp=new BigDecimal(0);
		if(!bill.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bill Not Found");
		}
		Bill b=bill.get();
		Optional<BigDecimal> totalPaid=billStateRepository.sumAmountPaid(b);
		tp=totalPaid.isPresent()?totalPaid.get():new BigDecimal(0);
		b.setBalance(b.getTotalDue().subtract(tp));
		sum=tp.add(new BigDecimal(paymentDto.getAmountPaid()));
		EBillState status=null;
		if(sum.compareTo(b.getTotalDue())>=0) {
			status=EBillState.FULLY_PAID;
		}else if(sum.compareTo(b.getTotalDue())<0 && sum.compareTo(new BigDecimal(0))>0){
			status=EBillState.PARTIALLY_PAID;
		}else {
			status=EBillState.NOT_PAID;
		}
		BillState billState=BillState.builder()
				.id(UUID.randomUUID())
				.dateTime(LocalDateTime.now())
				.status(status)
				.comment(paymentDto.getComment())
				.bill(bill.get())
				.bankName(paymentDto.getBankName())
				.receiptNo(paymentDto.getReceiptNo())
				.amountPaid(new BigDecimal(paymentDto.getAmountPaid()))
				.build();
		billStateRepository.save(billState);
		b.setBalance(b.getTotalDue().subtract(sum));
		b.setStatus(status);
		billRepository.save(b);
		return billState;
	}

	@Override
	public List<BillState> getAll() {
		return billStateRepository.findAll();
	}

	@Override
	public List<BillState> getByBranch(String branch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillState> getBillPayments(BillPaymentRequestDto billPaymentRequestDto) {
		return billStateRepository.getPayments(billRepository.findById(UUID.fromString(billPaymentRequestDto.getId())).get());
	}




}
