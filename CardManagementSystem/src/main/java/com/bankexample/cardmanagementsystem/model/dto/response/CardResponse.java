package com.bankexample.cardmanagementsystem.model.dto.response;

import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;

import java.time.LocalDateTime;

public record CardResponse(
        String panMasked,
        Long amount,
        ECardStatus status,
        LocalDateTime validityPeriod,
        String userFullName
) {}
