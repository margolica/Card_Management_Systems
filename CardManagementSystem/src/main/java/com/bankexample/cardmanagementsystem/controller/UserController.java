package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.controller.discription.UserApi;
import com.bankexample.cardmanagementsystem.model.dto.response.BalanceResponse;
import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.dto.request.TransactionCreateRequest;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.model.mapper.CardMapper;
import com.bankexample.cardmanagementsystem.service.TransactionService;
import com.bankexample.cardmanagementsystem.service.cardService.CardService;
import com.bankexample.cardmanagementsystem.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Реализация {@link UserApi} для управления операциями с банковскими картами пользователя.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final CardService cardService;
    private final TransactionService transactionService;
    private final CardMapper cardMapper;

    @Override
    public ResponseEntity<Page<CardResponse>> getUserCards(Pageable pageable) {
        String username = AuthUtils.getCurrentUsername();
        Page<CardResponse> responsePage = cardService
                .getAllCardsByUsername(username, pageable)
                .map(card -> cardMapper.fromCard(card));

        return ResponseEntity.ok(responsePage);
    }

    @Override
    public ResponseEntity<CardResponse> requestCardBlock(String panCard) {
        return ResponseEntity.ok(cardMapper.fromCard(cardService.updateStatus(panCard, ECardStatus.CARD_STATUS_BLOCKED)));
    }

    @Override
    public ResponseEntity<Void> transferBetweenCards (TransactionCreateRequest transactionCreateRequest) {
        String username = AuthUtils.getCurrentUsername();
        transactionService.createTransferTransactions(username, transactionCreateRequest);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @Override
    public ResponseEntity<BalanceResponse> getCardBalance(String panCard) {
        String username = AuthUtils.getCurrentUsername();
        return ResponseEntity.ok(cardMapper.fromCardToBalanceResponse(cardService.getCardBalance(username, panCard)));
    }
}
