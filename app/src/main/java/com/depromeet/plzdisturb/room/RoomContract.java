package com.depromeet.plzdisturb.room;

import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;

public interface RoomContract {

    interface View {

        boolean isActive();

        void addUserList(ArrayList<User> userList);

        void addUser(User user);

        void removeUser(int userId);

        void showToast(String msg);

        void goDisturbingScreen();

        void showShareIntent(String link);
    }

    interface Presenter {

        void load();

        void start();

        void invite();

        void onClickBtnStart();

    }
}
