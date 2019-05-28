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
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class FuncionarioControl {

	public void insert(Funcionario f) {
		if (f.getEndereco() == null) {
			msgError("Endereco vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getCpf().trim().isEmpty()) {
			msgError("Cpf vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getNome().trim().isEmpty()) {
			msgError("Nome vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getTelefone().trim().isEmpty()) {
			msgError("Telefone vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		/* celular pode ser null
		if (f.getCelular().trim().isEmpty()) {
			msgError("Celular vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		/* email pode ser null
		if (f.getEmail().trim().isEmpty()) {
			msgError("Email vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		if (f.getDataNascimento() == null) {
			msgError("Data de Nascimento vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getLogin().trim().isEmpty()) {
			msgError("Login vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getSenha().trim().isEmpty()) {
			msgError("Senha vazia", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getStatus() == Character.MIN_VALUE) {
			msgError("Status vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (f.getStatus() != 'A') {
			if (f.getStatus() != 'I' ) {
				msgError("Status deve ser A (Ativo) ou I (Inativo)", "Status Incorreto", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}		
		if (f.getTipoFuncionario() == null) {
			msgError("Tipo funcionario vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			// omitir o ID já que no banco ele é AUTO_INCREMENT
			pstmt = con.prepareStatement(" insert into funcionario "
					+ " (cep, cpf, nome, telefone, celular, email, dat_nascimento, status, login, senha, tipo_funcionario, num_residencia) "
					+ " values " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, f.getEndereco().getCep());
			pstmt.setString(2, f.getCpf());
			pstmt.setString(3, f.getNome());
			pstmt.setString(4, f.getTelefone());
			pstmt.setString(5, f.getCelular());
			pstmt.setString(6, f.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(f.getDataNascimento());
			Date nasc = sdf.parse(nascString);
			// f.setDataNascimento(nasc);
			pstmt.setDate(7, new java.sql.Date(nasc.getTime()));
			// se "A" funcionario Ativo, caso contrário, "I" para Inativo
			pstmt.setString(8, String.valueOf(f.getStatus()));
			pstmt.setString(9, f.getLogin());
			pstmt.setString(10, f.getSenha());
			pstmt.setString(11, EFuncionario.ADMINISTRADOR.role);
			pstmt.setInt(12, f.getNumResidencia());
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				EnderecoControl ec = new EnderecoControl();
				ec.insert(f.getEndereco());
				this.insert(f);
			} else if (errParser.contains("'cpf_UNIQUE'")) {
				msgError("CPF já existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			} else {
				msgError("Erro desconhecido...\nContate um administrador", "Funcionario", JOptionPane.ERROR_MESSAGE);
				except.printStackTrace();
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Funcionario selectDocFuncionario(String docFuncionario) {
		Funcionario func;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from funcionario where cpf = ?");
			pstmt.setString(1, docFuncionario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				func = new Funcionario();
				do {
					EnderecoControl ec = new EnderecoControl();
					func.setId(rs.getInt("id"));
					func.setEndereco(ec.selectCep(rs.getString("cep")));
					func.setCpf(rs.getString("cpf"));
					func.setNome(rs.getString("nome"));
					func.setTelefone(rs.getString("telefone"));
					func.setCelular(rs.getString("celular"));
					func.setEmail(rs.getString("email"));
					func.setDataNascimento(rs.getDate("dat_nascimento"));
					func.setStatus(rs.getString("status").charAt(0));
					func.setLogin(rs.getString("login"));
					func.setSenha(rs.getString("senha"));
					func.setTipoFuncionario((EFuncionario.valueOf(rs.getString("tipo_funcionario"))));
				} while (rs.next());
				return func;
			}
		} catch (SQLException except) {
			String errParser = except.getMessage();
			if (errParser.contains("Unknown column")) {
				msgError("Não encontrado", "Funcionario", JOptionPane.CLOSED_OPTION);
			} else if (errParser.contains("Unknown database")) {
				msgError("Base de dados desconhecida...", "Funcionario", JOptionPane.CLOSED_OPTION);
			} else {
				except.printStackTrace();
			}
		}
		return null;
	}
}
