package com.crow.billing.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crow.billing.model.Bill;
import com.crow.billing.model.BillState;

public interface BillStateRepository extends JpaRepository<BillState, UUID>{
	@Query(value = "SELECT SUM(s.amount_paid) FROM t_bills b,t_payments s where s.bill_id=b.id and s.bill_id=:bl", nativeQuery = true)
	Optional<BigDecimal> sumAmountPaid(@Param("bl") Bill bill);
	
	@Query("FROM BillState bs,Bill b where bs.bill=b and bs.bill=:bill")
	List<BillState> getPayments(@Param("bill") Bill bill);
}
