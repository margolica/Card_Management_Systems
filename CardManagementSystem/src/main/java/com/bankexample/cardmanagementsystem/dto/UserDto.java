package com.bankexample.cardmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @Schema(description = "Имя")
    String firstName;

    @Schema(description = "Фаимлия")
    String lastname;

    @Schema(description = "Отчество")
    String middle_name;

    @Schema(description = "Дата рождения", example = "дд.мм.гг")
    Date birthday;

    @Schema(description = "Пол")
    short gender;

    @Schema(description = "Дата регистрации", example = "дд.мм.гг")
    Date registration_date;
}
