package com.rakasettya.daggerstarter;

import android.app.Activity;
import android.app.Application;


import com.rakasettya.daggerstarter.dagger.component.DaggerAppComponent;
import com.rakasettya.daggerstarter.dagger.module.FirebaseModule;

import com.rakasettya.daggerstarter.dagger.module.NetworkModule;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.helper.cons.AppConst;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Inject
    DAOSqlite daoSqlite;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .network(new NetworkModule(AppConst.BASE_URL,daoSqlite))
                .firebase(new FirebaseModule())
                .build()
                .inject(this);
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
