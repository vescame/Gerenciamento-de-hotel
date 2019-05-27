package com.fatec.gerenciamentohotel.control;

import com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import com.fatec.gerenciamentohotel.entity.EFuncionario;
import com.fatec.gerenciamentohotel.entity.Funcionario;

import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioControl {
    List<Funcionario> funcionarios = new ArrayList<>();

    public void insert(Funcionario f) {
        if (f.getNome().trim().isEmpty()) {
            msgError("Nome vazio");
            return;
        }
        if (f.getCelular().trim().isEmpty()) {
            msgError("Celular vazio");
            return;
        }
        if (f.getCpf().trim().isEmpty()) {
            msgError("Cpf vazio");
            return;
        }
        if (f.getEmail().trim().isEmpty()) {
            msgError("Email vazio");
            return;
        }
        if (f.getEndereco() == null) {
            msgError("Endereco vazio");
            return;
        }
        if (f.getTelefone().trim().isEmpty()) {
            msgError("Telefone vazio");
            return;
        }
        if (f.getId() == 0) {
            msgError("Id vazio");
            return;
        }
        if (f.getTipoFuncionario() == null) {
            msgError("Tipo funcionario vazio");
            return;
        }
        if (f.getLogin().trim().isEmpty()) {
            msgError("Login vazio");
            return;
        }
        if (f.getSenha().trim().isEmpty()) {
            msgError("Senha vazio");
            return;
        }
        // this.funcionarios.add(f);
        try {
            Connection con = ConnectionDB.getInstance().getConnection();
            PreparedStatement pstmt =
                    con.prepareStatement("insert into endereco values" +
                            "(?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, 03652332);
            pstmt.setString(2, "Rua dos Aires");
            pstmt.setInt(3, 9098);
            pstmt.setString(4, "Bairro Argentino");
            pstmt.setString(5, "São Paulo");
            pstmt.setString(6, "SP");
            ResultSet rs;
            if (pstmt.execute()) System.out.println("endereco ok    ");

            pstmt = con.prepareStatement("Insert into funcionario values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, 1);
            pstmt.setInt(2, 03652332);
            pstmt.setString(3, "45865795162");
            pstmt.setString(4, "Renato Braga");
            pstmt.setString(5, "01125752366");
            pstmt.setString(6, "011984562570");
            pstmt.setString(7, "renato.braga@yahoo.com.br");
            DateFormat sdf = new SimpleDateFormat("dd/01/yyyy");
            java.util.Date nasc = sdf.parse("11/01/1996");
            pstmt.setDate(8, new java.sql.Date(nasc.getTime()));
            pstmt.setString(9, "A");
            pstmt.setString(10, "rbraga10");
            pstmt.setString(11, "senharbraga");
            pstmt.setString(12, EFuncionario.ADMIN.role);
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();
        } catch (SQLException | ParseException e){
            System.out.println(e.getMessage());
        }
    }

    private void msgError(String corpo) {
        JOptionPane.showMessageDialog(null, corpo, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public Funcionario selectDocFuncionario(String doc) {
        for (Funcionario f : this.funcionarios) {
            if (f.getCpf().contains(doc)) {
                return f;
            }
        }
        return null;
    }
}
