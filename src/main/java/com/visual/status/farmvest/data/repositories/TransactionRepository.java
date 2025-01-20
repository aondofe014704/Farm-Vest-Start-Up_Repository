package com.visual.status.farmvest.data.repositories;

import com.visual.status.farmvest.data.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
