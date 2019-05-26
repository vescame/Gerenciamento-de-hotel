package com.fatec.gerenciamentohotel.entity;

import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {
    private Reserva reserva;
    private List<ItemServico> itemsServicos  = new ArrayList<>();

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<ItemServico> getItemsServicos() {
        return itemsServicos;
    }

    public void setItemsServicos(List<ItemServico> itemsServicos) {
        this.itemsServicos = itemsServicos;
    }

}
