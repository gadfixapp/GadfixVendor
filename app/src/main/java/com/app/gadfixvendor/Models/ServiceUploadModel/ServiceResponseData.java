package com.app.gadfixvendor.Models.ServiceUploadModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceResponseData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("service_img")
    @Expose
    private String serviceImg;
    @SerializedName("mrp_price")
    @Expose
    private String mrpPrice;
    @SerializedName("selling_price")
    @Expose
    private String sellingPrice;
    @SerializedName("offer_percent")
    @Expose
    private String offerPercent;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public String getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(String mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getOfferPercent() {
        return offerPercent;
    }

    public void setOfferPercent(String offerPercent) {
        this.offerPercent = offerPercent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
