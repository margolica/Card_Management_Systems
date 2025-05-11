package com.bankexample.cardmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
//        (access= AccessLevel.PRIVATE, force=true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cards")
public class Card extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long clientId;

    @Column(unique = true, nullable = false)
    Byte[] panEncrypted;

    @Column(nullable = false)
    String panMasked;

    @Column(nullable = false, updatable = false, insertable = false)
    LocalDateTime validityPeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, insertable = false)
    CardStatus status;

    @Column(nullable = false)
    Long amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

}

