package com.ncu.edu.pojo;

public class Sale {
    private String saleId;
    private String goodsId;
    private String goodsName;
    private Integer quantity;
    private Float price;
    private Float subtotal;
    private String date;
    private String profit;//利润

    @Override
    public String toString() {
        return "Sale{" +
                "saleId='" + saleId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                ", date='" + date + '\'' +
                ", profit='" + profit + '\'' +
                '}';
    }

    public Sale(String saleId, String goodsId, String goodsName, Integer quantity, Float price, Float subtotal, String date, String profit) {
        this.saleId = saleId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.date = date;
        this.profit = profit;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Sale(String saleId, String goodsId, String goodsName, Integer quantity, Float price, Float subtotal, String date) {
        this.saleId = saleId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Sale(String saleId, String goodsId, String goodsName, Integer quantity, Float price, Float subtotal) {
        this.saleId = saleId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public Sale() {
    }
}
