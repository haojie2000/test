package com.ncu.edu.controller;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Parameter;
import com.ncu.edu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/sale/other/init")
    @ResponseBody
    CommonResult otherInit(@RequestBody Parameter param) {
        final CommonResult result = saleService.OtherInit(param);
        return result;
    }
    @PostMapping("/sale/other/onclick")
    @ResponseBody
    CommonResult otherOnclick(@RequestBody Parameter parameter){
        CommonResult result = saleService.getFund(parameter.getDate(), parameter.getTypeName());
        return result;
    }

}
