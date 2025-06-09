package com.bankexample.cardmanagementsystem.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record TransactionCreateRequest(
        @NotBlank(message = "Sender PAN must not be blank")
        String senderPan,

        @NotBlank(message = "Recipient PAN must not be blank")
        String recipientPan,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than 0")
        Long amount,
        LocalDateTime created_at
) {}