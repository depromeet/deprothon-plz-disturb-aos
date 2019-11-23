package com.depromeet.plzdisturb.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private String imageUrl;

    public User(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
