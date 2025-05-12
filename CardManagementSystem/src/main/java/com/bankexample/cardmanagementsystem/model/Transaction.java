package com.bankexample.cardmanagementsystem.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
//(access=AccessLevel.PRIVATE, force=true) // Spring JPA требует пустой конструктор, но его нужно инкапсулировать
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transactions")
public class Transaction extends BaseEntity<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация на уровне DB
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TransactionType type;

    @Column(nullable = false)
    Long amount;

    @Column(updatable = false, insertable = false)
    LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
