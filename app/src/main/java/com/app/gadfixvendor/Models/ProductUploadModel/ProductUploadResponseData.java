package com.app.gadfixvendor.Models.ProductUploadModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductUploadResponseData implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("mrp_price")
    @Expose
    private String mrpPrice;
    @SerializedName("selling_price")
    @Expose
    private String sellingPrice;
    @SerializedName("product_img")
    @Expose
    private String productImg;
    @SerializedName("product_status")
    @Expose
    private String productStatus;

    protected ProductUploadResponseData(Parcel in) {
        id = in.readString();
        userId = in.readString();
        productName = in.readString();
        type = in.readString();
        mrpPrice = in.readString();
        sellingPrice = in.readString();
        productImg = in.readString();
        productStatus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(productName);
        dest.writeString(type);
        dest.writeString(mrpPrice);
        dest.writeString(sellingPrice);
        dest.writeString(productImg);
        dest.writeString(productStatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductUploadResponseData> CREATOR = new Creator<ProductUploadResponseData>() {
        @Override
        public ProductUploadResponseData createFromParcel(Parcel in) {
            return new ProductUploadResponseData(in);
        }

        @Override
        public ProductUploadResponseData[] newArray(int size) {
            return new ProductUploadResponseData[size];
        }
    };

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    @Override
    public String toString() {
        return "ProductUploadResponseData{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                ", mrpPrice='" + mrpPrice + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productStatus='" + productStatus + '\'' +
                '}';
    }
}
