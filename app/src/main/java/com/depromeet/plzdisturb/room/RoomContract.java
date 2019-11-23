package com.depromeet.plzdisturb.room;

import com.depromeet.plzdisturb.model.User;

import java.util.List;

public interface RoomContract {

    interface View {

        boolean isActive();

        void addUserList(List<User> userList);

        void addUser(User user);

        void removeUser(int userId);

        void showToast(String msg);

        void goDisturbingScreen();
    }

    interface Presenter {

        void start();

        void invite();

        void onClickBtnStart();

    }
}
