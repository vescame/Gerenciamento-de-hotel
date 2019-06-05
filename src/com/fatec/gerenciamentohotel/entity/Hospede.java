package src.com.fatec.gerenciamentohotel.entity;

/*
import java.util.ArrayList;
import java.util.List;
*/

public class Hospede extends Pessoa {
	/*
	 * cpf cep nome telefone celular email dat_nascimento status num_residencia
	 */

	@Override
	public String toString() {
		return "Hospede [getId()=" + getId() + "\ngetEndereco()="
				+ getEndereco() + "\ngetCpf()=" + getCpf() + "\ngetNome()="
				+ getNome() + "\ngetTelefone()=" + getTelefone()
				+ "\ngetCelular()=" + getCelular() + "\ngetEmail()="
				+ getEmail() + "\ngetDataNascimento()=" + getDataNascimento()
				+ "\ngetStatus()=" + getStatus() + "\ngetNumResidencia()="
				+ getNumResidencia() + "]";
	}

}
