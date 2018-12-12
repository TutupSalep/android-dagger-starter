package com.rakasettya.daggerstarter.dagger.module;

import android.app.Application;
import android.content.Context;

import com.rakasettya.daggerstarter.R;
import com.rakasettya.daggerstarter.data.model.UserModel;
import com.rakasettya.daggerstarter.data.model.signup.BodyLogin;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.data.sqlite.model.token.Token;
import com.rakasettya.daggerstarter.data.sqlite.model.user.User;
import com.rakasettya.daggerstarter.helper.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtil(){
        return new NetworkUtils();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    UserModel provideUserModel(){
        return new UserModel();
    }

    @Provides
    @Singleton
    User user(){ return new User(); }

    @Provides
    @Singleton
    DAOSqlite daoSqlite(Context context){ return new DAOSqlite(context); }

    @Provides
    @Singleton
    BodyLogin bodyLogin(){ return new BodyLogin(); }

    @Provides
    @Singleton
    Token token(){ return new Token(); }

    @Provides
    @Singleton
    NetworkModule networkModule(String base_url, DAOSqlite daoSqlite){
        return new NetworkModule(base_url, daoSqlite);
    }
}
