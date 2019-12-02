package jp.co.ienter.bottomnavigation.viewmodels;

//
// Created by  on 2019-12-02.
//
public interface Callback<T> {
    void onSuccess(T t);
    void onFailure(Throwable t);
}
