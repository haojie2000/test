package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.GoodsType;
import com.ncu.edu.pojo.Supplier;
import com.ncu.edu.service.impl.SupplierServiceImpl;

import java.util.List;

public interface SupplierService {
    List<Supplier> getSuppliers(Integer size,Integer offset);
    Integer getCount();
    List<GoodsType> getByTypePId(String typePId);
    List<Goods> getGoodsBytypeId(String typeId);
    int addSupplier(Supplier supplier);
    int updateSupplier(Supplier supplier);
    CommonResult delete(String supplierId);
    List<Supplier> get(String queryString);
}
