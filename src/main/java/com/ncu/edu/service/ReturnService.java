package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Return;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReturnService {
    int add(Return data);
    List<Return> getWithPage(Integer size,Integer offset);
    int getCount();
    CommonResult createExcel();
    CommonResult createExcel(String date);
}
