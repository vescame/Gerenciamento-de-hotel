package com.fatec.gerenciamentohotel.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoControl {
    List<Endereco> enderecos = new ArrayList<>();

    public void insert(Endereco e) {
        if (e.getCep().trim().isEmpty()) {
            msgError("Cep Vazio");
            return;
        }
        if (e.getRua().trim().isEmpty()) {
            msgError("Rua Vazia");
            return;
        }
        if (e.getBairro().trim().isEmpty()) {
            msgError("Bairro Vazio");
            return;
        }
        if (e.getCidade().trim().isEmpty()) {
            msgError("Cidade Vazia");
            return;
        }
        if (e.getUf().trim().isEmpty()) {
            msgError("UF Vazio");
            return;
        }
        this.enderecos.add(e);
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public Endereco selectDocumento(String cep) {
        for (Endereco e : this.enderecos) {
            if (e.getCep().contains(cep)) {
                return e;
            }
        }
        return null;
    }
}
