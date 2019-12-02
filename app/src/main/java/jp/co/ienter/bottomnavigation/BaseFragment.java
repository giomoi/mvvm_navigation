package jp.co.ienter.bottomnavigation;

import androidx.fragment.app.Fragment;

//
// Created by  on 2019-12-02.
//
public class BaseFragment extends Fragment {
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
