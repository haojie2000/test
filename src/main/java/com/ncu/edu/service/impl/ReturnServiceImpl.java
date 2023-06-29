package com.ncu.edu.service.impl;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.dao.ReturnDao;
import com.ncu.edu.dao.SupplierDao;
import com.ncu.edu.dao.UserDao;
import com.ncu.edu.pojo.*;
import com.ncu.edu.service.ReturnService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private ReturnDao returnDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private UserDao userDao;
    private SnowflakeUtil snowflakeUtil = new SnowflakeUtil();

    @Override
    public int add(Return data) {
        final int i = returnDao.save(data);
        return i;
    }

    @Override
    public List<Return> getWithPage(Integer size, Integer offset) {
        List<Return> list = returnDao.Limit(size, offset);
        if (!list.isEmpty()) {
            for (Return ob : list) {
                Goods goods = goodsDao.selectById(ob.getGoodsId());
                ob.setGoodsName(goods.getGoodsName());
                Supplier supplier = supplierDao.selectById(ob.getSupplierId());
                ob.setSupplierName(supplier.getSupplierName());
                ob.setAddress(supplier.getAddress());
                User user = userDao.selectById(ob.getUserId());
                ob.setUserName(user.getUserName());
            }
        }
        return list;
    }

    @Override
    public int getCount() {
        final int count = returnDao.getCount();
        return count;
    }

    @Override
    public CommonResult createExcel() {
        List<Return> returnList = returnDao.selectAll();
        if (returnList.isEmpty()) {
            return new CommonResult(444, "暂时没有退货订单");
        } else {
            for (Return ob : returnList) {
                Goods goods = goodsDao.selectById(ob.getGoodsId());
                ob.setGoodsName(goods.getGoodsName());
                Supplier supplier = supplierDao.selectById(ob.getSupplierId());
                ob.setSupplierName(supplier.getSupplierName());
                ob.setAddress(supplier.getAddress());
                User user = userDao.selectById(ob.getUserId());
                ob.setUserName(user.getUserName());
            }
        }
        //一.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //二.创建sheet
        HSSFSheet sheet = workbook.createSheet("所有退货记录");
        //三.创建行，添加表头
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        //设置格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("退货记录表");
        cell1.setCellStyle(style);
        HSSFRow row1 = sheet.createRow(1);
        //四.创建单元格
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("退货id");
        cell.setCellStyle(style);

        cell = row1.createCell(1);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row1.createCell(3);
        cell.setCellValue("退货时间");
        cell.setCellStyle(style);

        cell = row1.createCell(4);
        cell.setCellValue("审批人");
        cell.setCellStyle(style);

        cell = row1.createCell(5);
        cell.setCellValue("退货数量");
        cell.setCellStyle(style);

        cell = row1.createCell(6);
        cell.setCellValue("退货厂家");
        cell.setCellStyle(style);

        //五.插入数据
        for (int i=0;i<returnList.size();i++){
            row = sheet.createRow(i + 2);
            HSSFCell cell2=row.createCell(0);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getReturnId());
            cell2=row.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getGoodsId());
            cell2=row.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getGoodsName());
            cell2=row.createCell(3);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getReturnTime());
            cell2=row.createCell(4);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getUserName());
            cell2=row.createCell(5);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getQuantity());
            cell2=row.createCell(6);
            cell2.setCellStyle(style);
            cell2.setCellValue(returnList.get(i).getSupplierName());
        }
        try{
            File file = new File("D:/Desktop/退货记录表.xls");
            if(!file.exists()){
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/退货记录表.xls");
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
        List<Return> returnList = returnDao.getByDate(date);
        if (returnList.isEmpty()) {
            return new CommonResult(444, "暂时没有退货订单");
        } else {
            for (Return ob : returnList) {
                Goods goods = goodsDao.selectById(ob.getGoodsId());
                ob.setGoodsName(goods.getGoodsName());
                Supplier supplier = supplierDao.selectById(ob.getSupplierId());
                ob.setSupplierName(supplier.getSupplierName());
                ob.setAddress(supplier.getAddress());
                User user = userDao.selectById(ob.getUserId());
                ob.setUserName(user.getUserName());
            }
            //一.创建workbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //二.创建sheet
            HSSFSheet sheet = workbook.createSheet(date+"退货记录");
            //三.创建行，添加表头
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            HSSFRow row = sheet.createRow(0);
            //设置格式
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue("退货记录表");
            cell1.setCellStyle(style);
            HSSFRow row1 = sheet.createRow(1);
            //四.创建单元格
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue("退货id");
            cell.setCellStyle(style);

            cell = row1.createCell(1);
            cell.setCellValue("商品id");
            cell.setCellStyle(style);

            cell = row1.createCell(2);
            cell.setCellValue("商品名称");
            cell.setCellStyle(style);

            cell = row1.createCell(3);
            cell.setCellValue("退货时间");
            cell.setCellStyle(style);

            cell = row1.createCell(4);
            cell.setCellValue("审批人");
            cell.setCellStyle(style);

            cell = row1.createCell(5);
            cell.setCellValue("退货数量");
            cell.setCellStyle(style);

            cell = row1.createCell(6);
            cell.setCellValue("退货厂家");
            cell.setCellStyle(style);

            //五.插入数据
            for (int i=0;i<returnList.size();i++){
                row = sheet.createRow(i + 2);
                HSSFCell cell2=row.createCell(0);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getReturnId());
                cell2=row.createCell(1);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getGoodsId());
                cell2=row.createCell(2);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getGoodsName());
                cell2=row.createCell(3);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getReturnTime());
                cell2=row.createCell(4);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getUserName());
                cell2=row.createCell(5);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getQuantity());
                cell2=row.createCell(6);
                cell2.setCellStyle(style);
                cell2.setCellValue(returnList.get(i).getSupplierName());
            }
            try{
                File file = new File("D:/Desktop/"+date+"退货记录表.xls");
                if(!file.exists()){
                    FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"退货记录表.xls");
                    workbook.write(fileOutputStream);
                    fileOutputStream.close();
                }else {
                    String id = snowflakeUtil.snowflakeId();
                    FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"退货记录表"+id+".xls");
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
}
