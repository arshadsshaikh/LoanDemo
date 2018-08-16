package com.example.arshadshaikh.loandemo.managers;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.arshadshaikh.loandemo.utilities.Constants;
import com.example.arshadshaikh.loandemo.volleyservices.GsonPostRequest;
import com.google.gson.Gson;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public class APIManager<T> {

    private GsonPostRequest<T> gsonPostRequest;


    public void postLoginAPI(String url, String request, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> classType, Context mContext) {


        gsonPostRequest = new GsonPostRequest<>(null,
                url,
                request,
                classType,
                new Gson(),
                listener,
                errorListener);

        gsonPostRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        Volley.newRequestQueue(mContext).add(gsonPostRequest);

    }

}
