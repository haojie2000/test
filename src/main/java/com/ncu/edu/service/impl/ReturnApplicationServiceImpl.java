package com.ncu.edu.service.impl;

import com.ncu.edu.dao.*;
import com.ncu.edu.pojo.*;
import com.ncu.edu.service.ReturnApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnApplicationServiceImpl implements ReturnApplicationService {
    @Autowired
    private ReturnApplicationDao returnApplicationDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SupplierDao supplierDao;
    @Override
    public CommonResult add(ReturnApplication returnApplication) {
        int i = returnApplicationDao.save(returnApplication);
        if (i>0){
            purchaseDao.updateById(returnApplication.getPurchaseId(),1);
            return new CommonResult(200,"申请成功");
        }else {
            return new CommonResult(400,"申请失败");
        }

    }

    @Override
    public CommonResult getAll() {
        List<ReturnApplication> returnApplications = returnApplicationDao.selectAll();
        if(returnApplications!=null){
            for (ReturnApplication returnApplication : returnApplications) {
                Goods goods = goodsDao.selectById(returnApplication.getGoodsId());
                returnApplication.setGoodsName(goods.getGoodsName());
                Supplier supplier = supplierDao.selectById(returnApplication.getSupplierId());
                returnApplication.setSupplierName(supplier.getSupplierName());
                User user = userDao.selectById(returnApplication.getUserId());
                returnApplication.setUserName(user.getUserName());
            }
            return new CommonResult(200,"查询成功",returnApplications);
        }else {
            return null;
        }

    }

    @Override
    public CommonResult getById(String id) {
        ReturnApplication returnApplication = returnApplicationDao.selectById(id);
        if(returnApplication!=null){
            return new CommonResult(200,"查询成功",returnApplication);
        }else {
            return new CommonResult(400,"失败");
        }

    }

    @Override
    public CommonResult getPages(Integer offset, Integer page) {
        List<Purchase> purchases = purchaseDao.select2(page, offset);
        if (purchases!=null){
            for (Purchase purchase : purchases) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
            return new CommonResult(200,purchases);
        }else {
            return new CommonResult(444,"目前暂时没有可处理的进货单");
        }
    }

    @Override
    public int getCount() {
        final int count2 = purchaseDao.getCount2();
        return count2;
    }

    @Override
    public int update(String id) {
        final ReturnApplication returnApplication = returnApplicationDao.selectById(id);
        final Integer quantity = returnApplication.getQuantity();
        final int i = returnApplicationDao.update(id);
        goodsDao.deleteStock(quantity,returnApplication.getGoodsId());
        return i;
    }

    @Override
    public CommonResult search(String search) {
        Purchase byId = purchaseDao.getById2(search);
        List<Purchase> byGoodsId = purchaseDao.getByGoodsId2(search);
        if (!byGoodsId.isEmpty()){
            for (Purchase purchase : byGoodsId) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
        }
        if (byId!=null){
            Goods goods = goodsDao.selectById(byId.getGoodsId());
            Supplier supplier = supplierDao.selectById(byId.getSupplierId());
            User user = userDao.selectById(byId.getUserId());
            byId.setGoodsName(goods.getGoodsName());
            byId.setSupplierName(supplier.getSupplierName());
            byId.setUserName(user.getUserName());
            byId.setAddress(supplier.getAddress());
        }
        if(byId!=null&&!byGoodsId.isEmpty()){
            byGoodsId.add(byId);
            return new CommonResult(200,"查询成功",byGoodsId);
        }if(byId==null&&!byGoodsId.isEmpty()){
            return new CommonResult(200,"查询成功",byGoodsId);
        }if(byId!=null&&byGoodsId.isEmpty()){
            List<Purchase> list=new ArrayList<>();
            list.add(byId);
            return new CommonResult(200,"查询成功",list);
        }else {
            return new CommonResult(400,"查询失败");
        }
    }


}
