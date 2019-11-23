package com.depromeet.plzdisturb.data;

import com.depromeet.plzdisturb.data.dto.CommonResponse;
import com.depromeet.plzdisturb.data.dto.DisturbRequest;
import com.depromeet.plzdisturb.data.dto.RoomInfoResponse;
import com.depromeet.plzdisturb.network.DisturbingApi;
import com.depromeet.plzdisturb.network.DisturbingService;

public class DisturbingRepositoryImpl implements DisturbingRepository {

    private DisturbingApi api;

    public DisturbingRepositoryImpl() {
        api = DisturbingService.getApi();
    }

    @Override
    public void disturb(int userId, CommonCallback callback) {
        Call<CommonResponse> disturbResponseCall = api.postDisturb(new DisturbRequest(userId));
        disturbResponseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                callback.onFailure("잠시 후에 시도해주세요");
            }
        });
    }

    @Override
    public void getRoomInfo(String code, RoomCallback roomCallback) {
        Call<RoomInfoResponse> roomInfoResponseCall = api.getRoomInfo(code);
        roomInfoResponseCall.enqueue(new Callback<RoomInfoResponse>() {
            @Override
            public void onResponse(Call<RoomInfoResponse> call, Response<RoomInfoResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    roomCallback.onFailure("잠시 후에 다시 시도해주세요");
                    return;
                }

                roomCallback.onSuccess(response.body().getRoom());
            }

            @Override
            public void onFailure(Call<RoomInfoResponse> call, Throwable t) {
                roomCallback.onFailure("잠시 후에 다시 시도해주세요");
            }
        });
    }
}
