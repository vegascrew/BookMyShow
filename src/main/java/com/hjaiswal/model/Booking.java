package com.hjaiswal.model;

import com.hjaiswal.exceptions.InvalidStateException;

import java.util.List;

public class Booking {
    private String id;
    private Show show;
    private List<Seat> seatsBooked;
    private String user;
    private BookingStatus bookingStatus;

    public Booking(String id, Show show, List<Seat> seatsBooked, String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.REQUESTED;
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public Show getShow() {
        return show;
    }

    public String getUser() {
        return user;
    }

    public Boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        if(this.bookingStatus != BookingStatus.REQUESTED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.CONFIRMED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.CANCELLED;
    }
}
