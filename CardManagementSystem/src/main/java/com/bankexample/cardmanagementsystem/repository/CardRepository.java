package com.bankexample.cardmanagementsystem.repository;

import com.bankexample.cardmanagementsystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long > {
}
