package com.rakasettya.daggerstarter.apps.splash;

public interface SplashView {

    void onSplashLoad();

    void gotoLogin();

    void showProgress(boolean isShow);

    void gotoMain();
//    void gotoMain(String uid);

    void showError(String message);
}
