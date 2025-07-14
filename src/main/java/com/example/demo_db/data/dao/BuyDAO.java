package com.example.demo_db.data.dao;

import com.example.demo_db.data.dto.UserDTO;
import com.example.demo_db.data.entity.BuyEntity;
import com.example.demo_db.data.entity.UserEntity;
import com.example.demo_db.data.repository.BuyEntityRepository;
import com.example.demo_db.data.repository.UserEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyDAO {
    private final BuyEntityRepository buyEntityRepository;

    public List<BuyEntity> searchBuyInfo(String userId) {
        Integer count=this.buyEntityRepository.getBuyInfoCount(userId);
        if(count==0){
            return null;
        }
        return this.buyEntityRepository.searchBuyInfo(userId);
    }

    public BuyEntity saveBuyInfo(BuyEntity buyEntity) {
        return this.buyEntityRepository.save(buyEntity);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
