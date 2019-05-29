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
		/*
		 * celular pode ser null if (h.getCelular().trim().isEmpty()) {
		 * msgError("Celular vazio", "Erro", JOptionPane.ERROR_MESSAGE); return; }
		 */
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
		} else if (h.getStatus() != 'A') {
			if (h.getStatus() != 'I' ) {
				msgError("Status deve ser A (Ativo) ou I (Inativo)", "Status Incorreto", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" insert into hospede "
					// + " (cep, cpf, nome, telefone, celular, email, dat_nascimento, status, num_residencia) "
					+ " values " + " (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, h.getCpf());
			pstmt.setString(2, h.getEndereco().getCep());
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
			} else if (errParser.contains("'cpf_UNIQUE'")) {
				msgError("CPF já existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "Hospede", JOptionPane.ERROR_MESSAGE);
				except.printStackTrace();
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
			PreparedStatement pstmt = con.prepareStatement("select * from hospede where cpf = ?");
			pstmt.setString(1, doc);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				hosp = new Hospede();
				do {
					EnderecoControl ec = new EnderecoControl();
					hosp.setCpf(rs.getString("cpf"));
					hosp.setEndereco(ec.selectCep(rs.getString("cep")));
					hosp.setNome(rs.getString("nome"));
					hosp.setTelefone(rs.getString("telefone"));
					hosp.setCelular(rs.getString("celular"));
					hosp.setEmail(rs.getString("email"));
					hosp.setDataNascimento(rs.getDate("dat_nascimento"));
					hosp.setNumResidencia(rs.getInt("num_residencia"));
					hosp.setStatus(rs.getString("status").charAt(0));
				} while (rs.next());
				return hosp;
			}
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				msgError("Coluna incorreta", "Hospede", JOptionPane.CLOSED_OPTION);
			} else if (errParser.contains("Unknown database")) {
				msgError("Base de dados desconhecida...", "Hospede", JOptionPane.CLOSED_OPTION);
			} else {
				except.printStackTrace();
			}
		}
		return null;
	}
}
