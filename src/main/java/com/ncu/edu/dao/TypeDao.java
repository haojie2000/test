package com.ncu.edu.dao;

import com.ncu.edu.pojo.GoodsType;
import com.ncu.edu.service.GoodsService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    GoodsType getByTypeId(@Param("typeId") String typeId);
    List<GoodsType> getTypeByTypePId(@Param("typePId") String typePId);
    String getTypeIdByName(@Param("typeName") String typeName);

}
