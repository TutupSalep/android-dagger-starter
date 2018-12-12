package com.rakasettya.daggerstarter.apps.main;

import com.rakasettya.daggerstarter.data.model.UserModel;

public interface MainView {
    void initProfile(UserModel userModel);

    void gotoLogin();
}
