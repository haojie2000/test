package com.ncu.edu.service;

import com.ncu.edu.dao.PurchaseApplicationDao;
import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.PurchaseApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseApplicationService {

    CommonResult save(PurchaseApplication purchaseApplication);
    CommonResult update(String id);
    CommonResult getAll();
    CommonResult getStateOne();



}
