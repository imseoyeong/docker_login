package com.example.demo_db.data.dto;

import com.example.demo_db.data.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {
    private Integer num;
    private String userId;
    private String prodName;
    private String groupName;
    @Min(value = 0)
    private Integer price;
    @Min(value = 1)
    private Short amount;
}
