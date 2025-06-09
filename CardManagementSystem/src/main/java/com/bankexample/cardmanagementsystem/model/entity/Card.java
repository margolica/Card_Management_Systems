package com.bankexample.cardmanagementsystem.model.entity;

import com.bankexample.cardmanagementsystem.model.enums.ECardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "transactions"})
@Table(name = "cards")
public class Card {

    @Id
    @Column(unique = true, nullable = false, length = 19)
    String pan;

    @Column(nullable = false, length = 4)
    String cvv;

    @Column(nullable = false) // TODO  Можно запретить обновление
    LocalDateTime validityPeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ECardStatus status;

    @Column(nullable = false)
    Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_username", referencedColumnName = "username")
    User user;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    Set<Transaction> transactions = new HashSet<>();
}
