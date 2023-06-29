package com.ncu.edu.dao;

import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Mapper
@Repository
public interface GoodsDao {
    /**
     * 查找所有商品信息
     * @return
     */
    List<Goods> selectAll();

    /**
     * 根据商品编号查找商品
     * @param goodsId
     * @return
     */
    Goods selectById(@Param("goodsId") String goodsId);

    /**
     * 根据商品名称模糊查询商品信息
     * @param name
     * @return
     */
    List<Goods> selectByName(@Param("goodsName") String name);

    /**
     * 分页显示商品信息
     * @param page 页数
     * @param offset 起始位置
     * @return
     */
    List<Goods> selectLimit(@Param("page") Integer page,@Param("offset") Integer offset);

    /**
     * 根据商品编号查找供应商列表
     * @param goodsId
     * @return
     */
    List<Supplier> selectSupplierByGoodsId(@Param("goodsId") String goodsId);
    int getCount();
    int addStock(@Param("num") Integer num,@Param("goodsId")String goodsId);
    int deleteStock(@Param("num")Integer num,@Param("goodsId")String goodsId);

    /**
     * 根据类别pid查找商品信息
     */
    List<Goods> selectByTypePId(@Param("typePId") String typePId,@Param("page") Integer page,@Param("offset") Integer offset);
    /**
     * 输入总分类获取该分类中的数量
     */
    Integer getCountByTypePId(@Param("typePId") String typePId);
    /**
     * 条件查找
     */
    List<Goods> getByCondition(@Param("typePId") String typePId,@Param("search") String search);
    List<Goods> getGoodsByTypeId(@Param("typeId")String typeId);
    int add(Goods goods);
    int update(Goods goods);
    int delete(@Param("goodsId") String goodsId);
    List<Goods> getBy(@Param("queryString") String queryString);
}
