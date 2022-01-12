package com.hjaiswal.api;

import com.hjaiswal.model.Movie;
import com.hjaiswal.model.Screen;
import com.hjaiswal.model.Seat;
import com.hjaiswal.model.Show;
import com.hjaiswal.services.MovieService;
import com.hjaiswal.services.SeatAvailabilityService;
import com.hjaiswal.services.ShowService;
import com.hjaiswal.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowController {
    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public ShowController(SeatAvailabilityService seatAvailabilityService, ShowService showService, TheatreService theatreService, MovieService movieService) {
        this.seatAvailabilityService = seatAvailabilityService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.movieService = movieService;
    }

    public String createShow(String movidId, String ScreenId, Date startTime, Integer durationInSeconds) {
        Movie movie = movieService.getMovie(movidId);
        Screen screen = theatreService.getScreen(ScreenId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(String showId) {
        Show show = showService.getShow(showId);
        List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(seat -> seat.getId()).collect(Collectors.toList());
        // return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
