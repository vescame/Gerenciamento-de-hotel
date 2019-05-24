package com.fatec.gerenciamentohotel.entity;

import java.util.Date;

public class Reserva {
    private long id;
    private Date checkIn;
    private Date checkOut;
    private Hospede hospede;
    private Quarto quarto;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStaus(String status) {
        this.status = status;
    }
}
