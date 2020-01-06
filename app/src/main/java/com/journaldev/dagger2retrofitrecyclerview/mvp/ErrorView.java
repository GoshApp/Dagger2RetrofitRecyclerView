package com.journaldev.dagger2retrofitrecyclerview.mvp;

/**
 * Created by rikmen00@gmail.com on 06.01.2020.
 */

public interface ErrorView {
    void showNetworkError();

    void showTimeOutError();

    void showSessionTimeout();
}
