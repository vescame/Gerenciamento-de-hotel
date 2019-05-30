package src.com.fatec.gerenciamentohotel.boundary.window.reserva;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

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
		getContentPane().add(btnBuscarHospede);

		btnNovoHospede = new JButton("Novo");
		btnNovoHospede.setBounds(451, 10, 114, 25);
		getContentPane().add(btnNovoHospede);

		textFieldQuarto = new JTextField();
		textFieldQuarto.setEnabled(false);
		textFieldQuarto.setColumns(10);
		textFieldQuarto.setBounds(98, 47, 215, 25);
		getContentPane().add(textFieldQuarto);

		btnBuscarQuarto = new JButton("Buscar");
		btnBuscarQuarto.setBounds(325, 47, 114, 25);
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
		getContentPane().add(btnInserirData);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(309, 231, 114, 25);
		getContentPane().add(btnCancelar);

		btnGerarReserva = new JButton("Gerar Reserva");
		btnGerarReserva.setBounds(435, 231, 130, 25);
		getContentPane().add(btnGerarReserva);

	}
}
