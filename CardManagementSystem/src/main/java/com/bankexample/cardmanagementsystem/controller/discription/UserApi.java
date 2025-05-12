package com.bankexample.cardmanagementsystem.controller.discription;

import com.bankexample.cardmanagementsystem.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс API для управления пользователями.
 * Определяет endpoints для работы с пользователями, включая регистрацию, получение, обновление и удаление.
 */
@Tag(name = "User Management", description = "API для управления пользователями")
public interface UserApi {

    /**
     * Получает список всех пользователей.
     *
     * @return список всех пользователей
     */
    @Operation(
            summary = "Получение всех пользователей",
            description = "Возвращает список всех зарегистрированных пользователей",
            operationId = "getAllUsers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    /**
     * Создает нового пользователя на основе предоставленных данных.
     *
     * @param user           объект пользователя с данными
     * @param bindingResult результат валидации входных данных
     * @return созданный пользователь
     */
    @Operation(
            summary = "Создание нового пользователя",
            description = "Создает нового пользователя с предоставленными данными",
            operationId = "createUser"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "409", description = "Пользователь уже существует"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @PostMapping
    ResponseEntity<User> createUser(
            @Valid @RequestBody User user,
            BindingResult bindingResult
    );

    /**
     * Получает данные пользователя по его уникальному идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь
     */
    @Operation(
            summary = "Получение пользователя по ID",
            description = "Получает данные пользователя по его уникальному идентификатору",
            operationId = "getUserById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(
            @Parameter(description = "Уникальный идентификатор пользователя")
            @PathVariable Long id
    );

    /**
     * Обновляет данные существующего пользователя по его идентификатору.
     *
     * @param id            идентификатор пользователя
     * @param user          объект пользователя с обновленными данными
     * @param bindingResult результат валидации входных данных
     * @return обновленный пользователь
     */
    @Operation(
            summary = "Обновление данных пользователя",
            description = "Обновляет данные существующего пользователя по его уникальному идентификатору",
            operationId = "updateUser"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные пользователя"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "409", description = "Конфликт с существующими данными пользователя"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(
            @Parameter(description = "Уникальный идентификатор пользователя")
            @PathVariable Long id,
            @Valid @RequestBody User user,
            BindingResult bindingResult
    );

    /**
     * Удаляет пользователя по его уникальному идентификатору.
     *
     * @param id идентификатор пользователя
     * @return подтверждение удаления
     */
    @Operation(
            summary = "Удаление пользователя",
            description = "Удаляет пользователя по его уникальному идентификатору",
            operationId = "deleteUser"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(
            @Parameter(description = "Уникальный идентификатор пользователя")
            @PathVariable Long id
    );

//    /**
//     * Регистрирует нового пользователя.
//     *
//     * @param user           объект пользователя с данными для регистрации
//     * @param bindingResult результат валидации входных данных
//     * @return зарегистрированный пользователь
//     */
//    @Operation(
//            summary = "Регистрация пользователя",
//            description = "Регистрирует нового пользователя с предоставленными данными",
//            operationId = "registerUser"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
//            @ApiResponse(responseCode = "400", description = "Некорректные данные для регистрации"),
//            @ApiResponse(responseCode = "401", description = "Неавторизованный запрос"),
//            @ApiResponse(responseCode = "403", description = "Запрещено: недостаточно прав"),
//            @ApiResponse(responseCode = "409", description = "Пользователь уже существует"),
//            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"),
//            @ApiResponse(responseCode = "502", description = "Ошибка шлюза"),
//            @ApiResponse(responseCode = "503", description = "Сервис недоступен"),
//            @ApiResponse(responseCode = "504", description = "Тайм-аут шлюза")
//    })
//    @PostMapping("/registration")
//    ResponseEntity<User> registerUser(
//            @Valid @RequestBody User user,
//            BindingResult bindingResult
//    );
}