package com.example.demo_db.data.dao;

import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenDAO {
    private final AuthenEntityRepository authenEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenEntity saveAuthen(String username, String password, String role) {
        AuthenEntity authenEntity = AuthenEntity.builder()
                .username(username)
                .password(this.passwordEncoder.encode(password))
                .role(role)
                .build();

        return authenEntityRepository.save(authenEntity);
    }

    public AuthenEntity getAdminById(String username) {
        Optional<AuthenEntity> adminEntity=this.authenEntityRepository.findById(username);
        if(adminEntity.isPresent()) {
            return adminEntity.get();
        }
        return null;
    }

    public boolean existAuthen(String username) {
        return this.authenEntityRepository.existsById(username);
    }

}
