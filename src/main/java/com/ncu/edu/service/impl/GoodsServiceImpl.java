package com.ncu.edu.service.impl;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.dao.SupplierDao;
import com.ncu.edu.dao.TypeDao;
import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.GoodsType;
import com.ncu.edu.pojo.Supplier;
import com.ncu.edu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private SupplierDao supplierDao;
    @Override
    public CommonResult getAll() {
        final List<Goods> goods = goodsDao.selectAll();
        return new CommonResult(200, "查询成功", goods);
    }

    @Override
    public CommonResult getByName(String goodsName) {
        List<Goods> goods = goodsDao.selectByName(goodsName);
        if (goods != null) {
            for (Goods good : goods) {
                GoodsType goodsType = typeDao.getByTypeId(good.getTypeId());
                good.setTypeName(goodsType.getTypeName());
                good.setTypePName(goodsType.getTypePName());
                List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(good.getGoodsId());
                String suppliers = supplierList.stream().collect(Collectors.joining(","));
                good.setSuppliers(suppliers);
            }
            return new CommonResult(200, "查询成功", goods);
        } else {
            return new CommonResult(555, "抱歉，未查找到相关数据");
        }

    }

    @Override
    public CommonResult getById(String id) {
        Goods goods = goodsDao.selectById(id);
        if (goods != null) {
            GoodsType goodsType = typeDao.getByTypeId(goods.getTypeId());
            goods.setTypeName(goodsType.getTypeName());
            goods.setTypePName(goodsType.getTypePName());
            List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(goods.getGoodsId());
            String suppliers = supplierList.stream().collect(Collectors.joining(","));
            goods.setSuppliers(suppliers);
            return new CommonResult(200, "查询成功", goods);
        } else {
            return new CommonResult(555, "抱歉，未查找到相关数据");
        }
    }

    @Override
    public CommonResult getWithLimit(Integer page, Integer offset) {
        List<Goods> goods = goodsDao.selectLimit(page, offset);
        for (Goods good : goods) {
            GoodsType goodsType = typeDao.getByTypeId(good.getTypeId());
            good.setTypeName(goodsType.getTypeName());
            good.setTypePName(goodsType.getTypePName());
            List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(good.getGoodsId());
            String suppliers = supplierList.stream().collect(Collectors.joining(","));
            good.setSuppliers(suppliers);
        }
        return new CommonResult(200, goods);
    }

    @Override
    public CommonResult getSupplierByGoodsId(String goodsId) {
        final List<Supplier> suppliers = goodsDao.selectSupplierByGoodsId(goodsId);
        return new CommonResult(200, "供应商Name", suppliers);
    }

    @Override
    public int addStock(Integer num, String goodsId) {
        final int i = goodsDao.addStock(num, goodsId);
        return i;
    }

    @Override
    public int deleteStock(Integer num, String goodsId) {
        final int i = goodsDao.deleteStock(num, goodsId);
        return i;
    }

    @Override
    public int getCount() {
        final int count = goodsDao.getCount();
        return count;
    }

    @Override
    public List<Goods> getByTypePId(String typePId,Integer page,Integer offset) {
        List<Goods> goodsList = goodsDao.selectByTypePId(typePId,page,offset);
        if(!goodsList.isEmpty()){
            for (Goods goods : goodsList) {
                List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(goods.getGoodsId());
                String supplier = supplierList.stream().collect(Collectors.joining("、"));
                goods.setSuppliers(supplier);
            }
            return goodsList;
        }else {
            return null;
        }

    }

    @Override
    public Integer getCountByTypePId(String typePId) {
        final Integer count = goodsDao.getCountByTypePId(typePId);
        return count;
    }

    @Override
    public List<Goods> getByCondition(String typePId, String search) {
        List<Goods> goodsList = goodsDao.getByCondition(typePId, search);
        if(!goodsList.isEmpty()){
            for (Goods goods : goodsList) {
                List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(goods.getGoodsId());
                String supplier = supplierList.stream().collect(Collectors.joining("、"));
                goods.setSuppliers(supplier);
            }
            return goodsList;
        }else {
            return null;
        }
    }

    @Override
    public CommonResult add(Goods goods) {
        final int i = goodsDao.add(goods);
        if(i>0){
            return new CommonResult(200,"添加成功");
        }else {
            return new CommonResult(444, "添加失败");
        }
    }

    @Override
    public CommonResult update(Goods goods) {
        final int i = goodsDao.update(goods);
        if(i>0){
            return new CommonResult(200,"修改成功");
        }else {
            return new CommonResult(444, "修改失败");
        }
    }

    @Override
    public CommonResult delete(String goodsId) {
        final int i = goodsDao.delete(goodsId);
        if(i>0){
            return new CommonResult(200,"删除成功");
        }else {
            return new CommonResult(444,"删除失败");
        }
    }

    @Override
    public CommonResult get(String queryString) {
        final List<Goods> goodsList = goodsDao.getBy(queryString);
        if(!goodsList.isEmpty()){
            for (Goods goods : goodsList) {
                List<String> supplierList = supplierDao.getSupplierNamesByGoodsId(goods.getGoodsId());
                String supplier = supplierList.stream().collect(Collectors.joining("、"));
                goods.setSuppliers(supplier);
            }
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(444,"查询失败");
        }
    }

    @Override
    public String change(String typeName) {
        final String id = typeDao.getTypeIdByName(typeName);
        return id;
    }

}
