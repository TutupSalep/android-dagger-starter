package com.rakasettya.daggerstarter.apps.splash;

import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.google.firebase.auth.FirebaseUser;
import com.rakasettya.daggerstarter.data.model.UserModel;
import com.rakasettya.daggerstarter.data.remote.FirebaseAuthService;

import java.util.Calendar;

import java.util.List;
import javax.inject.Inject;

public class SplashPresenterImpl implements SplashPresenter {
    SplashView splashView;
    DAOSqlite daoSqlite;

    @Inject
    public SplashPresenterImpl(SplashView splashView, DAOSqlite daoSqlite) {
        this.splashView = splashView;
        this.daoSqlite = daoSqlite;
    }

    @Override
    public void loadSplash() {
        splashView.showProgress(true);
        List token = daoSqlite.getAllToken();
        if (token == null){
            splashView.showProgress(false);
//            splashView.gotoLogin();
            splashView.gotoLogin();
        }else {
            splashView.showProgress(false);
            splashView.gotoMain();
        }
    }

    private void createUserDatbase(UserModel user) {
//        firebaseAuthService.frCreateUser(user).addOnCompleteListener(task -> {
//            splashView.showProgress(false);
//            splashView.gotoMain(user.getUid());
//        }).addOnFailureListener(e -> {
//            splashView.showError(e.getMessage());
//        });
    }
}
