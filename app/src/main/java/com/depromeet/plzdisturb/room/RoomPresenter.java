package com.depromeet.plzdisturb.room;

import com.depromeet.plzdisturb.data.DisturbingRepository;
import com.depromeet.plzdisturb.model.Room;

public class RoomPresenter implements RoomContract.Presenter {

    private final DisturbingRepository repository;
    private final RoomContract.View view;
    private final String code;


    public RoomPresenter(DisturbingRepository repository, RoomContract.View view, String code) {
        this.repository = repository;
        this.view = view;
        this.code = code;
    }

    @Override
    public void load() {
        repository.getRoomInfo(code, new DisturbingRepository.RoomCallback() {

            @Override
            public void onSuccess(Room room) {
                if (!view.isActive()) {
                    return;
                }

                view.addUserList(room.getMembers());
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

    @Override
    public void start() {
        view.goDisturbingScreen();
    }

    @Override
    public void invite() {
        view.showShareIntent("https://lee-kyungseok.github.io/temp/index.html?code=" + code);
    }

    @Override
    public void onClickBtnStart() {
        view.goDisturbingScreen();
    }
}
