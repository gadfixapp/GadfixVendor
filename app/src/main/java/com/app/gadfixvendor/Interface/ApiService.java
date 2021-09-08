package com.app.gadfixvendor.Interface;

import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordRequest;
import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordResponse;
import com.app.gadfixvendor.Models.LoginModel.LoginRequest;
import com.app.gadfixvendor.Models.LoginModel.LoginResponse;
import com.app.gadfixvendor.Models.OtpModel.OtpRequest;
import com.app.gadfixvendor.Models.OtpModel.OtpResponse;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationRequest;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationResponse;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsResponse;
import com.app.gadfixvendor.Utils.OtherConfig;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @POST("registration")
    Call<RegistrationResponse> getRegistration(@Body RegistrationRequest registrationRequest);

    @POST("login")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    @POST("verify_otp")
    Call<OtpResponse> getOtpVerify(@Body OtpRequest otpRequest);

    @POST("change_password")
    Call<ChangePasswordResponse> getChangePassword(@Body ChangePasswordRequest changePasswordRequest);

    @Multipart
    @POST("userdetails")
    Call<UserDetailsResponse> getUserDetails(@Part("shop_name") RequestBody shopName,
                                             @Part("licence_num") RequestBody licenceNo,
                                             @Part("since") RequestBody since,
                                             @Part("ragiter_num") RequestBody registrationNo,
                                             @Part("gst_num") RequestBody gstNo,
                                             @Part("address") RequestBody address,
                                             @Part("pincode") RequestBody pinCode,
                                             @Part("landmark") RequestBody landmark,
                                             @Part("timing") RequestBody timing,
                                             @Part("total_emp") RequestBody totalEmp,
                                             @Part("adhar_num") RequestBody aadharNo,
                                             @Part("pan_num") RequestBody panNo,
                                             @Part MultipartBody.Part imageFile,
                                             @Part MultipartBody.Part imageFile1,
                                             @Part MultipartBody.Part imageFile2,
                                             @Part MultipartBody.Part imageFile3,
                                             @Part MultipartBody.Part imageFile4,
                                             @Part MultipartBody.Part imageFile5);


}
