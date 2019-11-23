package com.depromeet.plzdisturb.network;

import com.depromeet.plzdisturb.data.dto.CommonResponse;
import com.depromeet.plzdisturb.data.dto.DisturbRequest;
import com.depromeet.plzdisturb.data.dto.RoomInfoResponse;

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

}
