package com.ncu.edu.pojo;


public class Return {
    private String returnId;
    private String supplierId;
    private String supplierName;
    private String address;
    private String goodsId;
    private String goodsName;
    private Integer quantity;
    private String returnTime;
    private String userId;
    private String userName;

    public Return(String returnId, String supplierId, String supplierName, String address, String goodsId, String goodsName, Integer quantity, String returnTime, String userId, String userName) {
        this.returnId = returnId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.address = address;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.returnTime = returnTime;
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Return{" +
                "returnId='" + returnId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", quantity=" + quantity +
                ", returnTime='" + returnTime + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Return() {
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Return(String returnId, String supplierId, String goodsId, String goodsName, Integer quantity, String returnTime, String userId) {
        this.returnId = returnId;
        this.supplierId = supplierId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.returnTime = returnTime;
        this.userId = userId;
    }
}
