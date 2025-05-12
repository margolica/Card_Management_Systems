package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.controller.discription.TransactionApi;
import com.bankexample.cardmanagementsystem.model.Transaction;
import com.bankexample.cardmanagementsystem.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления транзакциями.
 * Реализует API для операций с транзакциями, включая создание, получение и удаление.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController implements TransactionApi {

    @Autowired
    private TransactionService transactionService;

    /**
     * Создает новую транзакцию на основе предоставленных данных.
     *
     * @param transaction    объект транзакции с данными
     * @param bindingResult  результат валидации входных данных
     * @return созданная транзакция
     */
    @Override
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные транзакции");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transaction));
    }

    /**
     * Получает данные транзакции по её уникальному идентификатору.
     *
     * @param id идентификатор транзакции
     * @return найденная транзакция
     */
    @Override
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Идентификатор транзакции не может быть пустым");
        }
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Удаляет транзакцию по её уникальному идентификатору.
     *
     * @param id идентификатор транзакции
     * @return подтверждение удаления
     */
    @Override
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Идентификатор транзакции не может быть пустым");
        }
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}