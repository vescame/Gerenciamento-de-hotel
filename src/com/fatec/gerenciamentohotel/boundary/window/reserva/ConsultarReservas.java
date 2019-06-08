package src.com.fatec.gerenciamentohotel.boundary.window.reserva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
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

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.control.ReservaControl;
import src.com.fatec.gerenciamentohotel.entity.Reserva;

public class ConsultarReservas extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblHospede;
	private JLabel lblQuarto;
	private JLabel lblDias;
	private JLabel lblValor;
	private JLabel lblCheckin;
	private JLabel lblCheckout;

	private JTextField txtHospede;
	private JTextField txtQuarto;
	private JTextField txtValor;
	private JTextField txtDias;
	private JTextField txtCheckin;
	private JTextField txtCheckout;

	private JButton btnCancelar;
	private JButton btnLimpar;
	private JButton btnCheckout;
	private JButton btnBuscar;

	private JTable tblReservas;
	private DefaultTableModel dataModel;
	private List<Reserva> reservas = new ArrayList<>();

	public ConsultarReservas() {
		setTitle("Consulta Reservas");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);

		lblHospede = new JLabel("CPF:");
		lblHospede.setBounds(10, 20, 100, 13);
		getContentPane().add(lblHospede);

		txtHospede = new JTextField();
		txtHospede.setBounds(90, 17, 219, 19);
		getContentPane().add(txtHospede);
		txtHospede.setDocument(new JTextFieldLimit(11));

		btnBuscar = new JButton("Buscar Reserva Ativa");
		btnBuscar.setBounds(330, 15, 200, 21);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		lblQuarto = new JLabel("Quarto:");
		lblQuarto.setBounds(10, 50, 46, 13);
		getContentPane().add(lblQuarto);

		txtQuarto = new JTextField();
		txtQuarto.setEditable(false);
		txtQuarto.setBounds(90, 46, 50, 19);
		getContentPane().add(txtQuarto);
		txtQuarto.setColumns(10);

		lblDias = new JLabel("Dias:");
		lblDias.setBounds(210, 50, 50, 13);
		getContentPane().add(lblDias);

		txtDias = new JTextField();
		txtDias.setEditable(false);
		txtDias.setBounds(280, 46, 95, 19);
		getContentPane().add(txtDias);

		lblValor = new JLabel("Total:");
		lblValor.setBounds(400, 50, 50, 13);
		getContentPane().add(lblValor);

		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setBounds(450, 46, 95, 19);
		getContentPane().add(txtValor);

		lblCheckin = new JLabel("Check-in:");
		lblCheckin.setBounds(10, 80, 60, 13);
		getContentPane().add(lblCheckin);

		txtCheckin = new JTextField();
		txtCheckin.setEditable(false);
		txtCheckin.setBounds(90, 75, 95, 19);
		getContentPane().add(txtCheckin);
		txtCheckin.setColumns(10);

		lblCheckout = new JLabel("Check-out:");
		lblCheckout.setBounds(210, 80, 70, 13);
		getContentPane().add(lblCheckout);

		txtCheckout = new JTextField();
		txtCheckout.setEditable(false);
		txtCheckout.setBounds(280, 75, 95, 19);
		getContentPane().add(txtCheckout);
		txtCheckout.setColumns(10);

		configurarDataModel();
		tblReservas = new JTable(dataModel);
		tblReservas.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				/* */
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblReservas);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 130, this.getWidth(), 138);
		tblReservas.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnCheckout = new JButton("Check-Out");
		btnCheckout.setBounds(400, 74, 100, 21);
		btnCheckout.setActionCommand("btn_checkout");
		btnCheckout.addActionListener(this);
		getContentPane().add(btnCheckout);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 330, 115, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(450, 330, 115, 25);
		btnLimpar.setActionCommand("btn_limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setEnabled(false);
		getContentPane().add(btnLimpar);
	}

	private void configurarDataModel() {
		dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "codigo", "cpf cliente", "check-in",
					"check-out", "diaria", "total", "status" };

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public String getColumnName(int index) {
				return columnNames[index];
			}
		};
		atualizarModel();
	}

	private void atualizarModel() {
		limparLinhasModel();
		inserirLinhasModel();
	}

	private void limparLinhasModel() {
		while (dataModel.getRowCount() > 0) {
			dataModel.removeRow(0);
		}
	}

	private void inserirLinhasModel() {
		String cpfHospede = "";
		if (!cpfVazio()) {
			cpfHospede = txtHospede.getText();
		}
		this.reservas = new ReservaControl()
				.selectHistoricoReservas(cpfHospede);
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Reserva t : this.reservas) {
			dataModel.addRow(new Object[] { t.getId(), t.getHospede().getCpf(),
					sdf.format(t.getCheckIn()),
					t.getCheckOut() != null ? sdf.format(t.getCheckOut())
							: "NULL",
					t.getQuarto().getTipoDeQuarto().getValorDiaria(),
					t.getTotal(), t.getStatus() == 'A' ? "Ativo" : "Inativo" });
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

	private void limparCampos() {
		txtHospede.setText("");
		txtDias.setText("");
		txtQuarto.setText("");
		txtValor.setText("");
		txtCheckin.setText("");
		txtCheckout.setText("");
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
				txtHospede.setEditable(false);
				btnLimpar.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "Nao ha reserva ativa",
						"Reserva", JOptionPane.WARNING_MESSAGE);
			}
		} else if (nomeEvento.equals("btn_checkout")) {
			if (!txtHospede.isEditable()) {
				ReservaControl rc = new ReservaControl();
				Reserva r = rc.selectReserva(txtHospede.getText());
				if (r != null) {
					r.setTotal(Float.valueOf(txtValor.getText()));
					rc.update(r);
					btnLimpar.setEnabled(false);
					limparCampos();
					txtHospede.setEditable(true);
					tblReservas.grabFocus();
				}
			}
		} else if (nomeEvento.equals("btn_limpar")) {
			txtHospede.setEditable(true);
			btnLimpar.setEnabled(false);
			limparCampos();
		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}
	}
}
