package com.bankexample.cardmanagementsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Ответ на аутентификация пользователя")
public record UserResponseWithCredentials(
    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 4, max = 20, message = "Имя пользователя должно содержать от 4 до 20 символов")
    String username,

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, message = "Пароль должен содержать не менее 8 символов")
    String password)
    {}
