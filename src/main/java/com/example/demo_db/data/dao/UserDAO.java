package com.example.demo_db.data.dao;

import com.example.demo_db.data.entity.UserEntity;
import com.example.demo_db.data.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDAO {
    private final UserEntityRepository userEntityRepository;

    public List<UserEntity> searchUserInfo(String addr) {
        return this.userEntityRepository.searchUserInfo(addr);
    }
    public List<UserEntity> searchUserInfo(Integer birthYear) {
        return this.userEntityRepository.searchUserInfo(birthYear);
    }
    public List<UserEntity> searchUserInfo(String addr, Integer birthYear) {
        return this.userEntityRepository.searchUserInfo(addr, birthYear);
    }


    public UserEntity saveUserEntity(String id, String username, String mobile, Integer birthYear,
                                     String addr, Integer height, LocalDate joinDate) {
        UserEntity userEntity=UserEntity.builder()
                .userId(id)
                .userName(username)
                .mobile1(mobile!=null? mobile.substring(0,3):null)
                .mobile2(mobile!=null? mobile.substring(3):null)
                .birthYear(birthYear)
                .addr(addr)
                .height(height)
                .joinDate(joinDate)
                .build();

        boolean flag=this.userEntityRepository.existsById(userEntity.getUserId());
        if(!flag){
            return this.userEntityRepository.save(userEntity);
        }
        return null;
    }

    public UserEntity getUserEntityById(String userId) {
        Optional<UserEntity> userEntity= this.userEntityRepository.findById(userId);
        if(userEntity.isPresent()){
            return userEntity.get();
        }
        return null;
    }

    public boolean existUserId(String userId) {
        return this.userEntityRepository.existsById(userId);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
