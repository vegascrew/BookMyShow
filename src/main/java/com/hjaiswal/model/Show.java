package com.hjaiswal.model;

import java.util.Date;

public class Show {
    private String id;
    private Date startTime;
    private int durationInSeconds;

    private Movie movie;
    private Screen screen;

    public Show(String id, Date startTime, int durationInSeconds, Movie movie, Screen screen) {
        this.id = id;
        this.startTime = startTime;
        this.durationInSeconds = durationInSeconds;
        this.movie = movie;
        this.screen = screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getId() {
        return id;
    }
    public Screen getScreen() {return screen; }
}
