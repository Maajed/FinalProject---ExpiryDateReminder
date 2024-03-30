package com.example.finalproject;

public class DataClass {
    private String dataId;
    private String dataProduct;
    private String dataDesc;
    private String dataLang;
    private String dataImage;

    public String getDataId() {
        return dataId;
    }

    public String getDataProduct() {
        return dataProduct;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataId, String dataProduct, String dataDesc, String dataLang, String dataImage) {
        this.dataId = dataId;
        this.dataProduct = dataProduct;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }
}
