package com.ncu.edu.pojo;

import java.util.List;

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String tel;
    private String address;
    private String picker;
    private String goods;
    private List<String> goodsId;

    public List<String> getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(List<String> goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoods() {
        return goods;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", picker='" + picker + '\'' +
                ", goods='" + goods + '\'' +
                '}';
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Supplier(String supplierId, String supplierName, String tel, String address, String picker, String goods) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.tel = tel;
        this.address = address;
        this.picker = picker;
        this.goods = goods;
    }

    public Supplier() {
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public Supplier(String supplierId, String supplierName, String tel, String address, String picker) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.tel = tel;
        this.address = address;
        this.picker = picker;
    }
}
