package com.rakasettya.daggerstarter.apps.signin;

import android.annotation.SuppressLint;
import android.util.Log;
import com.rakasettya.daggerstarter.data.api.NetworkService;
import com.rakasettya.daggerstarter.data.model.signup.BodyLogin;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.data.sqlite.model.token.Token;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class SignInPresenterImp {
    SigninView signinView;
    NetworkService networkService;
    BodyLogin bodyLogin;
    DAOSqlite daoSqlite;
    Token token;

    @Inject
    public SignInPresenterImp(SigninView signinView,
        NetworkService networkService,
        BodyLogin bodyLogin,
        DAOSqlite daoSqlite,
        Token token) {
        this.signinView = signinView;
        this.networkService = networkService;
        this.bodyLogin = bodyLogin;
        this.daoSqlite = daoSqlite;
        this.token = token;
    }

    @SuppressLint("CheckResult")
    public void singinUser(String Username, String Password) {
        Log.e("singinUsername", "SignInPresenterImp" + Username);
        Log.e("singinUserpass", "SignInPresenterImp" + Password);
        bodyLogin.setEmail(Username);
        bodyLogin.setPassword(Password);
        networkService.login(bodyLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(response -> {
                Log.e("singinUser", "SignInPresenterImp" + response);
                signinView.onLoadSingin(response.getSuccess().getToken());
                token.setToken(response.getSuccess().getToken());
                daoSqlite.addToken(token);
                signinView.gotoMain();
            }, throwable -> {
                Log.e("singinUser", "SignInPresenterImp" + throwable);
            });
    }
}
