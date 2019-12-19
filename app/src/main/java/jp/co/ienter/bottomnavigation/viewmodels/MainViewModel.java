package jp.co.ienter.bottomnavigation.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//
// Created by  on 2019-12-02.
//
public class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> mLoadingState = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoadingState() {
        return mLoadingState;
    }

    public void setLoading(boolean isLoading) {
        mLoadingState.setValue(isLoading);
    }
}
