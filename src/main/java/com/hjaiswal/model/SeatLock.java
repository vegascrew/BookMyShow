package com.hjaiswal.model;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, Date lockTime, String lockedBy) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = lockedBy;
    }

    public boolean isLockExpired() {
        Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

    public String getLockedBy() {
        return lockedBy;
    }
}
