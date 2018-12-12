package com.rakasettya.daggerstarter.dagger.module;

import com.rakasettya.daggerstarter.data.api.NetworkService;
import com.rakasettya.daggerstarter.apps.signin.SignInActivity;
import com.rakasettya.daggerstarter.apps.signin.SignInPresenterImp;
import com.rakasettya.daggerstarter.apps.signin.SigninView;

import com.rakasettya.daggerstarter.data.model.signup.BodyLogin;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.data.sqlite.model.token.Token;
import dagger.Module;
import dagger.Provides;

@Module
public class SignInActivityModule {

    @Provides
    SigninView provideSinginActivity(SignInActivity activity){
        return activity;
    }

    @Provides
    SignInPresenterImp provideSinginPresenter(
        SigninView signinView,
        NetworkService networkService,
        BodyLogin bodyLogin,
        DAOSqlite daoSqlite,
        Token token){
        return new SignInPresenterImp(signinView,networkService,bodyLogin,daoSqlite,token);
    }
}
