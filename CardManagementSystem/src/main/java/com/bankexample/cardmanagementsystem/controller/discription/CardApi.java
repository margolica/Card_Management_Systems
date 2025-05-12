package com.bankexample.cardmanagementsystem.controller.discription;

import com.bankexample.cardmanagementsystem.model.Card;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Интерфейс API для управления банковскими картами.
 * Определяет endpoints для создания, получения, обновления и удаления карт.
 */
@Tag(name = "Card Management", description = "API для управления банковскими картами")
public interface CardApi {

    /**
     * Создает новую банковскую карту на основе предоставленных данных.
     *
     * @param card           объект карты с данными
     * @param bindingResult результат валидации входных данных
     * @return созданная карта
     */
    @Operation(
            summary = "Создание новой карты",
            description = "Создает новую карту с предоставленными данными",
            operationId = "createCard"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Карта успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные карты"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "409", description = "Карта уже существует"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @PostMapping
    ResponseEntity<Card> createCard(
            @Valid @RequestBody Card card,
            BindingResult bindingResult
    );

    /**
     * Получает данные карты по её уникальному идентификатору.
     *
     * @param id идентификатор карты
     * @return найденная карта
     */
    @Operation(
            summary = "Получение карты по ID",
            description = "Получает данные карты по её уникальному идентификатору",
            operationId = "getCardById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Карта найдена"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @GetMapping("/{id}")
    ResponseEntity<Card> getCardById(
            @Parameter(description = "Уникальный идентификатор карты")
            @PathVariable Long id
    );

    /**
     * Обновляет данные существующей карты по её идентификатору.
     *
     * @param id            идентификатор карты
     * @param card          объект карты с обновленными данными
     * @param bindingResult результат валидации входных данных
     * @return обновленная карта
     */
    @Operation(
            summary = "Обновление данных карты",
            description = "Обновляет данные существующей карты по её уникальному идентификатору",
            operationId = "updateCard"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Карта успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные карты"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "409", description = "Конфликт с существующими данными карты"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @PutMapping("/{id}")
    ResponseEntity<Card> updateCard(
            @Parameter(description = "Уникальный идентификатор карты")
            @PathVariable Long id,
            @Valid @RequestBody Card card,
            BindingResult bindingResult
    );

    /**
     * Удаляет карту по её уникальному идентификатору.
     *
     * @param id идентификатор карты
     * @return подтверждение удаления
     */
    @Operation(
            summary = "Удаление карты",
            description = "Удаляет карту по её уникальному идентификатору",
            operationId = "deleteCard"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Карта успешно удалена"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Карта не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCard(
            @Parameter(description = "Уникальный идентификатор карты")
            @PathVariable Long id
    );
}