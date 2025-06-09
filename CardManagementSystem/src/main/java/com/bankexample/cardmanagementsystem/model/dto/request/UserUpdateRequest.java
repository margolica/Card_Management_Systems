package com.bankexample.cardmanagementsystem.model.dto.request;

import com.bankexample.cardmanagementsystem.model.enums.EGender;
import com.bankexample.cardmanagementsystem.model.enums.ERole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
@Schema(description = "Запрос на обновление пользователя")
public record UserUpdateRequest(

        @NotBlank(message = "Имя пользователя обязательно")
        @Size(min = 4, max = 20, message = "Имя пользователя должно содержать от 4 до 20 символов")
        String username,
        @NotBlank(message = "Email обязателен")
        @Email(message = "Неверный формат email")
        String email,

        @NotBlank(message = "Имя обязательно")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        String lastName,

        @NotBlank(message = "Отчество обязательно")
        String middleName,

        @NotNull(message = "Дата рождения обязательна")
        LocalDateTime birthday,

        @NotNull(message = "Пол обязателен")
        EGender gender,

        @NotNull(message = "Роль обязательна")
        ERole role
) {}