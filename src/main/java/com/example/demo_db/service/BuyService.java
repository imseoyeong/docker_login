package com.example.demo_db.service;

import com.example.demo_db.data.dao.BuyDAO;
import com.example.demo_db.data.dao.UserDAO;
import com.example.demo_db.data.dto.BuyDTO;
import com.example.demo_db.data.entity.BuyEntity;
import com.example.demo_db.data.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyService {
    private final BuyDAO buyDAO;
    private final UserDAO userDAO;

    public List<BuyDTO> searchBuyInfo(String userId) {
        List<BuyEntity> buyEntities = this.buyDAO.searchBuyInfo(userId);
        if (buyEntities != null) {
            List<BuyDTO> buyDTOList = new ArrayList<>();
            for (BuyEntity buyEntity : buyEntities) {
                BuyDTO buyDTO = BuyDTO.builder()
                        .num(buyEntity.getNum())
                        .userId(buyEntity.getUser().getUserId())
                        .prodName(buyEntity.getProdName())
                        .groupName(buyEntity.getGroupName())
                        .price(buyEntity.getPrice())
                        .amount(buyEntity.getAmount())
                        .build();
                buyDTOList.add(buyDTO);
            }
            return buyDTOList;
        }
        if (!this.userDAO.existUserId(userId)) {
              throw new EntityNotFoundException("회원이 아닙니다");
        }
        return null;
    }

    public BuyDTO saveBuyInfo(BuyDTO buyDTO){
        UserEntity userEntity=this.userDAO.getUserEntityById(buyDTO.getUserId());
        if(userEntity!=null){
            BuyEntity buyEntity=BuyEntity.builder()
                    .user(userEntity)
                    .price(buyDTO.getPrice())
                    .amount(buyDTO.getAmount())
                    .groupName(buyDTO.getGroupName())
                    .prodName(buyDTO.getProdName())
                    .build();
            BuyEntity saveBuyEntity=this.buyDAO.saveBuyInfo(buyEntity);

            BuyDTO retBuyDTO=BuyDTO.builder()
                    .num(saveBuyEntity.getNum())
                    .userId(saveBuyEntity.getUser().getUserId())
                    .prodName(saveBuyEntity.getProdName())
                    .groupName(saveBuyEntity.getGroupName())
                    .price(saveBuyEntity.getPrice())
                    .amount(saveBuyEntity.getAmount())
                    .build();
            return retBuyDTO;
        }
        throw new EntityNotFoundException("존재하지 않는 아이디입니다.");
    }
}
