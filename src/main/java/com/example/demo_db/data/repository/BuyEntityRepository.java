package com.example.demo_db.data.repository;

import com.example.demo_db.data.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyEntityRepository extends JpaRepository<BuyEntity, Integer> {
    @Query(value="select * from buytbl where userid=:userid", nativeQuery = true)
    public List<BuyEntity> searchBuyInfo(@Param("userid") String userid);

    @Query(value="select count(*) from buytbl where userid=:userid", nativeQuery = true)
    public Integer getBuyInfoCount(@Param("userid") String userid);


}
