package com.ncu.edu.controller;

import com.ncu.edu.pojo.*;
import com.ncu.edu.service.GoodsService;
import com.ncu.edu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SaleService saleService;

    @GetMapping("/goods/count")
    int getCount(){
        final int count = goodsService.getCount();
        return count;
    }
    @PostMapping("/goods/stock")
    @ResponseBody
    CommonResult getAll(@RequestBody Page page){
        String queryString=page.getQueryString();
        if(queryString!=null&&queryString!=""){
            final CommonResult result = goodsService.get(queryString);
            return result;
        }else {
            int size=page.getPageSize();
            int offset=(page.getCurrentPage()-1)*size;
            CommonResult goods = goodsService.getWithLimit(size, offset);
            return goods;
        }
    }
    @GetMapping("/goods/search/{search}")
    CommonResult getById(@PathVariable("search") String search){
        System.out.println(1);
        CommonResult serviceById = goodsService.getById(search);
        CommonResult byName = goodsService.getByName(search);
        System.out.println(serviceById.getData());
        System.out.println(byName.getData());
        List<Goods> list= (List<Goods>) byName.getData();
        Goods goods= (Goods) serviceById.getData();
        if(serviceById.getData()!=null&& !((List<Goods>) byName.getData()).isEmpty()){
            System.out.println(2);
            return new CommonResult(200,"查询成功",list.add(goods));
        }if(serviceById.getData()==null&&!((List<Goods>) byName.getData()).isEmpty()){
            System.out.println(3);
            return byName;
        }if(serviceById.getData()!=null&&((List<Goods>) byName.getData()).isEmpty()){
            List<Goods> goodsList=new ArrayList<>();
            goodsList.add((Goods) serviceById.getData());
            System.out.println(4);
            return new CommonResult(200,"查询成功",goodsList);
        }else {
            return new CommonResult(400,"查询失败");
        }
    }
    @GetMapping("/checkStand/search/{goodsId}")
    CommonResult getGoodsByGoodsId(@PathVariable("goodsId")String goodsId){
        System.out.println("test001");
        CommonResult result = goodsService.getById(goodsId);
        return result;
    }
    @PostMapping("/checkStand/checkOut")
    @ResponseBody
    CommonResult checkOut(@RequestBody List<Sale> sales){
        CommonResult result = saleService.checkOut(sales);
        return result;
    }
    @PostMapping("/checkStand/add")
    @ResponseBody
    CommonResult add(@RequestBody Fund fund){
        CommonResult result = saleService.addFund(fund);
        return result;
    }
}
