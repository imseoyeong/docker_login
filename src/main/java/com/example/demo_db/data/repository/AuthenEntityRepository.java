package com.example.demo_db.data.repository;

import com.example.demo_db.data.entity.AuthenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenEntityRepository extends JpaRepository<AuthenEntity, String> {
}
