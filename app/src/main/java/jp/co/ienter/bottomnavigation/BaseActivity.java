package jp.co.ienter.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import jp.co.ienter.bottomnavigation.viewmodels.MainViewModel;

//
// Created by  on 2019-12-02.
//
public abstract class BaseActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    protected MainViewModel getViewModel() {
        return mViewModel;
    }

    protected void createViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void setLoading(boolean loading) {
        mViewModel.setLoading(loading);
    }
}
