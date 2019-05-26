package com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class TipoDeQuartoControl {
    List<TipoDeQuarto> tipos = new ArrayList<>();

    public void insert(TipoDeQuarto t) {
        if (t.getId() == 0) {
            msgError("Id Vazio");
            return;
        }
        if (t.getTipo().trim().isEmpty()) {
            msgError("Tipo Vazio");
            return;
        }
        if (t.getValorDiaria() == 0) {
            msgError("Diaria Vazia");
            return;
        }
        if (t.getQuantidadeAdultos() == 0) {
            msgError("Quantidade Adultos Vazia");
            return;
        }
        if (t.getQuantidateCriacas() == 0) {
            msgError("Quantidade Criancas Vazia");
            return;
        }
        this.tipos.add(t);
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public TipoDeQuarto selectTipo(long id) {
        for (TipoDeQuarto t : this.tipos) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
