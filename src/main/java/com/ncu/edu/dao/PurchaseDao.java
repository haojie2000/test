package com.ncu.edu.dao;

import com.ncu.edu.pojo.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PurchaseDao {
    List<Purchase> select2(@Param("page") Integer page,@Param("offset") Integer offset);
    List<Purchase> select(@Param("page") Integer page,@Param("offset") Integer offset);
    int insert(Purchase purchase);
    int updateById(@Param("purchaseId") String id,@Param("state")Integer state);
    List<Purchase> selectAll();
    int getCount();
    int getCount2();
    Purchase getById(@Param("purchaseId") String id);
    List<Purchase> getByGoodsId(@Param("goodsId")String goodsId);
    Purchase getById2(@Param("purchaseId") String id);
    List<Purchase> getByGoodsId2(@Param("goodsId")String goodsId);
    List<Purchase> getByDateExcel(@Param("date")String date);
}
