package com.crow.billing.service;

import java.util.List;

import com.crow.billing.dto.BillDto;
import com.crow.billing.dto.BillPaymentRequestDto;
import com.crow.billing.dto.PaymentDto;
import com.crow.billing.model.Bill;
import com.crow.billing.model.BillState;

public interface IBillStateService {
	//Accountant
	BillState pay(PaymentDto paymentDto);
	//Master, DAF
	List<BillState> getAll();
	//Accountant
	List<BillState> getByBranch(String branch);
	List<BillState> getBillPayments(BillPaymentRequestDto billPaymentRequestDto);

}
