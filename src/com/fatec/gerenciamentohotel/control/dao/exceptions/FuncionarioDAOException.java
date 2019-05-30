package src.com.fatec.gerenciamentohotel.control.dao.exceptions;

import src.com.fatec.gerenciamentohotel.control.dao.DAOException;

public class FuncionarioDAOException extends DAOException {
	private static final long serialVersionUID = 1L;
	private final String desc;
	
	public FuncionarioDAOException(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String getMessage() {
		// return super.getMessage();
		return String.format("Erro DAO: "+
				"[ %s ]", this.desc);
	}
	
}
