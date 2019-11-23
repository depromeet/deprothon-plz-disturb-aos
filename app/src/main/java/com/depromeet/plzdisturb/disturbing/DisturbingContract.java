package com.depromeet.plzdisturb.disturbing;

import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;

public interface DisturbingContract {

    interface View {

        boolean isActive();

        void addUserList(ArrayList<User> userList);

        void addUser(User user);

        void removeUser(int userId);

        void showDisturbingUI();

        void hideDisturbingUI();

        void showToast(String msg);

    }

    interface Presenter {

        void load(ArrayList<User> userList);

        void disturb(int userId);

    }

}
