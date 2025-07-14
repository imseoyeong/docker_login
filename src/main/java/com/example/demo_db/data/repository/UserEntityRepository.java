package com.example.demo_db.data.repository;

import com.example.demo_db.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

    @Query(value="select * from usertbl where addr=:addr", nativeQuery=true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr);

    @Query(value="select * from usertbl where birthyear=:birthyear", nativeQuery=true)
    List<UserEntity> searchUserInfo(@Param("birthyear") Integer birthYear);

    @Query(value="select * from usertbl where addr=:addr and birthyear=:birthyear", nativeQuery = true)
    List<UserEntity> searchUserInfo(@Param("addr") String addr, @Param("birthyear") Integer birthYear);

}
