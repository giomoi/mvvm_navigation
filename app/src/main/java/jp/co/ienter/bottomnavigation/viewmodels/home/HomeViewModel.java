package jp.co.ienter.bottomnavigation.viewmodels.home;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import jp.co.ienter.bottomnavigation.Event;
import jp.co.ienter.bottomnavigation.MyApp;
import jp.co.ienter.bottomnavigation.PendingNavigation;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.viewmodels.BaseViewModel;
import jp.co.ienter.bottomnavigation.views.loadings.LoadingIndicator;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<Event> liveData = new MutableLiveData<>();

    public LiveData<Event> getLiveData() {
        return liveData;
    }

    public void loadData(final boolean showLoadingUI) {
        if (showLoadingUI) {
            LoadingIndicator.getInstance().show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (showLoadingUI) {
                    LoadingIndicator.getInstance().hide();
                }

                MyApp.setPendingNavigation(new PendingNavigation(R.id.action_navigation_home_to_nextFragment));
                liveData.setValue(new Event<>(null));
            }
        }, 3000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}