package jp.co.ienter.bottomnavigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import custom_android.core.extensions.LOG;

//
// Created by  on 2019-12-05.
//
public abstract class LogcatFragment extends Fragment {
    protected String TAG = "DEBUG_FRAGMENT_" + getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LOG.d(TAG, "onAttach" + getAllState());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOG.d(TAG, "onCreate" + getAllState());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =super.onCreateView(inflater, container, savedInstanceState);
        LOG.d(TAG, "onCreateView" + getAllState());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LOG.d(TAG, "onActivityCreated" + getAllState());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LOG.d(TAG, "onViewCreated");
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        LOG.d(TAG, "onCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onStart() {
        super.onStart();
        LOG.d(TAG, "onStart" + getAllState());
    }

    @Override
    public void onResume() {
        super.onResume();
        LOG.d(TAG, "onResume" + getAllState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LOG.d(TAG, "onViewStateRestored" + getAllState());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LOG.d(TAG, "onSaveInstanceState" + getAllState());
    }

    @Override
    public void onPause() {
        super.onPause();
        LOG.d(TAG, "onPause" + getAllState());
    }

    @Override
    public void onStop() {
        super.onStop();
        LOG.d(TAG, "onStop" + getAllState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LOG.d(TAG, "onDestroyView" + getAllState());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOG.d(TAG, "onDestroy" + getAllState());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LOG.d(TAG, "onDetach" + getAllState());
    }

    public String getAllState() {
//        return ", isAdded= " + isAdded() + ", isDetached= " + isDetached()
//                + ", isHidden= " + isHidden() + ", isInLayout= " + isInLayout()
//                + ", isResumed= " + isResumed() + ", isVisible= " + isVisible() + ", isStateSaved= " + isStateSaved();
        return "";
    }
}
