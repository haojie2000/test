package com.ncu.edu.service.impl;

import com.ncu.edu.dao.*;
import com.ncu.edu.pojo.*;
import com.ncu.edu.service.PurchaseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PurchaseApplicationServiceImpl implements PurchaseApplicationService {
    @Autowired
    private PurchaseApplicationDao purchaseApplicationDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private UserDao userDao;

    @Override
    public CommonResult save(PurchaseApplication purchaseApplication) {
        int i = purchaseApplicationDao.save(purchaseApplication);
        if (i>0){
            return new CommonResult(200,"申请成功！");
        }else {
            return new CommonResult(555,"申请失败，请重新申请！");
        }
    }

    @Override
    public CommonResult update(String id) {
        int i = purchaseApplicationDao.update(id);
        PurchaseApplication purchaseApplication = purchaseApplicationDao.getById(id);
        if (i>0&&purchaseApplication!=null){
            return new CommonResult(200,"批准成功！",purchaseApplication);
        }else {
            return new CommonResult(555,"操作失败，请稍后再试！");
        }

    }

    @Override
    public CommonResult getAll() {
        List<PurchaseApplication> purchaseApplications = purchaseApplicationDao.selectAll();
        for (PurchaseApplication purchaseApplication : purchaseApplications) {
            Goods goods = goodsDao.selectById(purchaseApplication.getGoodsId());
            purchaseApplication.setGoodsName(goods.getGoodsName());
            Supplier supplier = supplierDao.selectById(purchaseApplication.getSupplierId());
            purchaseApplication.setSupplierName(supplier.getSupplierName());
            User user = userDao.selectById(purchaseApplication.getUserId());
            purchaseApplication.setUserName(user.getUserName());
        }

        return new CommonResult(200,"查询成功",purchaseApplications);
    }

    @Override
    public CommonResult getStateOne() {
        final List<PurchaseApplication> purchaseApplications = purchaseApplicationDao.selectStateOne();
        return new CommonResult(200,"查询成功",purchaseApplications);
    }
}
