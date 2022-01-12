package com.hjaiswal.services;

import com.hjaiswal.exceptions.NotFoundException;
import com.hjaiswal.model.Screen;
import com.hjaiswal.model.Seat;
import com.hjaiswal.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private Map<String,Theatre> theatres;
    private Map<String, Screen> screens;
    private Map<String, Seat> seats;

    public TheatreService() {
        theatres = new HashMap<>();
        screens = new HashMap<>();
        seats = new HashMap<>();
    }

    public Theatre getTheatre(String theatreId) {
        if(!theatres.containsKey(theatreId)) {
            throw new NotFoundException();
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(String screenId) {
        if(!screens.containsKey(screenId)) {
            throw new NotFoundException();
        }
        return screens.get(screenId);
    }

    public Seat getSeat(String seatId) {
        if(!seats.containsKey(seatId)) {
            throw new NotFoundException();
        }
        return seats.get(seatId);
    }

    public Theatre createTheatre(String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreen(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName);
        screens.put(screenId, screen);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeat(Integer rowNo, Integer seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeats(seat);
        return seat;
    }
}
