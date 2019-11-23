package com.depromeet.plzdisturb.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisturbingService {

	private static final String API_URL = "https://codingtest.op.gg/api/summoner/";

	public static DisturbingApi getApi() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return retrofit.create(DisturbingApi.class);
	}
}
