package com.rakasettya.daggerstarter.dagger.component;

import android.app.Application;

import com.rakasettya.daggerstarter.MyApplication;
import com.rakasettya.daggerstarter.dagger.module.ActivityBuilder;
import com.rakasettya.daggerstarter.dagger.module.AppModule;
import com.rakasettya.daggerstarter.dagger.module.FirebaseModule;
import com.rakasettya.daggerstarter.dagger.module.NetworkModule;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FirebaseModule.class,
        NetworkModule.class
})
public interface AppComponent  {

    @Component.Builder
    interface Builder {

        @BindsInstance Builder application(Application application);

        AppComponent build();

        Builder firebase(FirebaseModule firebaseModule);
        Builder network(NetworkModule networkModule);
    }

    void inject(MyApplication application);

}
