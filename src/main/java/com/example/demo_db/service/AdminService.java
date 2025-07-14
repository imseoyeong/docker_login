package com.example.demo_db.service;

import com.example.demo_db.data.dao.AuthenDAO;
import com.example.demo_db.data.dto.AdminAuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthenDAO authenDAO;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthenDTO saveAdmin(AdminAuthenDTO adminAuthenDTO) {
        if(!adminAuthenDTO.getAuthenNumber().equals("1234")){
            throw new AuthenticationException("관리자 인증번호가 틀립니다.");
        }
        if(this.authenDAO.existAuthen(adminAuthenDTO.getUsername())){
            throw new DuplicateKeyException("이미 있는 관리자 아이디입니다.");
        }

        AuthenEntity saveAdminEntity=this.authenDAO.saveAuthen(adminAuthenDTO.getUsername(), adminAuthenDTO.getPassword(), "ROLE_ADMIN");

        AdminAuthenDTO saveAdminAuthenDTO = AdminAuthenDTO.builder()
                .username(saveAdminEntity.getUsername())
                .build();
        return saveAdminAuthenDTO;
    }

}
