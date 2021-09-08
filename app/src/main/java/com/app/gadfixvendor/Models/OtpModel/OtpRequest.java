package com.app.gadfixvendor.Models.OtpModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpRequest {
    @SerializedName("mno")
    @Expose
    private String mno;
    @SerializedName("otp")
    @Expose
    private String otp;

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
