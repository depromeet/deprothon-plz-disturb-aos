package com.depromeet.plzdisturb.network;

import com.depromeet.plzdisturb.data.dto.CommonResponse;
import com.depromeet.plzdisturb.data.dto.CreateRoomRequest;
import com.depromeet.plzdisturb.data.dto.DisturbRequest;
import com.depromeet.plzdisturb.data.dto.RoomInfoResponse;

import com.depromeet.plzdisturb.data.dto.LoginRequest;
import com.depromeet.plzdisturb.data.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DisturbingApi {

	@GET("room")
    Call<RoomInfoResponse> getRoomInfo(@Query("code") String code);

	@POST("disturb")
    Call<CommonResponse> postDisturb(@Body DisturbRequest request);

    @POST("members/login")
    Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);

    @POST("room")
    Call<RoomInfoResponse> createRoom(@Body CreateRoomRequest createRoomRequest);

}
