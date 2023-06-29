package com.ncu.edu.service;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.ReturnApplication;
import org.springframework.stereotype.Service;

@Service
public interface ReturnApplicationService {
    CommonResult add(ReturnApplication returnApplication);
    CommonResult getAll();
    CommonResult getById(String id);
    CommonResult getPages(Integer offset,Integer page);
    int getCount();
    int update(String id);
    CommonResult search(String search);

}
