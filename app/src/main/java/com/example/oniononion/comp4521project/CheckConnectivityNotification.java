package com.example.oniononion.comp4521project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

/**
 * Created by oniononion on 2016/04/25.
 */
public class CheckConnectivityNotification {

    private static Activity mActivity;

    public static void checkOnline(final Activity act) {
        mActivity = act;
        if(!isOnline(mActivity.getApplicationContext())){showNotification(mActivity);}
    }

    public static boolean isOnline( Context c) {
        // check the devices network connectivity
        ConnectivityManager cm =
                (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public static void showNotification(Activity act){
        mActivity = act;
        final int notifyID = 1; // the id of notification
        final NotificationManager notificationManager = (NotificationManager) mActivity.getSystemService(Context.NOTIFICATION_SERVICE); // get the system service
        final Notification notification = new Notification.Builder(mActivity.getApplicationContext()).setSmallIcon(R.drawable.ic_error).setContentTitle("No Internet Connection").setContentText("Sorry your internet connection is not working, please enable it").build(); // build notification
        notificationManager.notify(notifyID, notification); // send notification

    }


}
