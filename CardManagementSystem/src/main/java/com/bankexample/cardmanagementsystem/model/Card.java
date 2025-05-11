package com.bankexample.cardmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cards")
public class Card extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "clienrt_id", nullable = false)
    Long clientId;

    @Column(unique = true, nullable = false)
    byte[] panEncrypted;

    @Column(nullable = false)
    String panMasked;

    @Column(nullable = false, updatable = false, insertable = false)
    LocalDateTime validityPeriod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, insertable = false)
    CardStatus status;

    @Column(nullable = false)
    Long amount;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "client_id", nullable = false)
//    private User user;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Transaction> transactions = new HashSet<>();

}

