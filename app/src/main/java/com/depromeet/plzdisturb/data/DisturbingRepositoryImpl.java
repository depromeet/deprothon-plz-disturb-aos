package com.depromeet.plzdisturb.data;

import com.depromeet.plzdisturb.network.DisturbingApi;
import com.depromeet.plzdisturb.network.DisturbingService;

public class DisturbingRepositoryImpl implements DisturbingRepository {

    private DisturbingApi api;

    public DisturbingRepositoryImpl() {
        api = DisturbingService.getApi();
    }

    @Override
    public void disturb(int userId, CommonCallback callback) {
        // @TODO : (jonghyo) api 연동 필요
        callback.onSuccess();
    }

    @Override
    public void free(int userId, CommonCallback callback) {
        // @TODO : (jonghyo) api 연동 필요
        callback.onSuccess();
    }
}
