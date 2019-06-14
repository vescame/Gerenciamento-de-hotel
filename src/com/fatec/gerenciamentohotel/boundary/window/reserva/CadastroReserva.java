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

import src.com.fatec.gerenciamentohotel.boundary.window.MainWindow;
import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.control.QuartoControl;
import src.com.fatec.gerenciamentohotel.control.ReservaControl;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;
import src.com.fatec.gerenciamentohotel.entity.Hospede;
import src.com.fatec.gerenciamentohotel.entity.Quarto;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class CadastroReserva extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospede;
	private JLabel lblQuarto;
	private JLabel lblCheckin;

	private JTextField textFieldHospede;
	private JTextField textFieldQuarto;
	private JTextField textFieldCheckIn;

	private JButton btnBuscarHospede;
	private JButton btnBuscarQuarto;
	private JButton btnInserirData;
	private JButton btnCancelar;
	private JButton btnGerarReserva;

	private Hospede h = null;
	private Quarto q = null;
	private Funcionario f = null;

	public CadastroReserva() {
		setTitle("Cadastrar Nova Reserva");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(null);

		lblHospede = new JLabel("Hóspede: ");
		lblHospede.setBounds(12, 15, 68, 15);
		getContentPane().add(lblHospede);

		textFieldHospede = new JTextField();
		textFieldHospede.setBounds(98, 10, 215, 25);
		getContentPane().add(textFieldHospede);
		textFieldHospede.setColumns(10);

		btnBuscarHospede = new JButton("Confirmar");
		btnBuscarHospede.setBounds(325, 10, 114, 25);
		btnBuscarHospede.setActionCommand("btn_buscar_hospede");
		btnBuscarHospede.addActionListener(this);
		getContentPane().add(btnBuscarHospede);

		textFieldQuarto = new JTextField();
		textFieldQuarto.setColumns(10);
		textFieldQuarto.setBounds(98, 47, 215, 25);
		getContentPane().add(textFieldQuarto);

		btnBuscarQuarto = new JButton("Confirmar");
		btnBuscarQuarto.setBounds(325, 47, 114, 25);
		btnBuscarQuarto.setActionCommand("btn_buscar_quarto");
		btnBuscarQuarto.addActionListener(this);
		getContentPane().add(btnBuscarQuarto);

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
		btnInserirData.setActionCommand("btn_dat_checkin");
		btnInserirData.addActionListener(this);
		getContentPane().add(btnInserirData);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(309, 231, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnGerarReserva = new JButton("Gerar Reserva");
		btnGerarReserva.setBounds(435, 231, 130, 25);
		btnGerarReserva.setActionCommand("btn_gerar_reserva");
		btnGerarReserva.addActionListener(this);
		getContentPane().add(btnGerarReserva);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar_hospede")) {
			h = new HospedeControl().selectCPF(textFieldHospede.getText());
			if (h != null) {
				JOptionPane.showMessageDialog(null, "Hospede OK", "Informativo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (nomeEvento.equals("btn_buscar_quarto")) {
			q = new QuartoControl()
					.selectNumQuarto(Long.parseLong(textFieldQuarto.getText()));
			if (q != null && q.isDisponivel()) {
				JOptionPane.showMessageDialog(null, "Quarto OK", "Informativo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Quarto Ja esta sendo utilizado", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (nomeEvento.equals("btn_dat_checkin")) {
			textFieldCheckIn.setText(String.valueOf(
					new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
		} else if (nomeEvento.equals("btn_gerar_reserva")) {
			f = new FuncionarioControl()
					.selectCPF(MainWindow.getPessoaLogada().getCpf());

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
			}
			r.setCheckIn(checkin);

			new ReservaControl().insert(r);
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		}

	}
}
