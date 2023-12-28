package com.crow.billing.dto;


import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentDto {
	@NotBlank(message = "Amount paid is required")
	private String amountPaid;
	@NotBlank(message = "Date Paid is required")
	private String dateTime;
	@NotBlank(message = "Comment is required")
	private String comment;
	@NotBlank(message = "Bill being paid")
	private String billId;
	@NotBlank(message = "Bank Name is required")
	private String bankName;
	@NotBlank(message = "Receipt No is required")
	private String receiptNo;
}
