package com.example.tunisartisan.Repositories;

import com.example.tunisartisan.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    public Transaction findByIdTransaction(Long id);
}
