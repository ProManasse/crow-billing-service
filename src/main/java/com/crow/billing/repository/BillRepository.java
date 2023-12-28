package com.crow.billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.crow.billing.model.Bill;
import com.crow.billing.model.Period;

public interface BillRepository extends JpaRepository<Bill, UUID>{
	@Query("FROM Bill b where b.period=:period and b.studentId=:studentId")
	List<Bill> verifyBill(@Param("period") Period period,@Param("studentId") String studentId);
	
	@Query("FROM Bill b where b.studentId=:studentId")
	Optional<Bill> getBill(@Param("studentId") String studentId);

}
