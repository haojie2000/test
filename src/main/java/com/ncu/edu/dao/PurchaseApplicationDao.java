package com.ncu.edu.dao;

import com.ncu.edu.pojo.PurchaseApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface PurchaseApplicationDao {
    /**
     * 根据数据插入进货申请单
     * @param purchaseApplication
     * @return
     */
    int save(PurchaseApplication purchaseApplication);

    /**
     * 根据进货申请单id修改申请表状态
     * @param id
     * @return
     */
    int update(@Param("id") String id);

    /**
     * 查找所有状态为0即未被批准的进货申请表
     * @return
     */
    List<PurchaseApplication> selectAll();
    /**
     * 查找所有状态为1即被批准的进货申请表
     * @return
     */
    List<PurchaseApplication> selectStateOne();

    /**
     * 根据进货申请表id查询进货申请单
     * @param id
     * @return
     */
    PurchaseApplication getById(@Param("id") String id);

    String getNameById(@Param("id") String id);

}
