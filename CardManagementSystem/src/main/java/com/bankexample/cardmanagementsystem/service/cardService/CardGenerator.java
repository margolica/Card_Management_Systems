package com.bankexample.cardmanagementsystem.service.cardService;

import com.bankexample.cardmanagementsystem.exception.EncryptionException;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.security.EncryptionCardUtil;

import java.time.LocalDateTime;
import java.util.Random;

public class CardGenerator {

    private static final Random RANDOM = new Random();

    public static Card generateCard(User user, EncryptionCardUtil encryptionCardUtil) {
            return Card.builder()
                    .pan(encryptionCardUtil.encrypt(generateCardNumber()))
                    .cvv(generateCVV())
                    .validityPeriod(LocalDateTime.now().plusYears(3))
                    .status(ECardStatus.CARD_STATUS_ACTIVE)
                    .amount(0L)
                    .user(user)
                    .build();
    }

    private static String generateCardNumber() {
        String bin = "220220";
        StringBuilder builder = new StringBuilder(bin);

        for (int i = 0; i < 9; i++)
            builder.append(RANDOM.nextInt(10));

        return builder.toString();
    }

    private static String generateCVV() {
        return String.format("%03d", RANDOM.nextInt(1000));
    }
}