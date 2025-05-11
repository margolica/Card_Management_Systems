package com.bankexample.cardmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.security.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {

    @Schema(description = "Тип операции", example = "{DEBIT, CREDIT}")
    String type;

    @Schema(description = "Сумма операции", example = "1000.00")
    Long amount;

    @Schema(description = "Дата совершения операции", example = "дд.мм.гг чч:мм")
    Timestamp created_at;
}
