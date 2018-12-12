package com.rakasettya.daggerstarter.helper.network;

import android.content.Context;
import com.rakasettya.daggerstarter.data.sqlite.dao.DAOSqlite;
import com.rakasettya.daggerstarter.helper.Logger;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.prefs.Preferences;
import javax.annotation.Nullable;
import javax.inject.Inject;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.Buffer;
import okio.BufferedSource;

public class CustomAuthenticator implements Authenticator {

  Context context;
  DAOSqlite daoSqlite;
  String tokens;

  public CustomAuthenticator(Context context) {
    this.context = context;
  }

  @Nullable
  @Override
  public Request authenticate(Route route, Response response) throws IOException {
    if (responseCount(response) > 5) {
      return null;
    }

    /* For Retry Request */
    return response.request()
        .newBuilder()
        .removeHeader("Authorization")
        .removeHeader("Accept")
        .addHeader("Authorization", tokens != null
            ? "Bearer ".concat(tokens)
            : "Bearer ")
        .addHeader("Accept","application/json")
        .addHeader("Content-Type", "application/json")
        .build();
  }

  private int responseCount(Response response) {
    int result = 1;
    while ((response = response.priorResponse()) != null) {
      result++;
    }
    return result;
  }
}
