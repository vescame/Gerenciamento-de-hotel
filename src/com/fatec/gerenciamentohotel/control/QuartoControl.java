package src.com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class QuartoControl {
    List<Quarto> quartos = new ArrayList<>();

    public void insert(Quarto q) {
        if (q.getId() == 0) {
            msgError("Id Vazio");
            return;
        }
        if (q.getAndar() == 0) {
            msgError("N� do Andar Vazio");
            return;
        }
        if (q.getTipoDeQuarto() == null) {
            msgError("Tipo de Quarto Vazio");
            return;
        }
        this.quartos.add(q);
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public Quarto selectQuarto(long id) {
        for (Quarto q : this.quartos) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}
