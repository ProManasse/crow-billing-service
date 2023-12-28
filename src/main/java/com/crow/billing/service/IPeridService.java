package com.crow.billing.service;

import java.util.List;

import com.crow.billing.dto.PeriodDto;
import com.crow.billing.model.Period;

public interface IPeridService {
	//DAF
	Period create(PeriodDto periodDto);
	//Master
	List<Period> getAll();
	//Accountant
	List<Period> getByBranch(String branch);
}
