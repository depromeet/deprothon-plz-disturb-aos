package com.depromeet.plzdisturb.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisturbingService {

	private static final String API_URL = "http://192.168.0.26:8080/api/";

	public static DisturbingApi getApi() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.addInterceptor(new AuthIntercepter())
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return retrofit.create(DisturbingApi.class);
	}
}
