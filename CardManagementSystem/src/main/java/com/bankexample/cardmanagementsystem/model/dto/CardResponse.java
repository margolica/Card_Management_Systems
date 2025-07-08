package com.bankexample.cardmanagementsystem.model.dto;

import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CardResponse(
        String pan,
        Long amount,
        ECardStatus status,
        LocalDateTime validityPeriod,
        String userFullName
) {}