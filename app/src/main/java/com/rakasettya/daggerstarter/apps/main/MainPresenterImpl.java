package com.rakasettya.daggerstarter.apps.main;

import android.annotation.SuppressLint;
import android.content.Context;

import com.rakasettya.daggerstarter.data.api.NetworkService;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.data.sqlite.model.token.Token;
import com.rakasettya.daggerstarter.data.sqlite.model.user.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class MainPresenterImpl {
    MainView mainView;
    DAOSqlite daoSqlite;
    NetworkService networkService;
    User user;

    @Inject
    public MainPresenterImpl(
        MainView mainView,
        DAOSqlite daoSqlite,
        NetworkService networkService,
        User user) {
        this.mainView = mainView;
        this.daoSqlite = daoSqlite;
        this.networkService = networkService;
        this.user = user;
    }

    @SuppressLint("CheckResult")
    public void geDataUser() {
        networkService.user()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object -> {
                for (int i = 0; i < object.getData().size(); i++) {
                user.setEmail(object.getData().get(i).getEmail());
                user.setDisplayName(object.getData().get(i).getDisplayName());
                user.setId(String.valueOf(object.getData().get(i).getId()));
                user.setFcmToken(String.valueOf(object.getData().get(i).getFcmToken()));
                user.setRoleId(String.valueOf(object.getData().get(i).getRoleId()));
                user.setUserableId(String.valueOf(object.getData().get(i).getUserableId()));
                user.setUsername(object.getData().get(i).getUsername());
                }
                daoSqlite.addUser(user);
            }, throwable -> {});
    }
    public void logout(Context context){
        daoSqlite.deleteAllToken();
        mainView.gotoLogin();
    }
}
