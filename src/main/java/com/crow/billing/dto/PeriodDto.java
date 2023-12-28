package com.crow.billing.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PeriodDto {
	private String academicYear;
	private String term;
}
