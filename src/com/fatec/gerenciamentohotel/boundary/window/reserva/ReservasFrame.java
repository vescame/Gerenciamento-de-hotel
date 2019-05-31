package src.com.fatec.gerenciamentohotel.boundary.window.reserva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.ReservaControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.Reserva;
import src.com.fatec.gerenciamentohotel.entity.enums.EFuncionario;

public class ReservasFrame extends JInternalFrame {
	private static final long serialVersionUID = 2787460975839782982L;
	private JTextField textFieldHospede;
	private JTextField textFieldQuarto;
	private JTextField textFieldCheckIn;
	private JLabel lblHspede;
	private JButton btnBuscarHospede;
	private JButton btnNovoHospede;
	private JButton btnBuscarQuarto;
	private JButton btnNovoQuarto;
	private JLabel lblQuarto;
	private JLabel lblCheckin;
	private JButton btnInserirData;
	private JButton btnCancelar;
	private JButton btnGerarReserva;
	private Hospede h;
	private Quarto q;

	public ReservasFrame() {

		setTitle("Cadastrar Nova Reserva");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(null);

		lblHspede = new JLabel("Hóspede: ");
		lblHspede.setBounds(12, 15, 68, 15);
		getContentPane().add(lblHspede);

		textFieldHospede = new JTextField();
		textFieldHospede.setBounds(98, 10, 215, 25);
		getContentPane().add(textFieldHospede);
		textFieldHospede.setColumns(10);

		btnBuscarHospede = new JButton("Buscar");
		btnBuscarHospede.setBounds(325, 10, 114, 25);
		btnBuscarHospede.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				h = new HospedeControl().selectCPF(textFieldHospede.getText());
				if (h != null) {
					JOptionPane.showMessageDialog(null, "Hospede OK",
							"Informativo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnBuscarHospede);

		btnNovoHospede = new JButton("Novo");
		btnNovoHospede.setBounds(451, 10, 114, 25);
		getContentPane().add(btnNovoHospede);

		textFieldQuarto = new JTextField();
		textFieldQuarto.setColumns(10);
		textFieldQuarto.setBounds(98, 47, 215, 25);
		getContentPane().add(textFieldQuarto);

		btnBuscarQuarto = new JButton("Buscar");
		btnBuscarQuarto.setBounds(325, 47, 114, 25);
		btnBuscarQuarto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				q = new QuartoControl().selectNumQuarto(
						Long.parseLong(textFieldQuarto.getText()));
				if (q != null) {
					JOptionPane.showMessageDialog(null, "Quarto OK",
							"Informativo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnBuscarQuarto);

		btnNovoQuarto = new JButton("Novo");
		btnNovoQuarto.setBounds(451, 47, 114, 25);
		getContentPane().add(btnNovoQuarto);

		lblQuarto = new JLabel("Quarto: ");
		lblQuarto.setBounds(12, 52, 68, 15);
		getContentPane().add(lblQuarto);

		textFieldCheckIn = new JTextField();
		textFieldCheckIn.setEnabled(false);
		textFieldCheckIn.setColumns(10);
		textFieldCheckIn.setBounds(98, 84, 215, 25);
		getContentPane().add(textFieldCheckIn);

		lblCheckin = new JLabel("CheckIn: ");
		lblCheckin.setBounds(12, 89, 68, 15);
		getContentPane().add(lblCheckin);

		btnInserirData = new JButton("Inserir Data");
		btnInserirData.setBounds(325, 84, 240, 25);
		btnInserirData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldCheckIn.setText(String.valueOf(
						new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			}
		});
		getContentPane().add(btnInserirData);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(309, 231, 114, 25);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);

		btnGerarReserva = new JButton("Gerar Reserva");
		btnGerarReserva.setBounds(435, 231, 130, 25);
		btnGerarReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Endereco end = new Endereco();
				end.setCep("03070200");
				end.setBairro("Bairro Funcionario");
				end.setCidade("SAO PAULO");
				end.setRua("Rua funcionario");
				end.setUf("SP");

				Funcionario f = new Funcionario();
				f.setCpf("55666333251");
				f.setTelefone("25556662");
				f.setDataNascimento(new Date());
				f.setEmail("funcmail@gmail");
				f.setEndereco(end);
				f.setLogin("logfunc");
				f.setSenha("senhafunc");
				f.setNumResidencia(485);
				f.setNome("Nome Funcionario");
				f.setStatus('A');
				f.setTipoFuncionario(EFuncionario.ADMINISTRADOR);

				new FuncionarioControl().novoFuncionario(f);

				Reserva r = new Reserva();
				r.setFuncionario(f);
				r.setHospede(h);
				r.setQuarto(q);
				r.setStatus('A');

				Date checkin = null;
				try {
					checkin = new SimpleDateFormat("dd/MM/yyyy")
							.parse(textFieldCheckIn.getText());
				} catch (ParseException ex) {
					ex.getMessage();
					checkin = new Date();
				}
				r.setCheckIn(checkin);
				if (!q.getTipoDeQuarto().getTipo().isEmpty()) {
					if (!h.getEmail().isEmpty()) {
						new ReservaControl().insert(r);
					}
				}
			}
		});
		getContentPane().add(btnGerarReserva);

	}
}
