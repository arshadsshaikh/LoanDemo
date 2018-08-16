package com.example.arshadshaikh.loandemo.login;

import android.content.Context;

import com.android.volley.Response;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public interface ILoginContract {

    interface ILoginViewLoader{


        void showDialog();
        void dismissDialog();
        void setLoginResponse(LoginResponse loginResponse);

        void setLoginError();

    }



    interface ILoginPresenterLoader{


        void userLogin(String url, String request,Context context);

        Response.Listener<LoginResponse>loginSuccessListener(Context context);
        Response.ErrorListener loginError(Context context);





    }



}
