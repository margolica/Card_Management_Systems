package com.bankexample.cardmanagementsystem.model.mapper;

import com.bankexample.cardmanagementsystem.model.dto.request.TransactionCreateRequest;
import com.bankexample.cardmanagementsystem.model.dto.response.BalanceResponse;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.Transaction;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.ETransactionType;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, LocalDateTime.class, ETransactionType.class})
public interface TransactionMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "type", constant = "TYPE_TRANSACTION_DEBIT")
    @Mapping(target = "amount", source = "request.amount")
    @Mapping(target = "created_at", expression = "java(request.created_at() != null ? request.created_at() : LocalDateTime.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "card", source = "card")
    Transaction toDebitTransaction(TransactionCreateRequest request, Card card, User user);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "type", constant = "TYPE_TRANSACTION_CREDIT")
    @Mapping(target = "amount", source = "request.amount")
    @Mapping(target = "created_at", expression = "java(request.created_at() != null ? request.created_at() : LocalDateTime.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "card", source = "card")
    Transaction toCreditTransaction(TransactionCreateRequest request, Card card, User user);
}
