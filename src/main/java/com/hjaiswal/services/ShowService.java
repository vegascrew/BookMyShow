package com.hjaiswal.services;

import com.hjaiswal.exceptions.NotFoundException;
import com.hjaiswal.model.Movie;
import com.hjaiswal.model.Screen;
import com.hjaiswal.model.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String showId) {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException();
        }
        return shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, startTime, durationInSeconds, movie, screen);
        this.shows.put(showId, show);
        return show;
    }

}
