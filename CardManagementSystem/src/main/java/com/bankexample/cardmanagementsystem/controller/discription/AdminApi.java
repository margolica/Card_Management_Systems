package com.bankexample.cardmanagementsystem.controller.discription;

import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.dto.request.UserCreateRequest;
import com.bankexample.cardmanagementsystem.model.dto.request.UserUpdateRequest;
import com.bankexample.cardmanagementsystem.model.dto.response.UserResponse;
import com.bankexample.cardmanagementsystem.model.entity.Card;
import com.bankexample.cardmanagementsystem.model.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер API для администрирования банковских карт и пользователей.
 * <p>
 * Предоставляет операции по управлению картами и пользователями:
 * - создание, блокировка, активация, удаление карт
 * - получение списка пользователей и карт
 * - блокировка, активация, удаление пользователей
 * </p>
 *
 * Все методы требуют аутентификации и наличия роли ADMIN.
 */
@Tag(name = "Admin API", description = "API для администратора")
public interface AdminApi {

    /**
     * Создаёт новую банковскую карту для указанного пользователя.
     *
     * @param username имя пользователя, которому будет выдана карта
     * @return объект {@link ResponseEntity} с информацией о созданной карте
     * @throws Exception при ошибке создания карты
     */
    @Operation(summary = "Добавление новой карты", description = "Создаёт новую банковскую карту по имени пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Карта успешно создана"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
    })
    @PostMapping(value = "/cards/{username}/create", produces = "application/json")
    ResponseEntity<CardResponse> addCard(@PathVariable String username) throws Exception;

    /**
     * Блокирует карту по её PAN.
     *
     * @param panCard PAN-код карты
     * @return объект {@link Card} с обновлённым статусом
     */
    @Operation(summary = "Блокировка карты", description = "Блокирует карту по заданному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Карта успешно заблокирована"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор карты"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена")
    })
    @PatchMapping(value = "/cards/{panCard}/block", produces = "application/json")
    ResponseEntity<CardResponse> blockCard(@PathVariable String panCard);


    /**
     * Активирует ранее заблокированную карту.
     *
     * @param panCard PAN-код карты
     * @return объект {@link Card} с обновлённым статусом
     */
    @Operation(summary = "Активация карты", description = "Активирует карту по её идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Карта успешно активирована"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор карты"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "409", description = "Карта уже имеет статус 'Заблокирована'")
    })
    @PatchMapping(value = "/cards/{panCard}/activate", produces = "application/json")
    ResponseEntity<CardResponse> activateCard(@PathVariable String panCard);

    /**
     * Удаляет карту по PAN.
     *
     * @param panCard PAN-код карты
     * @return HTTP 204 при успешном удалении
     */
    @Operation(summary = "Удаление карты", description = "Удаляет карту по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Карта успешно удалена"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор карты"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "409", description = "Карта уже имеет статус 'Активирована'")
    })
    @DeleteMapping(value = "/cards/{panCard}/delete", produces = "application/json")
    ResponseEntity<Void> deleteCard(@PathVariable String panCard);

    /**
     * Возвращает постраничный список всех карт.
     *
     * @param pageable параметры пагинации
     * @return список объектов {@link CardResponse}
     */
    @Operation(summary = "Получение всех карт", description = "Возвращает список всех карт, зарегистрированных в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка карт"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав")
    })
    @GetMapping(value = "/cards", produces = "application/json")
    ResponseEntity<Page<CardResponse>> getAllCards(Pageable pageable);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список объектов {@link User}
     */
    @Operation(summary = "Получение всех пользователей", description = "Возвращает список всех пользователей, зарегистрированных в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав")
    })
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable);

    /**
     * Обновляет данные пользователя по его ID.
     *
     * @param userId ID пользователя
     * @param request DTO с обновлёнными данными пользователя
     * @return объект {@link UserResponse} с актуальной информацией
     */
    @Operation(summary = "Обновление пользователя", description = "Обновляет данные пользователя по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса или ID"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping(value = "/users/{userId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    );


    /**
     * Создаёт нового пользователя в системе.
     *
     * @param request объект {@link UserCreateRequest}, содержащий данные нового пользователя
     * @return объект {@link UserResponse} с информацией о созданном пользователе
     */
    @Operation(summary = "Создание пользователя", description = "Создаёт нового пользователя и сохраняет его в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав")
    })
    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request);

    /**
     * Удаляет пользователя по ID.
     *
     * @param userId ID пользователя
     * @return HTTP 204 при успешном удалении
     */
    @Operation(summary = "Удаление пользователя", description = "Удаляет пользователя из системы по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь успешно удалён"),
            @ApiResponse(responseCode = "400", description = "Некорректный идентификатор пользователя"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping(value = "/users/{userId}", produces = "application/json")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);

}
