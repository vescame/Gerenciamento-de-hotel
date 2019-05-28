package src.com.fatec.gerenciamentohotel.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class TipoDeQuartoControl {

	public void insert(TipoDeQuarto t) {
		if (t.getTipo().trim().isEmpty()) {
			msgError("Tipo Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getValorDiaria() == 0) {
			msgError("Diaria Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (t.getQuantidadeAdultos() == 0) {
			msgError("Quantidade Adultos Vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" Insert into tipo_quarto "
					+ " (tipo_quarto, preco, qtd_adultos, qtd_criancas) values  (?, ?, ?, ?) ");
			pstmt.setString(1, t.getTipo());
			pstmt.setFloat(2, t.getValorDiaria());
			pstmt.setInt(3, t.getQuantidadeAdultos());
			pstmt.setInt(4, t.getQuantidadeCriancas());
			pstmt.executeQuery();
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Duplicate entry")) {
				msgError("Tipo de quarto já existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				except.printStackTrace();
				msgError("Erro desconhecido...\nContate um administrador", "Tipo De Quarto", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public TipoDeQuarto selectTipoQuarto(long id) {
		TipoDeQuarto tQuarto;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from tipo_quarto where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				tQuarto = new TipoDeQuarto();
				while (rs.next()) {
					tQuarto.setId(rs.getInt("id"));
					tQuarto.setTipo(rs.getString("tipo_quarto"));
					tQuarto.setValorDiaria(rs.getFloat("preco"));
					tQuarto.setQuantidadeAdultos(rs.getShort("qtd_adultos"));
					tQuarto.setQuantidadeCriancas(rs.getShort("qtd_criancas"));
				}
				return tQuarto;
			}
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				except.printStackTrace();
			}
			
		}
		return null;
	}
}
