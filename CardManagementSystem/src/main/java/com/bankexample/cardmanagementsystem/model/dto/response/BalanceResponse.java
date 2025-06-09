package com.bankexample.cardmanagementsystem.model.dto.response;

public record BalanceResponse(
        String username,
        String pan,
        Long amount
) {}
