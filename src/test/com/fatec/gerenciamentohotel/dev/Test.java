package src.test.com.fatec.gerenciamentohotel.dev;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class Test {
	public static void main(String[] args) {
		Endereco e = new Endereco();

		e.setCep("03570200");
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

		String strDate = "11/01/1996";
		f.setDataNascimento(customStrToDate(strDate));

		f.setLogin("vesca");
		f.setSenha("vesca1");
		f.setStatus('A');
		f.setTipoFuncionario(EFuncionario.ADMINISTRADOR);
		f.setNumResidencia(182);

		FuncionarioControl fc = new FuncionarioControl();
		fc.insert(f);

		Hospede h = new Hospede();
		h.setEndereco(e);
		h.setCpf("43207052894");
		h.setNome("Vinicius Escame");
		h.setTelefone("1127470008");
		h.setCelular("011987628162");
		h.setEmail("v.escame@outlook.com");
		h.setDataNascimento(customStrToDate(strDate));
		h.setStatus('I');
		h.setNumResidencia(182);
		
		HospedeControl hc = new HospedeControl();
		hc.insert(h);
		
		try {
			Funcionario f1 = fc.selectDocFuncionario(f.getCpf());
			System.out.println(f1);
			System.out.println(fc.selectDocFuncionario(f.getCpf()).getNome());
			System.out.println(h);
			System.out.println(fc.selectDocFuncionario(h.getCpf()).getNome());
		} catch (NullPointerException except) {
			except.printStackTrace();
			System.out.println("Não há registros deste documento para este funcionário");
		}
	}
	
	public static Date customStrToDate(String strDate) {
		Date nasc = null;
		try {
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			nasc = sdf.parse(strDate);
		} catch (ParseException except) {
			except.printStackTrace();
		}
		return nasc;
	}
}
