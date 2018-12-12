package com.rakasettya.daggerstarter.dagger.module;


import com.rakasettya.daggerstarter.data.remote.FirebaseAuthService;
import com.rakasettya.daggerstarter.apps.splash.SplashActivity;
import com.rakasettya.daggerstarter.apps.splash.SplashPresenterImpl;
import com.rakasettya.daggerstarter.apps.splash.SplashView;

import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashView splashView(SplashActivity splashActivity){
        return splashActivity;
    }


    @Provides
    SplashPresenterImpl provideSplahPresenter(SplashView splashView, DAOSqlite daoSqlite){
        return new SplashPresenterImpl(splashView,daoSqlite);
    }

}
