package com.rakasettya.daggerstarter.dagger.module;

import android.app.Application;
import android.content.Context;
import com.rakasettya.daggerstarter.data.api.NetworkService;
import com.rakasettya.daggerstarter.data.api.Service;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  String BASE_URL;
  DAOSqlite daoSqlite;
  String tokens;

  public NetworkModule(String BASE_URL,DAOSqlite daoSqlite) {
    this.BASE_URL = BASE_URL;
    this.daoSqlite = daoSqlite;
  }

    @Provides
    @Singleton
    Retrofit provideCall(Context context) {
      daoSqlite = new DAOSqlite(context);
      for (int i = 0; i < daoSqlite.getAllToken().size(); i++) {
        tokens = daoSqlite.getAllToken().get(i).getToken();
      }
    File cacheDir = context.getCacheDir();
    Cache cache  = new Cache(cacheDir, 10 * 1024 * 1024);;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
          .addInterceptor(loggingInterceptor)
          .addInterceptor(chain -> {
            Request original = chain.request();
            // Customize the request
            Request request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Authorization", tokens != null
                    ? "Bearer ".concat(tokens)
                    : "Bearer ")
                .build();
            okhttp3.Response response = chain.proceed(request);
            response.cacheResponse();
            // Customize or return the response
            return response;
          })
          .cache(cache)
          .build();
      Gson gson = new GsonBuilder()
        .setLenient()
        .create();
      return new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
        Retrofit retrofit) {
      return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service providesService(
        NetworkService networkService) {
      return new Service(networkService);
    }

}
