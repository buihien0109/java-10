package com.example.registeruser.repository;

import com.example.registeruser.entity.TokenConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
}