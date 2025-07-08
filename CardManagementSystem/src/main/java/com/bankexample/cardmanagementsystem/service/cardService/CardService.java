package com.bankexample.cardmanagementsystem.service.cardService;

import com.bankexample.cardmanagementsystem.exception.CardAlreadyBlockedException;
import com.bankexample.cardmanagementsystem.exception.CardNotFoundException;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.User;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.model.mapper.CardMapper;
import com.bankexample.cardmanagementsystem.repository.CardRepository;
import com.bankexample.cardmanagementsystem.security.EncryptionCardUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CardService {

    private final CardRepository cardRepository;
    private final EncryptionCardUtil encryptionCardUtil;

    public CardService(CardRepository cardRepository, EncryptionCardUtil encryptionCardUtil, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.encryptionCardUtil = encryptionCardUtil;
    }

    public Card create(User user) {
        return cardRepository.save(CardGenerator.generateCard(user, encryptionCardUtil));
    }

    public Card updateStatus(String panCard, ECardStatus cardStatus) {
        Card card = cardRepository.findByPan(encryptionCardUtil.encrypt(panCard))
                .orElseThrow(() -> new CardNotFoundException("Карта с PAN " + panCard + " не найдена"));

        if (card.getStatus().equals(cardStatus))
            throw new CardAlreadyBlockedException("Карта уже имеет сататус" + cardStatus);

        card.setStatus(cardStatus);

        return cardRepository.save(card);
    }

    public void delete(String panCard) {
        Card card = cardRepository.findByPan(panCard)
                .orElseThrow(() -> new CardNotFoundException("Карта с PAN " + panCard + " не найдена"));

        cardRepository.deleteByPan(panCard);
    }

    public Page<Card> getAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    public Card getCardByPan(String pan) {
        return cardRepository.findByPan(pan).orElseThrow();
    }

    public Card updateCard(String pan, Card updatedCard) {
        Card card = getCardByPan(pan);
        card.setAmount(updatedCard.getAmount());
        return cardRepository.save(card);
    }

    public Page<Card> getAllCardsByUsername(String username, Pageable pageable) {
        return cardRepository.findAllByUser_Username(username, pageable);
    }

    public boolean existsByPanAndUser_Username(String username, String pan) {
        return cardRepository.existsByPanAndUser_Username(username, pan);
    }

    public Card getCardBalance(String username, String pan) {
        if (!existsByPanAndUser_Username(username, pan))
            throw new CardNotFoundException("Карта не найдена, проверте правильность данных");
        return getCardByPan(pan);
    }

}