package jp.co.ienter.bottomnavigation.views.loadings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//
// Created by  on 2019-12-03.
//
public class LoadingViewModel extends ViewModel {

    private MutableLiveData<Boolean> mLoadingData = new MutableLiveData<>();

    MutableLiveData<Boolean> getLoadingData() {
        return mLoadingData;
    }
}
