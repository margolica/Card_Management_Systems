package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.controller.discription.CardApi;
import com.bankexample.cardmanagementsystem.model.Card;
import com.bankexample.cardmanagementsystem.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления банковскими картами.
 * Реализует API для операций с картами, включая создание, получение, обновление и удаление.
 */
@RestController
@RequestMapping("/api/cards")
public class CardController implements CardApi {

    @Autowired
    private CardService cardService;

    /**
     * Создает новую банковскую карту на основе предоставленных данных.
     *
     * @param card           объект карты с данными
     * @param bindingResult результат валидации входных данных
     * @return созданная карта
     */
    @Override
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные карты");
        }
        return ResponseEntity.status(201).body(cardService.createCard(card));
    }

    /**
     * Получает данные карты по её уникальному идентификатору.
     *
     * @param id идентификатор карты
     * @return найденная карта
     */
    @Override
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    /**
     * Обновляет данные существующей карты по её идентификатору.
     *
     * @param id            идентификатор карты
     * @param card          объект карты с обновленными данными
     * @param bindingResult результат валидации входных данных
     * @return обновленная карта
     */
    @Override
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @Valid @RequestBody Card card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные карты");
        }
        return ResponseEntity.ok(cardService.updateCard(id, card));
    }

    /**
     * Удаляет карту по её уникальному идентификатору.
     *
     * @param id идентификатор карты
     * @return подтверждение удаления
     */
    @Override
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}