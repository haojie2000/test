package com.ncu.edu.controller;

import com.ncu.edu.pojo.*;
import com.ncu.edu.service.GoodsService;
import com.ncu.edu.service.SupplierService;
import com.ncu.edu.service.UserService;
import com.ncu.edu.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UserService userService;
    private SnowflakeUtil snowflakeUtil=new SnowflakeUtil();
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/message/supplier")
    @ResponseBody
    CommonResult getSupplier(@SessionAttribute("role")String role,@RequestBody Page page){
        Integer size = page.getPageSize();
        Integer currentPage = page.getCurrentPage();
        Integer offset=(currentPage-1)*size;
        System.out.println(page.getQueryString());
        if(role.equals("1")){
            if(page.getQueryString()!=null&& page.getQueryString()!=""){
                final List<Supplier> suppliers = supplierService.get(page.getQueryString());
                if (suppliers!=null){
                    return new CommonResult(200,"查询成功",suppliers);
                }else {
                    return new CommonResult(400,"当前并未查询出结果");
                }
            }else {
                List<Supplier> suppliers = supplierService.getSuppliers(size,offset);
                if (suppliers!=null){
                    return new CommonResult(200,"查询成功",suppliers);
                }else {
                    return new CommonResult(400,"当前并未查询出结果");
                }
            }

        }else {
            return new CommonResult(444,"您的权限不够");
        }
    }
    @GetMapping("/message/supplier")
    CommonResult getCount(){
        final Integer count = supplierService.getCount();
        return new CommonResult(200,"信息总数量",count);
    }
    @GetMapping("/message/supplier/select/{typePId}")
    CommonResult getType(@PathVariable("typePId")String typePId){
        List<GoodsType> goodsTypes = supplierService.getByTypePId(typePId);
        if(goodsTypes!=null){
            return new CommonResult(200,"存在",goodsTypes);
        }else {
            return new CommonResult(444,"类别不存在");
        }
    }
    @GetMapping("/message/supplier/select2/{typeId}")
    CommonResult getGoods(@PathVariable("typeId")String typeId){
        final List<Goods> goodsList = supplierService.getGoodsBytypeId(typeId);
        if(goodsList!=null){
            return new CommonResult(200,"存在",goodsList);
        }else {
            return new CommonResult(444,"商品不存在");
        }
    }
    @PostMapping("/message/supplier/add")
    @ResponseBody
    CommonResult addSupplier(@RequestBody Supplier supplier){
        int i = supplierService.addSupplier(supplier);
        System.out.println("i=="+i);
        if(i!=0){
            return new CommonResult(200,"添加成功");
        }else {
            return new CommonResult(400,"添加失败");
        }
    }
    @PostMapping("/message/supplier/update")
    @ResponseBody
    CommonResult updateSupplier(@RequestBody Supplier supplier){
        System.out.println(supplier);
        int i = supplierService.updateSupplier(supplier);
        if (i>0){
            return new CommonResult(200,"更新成功");
        }else {
            return new CommonResult(444,"更新失败");
        }
    }
    @GetMapping("/message/supplier/{supplierId}")
    CommonResult delete(@PathVariable("supplierId") String supplierId){
        final CommonResult result = supplierService.delete(supplierId);
        return result;
    }

    @PostMapping("/message/user")
    @ResponseBody
    CommonResult userGetALL(@SessionAttribute("role")String role,@RequestBody Page page){
        Integer size = page.getPageSize();
        Integer currentPage = page.getCurrentPage();
        Integer offset=(currentPage-1)*size;
        String queryString = page.getQueryString();
        if(role.equals("1")){
            if(page.getQueryString()!=null&& page.getQueryString()!=""){
                CommonResult result = userService.get(queryString);
                return result;
            }else {
                CommonResult all = userService.getAll(offset, size);
                return all;
            }

        }else {
            return new CommonResult(489,"您的权限不够");
        }
    }
    @GetMapping("/message/user")
    CommonResult getUserCount(){
        CommonResult result = userService.getCount();
        return result;
    }
    @PostMapping("/message/user/add")
    @ResponseBody
    CommonResult userAdd(@RequestBody User user){
       if(user.getPassword()==null){
           user.setPassword("000000");
       }
        CommonResult result = userService.add(user);
       return result;
    }
    @PostMapping("/message/user/update")
    @ResponseBody
    CommonResult userUpdate(@RequestBody User user){
        CommonResult result = userService.update(user);
        return result;
    }
    @GetMapping("/message/user/delete/{userId}")
    CommonResult userDelete(@PathVariable("userId") String userId){
        final CommonResult result = userService.delete(userId);
        return result;
    }
    @PostMapping("/message/goods/add")
    @ResponseBody
    CommonResult goodsAdd(@RequestBody Goods goods){
        System.out.println(goods);
        final String goodsId = snowflakeUtil.snowflakeId();
        goods.setGoodsId(goodsId);
        final CommonResult result = goodsService.add(goods);
        return result;
    }
    @PostMapping("/message/goods/update")
    @ResponseBody
    CommonResult goodsUpdate(@RequestBody Goods goods){
        final CommonResult result = goodsService.update(goods);
        return result;
    }
    @GetMapping("/message/goods/delete/{goodsId}")
    CommonResult goodsDelete(@PathVariable("goodsId")String goodsId){
        final CommonResult result = goodsService.delete(goodsId);
        return result;
    }
    @GetMapping("/message/goods/change/{typeName}")
    CommonResult change(@PathVariable("typeName") String typeName){
        final String typeId = goodsService.change(typeName);
        return new CommonResult(200,"成功",typeId);
    }
}
