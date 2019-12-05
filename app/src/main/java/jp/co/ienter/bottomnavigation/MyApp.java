package jp.co.ienter.bottomnavigation;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

//
// Created by  on 2019-11-25.
//
public class MyApp extends Application {

    private static List<PendingNavigation> mPendingNavigations = new ArrayList<>();

    public static void setPendingNavigation(PendingNavigation pendingNavigation) {
        mPendingNavigations.add(pendingNavigation);
    }

    public static List<PendingNavigation> getPendingNavigations() {
        return mPendingNavigations;
    }

    public static void removePendingNavigation() {
        mPendingNavigations.clear();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
