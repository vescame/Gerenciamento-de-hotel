package com.fatec.gerenciamentohotel.control;

import java.util.List;

import javax.swing.JOptionPane;

import com.fatec.gerenciamentohotel.control.dao.HospedeDAO;
import com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import com.fatec.gerenciamentohotel.entity.Hospede;

public class HospedeControl {

	public void insert(Hospede h) {
		if (!validarHospede(h)) {
			return;
		}
		try {
			HospedeDAO hdao = new HospedeDAO();
			hdao.insert(h);
			userMessage("Hospede", "Hospede Cadastrado!",
					JOptionPane.NO_OPTION);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public Hospede selectCPF(String cpf) {
		try {
			HospedeDAO hdao = new HospedeDAO();
			return hdao.select(cpf);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
	public void alterarHospede(Hospede h) {
		if (!validarHospede(h)) {
			return;
		}
		try {
			new HospedeDAO().update(h);
			userMessage("Hospede", "Hospede alterado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void inativarHospede(String cpf) {
		try {
			new HospedeDAO().delete(cpf);
			userMessage("Hospede", "Hospede In / Ativado!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			userMessage("Hospede", e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
		}
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
	
	private void userMessage(String titulo, String mensagem, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}
	
	private boolean validarHospede(Hospede h) {
		if (h.getEndereco() == null) {
			userMessage("Erro", "Endereco vazio", JOptionPane.ERROR_MESSAGE);
		}
		if (h.getCpf().trim().isEmpty()) {
			userMessage("Erro", "CPF de hospede vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (h.getNome().trim().isEmpty()) {
			userMessage("Erro", "Preencha o Nome", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (h.getTelefone().trim().isEmpty()) {
			userMessage("Erro", "Preencha ao menos o Telefone",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (h.getEmail().trim().isEmpty()) {
			userMessage("Erro", "Preencha o E-Mail.", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (h.getDataNascimento() == null) {
			userMessage("Erro", "Data de Nascimento vazia",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (h.getStatus() == Character.MIN_VALUE) {
			userMessage("Erro", "Status vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (h.getStatus() != 'A') {
			if (h.getStatus() != 'I') {
				userMessage("Status Incorreto",
						"Status deve ser A (Ativo) ou I (Inativo)",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
