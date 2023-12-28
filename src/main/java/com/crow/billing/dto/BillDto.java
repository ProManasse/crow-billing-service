package com.crow.billing.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BillDto {
	private String studentId;
	private String name;
	private String otherName;
	private String branch;
	private String totalDue;
	private String studentClass;
}
