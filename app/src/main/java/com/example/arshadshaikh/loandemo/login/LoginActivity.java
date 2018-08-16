package com.example.arshadshaikh.loandemo.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arshadshaikh.loandemo.utilities.Constants;
import com.example.arshadshaikh.loandemo.R;
import com.example.arshadshaikh.loandemo.utilities.Utils;
import com.example.arshadshaikh.loandemo.home.HomeActivity;
import com.example.arshadshaikh.loandemo.utilities.EmailValidator;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  implements ILoginContract.ILoginViewLoader {

    @BindView(R.id.loginButton)Button loginButton;
    @BindView(R.id.usernameEditText)EditText usernameEditText;
    @BindView(R.id.passwordEditText)EditText passwordEditText;

    private Context context;
    private LoginPresenter loginPresenter;
    private Dialog spinningDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=LoginActivity.this;
        ButterKnife.bind(this);
        loginPresenter= new LoginPresenter(this);
        spinningDialog=CustomProgressDialog.showProgressDialog(context);




    }

    @OnClick(R.id.loginButton)
    void login(){

        if(isValid()){

            callToLoginAPI();

        }




    }

    private void callToLoginAPI() {


        LoginRequest loginRequest= new LoginRequest();

        loginRequest.setEmail(usernameEditText.getText().toString());
        loginRequest.setPassword(passwordEditText.getText().toString());

        if(Utils.isInternet(context)){

            showDialog();
            loginPresenter.userLogin(Constants.LOGINURL,new GsonBuilder().serializeNulls().create().toJson(loginRequest),context);


        }
        else {

            Toast.makeText(context, getString(R.string.internet_error_msg), Toast.LENGTH_SHORT).show();

        }







    }

    private boolean isValid(){

        EmailValidator emailValidator = new EmailValidator();

        String userEmail=usernameEditText.getText().toString();
        String userPassword=passwordEditText.getText().toString();


        if (!emailValidator.validate(userEmail)) {

            Toast.makeText(context, getString(R.string.enter_email), Toast.LENGTH_SHORT).show();

            return  false;
        }

        else if(!(userPassword.length()>0)){

            Toast.makeText(context, getString(R.string.signup_enetrpassword), Toast.LENGTH_SHORT).show();
            return  false;
        }

        else if(!(userPassword.length()>=6)){


            Toast.makeText(context, getString(R.string.pass_six_digit), Toast.LENGTH_SHORT).show();
            
            return  false;
        }

        return  true;
    }

    @Override
    public void showDialog() {

        if (spinningDialog != null && !spinningDialog.isShowing()) {
            spinningDialog.show();
        }
    }

    @Override
    public void dismissDialog() {

        if (spinningDialog != null && spinningDialog.isShowing()) {
            spinningDialog.dismiss();
        }
    }

    @Override
    public void setLoginResponse(LoginResponse loginResponse) {

        if(loginResponse.getToken()!=null){

            Toast.makeText(context, getString(R.string.user_logged_in), Toast.LENGTH_SHORT).show();

            Intent intent= new Intent(context, HomeActivity.class);

            startActivity(intent);

        }

    }

    @Override
    public void setLoginError() {

        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();

    }
}
