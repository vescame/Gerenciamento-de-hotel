package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.QuartoDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Quarto;

public class QuartoControl {

	public void insert(Quarto q) {
		if (q.getNumQuarto() == 0) {
			msgError("Erro", "Numero do Quarto nao pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getTipoDeQuarto() == null) {
			msgError("Erro", "Tipo de Quarto Vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (q.getAndar() == 0) {
			msgError("Erro", "O número do andar não pode ser \"0\"",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			QuartoDAO qdao = new QuartoDAO();
			qdao.insert(q);
		} catch (DAOException e) {
			msgError("Quarto", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Quarto selectNumQuarto(long id) {
		try {
			QuartoDAO qdao = new QuartoDAO();
			return qdao.select(String.valueOf(id));
		} catch (DAOException e) {
			msgError("Quarto", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void msgError(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
