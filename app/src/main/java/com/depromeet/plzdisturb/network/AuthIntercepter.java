package com.depromeet.plzdisturb.network;

import android.content.Context;

import com.depromeet.plzdisturb.PlzDistrubApplication;
import com.depromeet.plzdisturb.PreferenceUtilKt;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthIntercepter implements Interceptor {

    private Context context;

    public AuthIntercepter() {
        this.context = PlzDistrubApplication.getAppInstance().getApplicationContext();
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        String token = PreferenceUtilKt.getAccessToken(context);
        if (token != null) {
            String authHeader = "Bearer " + token;
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Authorization", authHeader);
            return chain.proceed(requestBuilder.build());
        } else {
            return chain.proceed(chain.request());
        }
    }
}

