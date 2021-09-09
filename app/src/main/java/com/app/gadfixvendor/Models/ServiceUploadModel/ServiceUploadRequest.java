package com.app.gadfixvendor.Models.ServiceUploadModel;

import java.io.File;

public class ServiceUploadRequest {
    private String serviceType;
    private String serviceMrp;
    private String serviceSellingPrice;
    private String serviceInfo;
    private File serviceImage;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceMrp() {
        return serviceMrp;
    }

    public void setServiceMrp(String serviceMrp) {
        this.serviceMrp = serviceMrp;
    }

    public String getServiceSellingPrice() {
        return serviceSellingPrice;
    }

    public void setServiceSellingPrice(String serviceSellingPrice) {
        this.serviceSellingPrice = serviceSellingPrice;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public File getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(File serviceImage) {
        this.serviceImage = serviceImage;
    }
}
