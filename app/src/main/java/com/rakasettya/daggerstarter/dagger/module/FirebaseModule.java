package com.rakasettya.daggerstarter.dagger.module;

import android.app.Application;

import com.rakasettya.daggerstarter.data.remote.FirebaseAuthService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {


    @Provides
    @Singleton
    FirebaseAuthService provideFirebaseAuthService(Application application){
        return new FirebaseAuthService(application);
    }
}
