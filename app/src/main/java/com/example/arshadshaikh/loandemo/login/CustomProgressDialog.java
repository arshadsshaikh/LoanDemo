package com.example.arshadshaikh.loandemo.login;

import android.app.Dialog;
import android.content.Context;

import com.example.arshadshaikh.loandemo.R;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

class CustomProgressDialog {

    public static Dialog showProgressDialog(Context ctx) {

        Dialog	dialog = new Dialog(ctx, R.style.Progres_Custom_Dialog);

        dialog.setContentView(R.layout.progress_dilog_layout);

	/*	GifImageView gifImageView = (GifImageView) dialog.findViewById(R.id.GifImageView);
		gifImageView.setGifImageResource(R.drawable.loader);*/

        dialog.setCancelable(false);


        return dialog;
    }

}
