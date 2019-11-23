package com.depromeet.plzdisturb.custom;

public interface OnUserEventListener {

    void onDisturbingEvent(int userId);

    void onFreeEvent(int userId);

}
