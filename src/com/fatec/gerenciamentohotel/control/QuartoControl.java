package src.com.fatec.gerenciamentohotel.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class QuartoControl {

	public void insert(Quarto q) {
		if (q.getNumQuarto() == 0) {
			msgError("Numero do Quarto nao pode ser `0`", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getTipoDeQuarto() == null) {
			msgError("Tipo de Quarto Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getAndar() == 0) {
			msgError("N do Andar Vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// ID não é AUTO_INCREMENT, representa o numero da porta dos quartos
			pstmt = con.prepareStatement(" Insert into quarto values (?, ?, ?) ");
			pstmt.setInt(1, q.getNumQuarto());
			pstmt.setLong(2, q.getTipoDeQuarto().getId());
			pstmt.setShort(3, q.getAndar());
			pstmt.executeQuery();
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				msgError("Insira um codigo valido para o tipo de quarto", "Tipo de Quarto Invalido",
						JOptionPane.ERROR_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "Quarto", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Quarto selectQuarto(long id) {
		Quarto quar;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from quarto where num_quarto = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				quar = new Quarto();
				while (rs.next()) {
					TipoDeQuartoControl tqc = new TipoDeQuartoControl();
					quar.setNumQuarto(rs.getInt("num_quarto"));
					quar.setTipoDeQuarto(tqc.selectTipoQuarto(rs.getInt("id_tipo_quarto")));
					quar.setAndar(rs.getShort("andar"));
				}
				return quar;
			}
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				msgError("Coluna incorreta, solicite a correção ao administrador", "Quarto", JOptionPane.CLOSED_OPTION);
			} else {
				except.printStackTrace();
			}
		}
		return null;
	}
}
