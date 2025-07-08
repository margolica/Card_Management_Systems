package com.bankexample.cardmanagementsystem.model.dto.response;

import com.bankexample.cardmanagementsystem.model.enums.EGender;

import java.time.LocalDateTime;

public record UserResponse(
        String username,
        String userFullName,
        String email,
        EGender gender,
        LocalDateTime registrationDate
) {
}
