package com.bankexample.cardmanagementsystem.model.dto.request;

import com.bankexample.cardmanagementsystem.model.enums.EGender;
import com.bankexample.cardmanagementsystem.model.enums.ERole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "Запрос на регистрацию в системе")
public record UserCreateRequest(
        @NotBlank(message = "Имя пользователя обязательно")
        @Size(min = 4, max = 20, message = "Имя пользователя должно содержать от 4 до 20 символов")
        String username,

        @NotBlank(message = "Пароль обязателен")
        @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
        String password,

        @Email(message = "Неверный формат email")
        @NotBlank(message = "Email обязателен")
        String email,

        @NotBlank(message = "Имя обязательно")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        String lastName,

        @NotBlank(message = "Отчество обязательно")
        String middleName,

        @NotNull(message = "Дата рождения обязательна")
        @Past(message = "Дата рождения должна быть в прошлом")
        LocalDateTime birthday,

        @NotNull(message = "Пол обязателен")
        EGender gender
) {}