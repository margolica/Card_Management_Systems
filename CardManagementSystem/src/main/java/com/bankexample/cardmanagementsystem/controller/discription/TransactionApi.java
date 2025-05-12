package com.bankexample.cardmanagementsystem.controller.discription;

import com.bankexample.cardmanagementsystem.model.Transaction;
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
 * Интерфейс API для управления транзакциями.
 * Определяет endpoints для создания, получения и удаления транзакций.
 */
@Tag(name = "Transaction Management", description = "API для управления транзакциями")
public interface TransactionApi {

    /**
     * Создает новую транзакцию на основе предоставленных данных.
     *
     * @param transaction    объект транзакции с данными
     * @param bindingResult  результат валидации входных данных
     * @return созданная транзакция
     */
    @Operation(
            summary = "Создание новой транзакции",
            description = "Создает новую транзакцию с предоставленными данными",
            operationId = "createTransaction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Транзакция успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные транзакции"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "409", description = "Транзакция уже существует"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @PostMapping
    ResponseEntity<Transaction> createTransaction(
            @Valid @RequestBody Transaction transaction,
            BindingResult bindingResult
    );

    /**
     * Получает данные транзакции по её уникальному идентификатору.
     *
     * @param id идентификатор транзакции
     * @return найденная транзакция
     */
    @Operation(
            summary = "Получение транзакции по ID",
            description = "Получает данные транзакции по её уникальному идентификатору",
            operationId = "getTransactionById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакция найдена"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Транзакция не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @GetMapping("/{id}")
    ResponseEntity<Transaction> getTransactionById(
            @Parameter(description = "Уникальный идентификатор транзакции")
            @PathVariable String id
    );

    /**
     * Удаляет транзакцию по её уникальному идентификатору.
     *
     * @param id идентификатор транзакции
     * @return подтверждение удаления
     */
    @Operation(
            summary = "Удаление транзакции",
            description = "Удаляет транзакцию по её уникальному идентификатору",
            operationId = "deleteTransaction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Транзакция успешно удалена"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Транзакция не найдена"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTransaction(
            @Parameter(description = "Уникальный идентификатор транзакции")
            @PathVariable String id
    );
}