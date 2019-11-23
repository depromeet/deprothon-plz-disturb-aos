package com.depromeet.plzdisturb.disturbing;

import com.depromeet.plzdisturb.model.User;

import java.util.List;

public interface DisturbingContract {

    interface View {

        boolean isActive();

        void addUserList(List<User> userList);

        void addUser(User user);

        void removeUser(int userId);

        void showDisturbingUI();

        void hideDisturbingUI();

    }

    interface Presenter {

        void start();

        void disturb();

    }

}
