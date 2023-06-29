package com.ncu.edu.controller;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.service.PurchaseService;
import com.ncu.edu.service.ReturnService;
import com.ncu.edu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ReturnService returnService;

    @GetMapping("/sale/out")
    CommonResult saleAllExcel(){
        CommonResult result = saleService.createExcel();
        return result;
    }
    @GetMapping("/sale/out/{date}")
    CommonResult saleByDateExcel(@PathVariable("date")String date){
        CommonResult result = saleService.createExcel(date);
        return result;
    }
    @GetMapping("/purchase/out")
    CommonResult purchaseAllExcel(){
        CommonResult result = purchaseService.createExcel();
        return result;
    }
    @GetMapping("/purchase/out/{date}")
    CommonResult purchaseByDateExcel(@PathVariable("date")String date){
        CommonResult result = purchaseService.createExcel(date);
        return result;
    }
    @GetMapping("/return/out")
    CommonResult returnAllExcel(){
        CommonResult result = returnService.createExcel();
        return result;
    }
    @GetMapping("/return/out/{date}")
    CommonResult returnByDateExcel(@PathVariable("date")String date){
        CommonResult result = returnService.createExcel(date);
        return result;
    }
}
