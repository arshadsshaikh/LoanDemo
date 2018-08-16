package com.example.arshadshaikh.loandemo.login;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.arshadshaikh.loandemo.managers.APIManager;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenterLoader {


    private ILoginContract.ILoginViewLoader viewLoader;

    public LoginPresenter(ILoginContract.ILoginViewLoader loader) {


        this.viewLoader=loader;

    }

    @Override
    public void userLogin(String url,String request, Context context) {

        new APIManager<LoginResponse>().postLoginAPI(url,request,loginSuccessListener(context),loginError(context),LoginResponse.class,context);


    }

    @Override
    public Response.Listener<LoginResponse> loginSuccessListener(Context context) {
        return new Response.Listener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse loginResponse) {

                viewLoader.dismissDialog();

                if(loginResponse!=null){


                    viewLoader.setLoginResponse(loginResponse);

                }
            }
        };
    }

    @Override
    public Response.ErrorListener loginError(Context context) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                viewLoader.dismissDialog();
                viewLoader.setLoginError();
            }
        };
    }
}
