package com.depromeet.plzdisturb.data;

import java.util.List;

public interface DisturbingRepository {

	interface CommonCallback {

		void onSuccess();

		void onFailure(String msg);

	}

}
