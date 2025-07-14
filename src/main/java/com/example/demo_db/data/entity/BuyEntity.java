package com.example.demo_db.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="buytbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num", nullable = false)
    private Integer num;
    @ManyToOne
    @JoinColumn(name="userid", nullable = false)
    private UserEntity user;
    @Column(name="prodname", length = 10, nullable = false)
    private String prodName;
    @Column(name="groupname", length = 4)
    private String groupName;
    @Column(name="price", nullable = false)
    private int price;
    @Column(name="amount", nullable = false)
    private short amount;
}
