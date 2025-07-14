package com.example.demo_db.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="usertbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name="userid", length=8, nullable=false)
    private String userId;
    @Column(name="username", length = 10, nullable=false)
    private String userName;
    @Column(name="birthyear", nullable=false)
    private Integer birthYear;
    @Column(name="addr", length=2, nullable=false)
    private String addr;
    @Column(name="mobile1", length = 3)
    private String mobile1;
    @Column(name="mobile2", length = 8)
    private String mobile2;
    @Column(name="height", nullable=false)
    private Integer height;
    @Column(name="mdate", nullable=false)
    private LocalDate joinDate;
}
