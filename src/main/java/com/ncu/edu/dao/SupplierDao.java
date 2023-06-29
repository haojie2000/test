package com.ncu.edu.dao;

import com.ncu.edu.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SupplierDao {
    Supplier selectById(@Param("supplierId") String id);
    Integer selectAll();
    int update(Supplier supplier);
    List<Supplier> select(@Param("queryString") String queryString);
    List<Supplier> limit(@Param("page") Integer page,@Param("offset") Integer offset);
    List<String> getSupplierNamesByGoodsId(@Param("goodsId") String goodsId);
    List<String> getGoodsNameBySupplierId(@Param("supplierId") String supplierId);
    int save(Supplier supplier);
    int addSupplierGoods(@Param("supplierId") String supplierId,@Param("goodsId") String goodsId);
    int deleteBySupplierId(@Param("supplierId") String supplierId);
    int deleteFromGoods(@Param("supplierId") String supplierId);
    String getByName(@Param("supplierName") String supplierName);
    int getSupplierGoods(@Param("supplierId") String supplierId,@Param("goodsId") String goodsId);
}
