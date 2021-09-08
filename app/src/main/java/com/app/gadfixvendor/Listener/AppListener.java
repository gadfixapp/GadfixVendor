package com.app.gadfixvendor.Listener;

import com.app.gadfixvendor.Models.ChangePasswordModel.ChangePasswordResponse;
import com.app.gadfixvendor.Models.LoginModel.LoginResponse;
import com.app.gadfixvendor.Models.OtpModel.OtpResponse;
import com.app.gadfixvendor.Models.RegistrationModel.RegistrationResponse;
import com.app.gadfixvendor.Models.UserDetailsModel.UserDetailsResponse;
import com.app.gadfixvendor.Utils.OtherConfig;

public class AppListener {

    public interface OnUserRegisterListener {
        void onSuccess(RegistrationResponse registrationResponse);
        void onFailure(String message);
    }

    public interface OnUserLoginListener{
        void onSuccess(LoginResponse loginResponse);
        void onFailure(String message);
    }

    public interface OnUserOtpListener{
        void onSuccess(OtpResponse otpResponse);
        void onFailure(String message);
    }

    public interface OnUserChangePasswordListener{
        void onSuccess(ChangePasswordResponse changePasswordResponse);
        void onFailure(String message);
    }

    public interface OnUserDetailsListener{
        void onSuccess(UserDetailsResponse userDetailsResponse);
        void onFailure(String message);
    }
}
