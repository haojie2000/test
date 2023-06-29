package com.ncu.edu.pojo;

import java.util.List;

public class Goods {
    private String goodsId;
    private String goodsName;
    private Float purchasePrice;
    private Float price;
    private String typeId;
    private String typePId;
    private Integer stock;
    private String typeName;
    private String typePName;
    private String suppliers;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", typeId='" + typeId + '\'' +
                ", typePId='" + typePId + '\'' +
                ", stock=" + stock +
                ", typeName='" + typeName + '\'' +
                ", typePName='" + typePName + '\'' +
                ", suppliers=" + suppliers +
                '}';
    }

    public Goods(String goodsId, String goodsName, Float purchasePrice, Float price, String typeId, String typePId, Integer stock, String typeName, String typePName, String suppliers) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.typeId = typeId;
        this.typePId = typePId;
        this.stock = stock;
        this.typeName = typeName;
        this.typePName = typePName;
        this.suppliers = suppliers;
    }

    public String getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }

    public Goods(String goodsId, String goodsName, Float purchasePrice, Float price, String typeId, String typePId, Integer stock, String typeName, String typePName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.typeId = typeId;
        this.typePId = typePId;
        this.stock = stock;
        this.typeName = typeName;
        this.typePName = typePName;
    }

    public String getTypePId() {
        return typePId;
    }

    public void setTypePId(String typePId) {
        this.typePId = typePId;
    }

    public Goods(String goodsId, String goodsName, Float purchasePrice, Float price, String typeId, Integer stock, String typeName, String typePName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.typeId = typeId;
        this.stock = stock;
        this.typeName = typeName;
        this.typePName = typePName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypePName() {
        return typePName;
    }

    public void setTypePName(String typePName) {
        this.typePName = typePName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Goods(String goodsId, String goodsName, Float purchasePrice, Float price, String typeId, Integer stock) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.typeId = typeId;
        this.stock = stock;
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

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Goods(String goodsId, String goodsName, Float purchasePrice, Float price, String typeId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.typeId = typeId;
    }

    public Goods() {
    }
}
