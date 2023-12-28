package com.crow.billing.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crow.billing.dto.PeriodDto;
import com.crow.billing.model.EPeriodStatus;
import com.crow.billing.model.ETerm;
import com.crow.billing.model.Period;
import com.crow.billing.repository.PeriodRepository;

@Service
public class PeriodServiceImpl implements IPeridService{
	
	
	@Autowired
	private PeriodRepository periodRepository;

	@Override
	public Period create(PeriodDto periodDto) {
		Period period=Period.builder()
				.id(UUID.randomUUID())
				.academicYear(periodDto.getAcademicYear())
				.term(ETerm.valueOf(periodDto.getTerm()))
				.status(EPeriodStatus.OPEN)
				.build();
		closeAllPeriods();
		return periodRepository.save(period);
	}

	@Override
	public List<Period> getAll() {
		return periodRepository.findAll();
	}

	@Override
	public List<Period> getByBranch(String branch) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void closeAllPeriods() {
		periodRepository.closePeriods();
	}
}
