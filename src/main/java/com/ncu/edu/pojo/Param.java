package com.ncu.edu.pojo;

public class Param {
    private String Search;
    private String typePId;
    private String oldPassword;
    private String newPassword;

    @Override
    public String toString() {
        return "Param{" +
                "Search='" + Search + '\'' +
                ", typePId='" + typePId + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public Param(String search, String typePId, String oldPassword, String newPassword) {
        Search = search;
        this.typePId = typePId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Param() {
    }

    public String getSearch() {
        return Search;
    }

    public void setSearch(String search) {
        Search = search;
    }

    public String getTypePId() {
        return typePId;
    }

    public void setTypePId(String typePId) {
        this.typePId = typePId;
    }

    public Param(String search, String typePId) {
        Search = search;
        this.typePId = typePId;
    }
}
