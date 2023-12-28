package com.crow.billing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="t_bills")
public class Bill {
	@Id
	private UUID id;
	private String studentId;
	private String name;
	private String otherName;
	private String branch;
	private BigDecimal totalDue;
	private LocalDateTime dateTime;
	private BigDecimal balance;
	private String studentClass;
	@Enumerated(EnumType.STRING)
	private EValidity validity;
	@Enumerated(EnumType.STRING)
	private EBillState status;
	@OneToMany(mappedBy="bill")
	private List<BillState> payments;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="period_id", nullable=false)
	private Period period;

	
}
