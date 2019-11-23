package com.depromeet.plzdisturb.data;

import com.depromeet.plzdisturb.network.DisturbingApi;
import com.depromeet.plzdisturb.network.DisturbingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisturbingRepositoryImpl implements DisturbingRepository {

	private DisturbingApi api;

	public DisturbingRepositoryImpl() {
		api = DisturbingService.getApi();
	}
}
