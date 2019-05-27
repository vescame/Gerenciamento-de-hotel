package src.com.fatec.gerenciamentohotel.control;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncionarioControl {

	public void insert(Funcionario f) {
		/*if (f.getId() == 0) {
			msgError("Id vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
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
		if (f.getCelular().trim().isEmpty()) {
			msgError("Celular vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getEmail().trim().isEmpty()) {
			msgError("Email vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
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
		if (f.getStatus() == ' ') {
			msgError("Status vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (f.getTipoFuncionario() == null) {
			msgError("Tipo funcionario vazio", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(
					" Insert into funcionario "
					+ " (cep, cpf, nome, telefone, celular, email, dat_nascimento, status, login, senha, tipo_funcionario) "
					+ " values "
					+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, f.getEndereco().getCep());
			pstmt.setString(2, f.getCpf());
			pstmt.setString(3, f.getNome());
			pstmt.setString(4, f.getTelefone());
			pstmt.setString(5, f.getCelular());
			pstmt.setString(6, f.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(f.getDataNascimento());
			Date nascD = sdf.parse(nascString);
			f.setDataNascimento(nascD);
			pstmt.setDate(7, new java.sql.Date(nascD.getTime()));
			// se "A" funcionario Ativo, caso contrário, "I" para Inativo
			pstmt.setString(8, String.valueOf(f.getStatus()));
			pstmt.setString(9, f.getLogin());
			pstmt.setString(10, f.getSenha());
			pstmt.setString(11, EFuncionario.ADMINISTRADOR.role);
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				EnderecoControl ec = new EnderecoControl();
				ec.insert(f.getEndereco());
				this.insert(f);
			} else {
				except.printStackTrace();
			}
		}
	}

	private void msgError(String mensagem, String titulo, int errorType) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, errorType);
	}

	public Funcionario selectDocFuncionario(String doc) {
		Funcionario func;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from funcionario where cpf = ?");
			pstmt.setString(1, doc);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.wasNull()) {
				func = new Funcionario();
				while (rs.next()) {
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
				}
				return func;
			}
		} catch (SQLException e) {
			String errParser = e.getMessage();
			if (errParser.contains("not found")) {
				msgError("Não encontrado","Funcionario" , JOptionPane.CLOSED_OPTION);
			}
		}
		return null;
	}
}
