package com.ncu.edu.pojo;

public class Parameter {
    private String date;
    private String typePId;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public Parameter(String date, String typePId, String typeName) {
        this.date = date;
        this.typePId = typePId;
        this.typeName = typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "date='" + date + '\'' +
                ", typePId='" + typePId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Parameter() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypePId() {
        return typePId;
    }

    public void setTypePId(String typePId) {
        this.typePId = typePId;
    }

    public Parameter(String date, String typePId) {
        this.date = date;
        this.typePId = typePId;
    }
}
