package com.bankexample.cardmanagementsystem.repository;

import com.bankexample.cardmanagementsystem.model.ERole;
import com.bankexample.cardmanagementsystem.model.Role;
import org.apache.commons.text.translate.NumericEntityUnescaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
