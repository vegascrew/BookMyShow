package com.hjaiswal.api;

import com.hjaiswal.services.BookingService;
import com.hjaiswal.services.PaymentsService;

public class PaymentsController {
    private final PaymentsService paymentsService;
    private final BookingService bookingService;


    public PaymentsController(PaymentsService paymentsService, BookingService bookingService) {
        this.paymentsService = paymentsService;
        this.bookingService = bookingService;
    }

    public void paymentSuccess(String bookingId, String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

    public void paymentFailed(String bookingId, String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }
}
