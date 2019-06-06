package src.com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import src.com.fatec.gerenciamentohotel.control.dao.HospedeDAO;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class HospedeControl {

	public void insert(Hospede h) {
		if (h.getEndereco() == null) {
			userMessage("Erro", "Insira um Endereço válido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getCpf().trim().isEmpty()) {
			userMessage("Erro", "CPF de hóspede vazio", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getNome().trim().isEmpty()) {
			userMessage("Erro", "Preencha o Nome", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getTelefone().trim().isEmpty()) {
			userMessage("Erro", "Preencha ao menos o Telefone",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getEmail().trim().isEmpty()) {
			userMessage("Erro", "Preenche o E-Mail.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getDataNascimento() == null) {
			userMessage("Erro", "Data de Nascimento vazia",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (h.getStatus() == Character.MIN_VALUE) {
			userMessage("Erro", "Status vazio", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (h.getStatus() != 'A') {
			if (h.getStatus() != 'I') {
				userMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		try {
			HospedeDAO hdao = new HospedeDAO();
			hdao.insert(h);
			userMessage("Hospede", "Hospede Cadastrado!", JOptionPane.WARNING_MESSAGE);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public Hospede selectCPF(String cpf) {
		try {
			HospedeDAO hdao = new HospedeDAO();
			return hdao.select(cpf);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(), JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public List<Hospede> selectTodos() {
		try {
			return new HospedeDAO().selectAll("");
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
}
