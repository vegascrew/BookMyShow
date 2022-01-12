package com.hjaiswal.services;

import com.hjaiswal.exceptions.BadRequestException;
import com.hjaiswal.model.Booking;
import com.hjaiswal.providers.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentsService {
    Map<Booking, Integer> bookingFailures;
    private final Integer allowedRetries;
    private final SeatLockProvider seatLockProvider;

    public PaymentsService(Integer allowedRetries, SeatLockProvider seatLockProvider) {
        this.bookingFailures = new HashMap<>();
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
    }

    public void processPaymentFailed(Booking booking, String user) {
        if(!booking.getUser().equals(user)) {
            throw new BadRequestException();
        }

        if(!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }

        Integer currentFailureCount = bookingFailures.get(booking);
        Integer newFailureCount = currentFailureCount + 1;
        bookingFailures.put(booking, newFailureCount);

        if(newFailureCount > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }
}
