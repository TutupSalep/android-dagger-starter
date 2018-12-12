package com.rakasettya.daggerstarter.dagger.module;

import com.rakasettya.daggerstarter.data.api.NetworkService;
import com.rakasettya.daggerstarter.apps.main.MainActivity;
import com.rakasettya.daggerstarter.apps.main.MainPresenterImpl;
import com.rakasettya.daggerstarter.apps.main.MainView;

import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.data.sqlite.model.user.User;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModul {
    @Provides
    MainView mainView(MainActivity mainActivity){
        return mainActivity;
    }
    @Provides
    MainPresenterImpl provideMainPresenterImp(MainView mainView, DAOSqlite daoSqlite, NetworkService networkService, User user){
        return new MainPresenterImpl(mainView,daoSqlite,networkService,user);
    }
}
