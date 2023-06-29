package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {

    CommonResult getAll();
    CommonResult getByName(String goodsName);
    CommonResult getById(String id);
    CommonResult getWithLimit(Integer page,Integer offset);
    CommonResult getSupplierByGoodsId(String goodsId);
    int addStock(Integer num,String goodsId);
    int deleteStock(Integer num,String goodsId);
    int getCount();
    List<Goods> getByTypePId(String typePId,Integer page,Integer offset);
    Integer getCountByTypePId(String typePId);
    List<Goods> getByCondition(String typePId,String search);
    CommonResult add(Goods goods);
    CommonResult update(Goods goods);
    CommonResult delete(String goodsId);
    CommonResult get(String queryString);
    String change(String typeName);
}
