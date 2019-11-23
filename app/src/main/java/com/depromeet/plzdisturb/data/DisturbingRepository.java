package com.depromeet.plzdisturb.data;

import java.util.List;

public interface DisturbingRepository {

	void disturb(int userId, CommonCallback callback);

	void free(int userId, CommonCallback callback);

	interface CommonCallback {

		void onSuccess();

		void onFailure(String msg);

	}

}
