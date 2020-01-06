package com.journaldev.dagger2retrofitrecyclerview.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.journaldev.dagger2retrofitrecyclerview.MyApplication;
import com.journaldev.dagger2retrofitrecyclerview.R;
import com.journaldev.dagger2retrofitrecyclerview.adapter.RecyclerViewAdapter;
import com.journaldev.dagger2retrofitrecyclerview.di.component.ApplicationComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.component.DaggerMainActivityComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.component.MainActivityComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.module.MainActivityContextModule;
import com.journaldev.dagger2retrofitrecyclerview.di.qualifier.ActivityContext;
import com.journaldev.dagger2retrofitrecyclerview.di.qualifier.ApplicationContext;
import com.journaldev.dagger2retrofitrecyclerview.pojo.Result;
import com.journaldev.dagger2retrofitrecyclerview.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends MainPresenter implements RecyclerViewAdapter.ClickListener {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView recyclerView;
    @BindString(R.string.error) String txtError;
    private MainActivityComponent mainActivityComponent;
    private ApplicationComponent applicationComponent;
    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        gettingUsers();
    }


    private void init() {
        ButterKnife.bind(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void populateRecyclerView(List<Result> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String userName) {
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", userName));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
