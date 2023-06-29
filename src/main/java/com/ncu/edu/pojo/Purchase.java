package com.ncu.edu.pojo;


public class Purchase {
    private String purchaseId;
    private String goodsId;
    private String supplierId;
    private Integer quantity;
    private String passTime;
    private String userId;
    private String goodsName;
    private String supplierName;
    private Integer count;
    private String address;
    private String userName;

    public Purchase(String purchaseId, String goodsId, String supplierId, Integer quantity, String passTime, String userId, String goodsName, String supplierName, Integer count, String address, String userName) {
        this.purchaseId = purchaseId;
        this.goodsId = goodsId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.passTime = passTime;
        this.userId = userId;
        this.goodsName = goodsName;
        this.supplierName = supplierName;
        this.count = count;
        this.address = address;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId='" + purchaseId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", quantity=" + quantity +
                ", passTime='" + passTime + '\'' +
                ", userId='" + userId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", count=" + count +
                ", address='" + address + '\'' +
                ", userName='" + userName + '\'' +
                '}';
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Purchase(String purchaseId, String goodsId, String supplierId, Integer quantity, String passTime, String userId, String goodsName, String supplierName, Integer count) {
        this.purchaseId = purchaseId;
        this.goodsId = goodsId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.passTime = passTime;
        this.userId = userId;
        this.goodsName = goodsName;
        this.supplierName = supplierName;
        this.count = count;
    }

    private static Integer state =0;

    public String getGoodsName() {
        return goodsName;
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

    public Purchase(String purchaseId, String goodsId, String supplierId, Integer quantity, String passTime, String userId, String goodsName, String supplierName) {
        this.purchaseId = purchaseId;
        this.goodsId = goodsId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.passTime = passTime;
        this.userId = userId;
        this.goodsName = goodsName;
        this.supplierName = supplierName;
    }

    public Purchase() {
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
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
        Purchase.state = state;
    }

    public Purchase(String purchaseId, String goodsId, String supplierId, Integer quantity, String passTime, String userId) {
        this.purchaseId = purchaseId;
        this.goodsId = goodsId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.passTime = passTime;
        this.userId = userId;
    }
}
