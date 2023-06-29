package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Purchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseService {

    CommonResult getWithLimit(Integer page, Integer offset);
    CommonResult add(Purchase purchase);
    CommonResult search(String search);
    CommonResult updateById(String id,Integer state);
    int getCount();
    CommonResult createExcel();
    CommonResult createExcel(String date);
}
