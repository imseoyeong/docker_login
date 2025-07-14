package com.example.demo_db.controller;

import com.example.demo_db.data.dto.BuyDTO;
import com.example.demo_db.service.BuyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/buyinfo")
public class BuyController {
    private final BuyService buyService;

    @GetMapping(value="{userid}")
    public ResponseEntity<List<BuyDTO>> getBuyInfo(@PathVariable("userid") String userId) {
        List<BuyDTO> buyDTOList=this.buyService.searchBuyInfo(userId);
        if(buyDTOList == null) {
            return ResponseEntity.status(250).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(buyDTOList);
    }

    @PostMapping(value="/new")
    public ResponseEntity<BuyDTO> addBuyInfo(@Valid @RequestBody BuyDTO buyDTO) {
        BuyDTO saveBuyDTO=this.buyService.saveBuyInfo(buyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBuyDTO);
    }
}
