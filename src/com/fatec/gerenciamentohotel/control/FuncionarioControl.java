package com.fatec.gerenciamentohotel.control;

import com.fatec.gerenciamentohotel.entity.Funcionario;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioControl {
    List<Funcionario> funcionarios = new ArrayList<>();

    public void insert(Funcionario f) {
        if (f.getNome().trim().isEmpty()){
            msgError("Nome vazio");
            return ;
        }
        if (f.getCelular().trim().isEmpty()){
            msgError("Celular vazio");
            return ;
        }
        if (f.getCpf().trim().isEmpty()){
            msgError("Cpf vazio");
            return;
        }
        if (f.getEmail().trim().isEmpty()){
            msgError("Email vazio");
            return;
        }
        if (f.getEndereco() == null){
            msgError("Endereco vazio");
            return;
        }
        if (f.getTelefone().trim().isEmpty()){
            msgError("Telefone vazio");
            return;
        }
        if (f.getId() ==  0){
            msgError("Id vazio");
            return;
        }
        if (f.getTipoFuncionario() == null){
            msgError("Tipo funcionario vazio");
            return;
        }
        if (f.getLogin().trim().isEmpty()){
            msgError("Login vazio");
            return;
        }
        if (f.getSenha().trim().isEmpty()){
            msgError("Senha vazio");
            return;
        }
        this.funcionarios.add(f);
    }

    private void msgError(String corpo){
        JOptionPane.showMessageDialog(null, corpo, "ERRO",JOptionPane.ERROR_MESSAGE);
    }

    public Funcionario selectDocumento(String doc) {
        for (Funcionario f : this.funcionarios) {
            if (f.getCpf().contains(doc)) {
                return f;
            }
        }
        return null;
    }
}
