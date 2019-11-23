package com.depromeet.plzdisturb.network;

import com.depromeet.plzdisturb.data.dto.LoginRequest;
import com.depromeet.plzdisturb.data.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DisturbingApi {

//	@GET("genetory")
//    Call<SummonerInfoResponse> getSummonerInfo();

//	@GET("genetory/matches")
//    Call<GameListResponse> getGameList(@Query("lastMatch") long lastGameCreatedAt);

    @POST("/members/login")
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);

}
