package jp.co.ienter.bottomnavigation.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import jp.co.ienter.bottomnavigation.BaseFragment;
import jp.co.ienter.bottomnavigation.Event;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.viewmodels.home.HomeViewModel;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getLiveData().observe(this, new Observer<Event>() {
            @Override
            public void onChanged(Event event) {
                if (getView() == null) {
                    Log.e(TAG, "onChanged: ", new NullPointerException("getView() is null"));
                    return;
                }

                Navigation.findNavController(getView()).navigate(R.id.action_navigation_home_to_nextFragment);

//                MyApp.removePendingNavigation();
            }
        });

        root.findViewById(R.id.btnNextOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.loadData(true);
            }
        });

        return root;
    }
}