package Arhive.repository;

import com.bankexample.cardmanagementsystem.model.Card;
import com.bankexample.cardmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
