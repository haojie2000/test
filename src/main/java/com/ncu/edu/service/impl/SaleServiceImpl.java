package com.ncu.edu.service.impl;

import com.ncu.edu.dao.GoodsDao;
import com.ncu.edu.dao.SaleDao;
import com.ncu.edu.dao.TypeDao;
import com.ncu.edu.pojo.*;
import com.ncu.edu.service.SaleService;
import com.ncu.edu.util.SnowflakeUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private TypeDao typeDao;
    private SnowflakeUtil snowflakeUtil = new SnowflakeUtil();

    /**
     * 商品订单结算时将信息添加至销售情况表中
     *
     * @param sales
     * @return
     */
    @Override
    public CommonResult checkOut(List<Sale> sales) {
        String saleId = snowflakeUtil.snowflakeId();
        for (Sale sale : sales) {
            sale.setSaleId(saleId);
            int i = saleDao.add(sale);
            final int res = goodsDao.deleteStock(sale.getQuantity(), sale.getGoodsId());
        }
        return new CommonResult<String>(200, "添加销售订单和删减库存成功！", saleId);
    }

    @Override
    public CommonResult addFund(Fund fund) {
        System.out.println("test0004" + fund);
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date nowDate = new Date();// 获取当前时间
        String date = sdf.format(nowDate);
        fund.setDate(date);
        System.out.println("test0005" + fund);
        int i = saleDao.addFund(fund);
        if (i > 0) {
            return new CommonResult(200, "销售记录添加成功");
        } else {
            return new CommonResult(444, "添加销售记录失败");
        }
    }

    /**
     * 1.根据夫分类id找到子分类id和name
     * 根据子分类id和时间段找出每个子分类该时间段的销售金额
     * 2.根据子分类id找到goodsId集合
     * 根据选择的时间段和遍历集合goodsId从tb_sale中找到符合的goodsId，goodsName和销售金额
     *
     * @param param
     * @return
     */
    @Override
    public CommonResult OtherInit(Parameter param) {
        List<GoodsType> goodsTypeList = saleDao.getByTypePId(param.getTypePId());
        List<Fund> list = new ArrayList<Fund>();
        for (GoodsType goodsType : goodsTypeList) {
            Fund fund = saleDao.getFund(param.getDate(), goodsType.getTypeId());
            if (fund != null) {
                fund.setTypeName(goodsType.getTypeName());
                fund.setTypeId(goodsType.getTypeId());
                list.add(fund);
            }
        }
        if (list.isEmpty()) {
            return new CommonResult(444, "目前还未有订单");
        } else {
            return new CommonResult(200, "成功", list);
        }

    }

    @Override
    public CommonResult getFund(String date, String typeName) {
        final String typeId = typeDao.getTypeIdByName(typeName);
        System.out.println("test1111" + typeId);
        final List<Goods> goodsList = goodsDao.getGoodsByTypeId(typeId);
        List<Fund> list = new ArrayList<Fund>();
        System.out.println("test-----" + goodsList);
        for (Goods goods : goodsList) {
            System.out.println("test1111" + goods);
            Fund funds = saleDao.getGoodsFund(goods.getGoodsId(), date);
            if (funds != null) {
                list.add(funds);
            }
        }
        System.out.println(list);
        return new CommonResult(200, list);
    }

    @Override
    public CommonResult createExcel() {
        List<Sale> saleList = saleDao.getALLSale();
        //一.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //二.创建sheet
        HSSFSheet sheet = workbook.createSheet("所有销售记录");
        //三.创建行，添加表头
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        //设置格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("销售情况表");
        cell1.setCellStyle(style);
        HSSFRow row1 = sheet.createRow(1);
        //四.创建单元格
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("销售id");
        cell.setCellStyle(style);

        cell = row1.createCell(1);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row1.createCell(3);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row1.createCell(4);
        cell.setCellValue("商品售价");
        cell.setCellStyle(style);

        cell = row1.createCell(5);
        cell.setCellValue("售卖数量");
        cell.setCellStyle(style);

        cell = row1.createCell(6);
        cell.setCellValue("净利润");
        cell.setCellStyle(style);

        //五.插入数据
        for (int i=0;i<saleList.size();i++){
            row = sheet.createRow(i + 2);
            HSSFCell cell2=row.createCell(0);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getSaleId());
            cell2=row.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getGoodsId());
            cell2=row.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getGoodsName());
            cell2=row.createCell(3);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getDate());
            cell2=row.createCell(4);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getPrice());
            cell2=row.createCell(5);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getQuantity());
            cell2=row.createCell(6);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getProfit());
        }
        try{
            File file = new File("D:/Desktop/销售情况表.xls");
            if(!file.exists()){
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/销售情况表.xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }else {
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/销售情况表(1).xls");
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
        List<Sale> saleList = saleDao.getSaleByDate(date);
        //一.创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //二.创建sheet
        HSSFSheet sheet = workbook.createSheet(date+"的销售记录");
        //三.创建行，添加表头
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow row = sheet.createRow(0);
        //设置格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("销售情况表");
        cell1.setCellStyle(style);
        HSSFRow row1 = sheet.createRow(1);
        //四.创建单元格
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("销售id");
        cell.setCellStyle(style);

        cell = row1.createCell(1);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row1.createCell(2);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row1.createCell(3);
        cell.setCellValue("时间");
        cell.setCellStyle(style);

        cell = row1.createCell(4);
        cell.setCellValue("商品售价");
        cell.setCellStyle(style);

        cell = row1.createCell(5);
        cell.setCellValue("售卖数量");
        cell.setCellStyle(style);

        cell = row1.createCell(6);
        cell.setCellValue("净利润");
        cell.setCellStyle(style);

        //五.插入数据
        for (int i=0;i<saleList.size();i++){
            row = sheet.createRow(i + 2);
            HSSFCell cell2=row.createCell(0);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getSaleId());
            cell2=row.createCell(1);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getGoodsId());
            cell2=row.createCell(2);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getGoodsName());
            cell2=row.createCell(3);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getDate());
            cell2=row.createCell(4);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getPrice());
            cell2=row.createCell(5);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getQuantity());
            cell2=row.createCell(6);
            cell2.setCellStyle(style);
            cell2.setCellValue(saleList.get(i).getProfit());
        }
        try{
            File file = new File("D:/Desktop/"+date+"销售情况表.xls");
            if(!file.exists()){
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"销售情况表.xls");
                workbook.write(fileOutputStream);
                fileOutputStream.close();
            }else {
                FileOutputStream fileOutputStream = new FileOutputStream("D:/Desktop/"+date+"销售情况表(1).xls");
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
