package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class ConsultarHospedes extends JInternalFrame
		implements ActionListener {
	private static final long serialVersionUID = 2733433101749296723L;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtDataNasc;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;

	private JLabel lblCpf;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private JLabel lblEmail;
	private JLabel lblDataNasc;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;

	private JButton btnBuscar;
	private JButton btnAlterar;
	private JButton btnInativar;
	private JButton btnCancelar;

	private JTable tblHospedes;
	private List<Hospede> hospedes;

	public ConsultarHospedes() {
		setClosable(true);
		setTitle("Consulta Hospedes");
		setBounds(100, 100, 468, 541);
		getContentPane().setLayout(null);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 20, 31, 13);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(61, 20, 130, 19);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(225, 17, 114, 25);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 50, 50, 13);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(61, 46, 365, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(8, 80, 60, 13);
		getContentPane().add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(61, 75, 148, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(232, 80, 50, 13);
		getContentPane().add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setBounds(278, 75, 148, 19);
		getContentPane().add(txtCelular);
		txtCelular.setColumns(10);

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 110, 50, 13);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(61, 107, 150, 19);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		lblDataNasc = new JLabel("Dat. Nasc.:");
		lblDataNasc.setBounds(220, 107, 80, 19);
		getContentPane().add(lblDataNasc);

		try {
			txtDataNasc = new JFormattedTextField(
					new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataNasc.setBounds(300, 107, 80, 19);
		getContentPane().add(txtDataNasc);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setToolTipText("");
		panel.setBounds(10, 145, 435, 140);
		getContentPane().add(panel);

		lblCep = new JLabel("CEP:");
		lblCep.setVerticalAlignment(SwingConstants.TOP);
		lblCep.setBounds(10, 19, 46, 13);
		panel.add(lblCep);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(50, 16, 130, 19);
		panel.add(txtCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 45, 30, 13);
		panel.add(lblRua);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(50, 42, 215, 19);
		panel.add(txtRua);

		lblNumero = new JLabel("N:");
		lblNumero.setBounds(310, 42, 30, 13);
		panel.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(329, 39, 96, 19);
		panel.add(txtNumero);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 74, 40, 13);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(50, 71, 215, 19);
		panel.add(txtBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 104, 46, 13);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(50, 104, 215, 19);
		panel.add(txtCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(289, 104, 30, 13);
		panel.add(lblUf);

		txtUf = new JTextField();
		txtUf.setColumns(10);
		txtUf.setBounds(329, 104, 96, 19);
		panel.add(txtUf);

		tblHospedes = new JTable(dataModelFuncionarios());
		JScrollPane scrollPane = new JScrollPane(tblHospedes);
		scrollPane.setVisible(true);
		scrollPane.setBounds(10, 300, 435, 147);
		tblHospedes.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(80, 472, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(206, 472, 114, 25);
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		getContentPane().add(btnAlterar);

		btnInativar = new JButton("Inativar");
		btnInativar.setBounds(332, 472, 114, 25);
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		getContentPane().add(btnInativar);

	}

	private DefaultTableModel dataModelFuncionarios() {
		DefaultTableModel dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "cpf", "nome", "email", "status" };

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
		this.hospedes = new HospedeControl().selectTodos();
		for (Hospede t : this.hospedes) {
			m.addRow(new Object[] { t.getCpf(), t.getNome(), t.getEmail(),
					t.getStatus() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			if (!txtCpf.getText().trim().isEmpty()) {
				Hospede h = new HospedeControl().selectCPF(txtCpf.getText());
				if (h != null) {
					txtNome.setText(h.getNome());
					txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy")
							.format(h.getDataNascimento()));
					txtCelular.setText(h.getCelular());
					txtTelefone.setText(h.getTelefone());
					txtEmail.setText(h.getEmail());
					txtCep.setText(h.getEndereco().getCep());
					txtRua.setText(h.getEndereco().getRua());
					txtCidade.setText(h.getEndereco().getCidade());
					txtBairro.setText(h.getEndereco().getBairro());
					txtUf.setText(h.getEndereco().getUf());
					txtNumero.setText(String.valueOf(h.getNumResidencia()));
				}
			}
		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		} else if (nomeEvento.equals("btn_alterar")) {

		} else if (nomeEvento.equals("btn_inativar")) {

		}
	}

}
