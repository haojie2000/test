package com.ncu.edu.controller;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.pojo.CommonResult;
import com.ncu.edu.pojo.Goods;
import com.ncu.edu.pojo.Page;
import com.ncu.edu.pojo.Param;
import com.ncu.edu.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModuleController {

    @Autowired
    private GoodsService goodsService;
    @PostMapping("/module/1002")
    @ResponseBody
    CommonResult getByTypePId(@RequestBody Page page) {
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        List<Goods> goodsList = goodsService.getByTypePId("1002", size, offset);
        if(!goodsList.isEmpty()){
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"没有数据");
        }
    }
    @PostMapping("/module/1001")
    @ResponseBody
    CommonResult getByTypePId1(@RequestBody Page page) {
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        List<Goods> goodsList = goodsService.getByTypePId("1001", size, offset);
        if(!goodsList.isEmpty()){
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"没有数据");
        }
    }
    @PostMapping("/module/1003")
    @ResponseBody
    CommonResult getByTypePId2(@RequestBody Page page) {
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        List<Goods> goodsList = goodsService.getByTypePId("1003", size, offset);
        if(!goodsList.isEmpty()){
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"没有数据");
        }
    }
    @PostMapping("/module/1004")
    @ResponseBody
    CommonResult getByTypePId3(@RequestBody Page page) {
        Integer currentPage=page.getCurrentPage();
        Integer size=page.getPageSize();
        Integer offset=(currentPage-1)*size;
        List<Goods> goodsList = goodsService.getByTypePId("1004", size, offset);
        if(!goodsList.isEmpty()){
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"没有数据");
        }
    }
    @GetMapping("/module/{typePId}")
    int getCountByTypePId(@PathVariable("typePId") String typePId){
        Integer count = goodsService.getCountByTypePId(typePId);
        return count;
    }

    @PostMapping("/module/search")
    @ResponseBody
    CommonResult search(@RequestBody Param param){
        String search = param.getSearch();
        String typePId = param.getTypePId();
        List<Goods> goodsList = goodsService.getByCondition(typePId, search);
        if (goodsList!=null){
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"为查询到数据");
        }

    }
}
