package com.crow.billing.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.crow.billing.model.Period;

public interface PeriodRepository extends JpaRepository<Period, UUID>{
	@Query("from Period p where p.status='OPEN'")
	Optional<Period> findOpenPeriod();
	
    @Transactional
    @Modifying
    @Query(value = "update t_periods set status='CLOSED'",nativeQuery = true)
	void closePeriods();
}
