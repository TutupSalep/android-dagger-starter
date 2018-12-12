package com.rakasettya.daggerstarter.data.api;

import com.rakasettya.daggerstarter.data.model.signup.BodyLogin;
import com.rakasettya.daggerstarter.data.model.signup.Login;
import com.rakasettya.daggerstarter.data.model.user.ResponseUser;
import com.rakasettya.daggerstarter.helper.cons.UrlCons;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rakhadota2 on 2/15/18.
 */

public interface NetworkService {
    @POST(UrlCons.URL_LOGIN)
    Observable<Login> login(@Body BodyLogin bodyLogin);

    @POST(UrlCons.URL_USER)
    Observable<ResponseUser> user();
}
