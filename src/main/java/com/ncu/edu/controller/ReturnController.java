package com.ncu.edu.controller;

import com.ncu.edu.pojo.*;
import com.ncu.edu.service.GoodsService;
import com.ncu.edu.service.PurchaseService;
import com.ncu.edu.service.ReturnApplicationService;
import com.ncu.edu.service.ReturnService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ReturnController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ReturnApplicationService returnApplicationService;
    @Autowired
    private ReturnService returnService;
    @Autowired
    private GoodsService goodsService;
    private SnowflakeUtil snowflakeUtil = new SnowflakeUtil();

    @PostMapping("/return/application")
    @ResponseBody
    CommonResult addReturnApplication(HttpSession session, @RequestBody ReturnApplication returnApplication) {
        String role = (String) session.getAttribute("role");
        String userId = (String) session.getAttribute("userId");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String newDate = sdf.format(date);
        final String s = snowflakeUtil.snowflakeId();
        returnApplication.setUserId(userId);
        returnApplication.setReturnApplicationId(s);
        returnApplication.setApplicationTime(newDate);
        CommonResult result = returnApplicationService.add(returnApplication);
        return result;
    }
    @PostMapping("/return/applications")
    @ResponseBody
    CommonResult getAll(@RequestBody Page page){
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        final CommonResult pages = returnApplicationService.getPages(offset, size);
        return pages;
    }
    @GetMapping("/return/applications")
    int getCount(){
        int count = returnApplicationService.getCount();
        return count;
    }
    @GetMapping("/return/application/search/{search}")
    CommonResult search(@PathVariable("search") String search){
        final CommonResult result = purchaseService.search(search);
        return result;
    }
    @GetMapping("/return")
    CommonResult getReturnApplication(){
        final CommonResult result = returnApplicationService.getAll();
        return result;
    }
    @DeleteMapping("/return/{id}")
    CommonResult deleteReturnApplication(@SessionAttribute("userId") String userId,@PathVariable("id") String id,@SessionAttribute("role") String role){
        if(role.equals("1")){
            System.out.println("id"+id);
            CommonResult data1 =returnApplicationService.getById(id);
            ReturnApplication data = (ReturnApplication) data1.getData();
            CommonResult result = purchaseService.updateById(data.getPurchaseId(),2);
            System.out.println("dhuahdua"+result.getData());
            int i1 = returnApplicationService.update(id);
            String returnId = snowflakeUtil.snowflakeId();
            System.out.println(returnId);
            System.out.println(data);
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
            Date date = new Date();// 获取当前时间
            String newDate = sdf.format(date);
          Return data2=new Return(returnId,data.getSupplierId(),data.getGoodsId(),data.getGoodsName(),data.getQuantity(),newDate
          ,userId);
            int i = returnService.add(data2);
            if (i>0){
                return new CommonResult(200,"批准成功");
            }else {
                return new CommonResult(400,"失败");
            }

        }else {
            return new CommonResult(400,"对不起，您的权限不够");
        }
    }
    @PostMapping("/return03")
    @ResponseBody
    CommonResult getReturn(@RequestBody Page page){
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        List<Return> list = returnService.getWithPage(size, offset);
        return new CommonResult(200,"查询成功",list);
    }
    @GetMapping("/return03")
    CommonResult getCount2(){
        final int count = returnService.getCount();
        return new CommonResult(200,"总共有"+count+"条记录",count);
    }
}
