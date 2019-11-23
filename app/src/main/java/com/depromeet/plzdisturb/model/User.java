package com.depromeet.plzdisturb.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private String profileUrl;

    public User(int id, String name, String profileUrl) {
        this.id = id;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
