package src.com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ReservaControl {
    List<Reserva> reservas = new ArrayList<>();

    public void insert(Reserva r) {
        if (r.getId() == 0) {
            msgError("Id Vazio");
            return;
        }
        if (r.getCheckIn() == null) {
            msgError("CheckIn Vazio");
            return;
        }
        if (r.getCheckOut() == null) {
            msgError("CheckOut Vazio");
            return;
        }
        if (r.getHospede() == null) {
            msgError("Hospede Vazio");
            return;
        }
        if (r.getQuarto() == null) {
            msgError("Quarto Vazio");
            return;
        }
        this.reservas.add(r);
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public Reserva selectReserva(long id) {
        for (Reserva r : this.reservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}
