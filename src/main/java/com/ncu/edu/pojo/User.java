package com.ncu.edu.pojo;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String trueName;
    private String address;
    private String roleId;
    private String roleName;

    public User(String userId, String userName, String password, String trueName, String address, String roleId, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.trueName = trueName;
        this.address = address;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", address='" + address + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public User(String userId, String userName, String password, String trueName, String address, String roleId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.trueName = trueName;
        this.address = address;
        this.roleId = roleId;
    }
}
