package com.crow.billing.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPaymentRequestDto {
	@NotBlank(message = "Bill Id is required")
	private String id;
}
