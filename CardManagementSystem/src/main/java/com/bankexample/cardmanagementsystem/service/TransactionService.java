package com.bankexample.cardmanagementsystem.service;

import com.bankexample.cardmanagementsystem.exception.AccessDeniedException;
import com.bankexample.cardmanagementsystem.exception.CardNotActiveException;
import com.bankexample.cardmanagementsystem.exception.InsufficientFundsException;
import com.bankexample.cardmanagementsystem.model.dto.request.TransactionCreateRequest;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.model.mapper.TransactionMapper;
import com.bankexample.cardmanagementsystem.repository.TransactionRepository;
import com.bankexample.cardmanagementsystem.service.cardService.CardService;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final UserService userService;
    private final CardService cardService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public void createTransferTransactions(String username, TransactionCreateRequest transactionCreateRequest) {

        if (!cardService.existsByPanAndUser_Username(username, transactionCreateRequest.senderPan())
                || cardService.existsByPanAndUser_Username(username, transactionCreateRequest.recipientPan())) {
            throw new AccessDeniedException("Одна или обе карты не принадлежат пользователю");
        }

        Card senderCard = cardService.getCardByPan(transactionCreateRequest.senderPan());
        Card recipientCard = cardService.getCardByPan(transactionCreateRequest.recipientPan());

        if (senderCard.getAmount() < transactionCreateRequest.amount())
            throw new InsufficientFundsException("На карте не достаточно средств");

        if (!senderCard.getStatus().equals(ECardStatus.CARD_STATUS_ACTIVE) || !recipientCard.getStatus().equals(ECardStatus.CARD_STATUS_ACTIVE))
            throw new CardNotActiveException("Одна или обе карты не активны");
        // TODO Можно настроить блокировку карты при наступлении срока

        User user = userService.getByUsername(username);

        transactionRepository.save(transactionMapper.toDebitTransaction(transactionCreateRequest, senderCard, user));
        transactionRepository.save(transactionMapper.toCreditTransaction(transactionCreateRequest, recipientCard, user));
    }


    public TransactionService(UserService userService, CardService cardService, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.userService = userService;
        this.cardService = cardService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


}
