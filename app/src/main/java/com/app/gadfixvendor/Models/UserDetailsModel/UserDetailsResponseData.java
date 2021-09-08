package com.app.gadfixvendor.Models.UserDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponseData {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("kyc_status")
    @Expose
    private String kycStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

}
