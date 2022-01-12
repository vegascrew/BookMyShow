package com.hjaiswal.services;

import com.hjaiswal.exceptions.BadRequestException;
import com.hjaiswal.exceptions.NotFoundException;
import com.hjaiswal.exceptions.SeatPermanentlyUnavailableException;
import com.hjaiswal.model.Booking;
import com.hjaiswal.model.Seat;
import com.hjaiswal.model.Show;
import com.hjaiswal.providers.InMemorySeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    Map<String, Booking> showBooking;
    InMemorySeatLockProvider SeatLockProvider;

    public BookingService(InMemorySeatLockProvider SeatLockProvider) {
        this.SeatLockProvider = SeatLockProvider;
        this.showBooking = new HashMap<>();
    }

    public Booking getBooking(String bookingId) {
        if (!showBooking.containsKey(bookingId)) {
            throw new NotFoundException();
        }
        return showBooking.get(bookingId);
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatPermanentlyUnavailableException();
        }
        SeatLockProvider.lockSeats(show, seats, userId);
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, seats, userId);
        showBooking.put(bookingId, booking);
        return booking;
    }

    public List<Seat> getBookedSeats(Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Booking> getAllBookings(Show show) {
        List<Booking> response = new ArrayList<>();
        for(Booking booking : showBooking.values()) {
            if(booking.getShow().equals(show)){
                response.add(booking);
            }
        }
        return response;
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat seat : seats) {
            if(bookedSeats.contains(seat)){
                return true;
            }
        }
        return false;
    }

    public void confirmBooking(Booking booking, String user) {
        if(!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }

        for(Seat seat : booking.getSeatsBooked()) {
            if(!SeatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();
    }
}
