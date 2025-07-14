package com.example.demo_db.service;


import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenEntityRepository;
import com.example.demo_db.exception.RoleAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginDetailService implements UserDetailsService {
    private final AuthenEntityRepository adminEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int index = username.indexOf('_');
        String role = username.substring(0, index);
        String uNmae = username.substring(index+1);

        AuthenEntity adminEntity =this.adminEntityRepository.findById(uNmae).orElse(null);
        if(adminEntity == null){
            throw new BadCredentialsException("User not found");
        }
        if(role.equals("ADMIN")&& !adminEntity.getRole().equals("ROLE_ADMIN") ) {
            throw new BadCredentialsException("관리자가 아닌 계정으로 로그인을 시도했습니다.");
        }

        if(role.equals("USER") && !adminEntity.getRole().equals("ROLE_USER")) {
            throw new BadCredentialsException("일반유저가 아닌계정으로 로그인을 시도했습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(adminEntity.getRole()));
        return new User(adminEntity.getUsername(), adminEntity.getPassword(), grantedAuthorities);
    }

}

