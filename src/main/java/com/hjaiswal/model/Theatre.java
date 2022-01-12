package com.hjaiswal.model;

import java.util.ArrayList;
import java.util.List;

//CinemaHall
public class Theatre {
    private String id;
    private String name;
    private List<Screen> screens;

    public Theatre(String id, String name) {
        this.id = id;
        this.name = name;
        screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }
    public String getId() {return this.id;}
}
