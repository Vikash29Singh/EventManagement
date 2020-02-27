package com.example.eventmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static String getConnectivityStatusString(Context context) {
        ProgressDialog mProgress;

        String status = null;
        //progress bar
        mProgress = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        mProgress.setIndeterminate(true);
        //mProgress.setTitle("Processing...");

        mProgress.setCancelable(false);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled";
                // mProgress.setMessage(status);
                //  mProgress.dismiss();
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled";
                // mProgress.setMessage(status);
                // mProgress.dismiss();
                return status;
            }
        } else {
            status = "No internet is available";
            mProgress.setMessage(status);
            // mProgress.show();
            return status;
        }
        return status;
    }
}
