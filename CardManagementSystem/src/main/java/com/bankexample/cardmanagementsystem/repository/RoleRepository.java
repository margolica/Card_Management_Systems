package com.bankexample.cardmanagementsystem.repository;

import com.bankexample.cardmanagementsystem.model.ERole;
import com.bankexample.cardmanagementsystem.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository <Role, Long> {
    Optional<Role> findByName(ERole name);
}
