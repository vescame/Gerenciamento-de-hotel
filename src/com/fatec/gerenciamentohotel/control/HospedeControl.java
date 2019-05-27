package src.com.fatec.gerenciamentohotel.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class HospedeControl {
	
	public void insert(Hospede h) {
		if (h.getEndereco() == null) {
			msgError("Endereco vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getCpf().trim().isEmpty()) {
			msgError("Cpf vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getNome().trim().isEmpty()) {
			msgError("Nome vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getTelefone().trim().isEmpty()) {
			msgError("Telefone vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getCelular().trim().isEmpty()) {
			msgError("Celular vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getEmail().trim().isEmpty()) {
			msgError("Email vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getDataNascimento() == null) {
			msgError("Data de Nascimento vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getStatus() == Character.MIN_VALUE) {
			msgError("Status vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(
					" Insert into hospede "
					+ " (cep, cpf, nome, telefone, celular, email, dat_nascimento, status, num_residencia) "
					+ " values "
					+ " (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, h.getEndereco().getCep());
			pstmt.setString(2, h.getCpf());
			pstmt.setString(3, h.getNome());
			pstmt.setString(4, h.getTelefone());
			pstmt.setString(5, h.getCelular());
			pstmt.setString(6, h.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(h.getDataNascimento());
			Date nasc = sdf.parse(nascString);
			h.setDataNascimento(nasc);
			pstmt.setDate(7, new java.sql.Date(nasc.getTime()));
			// se "A" hospede Ativo, caso contrário, "I" para Inativo
			pstmt.setString(8, String.valueOf(h.getStatus()));
			pstmt.setInt(9, h.getNumResidencia());
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				EnderecoControl ec = new EnderecoControl();
				ec.insert(h.getEndereco());
				this.insert(h);
			} else if (errParser.contains("'cpf_UNIQUE'")){
				msgError("CPF já existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Hospede selectDocHospede(String doc) {
		Hospede hosp;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from hospede where cpf = ?");
			pstmt.setString(1, doc);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				hosp = new Hospede();
				while (rs.next()) {
					EnderecoControl ec = new EnderecoControl();
					hosp.setId(rs.getInt("id"));
					hosp.setEndereco(ec.selectCep(rs.getString("cep")));
					hosp.setCpf(rs.getString("cpf"));
					hosp.setNome(rs.getString("nome"));
					hosp.setTelefone(rs.getString("telefone"));
					hosp.setCelular(rs.getString("celular"));
					hosp.setEmail(rs.getString("email"));
					hosp.setDataNascimento(rs.getDate("dat_nascimento"));
					hosp.setStatus(rs.getString("status").charAt(0));
				}
				return hosp;
			}
		} catch (SQLException e) {
			String errParser = e.getMessage();
			if (errParser.contains("not found")) {
				msgError("Não encontrado","Hospede" , JOptionPane.CLOSED_OPTION);
			}
		}
		return null;
	}
}
