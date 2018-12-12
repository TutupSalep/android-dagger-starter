package com.rakasettya.daggerstarter.dagger.module;

import com.rakasettya.daggerstarter.apps.barcode.BarcodeActivity;
import com.rakasettya.daggerstarter.apps.main.MainActivity;
import com.rakasettya.daggerstarter.apps.signin.SignInActivity;
import com.rakasettya.daggerstarter.apps.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {


  @ContributesAndroidInjector(modules = SplashActivityModule.class)
  abstract SplashActivity splashActivity();

  @ContributesAndroidInjector(modules = SignInActivityModule.class)
  abstract SignInActivity singInActivity();

  @ContributesAndroidInjector(modules = MainActivityModul.class)
  abstract MainActivity mainActivity();

  @ContributesAndroidInjector(modules = BarcodeModule.class)
  abstract BarcodeActivity barcodeActivity();
}
