package com.bankexample.cardmanagementsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на смену пароля")
public record UserPasswordUpdateRequest(
        @NotBlank(message = "Старый пароль обязателен")
        String oldPassword,

        @NotBlank(message = "Новый пароль обязателен")
        @Size(min = 4, message = "Новый пароль должен быть не менее 4 символов")
        String newPassword
) {}