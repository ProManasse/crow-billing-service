package com.crow.billing.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_periods")
public class Period {
	@Id
	private UUID id;
	private String academicYear;
	@Enumerated(EnumType.STRING)
	private ETerm term;
	@Enumerated(EnumType.STRING)
	private EPeriodStatus status;
	@OneToMany(mappedBy="period")
	private List<Bill> bills; 
	
	
}
