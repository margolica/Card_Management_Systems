package com.bankexample.cardmanagementsystem.repository;

import com.bankexample.cardmanagementsystem.model.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long > {
    Optional<Card> findByPan(String panCard);
    void deleteByPan(String panCard);
    Page<Card> findAllByUser_Username(String username, Pageable pageable);

    boolean existsByPanAndUser_Username(String username, String pan);
}
