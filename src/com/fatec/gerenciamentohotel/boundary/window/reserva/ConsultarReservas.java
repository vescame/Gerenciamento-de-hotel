package src.com.fatec.gerenciamentohotel.boundary.window.reserva;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import src.com.fatec.gerenciamentohotel.control.ReservaControl;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ConsultarReservas extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtHospede;
	private JTextField txtQuarto;
	private JTextField txtValor;
	private JTextField txtDias;
	private JTextField txtCheckin;
	private JTextField txtCheckout;

	private JTable tblReservas;
	private List<Reserva> reservas = new ArrayList<>();

	public ConsultarReservas() {
		setTitle("Consulta Reservas");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 400);
		setLayout(new BorderLayout(15, 15));
		getContentPane().setLayout(null);

		JLabel lblHospede = new JLabel("Hospede:");
		lblHospede.setBounds(10, 20, 60, 13);
		getContentPane().add(lblHospede);

		txtHospede = new JTextField();
		txtHospede.setBounds(90, 17, 219, 19);
		getContentPane().add(txtHospede);
		txtHospede.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(330, 15, 90, 21);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		JLabel lblQuarto = new JLabel("Quarto:");
		lblQuarto.setBounds(10, 50, 46, 13);
		getContentPane().add(lblQuarto);

		txtQuarto = new JTextField();
		txtQuarto.setEditable(false);
		txtQuarto.setBounds(90, 46, 50, 19);
		getContentPane().add(txtQuarto);
		txtQuarto.setColumns(10);

		JLabel lblDias = new JLabel("Dias:");
		lblDias.setBounds(210, 50, 50, 13);
		getContentPane().add(lblDias);

		txtDias = new JTextField();
		txtDias.setEditable(false);
		txtDias.setBounds(280, 46, 95, 19);
		getContentPane().add(txtDias);

		JLabel lblValor = new JLabel("Total:");
		lblValor.setBounds(400, 50, 50, 13);
		getContentPane().add(lblValor);

		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setBounds(450, 46, 95, 19);
		getContentPane().add(txtValor);

		JLabel lblCheckin = new JLabel("Check-in:");
		lblCheckin.setBounds(10, 80, 60, 13);
		getContentPane().add(lblCheckin);

		txtCheckin = new JTextField();
		txtCheckin.setEditable(false);
		txtCheckin.setBounds(90, 75, 95, 19);
		getContentPane().add(txtCheckin);
		txtCheckin.setColumns(10);

		JLabel lblCheckout = new JLabel("Check-out:");
		lblCheckout.setBounds(210, 80, 70, 13);
		getContentPane().add(lblCheckout);

		txtCheckout = new JTextField();
		txtCheckout.setEditable(false);
		txtCheckout.setBounds(280, 75, 95, 19);
		getContentPane().add(txtCheckout);
		txtCheckout.setColumns(10);

		tblReservas = new JTable(dataModelTipoDeQuarto());
		JScrollPane scrollPane = new JScrollPane(tblReservas);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 130, this.getWidth(), 138);
		tblReservas.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 330, 115, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		JButton btnCheckout = new JButton("Check-Out");
		btnCheckout.setBounds(400, 74, 100, 21);
		btnCheckout.setActionCommand("btn_checkout");
		btnCheckout.addActionListener(this);
		getContentPane().add(btnCheckout);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			final String cpfHospede = txtHospede.getText();
			if (cpfVazio()) {
				JOptionPane.showMessageDialog(this,
						"CPF do hospede vazio, tente novamente.", "ERRO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Reserva r = new ReservaControl().selectReserva(cpfHospede);
			if (r != null) {
				txtQuarto.setText(String.valueOf(r.getQuarto().getNumQuarto()));
				txtCheckin.setText(new SimpleDateFormat("dd/MM/yyyy")
						.format(r.getCheckIn()));
				int dias = (int) diasDeReserva(r.getCheckIn());
				txtDias.setText(String.valueOf(dias));
				DecimalFormat df = new DecimalFormat("#0.00");
				String valor = df.format(Double.valueOf(
						r.getQuarto().getTipoDeQuarto().getValorDiaria()
								* dias));
				txtValor.setText(valor);
			}
		} else if (nomeEvento.equals("btn_checkout")) {

		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}
	}

	private DefaultTableModel dataModelTipoDeQuarto() {
		DefaultTableModel dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "codigo", "cpf cliente", "check-in" };

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public String getColumnName(int index) {
				return columnNames[index];
			}

		};
		atualizarDadosTabela(dataModel);
		return dataModel;
	}

	private void atualizarDadosTabela(DefaultTableModel m) {
		String cpfHospede = "";
		if (!cpfVazio()) {
			cpfHospede = txtHospede.getText();
		}
		this.reservas = new ReservaControl()
				.selectHistoricoReservas(cpfHospede);
		for (Reserva t : this.reservas) {
			m.addRow(new Object[] { t.getId(), t.getHospede().getCpf(),
					new SimpleDateFormat("dd/MM/yyyy").format(t.getCheckIn()) });
		}
	}

	private boolean cpfVazio() {
		final String cpfHospede = txtHospede.getText();
		if (cpfHospede.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	private float diasDeReserva(Date ini) {
		Date hoje = new Date();
		long diff = hoje.getTime() - ini.getTime();
		int dias = (int) (diff / (1000 * 60 * 60 * 24));
		return dias;
	}
}
