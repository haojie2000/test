package com.ncu.edu.service.impl;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.dao.SupplierDao;
import com.ncu.edu.dao.TypeDao;
import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.GoodsType;
import com.ncu.edu.pojo.Supplier;
import com.ncu.edu.service.SupplierService;
import com.ncu.edu.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private GoodsDao goodsDao;
    private SnowflakeUtil snowflakeUtil = new SnowflakeUtil();
    @Override
    public List<Supplier> getSuppliers(Integer size,Integer offset) {
        List<Supplier> supplierList = supplierDao.limit(size, offset);
        if(supplierList.isEmpty()){
            return null;
        }else {
            for (Supplier supplier : supplierList) {
                List<String> goodsNameList = supplierDao.getGoodsNameBySupplierId(supplier.getSupplierId());
                String goodsName = goodsNameList.stream().collect(Collectors.joining("、"));
                System.out.println(goodsName);
                supplier.setGoods(goodsName);
            }
            return supplierList;
        }

    }

    @Override
    public Integer getCount() {
        Integer count = supplierDao.selectAll();
        return count;
    }

    @Override
    public List<GoodsType> getByTypePId(String typePId) {
        List<GoodsType> goodsTypeList = typeDao.getTypeByTypePId(typePId);
        if(goodsTypeList.isEmpty()){
            return null;
        }else {
            return goodsTypeList;
        }

    }

    @Override
    public List<Goods> getGoodsBytypeId(String typeId) {
        List<Goods> goodsList = goodsDao.getGoodsByTypeId(typeId);
        if(goodsList.isEmpty()){
            return null;
        }else {
            return goodsList;
        }
    }

    @Override
    public int addSupplier(Supplier supplier) {
        String supplierName = supplier.getSupplierName();
        String id = supplierDao.getByName(supplierName);
        Integer result=0;
        if(id==null){
            String supplierId = snowflakeUtil.snowflakeId();
            supplier.setSupplierId(supplierId);
            int i = supplierDao.save(supplier);
            if(i>0){
                List<String> goodsIds = supplier.getGoodsId();
                for (String goodsId : goodsIds) {
                    supplierDao.addSupplierGoods(supplierId, goodsId);
                }
                return i;
            }else {
                return 0;
            }
        }else {
            List<String> goodsIds= supplier.getGoodsId();
            for (String goodsId : goodsIds) {
                final int i = supplierDao.getSupplierGoods(id, goodsId);
                if(i==0){
                    supplierDao.addSupplierGoods(id, goodsId);
                    result++;
                }
            }
           return result;
        }
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        final int count = supplierDao.update(supplier);
        return count;
    }

    @Override
    public CommonResult delete(String supplierId) {
        int i = supplierDao.deleteBySupplierId(supplierId);
        int i1 = supplierDao.deleteFromGoods(supplierId);
        if (1>0&&i1>0){
            return new CommonResult(200,"删除成功");
        }else {
            return new CommonResult(444,"删除失败");
        }
    }

    @Override
    public List<Supplier> get(String queryString) {
        final List<Supplier> select = supplierDao.select(queryString);
        return select;
    }
}
