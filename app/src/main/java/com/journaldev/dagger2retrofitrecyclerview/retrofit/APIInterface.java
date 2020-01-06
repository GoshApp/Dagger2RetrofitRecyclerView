package com.journaldev.dagger2retrofitrecyclerview.retrofit;


import com.journaldev.dagger2retrofitrecyclerview.pojo.RandomUser;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/?results=20")
    Observable <RandomUser> getUser();
}
