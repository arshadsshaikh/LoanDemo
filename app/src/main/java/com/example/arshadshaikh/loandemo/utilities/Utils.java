package com.example.arshadshaikh.loandemo.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public class Utils {


    public static boolean isInternet(Context context) {
        if(context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                NetworkInfo wifi = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                return (mobile != null && mobile.isConnectedOrConnecting())
                        || (wifi != null && wifi.isConnectedOrConnecting());
            } else
                return false;
        }
        return false;
    }


    public static  boolean    panCardValidator(String card, Context context){
        if (TextUtils.isEmpty(card))
            return false;
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

        Matcher matcher = pattern.matcher(card);

        if(matcher.matches()){

            return true;

        }
        else{


            return false;
        }

    }

    public  static  boolean voterCardValidator(String voterID, Context context){
        if (TextUtils.isEmpty(voterID))
            return false;

        Pattern pattern= Pattern.compile("[A-Z]{3}[0-9]{7}");

        Matcher matcher= pattern.matcher(voterID);

        if(matcher.matches()){

            return true;

        }else{

            return  false;

        }



    }

    public  static  boolean aadharValidator(String aadharId,Context context){

        if (TextUtils.isEmpty(aadharId))
            return false;

        Pattern pattern= Pattern.compile("[0-9]{12}");

        Matcher matcher= pattern.matcher(aadharId);

        if(matcher.matches()){

            return true;

        }else{

            return  false;

        }


    }






}
