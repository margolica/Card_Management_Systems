package com.bankexample.cardmanagementsystem.controller.discription;

import com.bankexample.cardmanagementsystem.model.dto.response.BalanceResponse;
import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.dto.request.TransactionCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * API для действий пользователя с его банковскими картами.
 *
 * Пользователь может:
 * <ul>
 *   <li>Просматривать свои карты</li>
 *   <li>Запрашивать блокировку карты</li>
 *   <li>Переводить средства между своими картами</li>
 *   <li>Просматривать баланс карт</li>
 * </ul>
 */
@Tag(name = "User Cards", description = "API для управления банковскими картами пользователя")
public interface UserApi {

    /**
     * Получение списка карт текущего пользователя.
     *
     * @param pageable параметры пагинации
     * @return постраничный список {@link CardResponse}
     */
    @Operation(summary = "Просмотр всех карт", description = "Получение списка всех карт, привязанных к текущему пользователю, с поддержкой пагинации")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Карты успешно получены"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён")
    })
    @GetMapping("/cards")
    ResponseEntity<Page<CardResponse>> getUserCards(Pageable pageable);

    /**
     * Запрос на блокировку карты пользователя.
     *
     * @param cardId уникальный идентификатор карты (PAN)
     * @return информация о заблокированной карте {@link CardResponse}
     */
    @Operation(summary = "Блокировка карты", description = "Отправляет запрос на блокировку карты текущего пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Карта успешно заблокирована"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор карты"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @PatchMapping("/cards/{pan}/block")
    ResponseEntity<CardResponse> requestCardBlock(@PathVariable String pan);

    /**
     * Перевод средств между картами текущего пользователя.
     *
     * @param request данные перевода
     * @return HTTP 200 при успешной операции
     */
    @Operation(summary = "Перевод между своими картами", description = "Выполняет перевод средств с одной карты пользователя на другую")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Перевод успешно выполнен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные перевода"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "409", description = "Недостаточно средств")
    })
    @PostMapping("/cards/transfer")
    ResponseEntity<Void> transferBetweenCards(@Valid @RequestBody TransactionCreateRequest request);

    /**
     * Получение текущего баланса указанной карты.
     *
     * @param cardId уникальный идентификатор карты (PAN)
     * @return текущий баланс карты {@link BalanceResponse}
     */
    @Operation(summary = "Проверка баланса", description = "Возвращает текущий баланс выбранной карты пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Баланс успешно получен"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор карты"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @GetMapping("/cards/{pan}/balance")
    ResponseEntity<BalanceResponse> getCardBalance(@PathVariable String pan);
}
