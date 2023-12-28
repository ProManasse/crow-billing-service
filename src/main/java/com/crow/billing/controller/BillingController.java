package com.crow.billing.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crow.billing.dto.BillDto;
import com.crow.billing.dto.BillPaymentRequestDto;
import com.crow.billing.dto.MessageResponse;
import com.crow.billing.dto.PaymentDto;
import com.crow.billing.dto.PeriodBillDTO;
import com.crow.billing.dto.PeriodDto;
import com.crow.billing.service.IBillService;
import com.crow.billing.service.IBillStateService;
import com.crow.billing.service.IPeridService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping("/crow/billing/")
@RequiredArgsConstructor
public class BillingController {
	
	@Autowired
	protected final IBillService billService;
	
	@Autowired
	protected final IPeridService periodService;
	
	@Autowired
	protected final IBillStateService billStateService;
	
	@PostMapping({"/period"})
	public ResponseEntity<?> createEmployee(@RequestBody PeriodDto periodDto) {
		periodService.create(periodDto);
		return new ResponseEntity<>(new MessageResponse("Billing Period Opened Successfully!"), HttpStatus.CREATED);
	}
	
	@PostMapping({"/bill"})
	public ResponseEntity<?> createBill(@RequestBody BillDto billDto) {
		return new ResponseEntity<>(billService.create(billDto), HttpStatus.CREATED);
	}
	
	@PostMapping({"/payment"})
	public ResponseEntity<?> createPay(@RequestBody PaymentDto paymentDto) {
		billStateService.pay(paymentDto);
		return new ResponseEntity<>(new MessageResponse("Payment Saved Successfully!"), HttpStatus.CREATED);
	}
	
	@GetMapping({"/periods"})
	public ResponseEntity<?> getAllPeriods() {
		return new ResponseEntity<>(periodService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping({"/bills"})
	public ResponseEntity<?> getAllBills() {
		return new ResponseEntity<>(billService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping({"/payments"})
	public ResponseEntity<?> getAllPayments() {
		return new ResponseEntity<>(billStateService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping({"/bill-payments"})
	public ResponseEntity<?> getBillPayments(@RequestBody BillPaymentRequestDto billPaymentDto) {
		return new ResponseEntity<>(billStateService.getBillPayments(billPaymentDto), HttpStatus.OK);
	}
	
	@PostMapping({"/bulkBilling"})
	public ResponseEntity<?> bulkBilling(@RequestBody List<BillDto> billDtos) {
		billService.bulkBilling(billDtos);
		return new ResponseEntity<>(new MessageResponse("Billed Successfully"), HttpStatus.OK);
	}
	
	@PostMapping({"/validateBill"})
	public ResponseEntity<?> validateBill(@RequestBody PeriodBillDTO pbd) {
		billService.validateBill(pbd);
		return new ResponseEntity<>(new MessageResponse("Bill Validated Successfully"), HttpStatus.OK);
	}
	

}
