package com.example.arshadshaikh.loandemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.arshadshaikh.loandemo.database.DBAccessObject;
import com.example.arshadshaikh.loandemo.managers.DatabaseManager;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public class AppController extends Application {

    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        new DBAccessObject().initializeInstance(getApplicationContext());
        DatabaseManager.initializeInstance(getApplicationContext());
        getRequestQueue();

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }



    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
}
