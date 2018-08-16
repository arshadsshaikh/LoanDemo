package com.example.arshadshaikh.loandemo.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arshadshaikh.loandemo.R;
import com.example.arshadshaikh.loandemo.utilities.Utils;
import com.example.arshadshaikh.loandemo.managers.DatabaseManager;
import com.example.arshadshaikh.loandemo.utilities.EmailValidator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddApplicantActivity extends AppCompatActivity {

    @BindView(R.id.firstNameEditText)EditText firstNameEditText;
    @BindView(R.id.lastNameEditText)EditText lastNameEditText;
    @BindView(R.id.emailEditText)EditText emailEditText;
    @BindView(R.id.loanAmountEditText)EditText loanAmountEditText;
    @BindView(R.id.panCardEditText)EditText panCardEditText;
    @BindView(R.id.aadharCardEditText)EditText aadharCardEditText;
    @BindView(R.id.voterCardEditText)EditText voterCardEditText;
    
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_applicant);
        
        context=AddApplicantActivity.this;
        ButterKnife.bind(this);

    }

@OnClick(R.id.backImagevIew)
void back(){

        finish();
}



    @OnClick(R.id.submitButton)
    void submitApplicant(){


        if(isValidDocs()){

            String firstName=firstNameEditText.getText().toString().trim();
            String lastName=lastNameEditText.getText().toString();
            String email=emailEditText.getText().toString();
            String panId=panCardEditText.getText().toString();
           String aadharId=aadharCardEditText.getText().toString();
            String voterId=voterCardEditText.getText().toString();
            String loanAmount =loanAmountEditText.getText().toString();

            DatabaseManager.insertData(firstName, lastName, email, Double.parseDouble(loanAmount), panId, aadharId,
                    voterId);

            Toast.makeText(context, getString(R.string.loan_details_submitted), Toast.LENGTH_SHORT).show();

            finish();


        }

    }

    private boolean isValidDocs() {


       String firstName=firstNameEditText.getText().toString().trim();
        String lastName=lastNameEditText.getText().toString();
       String email=emailEditText.getText().toString();
        String panId,aadharId,voterId;

        panId=panCardEditText.getText().toString();
        aadharId=aadharCardEditText.getText().toString();
        voterId=voterCardEditText.getText().toString();
        String loanAmount =loanAmountEditText.getText().toString();

        EmailValidator emailValidator = new EmailValidator();

        if (TextUtils.isEmpty(firstName)) {

            Toast.makeText(this, getString(R.string.enter_first_name), Toast.LENGTH_SHORT).show();
            
            return false;
        }

        if(firstName.length()<3){

            Toast.makeText(context, getString(R.string.first_name_limit), Toast.LENGTH_SHORT).show();

            return false;

        }


        
        else if(TextUtils.isEmpty(lastName)){

            Toast.makeText(this, getString(R.string.enter_last_name), Toast.LENGTH_SHORT).show();
            
            return  false;
            
        }

        if(lastName.length()<3){

            Toast.makeText(context, getString(R.string.last_name_limit), Toast.LENGTH_SHORT).show();

            return false;

        }



       else if (!emailValidator.validate(email)) {

            Toast.makeText(context, getString(R.string.enter_email), Toast.LENGTH_SHORT).show();

            return  false;
        }

        else if(TextUtils.isEmpty(loanAmount)){

            Toast.makeText(context, getString(R.string.enter_your_loan_amount), Toast.LENGTH_SHORT).show();
            return false;
        } else if(Integer.parseInt(loanAmount)<=0||Integer.parseInt(loanAmount)>100000){
            Toast.makeText(context,getString(R.string.enter_amount_limit), Toast.LENGTH_SHORT).show();
            return false;
        }
      else {
            boolean flag=true;
            if(Integer.parseInt(loanAmount)>=50000){
                if (!Utils.panCardValidator(panId,context))
                {
                    Toast.makeText(context,getString(R.string.enter_your_pan_card), Toast.LENGTH_SHORT).show();
                    return false;

                }

            }else if(Integer.parseInt(loanAmount)>=30000){
                if(!Utils.panCardValidator(panId,context)
                        && !Utils.aadharValidator(aadharId,context)){
                    if (!Utils.panCardValidator(panId,context))
                    {

                        Toast.makeText(context,getString(R.string.enter_your_pan_card), Toast.LENGTH_SHORT).show();
                        flag=false;
                    }
                    else  if ((!flag)&&(!Utils.aadharValidator(aadharId,context)))
                    {
                        flag=false;
                        Toast.makeText(context,getString(R.string.enter_your_aadhar_card), Toast.LENGTH_SHORT).show();

                    }
                }

                return flag;


            }
            else {
                if(!Utils.panCardValidator(panId,context)
                        && !Utils.aadharValidator(aadharId,context)
                        && !Utils.voterCardValidator(voterId,context)){

                    if (!Utils.panCardValidator(panId,context))
                    {

                        Toast.makeText(context,getString(R.string.enter_your_pan_card), Toast.LENGTH_SHORT).show();
                        flag=false;
                    }
                    else if ((!flag)&&(!Utils.aadharValidator(aadharId,context)))
                    {

                        Toast.makeText(context,getString(R.string.enter_your_aadhar_card), Toast.LENGTH_SHORT).show();
                        flag=false;
                    }
                    else if ((!flag) &&(!Utils.voterCardValidator(voterId,context)))
                    {

                        Toast.makeText(context,getString(R.string.enter_your_voter_card), Toast.LENGTH_SHORT).show();
                        flag=false;
                    }

                }
                return flag;


            }
            return flag;
        }
    }
}
