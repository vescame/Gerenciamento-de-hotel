package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.DAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.dao.IObjectDAO;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class FuncionarioDAO implements IObjectDAO<Funcionario, String> {

	@Override
	public void insert(Funcionario f) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(
					" insert into funcionario values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, f.getCpf());
			pstmt.setString(2, f.getEndereco().getCep());
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
			pstmt.setString(11, f.getTipoFuncionario().role);
			pstmt.setInt(12, f.getNumResidencia());
			pstmt.executeQuery();
		} catch (SQLException | ParseException except) {
			final String errParser = except.getMessage();
			if (errParser.contains("foreign key constraint")) {
				throw new DAOException(
						"Endereco " + f.getEndereco().getCep() + " nao existe");
			} else if (errParser.contains("Duplicate entry")) {
				throw new DAOException(
						"Funcionario " + f.getCpf() + " ja existe");
			} else {
				throw new DAOException("Erro ao inserir funcionario");
			}
		}
	}

	@Override
	public Funcionario select(String cpf) throws DAOException {
		Funcionario func;
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select * from funcionario where cpf = ?");
			pstmt.setString(1, cpf);
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
				throw new DAOException("Funcionario nao encontrado.");
			}
		} catch (SQLException except) {
			except.printStackTrace();
			throw new DAOException("Erro ao buscar funcionario");
		}
	}

	@Override
	public List<Funcionario> selectAll(String docFuncionario)
			throws DAOException {
		Funcionario func;
		List<Funcionario> l = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select * from funcionario");
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				do {
					func = new Funcionario();
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
					l.add(func);
				} while (rs.next());
				return l;
			} else {
				throw new DAOException("Nao ha funcionarios");
			}
		} catch (SQLException except) {
			except.printStackTrace();
			throw new DAOException("Erro ao buscar funcionario");
		}
	}

	@Override
	public void update(Funcionario f) throws DAOException {
		try {
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(
					" update funcionario set cep = ?, "
					+ "nome = ?,"
					+ "telefone = ?, celular = ?,"
					+ "email = ?,"
					+ "dat_nascimento = ?,"
					+ "num_residencia = ?,"
					+ "login = ?, senha = ?,"
					+ "tipo_funcionario = ? where cpf = ?");
			pstmt.setString(1, f.getEndereco().getCep());
			pstmt.setString(2, f.getNome());
			pstmt.setString(3, f.getTelefone());
			pstmt.setString(4, f.getCelular());
			pstmt.setString(5, f.getEmail());
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = sdf.format(f.getDataNascimento());
			Date nasc = sdf.parse(nascString);
			pstmt.setDate(6, new java.sql.Date(nasc.getTime()));
			pstmt.setInt(7, f.getNumResidencia());
			pstmt.setString(8, f.getLogin());
			pstmt.setString(9, f.getSenha());
			pstmt.setString(10, f.getTipoFuncionario().role);
			pstmt.setString(11, f.getCpf());
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if (res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel alterar Funcionario");
		}
	}

	@Override
	public void delete(String cpf) throws DAOException {
		/* funcionarios nao sao deletados, sao inativados */
		try {
			Funcionario f = select(cpf);
			String ativarOuInativar = "I";
			if (f.getStatus() == 'I') {
				ativarOuInativar = "A";
			}
			Connection con = ConnectionDB.getInstance().getConnection();
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(
					" update funcionario set status = ? where cpf = ?");
			pstmt.setString(1, ativarOuInativar);
			pstmt.setString(2, cpf);
			final int res = pstmt.executeUpdate();
			final int resInesperado = 0;
			if (res == resInesperado) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossivel alterar Funcionario");
		}
	}
}
