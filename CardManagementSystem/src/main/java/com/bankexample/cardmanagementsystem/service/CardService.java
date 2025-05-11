package com.bankexample.cardmanagementsystem.service;

import com.bankexample.cardmanagementsystem.model.Card;
import com.bankexample.cardmanagementsystem.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow();
    }

    public Card updateCard(Long id, Card updatedCard) {
        Card card = getCardById(id);
//        card.setAmount(updatedCard.getAmount());
        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}