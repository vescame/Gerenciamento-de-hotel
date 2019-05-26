package com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.fatec.gerenciamentohotel.entity.Hospede;

public class HospedeControl {
    private List<Hospede> hospedes;

    public HospedeControl(){
         this.hospedes = new ArrayList<>();
    }

    public void insert(Hospede h) {
        if (h.getNome().trim().isEmpty()) {
            msgError("Nome Vazio");
            return;
        }
        if (h.getCpf().trim().isEmpty()) {
            msgError("Cpf Vazio");
            return;
        }
        if (h.getEndereco() == null) {
            msgError("Endereco Vazio");
            return;
        }
        if (h.getId() == 0) {
            msgError("Id Vazio");
            return;
        }
        this.hospedes.add(h);
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public Hospede selectDocHospede(String doc) {
        for (Hospede h : this.hospedes) {
            if (h.getCpf().contains(doc)) {
                return h;
            }
        }
        return null;
    }
}
