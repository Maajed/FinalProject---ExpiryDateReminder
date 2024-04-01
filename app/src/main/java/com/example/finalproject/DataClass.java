package com.example.finalproject;

public class DataClass {
    private String dataId;
    private String dataProduct;
    private String dataDesc;
    private String dataImage;
    private String expiryDate;

    public String getDataId() {
        return dataId;
    }

    public String getDataProduct() {
        return dataProduct;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataImage() {
        return dataImage;
    }

    @Override
    public String toString() {
        return "DataClass{" +
                "dataDesc='" + dataDesc + '\'' +
                ", dataId='" +  dataId + '\'' +
                ", dataImage='" + dataImage + '\'' +
                ", dataProduct='" + dataProduct + '\'' +
               ", expiryDate='" + expiryDate + '\'' +
                '}';
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public DataClass(String dataId, String dataProduct, String dataDesc, String dataImage, String expiryDate) {
        this.dataDesc = dataDesc;
        this.dataId = dataId;
        this.dataImage = dataImage;
        this.dataProduct = dataProduct;
        this.expiryDate = expiryDate;
    }
}
