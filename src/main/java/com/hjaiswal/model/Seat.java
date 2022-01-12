package com.hjaiswal.model;

public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;

    public Seat(String id, int rowNo, int seatNo) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
    }

    public String getId() { return id; }
}
