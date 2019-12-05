package jp.co.ienter.bottomnavigation.viewmodels;

import androidx.lifecycle.ViewModel;
import custom_android.core.extensions.LOG;

//
// Created by  on 2019-12-05.
//
class LogcatViewModel extends ViewModel {
    private final String TAG = "DEBUG_VIEWMODEL_" + getClass().getSimpleName();

    @Override
    protected void onCleared() {
        super.onCleared();
        LOG.d(TAG, "onCleared");
    }
}
