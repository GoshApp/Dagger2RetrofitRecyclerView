package com.journaldev.dagger2retrofitrecyclerview.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import com.journaldev.dagger2retrofitrecyclerview.mvp.BaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by rikmen00@gmail.com on 06.01.2020.
 */

public class MainPresenter extends AppCompatActivity{



    void gettingUsers(){
        if(isConnect()){
            compositeDisposable.add(
                    apiInterface.getUser().subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(randomUser -> populateRecyclerView(randomUser.getResults()),
                                    throwable -> {
                                        Timber.e(txtError + throwable);{
                                        }
                                    }));
        }
    }

    interface View extends BaseView {
        void onCreditsValidated();
    }
    boolean isConnect(){
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
