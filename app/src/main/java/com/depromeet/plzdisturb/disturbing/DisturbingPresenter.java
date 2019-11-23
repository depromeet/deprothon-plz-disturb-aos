package com.depromeet.plzdisturb.disturbing;

import com.depromeet.plzdisturb.data.DisturbingRepository;
import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;

public class DisturbingPresenter implements DisturbingContract.Presenter {

    private final DisturbingRepository repository;
    private final DisturbingContract.View view;

    DisturbingPresenter(DisturbingRepository repository, DisturbingContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void load(ArrayList<User> userList) {
        view.addUserList(userList);
    }

    @Override
    public void disturb(int userId) {
        repository.disturb(userId, new DisturbingRepository.CommonCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String msg) {
                if (!view.isActive()) {
                    return;
                }

                view.showToast(msg);
            }
        });
    }
}
