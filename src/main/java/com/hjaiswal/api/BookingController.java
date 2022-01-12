package com.hjaiswal.api;

import com.hjaiswal.model.Seat;
import com.hjaiswal.model.Show;
import com.hjaiswal.services.BookingService;
import com.hjaiswal.services.ShowService;
import com.hjaiswal.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingController {
    private final BookingService bookingService;
    private final ShowService showService;
    private final TheatreService theatreService;

    public BookingController(BookingService bookingService, ShowService showService, TheatreService theatreService) {
        this.bookingService = bookingService;
        this.showService = showService;
        this.theatreService = theatreService;
    }

    public String createBooking(String showId, String userId, List<String> seatsIds) {
        Show show = showService.getShow(showId);
        List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
