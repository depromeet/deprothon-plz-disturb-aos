package com.depromeet.plzdisturb.data;

import com.depromeet.plzdisturb.model.Room;

public interface DisturbingRepository {

	void disturb(int userId, CommonCallback callback);

    void getRoomInfo(String code, RoomCallback roomCallback);

    interface CommonCallback {

		void onSuccess();

		void onFailure(String msg);

	}

	interface RoomCallback {

    	void onSuccess(Room room);

		void onFailure(String msg);

	}

}
