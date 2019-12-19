package jp.co.ienter.bottomnavigation.views.loadings;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import jp.co.ienter.bottomnavigation.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

//
// Created by  on 2019-12-03.
//
public class LoadingIndicator {

    private static LoadingIndicator instance;
    private static int counter;
    private WeakReference<ProgressBar> mLoadingViewRef;

    public static LoadingIndicator getInstance() {
        if (instance == null) {
            instance = new LoadingIndicator();
        }
        return instance;
    }

    private @Nullable LoadingViewModel mViewModel;

    public void init(FragmentActivity activity) {
        ProgressBar mLoadingIndicator = activity.findViewById(R.id.loadingIndicator);

        if (mLoadingIndicator == null) {
            Log.e(TAG, "setupLoadingLiveData: ", new NullPointerException("Loading Indicator is null"));
            return;
        }
        counter = 0;
        mLoadingViewRef = new WeakReference<>(mLoadingIndicator);
        mViewModel = ViewModelProviders.of(activity).get(LoadingViewModel.class);
        mViewModel.getLoadingData().observe(activity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (mLoadingViewRef.get() != null) {
                    mLoadingViewRef.get().setVisibility(isLoading ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    public void deinit() {
        instance = null;
        mLoadingViewRef.clear();
        mLoadingViewRef = null;
    }

    public void show() {
        if (mViewModel != null) {
            synchronized (this) {
                counter++;
                MutableLiveData<Boolean> liveData = mViewModel.getLoadingData();
                if (liveData.getValue() != null && !liveData.getValue()) {
                    liveData.setValue(true);
                }
            }
        } else
            Log.e(TAG, "show: cannot operate", new NullPointerException("LoadingViewModel not initialized"));
    }

    public void hide() {
        hide(false);
    }

    public void hide(boolean mandatory) {
        if (mViewModel != null) {
            synchronized (this) {
                if (mandatory) {
                    counter = 0;
                    mViewModel.getLoadingData().setValue(false);
                    return;
                }

                counter--;
                if (counter <= 0) {
                    mViewModel.getLoadingData().setValue(false);
                }
            }
        } else
            Log.e(TAG, "hide: cannot operate", new NullPointerException("LoadingViewModel not initialized"));
    }
}
