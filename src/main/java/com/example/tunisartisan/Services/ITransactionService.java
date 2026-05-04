package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.Transaction;
import java.util.List;

public interface ITransactionService {
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
}

