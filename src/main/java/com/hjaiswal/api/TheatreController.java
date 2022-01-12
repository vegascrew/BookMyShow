package com.hjaiswal.api;

import com.hjaiswal.model.Screen;
import com.hjaiswal.model.Theatre;
import com.hjaiswal.services.TheatreService;

public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public String createTheatre(String theatreName){
        return this.theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(String screenName, String theatreId) {
        final Theatre theatre = this.theatreService.getTheatre(theatreId);
        return this.theatreService.createScreen(screenName, theatre).getId();
    }

    public String createSeatInScreen(Integer rowNo, Integer seatNo, String screenId) {
        final Screen screen = this.theatreService.getScreen(screenId);
        return this.theatreService.createSeat(rowNo, seatNo, screen).getId();
    }
}
