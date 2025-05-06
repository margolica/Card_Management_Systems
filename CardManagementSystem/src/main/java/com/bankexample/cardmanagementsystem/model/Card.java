package com.bankexample.cardmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    Long clientId;
    @Column
    Byte[] panEncrypted;
    @Column
    String panMasked;
    @Column
    Date validityPeriod;
    @Column
    String status;
    @Column
    Long amount;
}
