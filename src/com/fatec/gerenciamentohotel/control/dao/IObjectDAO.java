package src.com.fatec.gerenciamentohotel.control.dao;

import java.util.List;

public interface IObjectDAO<T, S> {
	public void insert(T obj) throws DAOException;
	public T select(S obj) throws DAOException;
	public List<T> selectAll(S obj) throws DAOException;
}
