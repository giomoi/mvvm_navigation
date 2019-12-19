package jp.co.ienter.bottomnavigation.viewmodels.home;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.co.ienter.bottomnavigation.Event;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Event> liveData = new MutableLiveData<>();

    public LiveData<Event> getLiveData() {
        return liveData;
    }

    public void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.setValue(new Event<>(null));
            }
        }, 1000);
    }
}