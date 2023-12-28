package com.crow.billing.service;

import java.util.List;
import com.crow.billing.dto.BillDto;
import com.crow.billing.dto.PeriodBillDTO;
import com.crow.billing.model.Bill;

public interface IBillService {
	//by Student
	Bill create(BillDto billDto);
	//Accountant
	List<Bill> getByBranch(String branch);
	//Master,DAF
	List<Bill> getAll();
	void bulkBilling(List<BillDto> billDtos);
	
	void validateBill(PeriodBillDTO periodBillDTO);
}
