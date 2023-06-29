package com.ncu.edu.service.impl;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.dao.PurchaseDao;
import com.ncu.edu.dao.SupplierDao;
import com.ncu.edu.dao.UserDao;
import com.ncu.edu.pojo.*;
import com.ncu.edu.service.GoodsService;
import com.ncu.edu.service.PurchaseService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.ibatis.annotations.Update;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private UserDao userDao;
    private SnowflakeUtil snowflakeUtil=new SnowflakeUtil();

    @Override
    public CommonResult getWithLimit(Integer page, Integer offset) {
        List<Purchase> purchases = purchaseDao.select(page, offset);
        if (purchases!=null){
            for (Purchase purchase : purchases) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
            return new CommonResult(200,purchases);
        }else {
            return new CommonResult(444,"目前暂时没有可处理的进货单");
        }
    }

    @Override
    public CommonResult add(Purchase purchase) {
        final int i = purchaseDao.insert(purchase);
        goodsDao.addStock(purchase.getQuantity(),purchase.getGoodsId());
        if(i>0){
            return new CommonResult(200,"添加成功");
        }else {
            return new CommonResult(444,"进货表添加失败");
        }

    }

    @Override
    public CommonResult search(String search) {
        Purchase byId = purchaseDao.getById(search);
        List<Purchase> byGoodsId = purchaseDao.getByGoodsId(search);
        if (!byGoodsId.isEmpty()){
            for (Purchase purchase : byGoodsId) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
        }
        if (byId!=null){
            Goods goods = goodsDao.selectById(byId.getGoodsId());
            Supplier supplier = supplierDao.selectById(byId.getSupplierId());
            User user = userDao.selectById(byId.getUserId());
            byId.setGoodsName(goods.getGoodsName());
            byId.setSupplierName(supplier.getSupplierName());
            byId.setUserName(user.getUserName());
            byId.setAddress(supplier.getAddress());
        }
        if(byId!=null&&!byGoodsId.isEmpty()){
            byGoodsId.add(byId);
            return new CommonResult(200,"查询成功",byGoodsId);
        }if(byId==null&&!byGoodsId.isEmpty()){
            return new CommonResult(200,"查询成功",byGoodsId);
        }if(byId!=null&&byGoodsId.isEmpty()){
            List<Purchase> list=new ArrayList<>();
            list.add(byId);
            return new CommonResult(200,"查询成功",list);
        }else {
            return new CommonResult(400,"查询失败");
        }

    }

    @Override
    public CommonResult updateById(String id,Integer state) {
        System.out.println(6868);
        int i = purchaseDao.updateById(id,state);
        System.out.println(1);
        if (i>0){
            System.out.println(66);
            return new CommonResult(200,"退货成功");
        }else {
            return new CommonResult(444,"退货失败");
        }

    }

    @Override
    public int getCount() {
        int count = purchaseDao.getCount();
        return count;
    }

    @Override
    public CommonResult createExcel() {
        List<Purchase> purchases = purchaseDao.selectAll();
        if(purchases.isEmpty()){
            return new CommonResult(444,"暂时未有进货单");
        }else {
            for (Purchase purchase : purchases) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
        }
        //一.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //二.创建sheet
        HSSFSheet sheet = workbook.createSheet("所有进货记录");
        //三.创建行，添加表头
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        //设置格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("进货记录表");
        cell1.setCellStyle(style);
        HSSFRow row1 = sheet.createRow(1);
        //四.创建单元格
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("进货id");
        cell.setCellStyle(style);

        cell = row1.createCell(1);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row1.createCell(3);
        cell.setCellValue("进货时间");
        cell.setCellStyle(style);

        cell = row1.createCell(4);
        cell.setCellValue("审批人");
        cell.setCellStyle(style);

        cell = row1.createCell(5);
        cell.setCellValue("进货数量");
        cell.setCellStyle(style);

        cell = row1.createCell(6);
        cell.setCellValue("进货厂家");
        cell.setCellStyle(style);

        //五.插入数据
        for (int i=0;i<purchases.size();i++){
            row = sheet.createRow(i + 2);
            HSSFCell cell2=row.createCell(0);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getPurchaseId());
            cell2=row.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getGoodsId());
            cell2=row.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getGoodsName());
            cell2=row.createCell(3);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getPassTime());
            cell2=row.createCell(4);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getUserName());
            cell2=row.createCell(5);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getQuantity());
            cell2=row.createCell(6);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getSupplierName());
        }
        try{
            File file = new File("D:/Desktop/进货记录表.xls");
            if(!file.exists()){
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/进货记录表.xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }else {
                String id = snowflakeUtil.snowflakeId();
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/进货记录表"+id+".xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("文件生成成功");
            return new CommonResult(200,"输出成功");
        }
    }

    @Override
    public CommonResult createExcel(String date) {
        List<Purchase> purchases = purchaseDao.getByDateExcel(date);
        if(purchases.isEmpty()){
            return new CommonResult(444,"暂时未有进货单");
        }else {
            for (Purchase purchase : purchases) {
                Goods goods = goodsDao.selectById(purchase.getGoodsId());
                Supplier supplier = supplierDao.selectById(purchase.getSupplierId());
                User user = userDao.selectById(purchase.getUserId());
                purchase.setGoodsName(goods.getGoodsName());
                purchase.setSupplierName(supplier.getSupplierName());
                purchase.setUserName(user.getUserName());
                purchase.setAddress(supplier.getAddress());
            }
        }
        //一.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //二.创建sheet
        HSSFSheet sheet = workbook.createSheet(date+"进货记录");
        //三.创建行，添加表头
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        //设置格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("进货记录表");
        cell1.setCellStyle(style);
        HSSFRow row1 = sheet.createRow(1);
        //四.创建单元格
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("进货id");
        cell.setCellStyle(style);

        cell = row1.createCell(1);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row1.createCell(3);
        cell.setCellValue("进货时间");
        cell.setCellStyle(style);

        cell = row1.createCell(4);
        cell.setCellValue("审批人");
        cell.setCellStyle(style);

        cell = row1.createCell(5);
        cell.setCellValue("进货数量");
        cell.setCellStyle(style);

        cell = row1.createCell(6);
        cell.setCellValue("进货厂家");
        cell.setCellStyle(style);

        //五.插入数据
        for (int i=0;i<purchases.size();i++){
            row = sheet.createRow(i + 2);
            HSSFCell cell2=row.createCell(0);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getPurchaseId());
            cell2=row.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getGoodsId());
            cell2=row.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getGoodsName());
            cell2=row.createCell(3);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getPassTime());
            cell2=row.createCell(4);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getUserName());
            cell2=row.createCell(5);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getQuantity());
            cell2=row.createCell(6);
            cell2.setCellStyle(style);
            cell2.setCellValue(purchases.get(i).getSupplierName());
        }
        try{
            File file = new File("D:/Desktop/"+date+"进货记录表.xls");
            if(!file.exists()){
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"进货记录表.xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }else {
                String id = snowflakeUtil.snowflakeId();
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"进货记录表"+id+".xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("文件生成成功");
            return new CommonResult(200,"输出成功");
        }
    }

}
