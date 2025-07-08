package com.bankexample.cardmanagementsystem.model.entity;


import com.bankexample.cardmanagementsystem.model.enums.ETransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "card"})
//(access=AccessLevel.PRIVATE, force=true) // Spring JPA требует пустой конструктор, но его нужно инкапсулировать
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация на уровне DB
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ETransactionType type;

    @Column(nullable = false)
    Long amount;

    @Column(updatable = false, insertable = false)
    LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_username", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_pan", referencedColumnName = "pan", nullable = false)
    private Card card;
}
