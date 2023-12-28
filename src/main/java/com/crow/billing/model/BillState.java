package com.crow.billing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_payments")
public class BillState {
	@Id
	private UUID id;
	private BigDecimal amountPaid;
	private LocalDateTime dateTime;
	@Enumerated(EnumType.STRING)
	private EBillState status;
	private String comment;
	@Column(unique = false)
	private String receiptNo;
	private String bankName;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="bill_id", nullable=false)
	private Bill bill;
	
	
}
