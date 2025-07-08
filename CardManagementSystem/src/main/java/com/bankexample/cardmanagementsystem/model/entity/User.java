package com.bankexample.cardmanagementsystem.model.entity;

import com.bankexample.cardmanagementsystem.model.enums.EGender;
import com.bankexample.cardmanagementsystem.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.bankexample.cardmanagementsystem.model.enums.ERole.*;

@Entity
@Data
@Builder
@ToString(exclude = {"cards", "transactions"})
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    String middleName;

    @Column(nullable = false)
    LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EGender gender;

    @Column(nullable = false)
    LocalDateTime registrationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role = USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Transaction> transactions = new HashSet<>();

    public String getFullName() {
        return this.getLastName() + ' ' + this.getFirstName() + ' ' + this.getMiddleName();
    }
}
