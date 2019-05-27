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

	public void insert(Endereco e) {
		if (e.getCep().trim().isEmpty()) {
			msgError("Cep Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (e.getNumero() == 0) {
			msgError("Numero vazio", "Erro", JOptionPane.ERROR_MESSAGE);
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
			pstmt = con.prepareStatement("insert into endereco values" + "(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, e.getCep());
			pstmt.setString(2, e.getRua());
			pstmt.setInt(3, e.getNumero());
			pstmt.setString(4, e.getBairro());
			pstmt.setString(5, e.getCidade());
			pstmt.setString(6, e.getUf());
			pstmt.execute();
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Duplicate entry")) {
				msgError("Cep já cadastrado", "Aviso!", JOptionPane.WARNING_MESSAGE);
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
			PreparedStatement pstmt = con.prepareStatement("Select * from endereco where cep = ?");
			pstmt.setInt(1, Integer.parseInt(cep));
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				e = new Endereco();
				while (rs.next()) {
					e.setCep(rs.getString("cep"));
					e.setRua(rs.getString("rua"));
					e.setNumero(rs.getInt("numero"));
					e.setBairro(rs.getString("bairro"));
					e.setCidade(rs.getString("cidade"));
					e.setUf(rs.getString("uf"));
				}
				return e;
			}
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			errParser.toUpperCase();
		}
		return null;
	}
}
