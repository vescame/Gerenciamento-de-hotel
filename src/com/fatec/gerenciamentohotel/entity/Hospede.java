package com.fatec.gerenciamentohotel.entity;

public class Hospede extends Pessoa {
    private Reserva reserva;

    private ItemServico itemServico;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public ItemServico getItemServico() {
        return itemServico;
    }

    public void setItemServico(ItemServico itemServico) {
        this.itemServico = itemServico;
    }
}
