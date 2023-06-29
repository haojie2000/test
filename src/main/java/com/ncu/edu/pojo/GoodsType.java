package com.ncu.edu.pojo;

public class GoodsType {
    private String typeId;
    private String typeName;
    private String typePId;
    private String typePName;

    public GoodsType() {
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typePId='" + typePId + '\'' +
                ", typePName='" + typePName + '\'' +
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

    public String getTypePId() {
        return typePId;
    }

    public void setTypePId(String typePId) {
        this.typePId = typePId;
    }

    public String getTypePName() {
        return typePName;
    }

    public void setTypePName(String typePName) {
        this.typePName = typePName;
    }

    public GoodsType(String typeId, String typeName, String typePId, String typePName) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typePId = typePId;
        this.typePName = typePName;
    }
}
