package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.controller.discription.UserApi;
import com.bankexample.cardmanagementsystem.model.ERole;
import com.bankexample.cardmanagementsystem.model.User;
import com.bankexample.cardmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для управления пользователями.
 * Реализует API для операций с пользователями, включая регистрацию, получение, обновление и удаление.
 */
@RestController
@RequestMapping("/api/admin")
//@RequestMapping
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные");
        }
        user.setRegistrationDate(LocalDateTime.now());
        if (user.getRole() == null) {
            user.setRole(ERole.ROLE_USER); // Устанавливаем роль по умолчанию
        }
        User registeredUser = userService.registration(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }

    /**
     * Получает список всех пользователей.
     *
     * @return список всех пользователей
     */
    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Создает нового пользователя на основе предоставленных данных.
     *
     * @param user           объект пользователя с данными
     * @param bindingResult результат валидации входных данных
     * @return созданный пользователь
     */
    @Override
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные пользователя");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    /**
     * Получает данные пользователя по его уникальному идентификатору.
     *
     * @param id идентификатор пользователя
     * @return найденный пользователь
     */
    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Обновляет данные существующего пользователя по его идентификатору.
     *
     * @param id            идентификатор пользователя
     * @param user          объект пользователя с обновленными данными
     * @param bindingResult результат валидации входных данных
     * @return обновленный пользователь
     */
    @Override
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Некорректные данные пользователя");
        }
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    /**
     * Удаляет пользователя по его уникальному идентификатору.
     *
     * @param id идентификатор пользователя
     * @return подтверждение удаления
     */
    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    /**
//     * Регистрирует нового пользователя.
//     *
//     * @param user           объект пользователя с данными для регистрации
//     * @param bindingResult результат валидации входных данных
//     * @return зарегистрированный пользователь
//     */
//    @Override
//    public ResponseEntity<User> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new IllegalArgumentException("Некорректные данные для регистрации");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registration(user));
//    }


}