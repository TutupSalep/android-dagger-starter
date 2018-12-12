package com.rakasettya.daggerstarter.apps.signin;


public interface SignInPresenter <V extends SigninView>{
        void onServerLoginClick(String email, String password);

}
