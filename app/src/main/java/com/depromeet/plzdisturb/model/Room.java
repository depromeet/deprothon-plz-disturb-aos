package com.depromeet.plzdisturb.model;

import java.util.ArrayList;

public class Room {

    private String code;
    private int id;
    private String name;
    private ArrayList<User> members;

    public String getCode() {
        return code;
    }

    public ArrayList<User> getMembers() {
        return members;
    }
}
