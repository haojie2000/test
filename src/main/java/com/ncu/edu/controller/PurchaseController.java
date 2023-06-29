package com.ncu.edu.controller;

import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Page;
import com.ncu.edu.pojo.Purchase;
import com.ncu.edu.pojo.PurchaseApplication;
import com.ncu.edu.service.GoodsService;
import com.ncu.edu.service.PurchaseApplicationService;
import com.ncu.edu.service.PurchaseService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class PurchaseController {
    @Autowired
    private GoodsService goodsService;

    private SnowflakeUtil snowflakeUtil=new SnowflakeUtil();
    @Autowired
    private PurchaseApplicationService purchaseApplicationService;
    @Autowired
    private PurchaseService purchaseService;
    @PostMapping("/purchase/application/supplier")
    CommonResult getSupplier(ModelAndView mav, @RequestBody HashMap<String,String> map){
        String goodsId = map.get("goodsId");
        System.out.println(goodsId);
        CommonResult supplier = goodsService.getSupplierByGoodsId(goodsId);
        System.out.println(supplier.getData());
        return supplier;
    }
    @PostMapping("/purchase/application")
    @ResponseBody
    CommonResult addPurchase(HttpSession session,@RequestBody PurchaseApplication purchaseApplication){
        String userId = (String) session.getAttribute("userId");
        String id = snowflakeUtil.snowflakeId();
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String newDate = sdf.format(date);
        purchaseApplication.setPurchaseApplicationId(id);
        purchaseApplication.setApplicationDate(newDate);
        purchaseApplication.setUserId(userId);
        System.out.println(purchaseApplication);
        CommonResult result = purchaseApplicationService.save(purchaseApplication);
        return result;
    }

    @GetMapping("/purchase/application")
    CommonResult getPurchaseALL(){
        CommonResult serviceAll = purchaseApplicationService.getAll();
        return serviceAll;
    }
    @DeleteMapping("/purchase/{id}")
    CommonResult<PurchaseApplication> deleteById(@SessionAttribute("userId") String userId,@PathVariable("id") String id,@SessionAttribute("role") String role){
        if(role.equals("1")){
            CommonResult result = purchaseApplicationService.update(id);
            PurchaseApplication data = (PurchaseApplication) result.getData();
            String purchaseId = snowflakeUtil.snowflakeId();
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
            Date date = new Date();// 获取当前时间
            String newDate = sdf.format(date);
            Purchase purchase=new Purchase(purchaseId,data.getGoodsId(),data.getSupplierId(),data.getQuantity(),newDate,
                    userId);
            CommonResult result1 = purchaseService.add(purchase);
            return result1;
        }else {
            return new CommonResult(400,"对不起，您的权限不够");
        }

    }
    @PostMapping("/purchase")
    @ResponseBody
    CommonResult getAll(@RequestBody Page page){
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        CommonResult purchases = purchaseService.getWithLimit(size,offset);
        return purchases;
    }
    @GetMapping("/purchase")
    int getCount(){
        int count = purchaseService.getCount();
        return count;
    }
    @GetMapping("/purchase/search/{search}")
    CommonResult search(@PathVariable("search") String search){
        final CommonResult result = purchaseService.search(search);
        return result;
    }
}
