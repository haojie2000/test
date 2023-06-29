package com.ncu.edu.pojo;

public class Fund {
    private String saleId;
    private String date;
    private Float total;
    private String typeId;
    private String typeName;
    private String goodsName;

    public Fund(String saleId, String date, Float total, String typeId, String typeName, String goodsName) {
        this.saleId = saleId;
        this.date = date;
        this.total = total;
        this.typeId = typeId;
        this.typeName = typeName;
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "saleId='" + saleId + '\'' +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Fund(String saleId, String date, Float total, String typeName, String goodsName) {
        this.saleId = saleId;
        this.date = date;
        this.total = total;
        this.typeName = typeName;
        this.goodsName = goodsName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Fund(String saleId, String date, Float total) {
        this.saleId = saleId;
        this.date = date;
        this.total = total;
    }

    public Fund() {
    }
}
