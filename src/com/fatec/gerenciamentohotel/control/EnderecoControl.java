package src.com.fatec.gerenciamentohotel.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoControl {
	/*
	 * id cep rua numero bairro cidade uf
	 */

	public void insert(Endereco e) {
		if (e.getCep().trim().isEmpty()) {
			msgError("Cep Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getRua().trim().isEmpty()) {
			msgError("Rua Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getBairro().trim().isEmpty()) {
			msgError("Bairro Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getCidade().trim().isEmpty()) {
			msgError("Cidade Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getUf().trim().isEmpty()) {
			msgError("UF Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Utilizando banco de dados
		PreparedStatement pstmt;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			pstmt = con.prepareStatement("insert into endereco (cep, rua, bairro, cidade, uf) values (?, ?, ?, ?, ?)");
			pstmt.setString(1, e.getCep());
			pstmt.setString(2, e.getRua());
			pstmt.setString(3, e.getBairro());
			pstmt.setString(4, e.getCidade());
			pstmt.setString(5, e.getUf());
			pstmt.execute();
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Duplicate entry")) {
				msgError("Cep já cadastrado", "Aviso!", JOptionPane.WARNING_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "Endereco", JOptionPane.ERROR_MESSAGE);
				except.printStackTrace();
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Endereco selectCep(String cep) {
		Endereco e;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from endereco where cep = ?");
			pstmt.setInt(1, Integer.parseInt(cep));
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				e = new Endereco();
				do {
					e.setCep(rs.getString("cep"));
					e.setRua(rs.getString("rua"));
					e.setBairro(rs.getString("bairro"));
					e.setCidade(rs.getString("cidade"));
					e.setUf(rs.getString("uf"));
				} while (rs.next());
				return e;
			}
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				msgError("Coluna incorreta", "Endereco", JOptionPane.CLOSED_OPTION);
			} else if (errParser.contains("Unknown database")) {
				msgError("Base de dados desconhecida...", "Endereco", JOptionPane.CLOSED_OPTION);
			} else {
				except.printStackTrace();
			}
		}
		return null;
	}
}
