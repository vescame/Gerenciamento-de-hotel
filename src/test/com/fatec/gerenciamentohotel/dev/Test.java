package src.test.com.fatec.gerenciamentohotel.dev;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.ReservaControl;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.Reserva;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

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
		
		TipoDeQuarto t = new TipoDeQuarto();
		t.setTipo("Forever Alone");
		t.setValorDiaria(35.50f);
		t.setQuantidadeAdultos((short) 1);
		t.setQuantidadeCriancas((short) 0);

		TipoDeQuartoControl tqc = new TipoDeQuartoControl();
		tqc.insert(t);
		
		Quarto q = new Quarto();
		q.setNumQuarto(125);
		q.setAndar((short) 1);
		q.setTipoDeQuarto(tqc.selectTipoQuarto(1));
		
		QuartoControl qc = new QuartoControl();
		qc.insert(q);
		
		Reserva r0 = new Reserva();
		r0.setHospede(hc.selectDocHospede("43207052894"));
		r0.setFuncionario(fc.selectDocFuncionario("43207052894"));
		r0.setQuarto(qc.selectQuarto(125));
		r0.setStatus('I');
		r0.setCheckIn(new Date());
		
		Reserva r1 = new Reserva();
		r1.setHospede(hc.selectDocHospede("43207052894"));
		r1.setFuncionario(fc.selectDocFuncionario("43207052894"));
		r1.setQuarto(qc.selectQuarto(125));
		r1.setStatus('I');
		r1.setCheckIn(new Date());
		
		Reserva r2 = new Reserva();
		r2.setHospede(hc.selectDocHospede("43207052894"));
		r2.setFuncionario(fc.selectDocFuncionario("43207052894"));
		r2.setQuarto(qc.selectQuarto(125));
		r2.setStatus('A');
		r2.setCheckIn(new Date());
		
		ReservaControl rc = new ReservaControl();
		
		rc.insert(r0);
		rc.insert(r1);
		rc.insert(r2);
		try {
			/*
			// select funcionario
			System.out.println(fc.selectDocFuncionario(f.getCpf()).getNome());

			// select hospede
			System.out.println(hc.selectDocHospede(h.getCpf()).getNome());

			// select tipo quarto
			System.out.println(tqc.selectTipoQuarto(1));
			
			// select quarto
			System.out.println(q);
			System.out.println(qc.selectQuarto(125));
			
			// select reserva
			System.out.println(rc.selectReserva(r.getHospede().getCpf()));
			*/
			
			// select historico de reservas de hospede
			//System.out.println(rc.selectHistoricoReservas("43207052894"));
		} catch (NullPointerException except) {
			except.printStackTrace();
			System.out.println("Não há registros");
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

