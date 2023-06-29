package com.ncu.edu.dao;

import com.ncu.edu.pojo.Fund;
import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.GoodsType;
import com.ncu.edu.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SaleDao {
    int add(Sale sale);
    int addFund(Fund fund);
    List<GoodsType> getByTypePId(@Param("typePId") String typePId);
    Fund getFund(@Param("date") String date,@Param("typeId") String typeId);
    Fund getGoodsFund(@Param("goodsId")String goodsId,@Param("date") String date);
    List<Sale> getALLSale();
    List<Sale> getSaleByDate(@Param("date") String date);
}
