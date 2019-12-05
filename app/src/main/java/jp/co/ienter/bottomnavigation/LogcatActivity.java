package jp.co.ienter.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import custom_android.core.extensions.LOG;

public abstract class LogcatActivity extends AppCompatActivity {

    public String TAG = "DEBUG_ACTIVITY_" + getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOG.d(TAG, "onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LOG.d(TAG, "onSaveInstanceState");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        LOG.d(TAG, "onStateNotSaved");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LOG.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LOG.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOG.d(TAG, "onResume");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        LOG.d(TAG, "onResumeFragments");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LOG.d(TAG, "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LOG.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LOG.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LOG.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LOG.d(TAG, "onDestroy");
    }
}
