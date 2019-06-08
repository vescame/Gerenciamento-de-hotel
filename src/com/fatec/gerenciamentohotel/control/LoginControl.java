package src.com.fatec.gerenciamentohotel.control;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.LoginDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class LoginControl {

	public Funcionario select(String login, String senha) {
		try {
			return new LoginDAO().funcionario(login, senha);
		} catch (DAOException e) {
			msgError("Login", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public Hospede select(String cpf) {
		try {
			return new LoginDAO()
					.hospede(cpf.replaceAll("\\.", "").replace("-", ""));
		} catch (DAOException e) {
			msgError("Login", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void msgError(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
}
