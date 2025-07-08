package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.model.enums.EGender;
import com.bankexample.cardmanagementsystem.model.enums.ERole;
import com.bankexample.cardmanagementsystem.model.mapper.CardMapper;
import com.bankexample.cardmanagementsystem.service.TransactionService;
import com.bankexample.cardmanagementsystem.service.cardService.CardService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private CardService cardService;
    @Mock
    private TransactionService transactionService;
    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private final String pan = "000000000000";
    private Card cardNegativeBalance;
    private Card  cardPositiveBalance;
    private Card  cardZeroBalance;
    private CardResponse response;
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = User.builder()
                .username("user")
                .password("user")
                .email("user@mail.ru")
                .firstName("user")
                .lastName("user")
                .middleName("user")
                .birthday(LocalDate.of(2000, 1, 1).atStartOfDay())
                .gender(EGender.MALE)
                .registrationDate(LocalDateTime.now())
                .role(ERole.USER)
                .build();

        cardPositiveBalance = Card.builder()
                .pan(pan)
                .cvv("000")
                .validityPeriod(LocalDateTime.now().plusYears(3))
                .status(ECardStatus.CARD_STATUS_ACTIVE)
                .amount(0L)
                .user(user)
                .build();


    }

//    @Test
//    void getUserCards() throws Exception {
//        mockMvc.perform(get("/api/user/cards")).andExpect()
//    }

    @Test
    @DisplayName("Patch /api/user обновляет status на CARD_STATUS_BLOCKED")
    void requestCardBlock_ReturnResponseEntityWithCardResponse() {
//        given

        Card card = Card.builder()
                .pan(pan)
                .cvv("000")
                .validityPeriod(LocalDateTime.now().plusYears(3))
                .status(ECardStatus.CARD_STATUS_BLOCKED)
                .user(user)
                .build();

        CardResponse cardResponse = CardResponse.builder()
                .pan(pan)
                .amount(0L)
                .status(ECardStatus.CARD_STATUS_BLOCKED)
                .validityPeriod(card.getValidityPeriod())
                .userFullName(user.getFullName())
                .build();

        when(cardService.updateStatus(pan, ECardStatus.CARD_STATUS_BLOCKED)).thenReturn(card);
        when(cardMapper.fromCard(card)).thenReturn(cardResponse);
//        when
        ResponseEntity<CardResponse> response = this.userController.requestCardBlock(pan);
//        then
        assertEquals(response.getBody().status(), ECardStatus.CARD_STATUS_BLOCKED);
    }

}
