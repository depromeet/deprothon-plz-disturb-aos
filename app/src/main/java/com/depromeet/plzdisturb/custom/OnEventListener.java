package com.depromeet.plzdisturb.custom;

public interface OnEventListener {

    void onDisturbingEvent(int userId);

    void onFreeEvent(int userId);

}
