package com.bankexample.cardmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true) // Spring JPA требует пустой конструктор, но его нужно инкапсулировать
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация на уровне DB
    UUID id;
    @Column
    Long clientId;
    @Column
    Long cardId;
    @Column
    String type;
    @Column
    Long amount;
    @Column(updatable = false, insertable = false)
    Timestamp created_at;
}
