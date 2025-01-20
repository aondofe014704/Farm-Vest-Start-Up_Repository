package com.visual.status.farmvest.data.repositories;

import com.visual.status.farmvest.data.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
