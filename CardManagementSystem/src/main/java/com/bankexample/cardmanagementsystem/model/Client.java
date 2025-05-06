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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация на уровне DB
    Long id;
    @Column
    String firstName;
    @Column
    String lastname;
    @Column
    String middle_name;
    @Column
    Date birthday;
    @Column
    short gender;
    @Column
    Date registration_date;
}
