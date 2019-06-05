package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Pessoa;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class LoginDAO implements IObjectDAO<Pessoa, String> {

	public Hospede hospede(String cpf) throws DAOException {
		return new HospedeDAO().select(cpf);
	}

	public Funcionario funcionario(String login, String senha)
			throws DAOException {
		Funcionario func;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from funcionario where login = ? and senha = ?");
			pstmt.setString(1, login);
			pstmt.setString(2, senha);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				func = new Funcionario();
				do {
					EnderecoDAO edao = new EnderecoDAO();
					func.setCpf(rs.getString("cpf"));
					func.setEndereco(edao.select(rs.getString("cep")));
					func.setNome(rs.getString("nome"));
					func.setTelefone(rs.getString("telefone"));
					func.setCelular(rs.getString("celular"));
					func.setEmail(rs.getString("email"));
					func.setDataNascimento(rs.getDate("dat_nascimento"));
					func.setStatus(rs.getString("status").charAt(0));
					func.setLogin(rs.getString("login"));
					func.setSenha(rs.getString("senha"));
					func.setTipoFuncionario((EFuncionario
							.valueOf(rs.getString("tipo_funcionario"))));
					func.setNumResidencia(rs.getInt("num_residencia"));
				} while (rs.next());
				return func;
			} else {
				throw new DAOException(
						"Nao foi possivel logar com estas credenciais. Tente novamente.");
			}
		} catch (SQLException except) {
			except.printStackTrace();
			throw new DAOException("Erro ao buscar Login");
		}
	}

	@Override
	public List<Pessoa> selectAll(String docFuncionario) throws DAOException {
		return null;
	}

	@Override
	public Pessoa select(String obj) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Pessoa obj) throws DAOException {
		// TODO Auto-generated method stub

	}
}
