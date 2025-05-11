package com.bankexample.cardmanagementsystem.service;

import com.bankexample.cardmanagementsystem.model.Transaction;
import com.bankexample.cardmanagementsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction tx) {
        return transactionRepository.save(tx);
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public void deleteTransaction(String id) {
        transactionRepository.deleteById(UUID.fromString(id));
    }
}
