package com.ncu.edu.pojo;


public class PurchaseApplication {
    private String purchaseApplicationId;
    private String goodsId;
    private Integer quantity;
    private String supplierId;
    private String applicationDate;
    private String userId;
    private String remark;
    private String goodsName;
    private String supplierName;
    private String userName;

    @Override
    public String toString() {
        return "PurchaseApplication{" +
                "purchaseApplicationId='" + purchaseApplicationId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", quantity=" + quantity +
                ", supplierId='" + supplierId + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", userId='" + userId + '\'' +
                ", remark='" + remark + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PurchaseApplication(String purchaseApplicationId, String goodsId, Integer quantity, String supplierId, String applicationDate, String userId, String remark, String goodsName, String supplierName, String userName) {
        this.purchaseApplicationId = purchaseApplicationId;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.applicationDate = applicationDate;
        this.userId = userId;
        this.remark = remark;
        this.goodsName = goodsName;
        this.supplierName = supplierName;
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public PurchaseApplication(String purchaseApplicationId, String goodsId, Integer quantity, String supplierId, String applicationDate, String userId, String remark, String goodsName, String supplierName) {
        this.purchaseApplicationId = purchaseApplicationId;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.applicationDate = applicationDate;
        this.userId = userId;
        this.remark = remark;
        this.goodsName = goodsName;
        this.supplierName = supplierName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    private static Integer state = 0;

    public PurchaseApplication() {
    }

    public String getPurchaseApplicationId() {
        return purchaseApplicationId;
    }

    public void setPurchaseApplicationId(String purchaseApplicationId) {
        this.purchaseApplicationId = purchaseApplicationId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static Integer getState() {
        return state;
    }

    public static void setState(Integer state) {
        PurchaseApplication.state = state;
    }

    public PurchaseApplication(String purchaseApplicationId, String goodsId, Integer quantity, String supplierId, String applicationDate, String userId, String remark) {
        this.purchaseApplicationId = purchaseApplicationId;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.applicationDate = applicationDate;
        this.userId = userId;
        this.remark = remark;
    }
}
