package src.com.fatec.gerenciamentohotel.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import src.com.fatec.gerenciamentohotel.control.connection.ConnectionDB;
import src.com.fatec.gerenciamentohotel.control.dao.exceptions.EnderecoDAOException;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class EnderecoDAO implements IObjectDAO<Endereco, String> {

    @Override
    public void insert(Endereco e) throws EnderecoDAOException {
	try {
	    PreparedStatement pstmt;
	    Connection con = ConnectionDB.getInstance().getConnection();
	    pstmt = con.prepareStatement(
		    "insert into endereco (cep, rua, bairro, cidade, uf) values (?, ?, ?, ?, ?)");
	    pstmt.setString(1, e.getCep());
	    pstmt.setString(2, e.getRua());
	    pstmt.setString(3, e.getBairro());
	    pstmt.setString(4, e.getCidade());
	    pstmt.setString(5, e.getUf());
	    pstmt.execute();
	} catch (SQLException except) {
	    throw new EnderecoDAOException("Erro ao inserir Endereço");
	}

    }

    @Override
    public Endereco select(String cep) throws EnderecoDAOException {
	Endereco e;
	try {
	    Connection con = ConnectionDB.getInstance().getConnection();
	    PreparedStatement pstmt = con
		    .prepareStatement("select * from endereco where cep = ?");
	    pstmt.setInt(1, Integer.parseInt(cep));
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.first()) {
		e = new Endereco();
		do {
		    e.setCep(rs.getString("cep"));
		    e.setRua(rs.getString("rua"));
		    e.setBairro(rs.getString("bairro"));
		    e.setCidade(rs.getString("cidade"));
		    e.setUf(rs.getString("uf"));
		} while (rs.next());
		return e;
	    }
	} catch (SQLException | ParseException except) {
	    throw new EnderecoDAOException("Erro ao buscar endereco");
	}
	return null;
    }

    @Override
    public List<Endereco> selectAll(String cep) throws EnderecoDAOException {
	return null;
    }

}
