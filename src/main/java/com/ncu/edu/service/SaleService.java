package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Parameter;
import com.ncu.edu.pojo.Fund;
import com.ncu.edu.pojo.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {
    CommonResult checkOut(List<Sale> sales);
    CommonResult addFund(Fund fund);
    CommonResult OtherInit(Parameter param);
    CommonResult getFund(String date,String typeName);
    CommonResult createExcel();
    CommonResult createExcel(String date);
}
