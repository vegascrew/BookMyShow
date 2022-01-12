package com.hjaiswal.services;

import com.hjaiswal.model.Seat;
import com.hjaiswal.model.Show;
import com.hjaiswal.providers.InMemorySeatLockProvider;
import com.hjaiswal.providers.SeatLockProvider;

import java.util.List;
import java.util.function.UnaryOperator;

public class SeatAvailabilityService {
    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(BookingService bookingService, SeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(Show show) {
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);

        allSeats.removeAll(unavailableSeats);
        return  allSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
