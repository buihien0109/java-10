package com.example.blog.repository;

import com.example.blog.entity.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}