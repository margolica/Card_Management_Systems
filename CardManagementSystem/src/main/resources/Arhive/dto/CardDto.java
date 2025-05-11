package Arhive.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDto {

    @Schema(description = "Данные карты", example = "1234 1234 1234 1234")
    Byte[] panEncrypted;

    @Schema(description = "Данные карты с маской", example = "**** **** **** 1234")
    String panMasked;

    @Schema(description = "Срок действия карты", example = "мм.гг")
    LocalDateTime validityPeriod;

    @Schema(description = "Статус карты", example = "{Active, Blocked, Expired}")
    String status;

    @Schema(description = "Текущий баланс по карте", example = "1000.00")
    Long amount;
}
