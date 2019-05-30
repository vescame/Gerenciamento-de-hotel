package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.FuncionarioDAOException;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class FuncionarioDAO implements IObjectDAO<Funcionario, String> {

    @Override
    public void insert(Funcionario f) throws FuncionarioDAOException {
	try {
	    Connection con = ConnectionDB.getInstance().getConnection();
	    PreparedStatement pstmt;
	    // omitir o ID já que no banco ele é AUTO_INCREMENT
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
	    pstmt.setString(11, EFuncionario.ADMINISTRADOR.role);
	    pstmt.setInt(12, f.getNumResidencia());
	    pstmt.executeQuery();
	} catch (SQLException | ParseException except) {
	    throw new FuncionarioDAOException("Erro ao inserir funcionario");
	}
    }

    @Override
    public Funcionario select(String docFuncionario)
	    throws FuncionarioDAOException {
	Funcionario func;
	try {
	    Connection con = ConnectionDB.getInstance().getConnection();
	    PreparedStatement pstmt = con.prepareStatement(
		    "select * from funcionario where cpf = ?");
	    pstmt.setString(1, docFuncionario);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.first()) {
		func = new Funcionario();
		do {
		    EnderecoControl ec = new EnderecoControl();
		    func.setCpf(rs.getString("cpf"));
		    func.setEndereco(ec.selectCep(rs.getString("cep")));
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
		} while (rs.next());
		return func;
	    }
	} catch (SQLException except) {
	    except.printStackTrace();
	    throw new FuncionarioDAOException("Erro ao buscar funcionário");
	}
	return null;
    }

    @Override
    public List<Funcionario> selectAll(String docFuncionario)
	    throws FuncionarioDAOException {
	return null;
    }
}
