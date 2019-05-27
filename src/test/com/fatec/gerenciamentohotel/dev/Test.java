package src.test.com.fatec.gerenciamentohotel.dev;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class Test {
	public static void main(String[] args) {
		Endereco e = new Endereco();

		e.setCep("03570200");
		e.setNumero(182);
		e.setBairro("Parque Savoy City");
		e.setCidade("São Paulo");
		e.setUf("SP");
		e.setRua("Rua José Torres Lima");

		Funcionario f = new Funcionario();

		f.setEndereco(e);
		f.setCpf("43207052894");
		f.setNome("Vinicius Escame");
		f.setTelefone("151516");
		f.setCelular("011987628162");
		f.setEmail("v.escame@gmail.com");

		try {
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nascString = "11/01/1996";
			Date nasc = sdf.parse(nascString);
			f.setDataNascimento(nasc);
		} catch (ParseException except) {
			except.printStackTrace();
		}

		f.setLogin("vesca");
		f.setSenha("vesca1");
		// f.setStatus('A');
		f.setTipoFuncionario(EFuncionario.ADMINISTRADOR);

		FuncionarioControl fc = new FuncionarioControl();
		fc.insert(f);

		try {
			Funcionario f1 = fc.selectDocFuncionario(f.getCpf());
			System.out.println(f1);
			System.out.println(fc.selectDocFuncionario("125").getNome());
		} catch (NullPointerException except) {
			except.printStackTrace();
			System.out.println("Não há registros deste documento para este funcionário");
		}
	}
}
