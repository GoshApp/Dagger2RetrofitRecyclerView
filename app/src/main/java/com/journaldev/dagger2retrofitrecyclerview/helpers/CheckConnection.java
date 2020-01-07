package com.journaldev.dagger2retrofitrecyclerview.helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.journaldev.dagger2retrofitrecyclerview.R;

/**
 * Created by Rikmen on 09.07.2018.
 */

public class CheckConnection {

    public void makeToastConnection(Activity activity) {
        Toast.makeText(activity, R.string.connection, Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
