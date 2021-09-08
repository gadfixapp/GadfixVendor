package com.app.gadfixvendor.Network;

import android.content.Context;

import com.app.gadfixvendor.Interface.ApiService;
import com.app.gadfixvendor.Listener.AppListener;
import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordRequest;
import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordResponse;
import com.app.gadfixvendor.Models.LoginModel.LoginRequest;
import com.app.gadfixvendor.Models.LoginModel.LoginResponse;
import com.app.gadfixvendor.Models.OtpModel.OtpRequest;
import com.app.gadfixvendor.Models.OtpModel.OtpResponse;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationRequest;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationResponse;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsRequest;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsResponse;
import com.app.gadfixvendor.Preference.SharedPreferenceConfig;
import com.app.gadfixvendor.Preference.UserSharedpreference;
import com.app.gadfixvendor.Utils.OtherConfig;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GadfixApiController {
    private Context context;
    private ApiService apiService;
    private UserSharedpreference userSharedpreference;

    public GadfixApiController(Context context) {
        this.context = context;
        apiService = RetrofitClient.getInterface();
        userSharedpreference = UserSharedpreference.getInstance(context);
    }

    public void getRegistration(RegistrationRequest registrationRequest, final AppListener.OnUserRegisterListener onUserRegisterListener){
        apiService.getRegistration(registrationRequest)
                .enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().getResponseCode() == 200){
                                onUserRegisterListener.onSuccess(response.body());
                            }else if (response.body().getResponseCode()==100){
                                onUserRegisterListener.onFailure(response.body().getMessage());
                            }else if (response.body().getResponseCode() == 401){
                                onUserRegisterListener.onFailure(response.body().getMessage());
                            }

                        }else {
                            onUserRegisterListener.onFailure(OtherConfig.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    onUserRegisterListener.onFailure(t.getMessage());
                    }
                });
    }

    public void getLogin(LoginRequest loginRequest, final AppListener.OnUserLoginListener onUserLoginListener){
        apiService.getLogin(loginRequest)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().getResponseCode() == 200){
                                onUserLoginListener.onSuccess(response.body());
                            }else if (response.body().getResponseCode() == 100){
                                onUserLoginListener.onFailure(response.body().getMessage());
                            }else if (response.body().getResponseCode() == 201){
                                onUserLoginListener.onSuccess(response.body());
                            }
                        }else {
                           onUserLoginListener.onFailure(OtherConfig.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                       onUserLoginListener.onFailure(t.getMessage());
                    }
                });
    }

    public void getOtpVerify(OtpRequest otpRequest, final AppListener.OnUserOtpListener onUserOtpListener){
        apiService.getOtpVerify(otpRequest)
                .enqueue(new Callback<OtpResponse>() {
                    @Override
                    public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                        if (response.isSuccessful()){
                          if (response.body().getResponseCode() == 200){
                              onUserOtpListener.onSuccess(response.body());
                          }else {
                              onUserOtpListener.onFailure(response.body().getMessage());
                          }
                        }else {
                            onUserOtpListener.onFailure(OtherConfig.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponse> call, Throwable t) {
                     onUserOtpListener.onFailure(t.getMessage());
                    }
                });
    }

    public void getChangePassword(ChangePasswordRequest changePasswordRequest, final AppListener.OnUserChangePasswordListener onUserChangePasswordListener){
        apiService.getChangePassword(changePasswordRequest)
                .enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().getResponseCode() == 200){
                                onUserChangePasswordListener.onSuccess(response.body());
                            }else if (response.body().getResponseCode() == 201){
                                onUserChangePasswordListener.onSuccess(response.body());
                            }else {
                                onUserChangePasswordListener.onFailure(response.body().getMessage());
                            }
                        }else {
                            onUserChangePasswordListener.onFailure(OtherConfig.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                    onUserChangePasswordListener.onFailure(t.getMessage());
                    }
                });
    }

    public void getUserDetails(UserDetailsRequest userDetailsRequest, final AppListener.OnUserDetailsListener onUserDetailsListener){
        MultipartBody.Part imageFile = getFileUserPicUpload(userDetailsRequest.getUserPic(), "vendor_img");
        MultipartBody.Part imageFile1 = getFileUserShopPicUpload(userDetailsRequest.getUserShopPic(), "shop_img");
        MultipartBody.Part imageFile2 = getFileUserFontAadharPicUpload(userDetailsRequest.getUserFontAadhar(), "adhar_imagfront");
        MultipartBody.Part imageFile3 = getFileUserBackAadharPicUpload(userDetailsRequest.getUserBackAadhar(), "adhar_imagback");
        MultipartBody.Part imageFile4 = getFileUserFontPanPicUpload(userDetailsRequest.getUserFontPan(), "pan_imgfront");
        MultipartBody.Part imageFile5 = getFileUserBackPanPicUpload(userDetailsRequest.getUserBackPan(), "pan_imgback");
        RequestBody shopName = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getShopName());
        RequestBody licenceNo = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getLicenseNumber());
        RequestBody since = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getSince());
        RequestBody registrationNo = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getRegistrationNo());
        RequestBody gstNo = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getGstNo());
        RequestBody address = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getAddress());
        RequestBody pinCode = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getPinCode());
        RequestBody landmark = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getLandmark());
        RequestBody timing = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getTiming());
        RequestBody noOfEmployee = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getNoOfEmployee());
        RequestBody aadharNo = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getAadharNo());
        RequestBody panNo = RequestBody.create(okhttp3.MultipartBody.FORM, userDetailsRequest.getPanNo());
        apiService.getUserDetails(shopName,licenceNo,since,registrationNo,gstNo,address,pinCode,landmark,timing,noOfEmployee,aadharNo,panNo,imageFile,imageFile1,imageFile2,imageFile3,imageFile4,imageFile5)
                .enqueue(new Callback<UserDetailsResponse>() {
                    @Override
                    public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body().getResponseCode() == 200){
                                userSharedpreference.saveBooleanData(SharedPreferenceConfig.IS_USER_LOGIN, true);
                                onUserDetailsListener.onSuccess(response.body());
                            }else {
                                onUserDetailsListener.onFailure(response.body().getMessage());
                            }
                        }else {
                            onUserDetailsListener.onFailure(OtherConfig.ERROR_MESSAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                      onUserDetailsListener.onFailure(t.getMessage());
                    }
                });

    }

    private MultipartBody.Part getFileUserBackPanPicUpload(File userBackPan, String pan_imgback) {
        if (userBackPan != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userBackPan);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(pan_imgback, userBackPan.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }

    private MultipartBody.Part getFileUserFontPanPicUpload(File userFontPan, String pan_imgfront) {
        if (userFontPan != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userFontPan);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(pan_imgfront, userFontPan.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }

    private MultipartBody.Part getFileUserBackAadharPicUpload(File userBackAadhar, String adhar_imagback) {
        if (userBackAadhar != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userBackAadhar);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(adhar_imagback, userBackAadhar.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }

    private MultipartBody.Part getFileUserFontAadharPicUpload(File userFontAadhar, String adhar_imagfront) {
        if (userFontAadhar != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userFontAadhar);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(adhar_imagfront, userFontAadhar.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }

    private MultipartBody.Part getFileUserShopPicUpload(File userShopPic, String shop_img) {
        if (userShopPic != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userShopPic);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(shop_img, userShopPic.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }

    private MultipartBody.Part getFileUserPicUpload(File userPic, String vendor_img) {
        if (userPic != null) {
            RequestBody mFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), userPic);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData(vendor_img, userPic.getName(), mFile1);
            return fileToUpload;
        }
        return null;
    }
}
