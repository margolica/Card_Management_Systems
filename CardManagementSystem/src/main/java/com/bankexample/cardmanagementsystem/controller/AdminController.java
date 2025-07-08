package com.bankexample.cardmanagementsystem.controller;

import com.bankexample.cardmanagementsystem.controller.discription.AdminApi;
import com.bankexample.cardmanagementsystem.model.dto.CardResponse;
import com.bankexample.cardmanagementsystem.model.dto.request.UserCreateRequest;
import com.bankexample.cardmanagementsystem.model.dto.request.UserUpdateRequest;
import com.bankexample.cardmanagementsystem.model.dto.response.UserResponse;
import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import com.bankexample.cardmanagementsystem.model.mapper.CardMapper;
import com.bankexample.cardmanagementsystem.model.mapper.UserMapper;
import com.bankexample.cardmanagementsystem.security.EncryptionCardUtil;
import com.bankexample.cardmanagementsystem.service.UserService;
import com.bankexample.cardmanagementsystem.service.cardService.CardService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/")
public class AdminController implements AdminApi {

    private final CardService cardService;

    private final UserService userService;
    private final CardMapper cardMapper;
    private final UserMapper userMapper;

    private final EncryptionCardUtil encryptionCardUtil;

    public AdminController(CardService cardService, UserService userService, CardMapper cardMapper, UserMapper userMapper, EncryptionCardUtil encryptionCardUtil) {
        this.cardService = cardService;
        this.userService = userService;
        this.cardMapper = cardMapper;
        this.userMapper = userMapper;
        this.encryptionCardUtil = encryptionCardUtil;
    }


    @Override
    public ResponseEntity<CardResponse> addCard(@PathVariable String username) throws UsernameNotFoundException { // TODO Добавить пользовательсое исключение
            return ResponseEntity.ok(cardMapper.fromCard(cardService.create(userService.getByUsername(username))));
    }

    @Override
    public ResponseEntity<CardResponse> blockCard(String panCard) {
        return ResponseEntity.ok(cardMapper.fromCard(cardService.updateStatus(panCard, ECardStatus.CARD_STATUS_BLOCKED)));
    }

@Override
    public ResponseEntity<CardResponse> activateCard(String panCard) {
    return ResponseEntity.ok(cardMapper.fromCard(cardService.updateStatus(panCard, ECardStatus.CARD_STATUS_ACTIVE)));

}

    @Override
    public ResponseEntity<Void> deleteCard(String panCard) {
        cardService.delete(panCard);
        return (ResponseEntity<Void>) ResponseEntity.noContent();
    }

    @Override
    public ResponseEntity<Page<CardResponse>> getAllCards(@ParameterObject Pageable pageable) {
        Page<CardResponse> response = cardService.getAllCards(pageable)
                .map(card -> {
                    String decryptedPan = encryptionCardUtil.decrypt(card.getPan());
                    card.setPan(decryptedPan);
                    return cardMapper.fromCard(card);
                });

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<UserResponse>> getAllUsers(@ParameterObject Pageable pageable) {
        Page<UserResponse> response = userService.getAllUsers(pageable)
                .map(user -> {
                        return userMapper.fromUser(user);
                    });
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long userId, @Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userMapper.fromUser(userService.update(request)));
    }

    @Override
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userMapper.fromUser(userService.create(userMapper.toCreateRequest(request))));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        return (ResponseEntity<Void>) ResponseEntity.noContent();
    }
}
