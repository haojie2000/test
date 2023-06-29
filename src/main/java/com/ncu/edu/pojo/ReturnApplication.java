package com.ncu.edu.pojo;


import java.util.Date;

public class ReturnApplication {
        private String returnApplicationId;
        private String purchaseId;
        private String goodsId;
        private String goodsName;
        private String supplierId;
        private Integer quantity;
        private String applicationTime;
        private String userId;
        private String supplierName;
        private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    private static Integer state=0;

    public String getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "ReturnApplication{" +
                "returnApplicationId='" + returnApplicationId + '\'' +
                ", purchaseId='" + purchaseId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", quantity=" + quantity +
                ", applicationTime='" + applicationTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public ReturnApplication(String returnApplicationId, String purchaseId, String goodsId, String goodsName, String supplierId, Integer quantity, String applicationTime, String userId) {
        this.returnApplicationId = returnApplicationId;
        this.purchaseId = purchaseId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.applicationTime = applicationTime;
        this.userId = userId;
    }

    public ReturnApplication() {
    }

    public String getReturnApplicationId() {
        return returnApplicationId;
    }

    public void setReturnApplicationId(String returnApplicationId) {
        this.returnApplicationId = returnApplicationId;
    }

    public ReturnApplication(String returnApplicationId, String goodsId, String goodsName, String supplierId, Integer quantity, String applicationTime, String userId) {
        this.returnApplicationId = returnApplicationId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.applicationTime = applicationTime;
        this.userId = userId;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static Integer getState() {
        return state;
    }

    public static void setState(Integer state) {
        ReturnApplication.state = state;
    }



}
