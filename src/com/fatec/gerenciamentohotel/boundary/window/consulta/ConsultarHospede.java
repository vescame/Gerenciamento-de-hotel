package com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import com.fatec.gerenciamentohotel.control.EnderecoControl;
import com.fatec.gerenciamentohotel.control.HospedeControl;
import com.fatec.gerenciamentohotel.entity.Endereco;
import com.fatec.gerenciamentohotel.entity.Hospede;

public class ConsultarHospede extends JInternalFrame implements ActionListener {
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
	private JButton btnLimpar;
	private JButton btnBuscarCep;
	private JButton btnAlterar;
	private JButton btnInativar;
	private JButton btnCancelar;

	private DefaultTableModel dataModel;
	private JTable tblHospedes;
	private List<Hospede> hospedes;

	public ConsultarHospede() {
		setTitle("Consulta Hospedes");
		setClosable(true);
		setIconifiable(true);
		setLayout(null);
		setBounds(100, 100, 470, 540);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 20, 31, 13);
		getContentPane().add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(70, 20, 130, 19);
		txtCpf.setDocument(new JTextFieldLimit(11));
		getContentPane().add(txtCpf);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(220, 15, 100, 25);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds((btnBuscar.getX() + btnBuscar.getWidth()) + 25, btnBuscar.getY(), 100, 25);
		btnLimpar.setActionCommand("btn_limpar");
		btnLimpar.addActionListener(this);
		getContentPane().add(btnLimpar);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 50, 50, 13);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(70, 46, 365, 19);
		getContentPane().add(txtNome);
		txtNome.setDocument(new JTextFieldLimit(35));

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(8, 80, 60, 13);
		getContentPane().add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(70, 75, 148, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setDocument(new JTextFieldLimit(15));

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(232, 80, 50, 13);
		getContentPane().add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setBounds(285, 75, 148, 19);
		getContentPane().add(txtCelular);
		txtCelular.setDocument(new JTextFieldLimit(15));

		lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 110, 50, 13);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(70, 107, 150, 19);
		getContentPane().add(txtEmail);
		txtEmail.setDocument(new JTextFieldLimit(35));

		lblDataNasc = new JLabel("Dat. Nasc.:");
		lblDataNasc.setBounds(232, 107, 80, 19);
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
		lblCep.setBounds(5, 19, 46, 13);
		panel.add(lblCep);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(60, 16, 130, 19);
		panel.add(txtCep);
		
		btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.setBounds(((txtCep.getX() + txtCep.getWidth()) + 50), 14, 115, 20);
		btnBuscarCep.setActionCommand("btn_buscar_cep");
		btnBuscarCep.addActionListener(this);
		panel.add(btnBuscarCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(5, 45, 30, 13);
		panel.add(lblRua);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(60, 42, 215, 19);
		panel.add(txtRua);

		lblNumero = new JLabel("N:");
		lblNumero.setBounds(310, 45, 30, 13);
		panel.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(329, 42, 96, 19);
		panel.add(txtNumero);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(5, 74, 40, 13);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(60, 71, 215, 19);
		panel.add(txtBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(5, 107, 46, 13);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(60, 104, 215, 19);
		panel.add(txtCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(303, 107, 30, 13);
		panel.add(lblUf);

		txtUf = new JTextField();
		txtUf.setColumns(10);
		txtUf.setBounds(329, 104, 96, 19);
		panel.add(txtUf);

		configurarDataModel();
		tblHospedes = new JTable(dataModel);
		tblHospedes.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				atualizarModel();
			}

			@Override
			public void focusGained(FocusEvent e) {
				atualizarModel();
			}
		});
		JScrollPane scrollPane = new JScrollPane(tblHospedes);
		scrollPane.setVisible(true);
		scrollPane.setBounds(0, 300, getWidth(), 150);
		tblHospedes.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 472, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(206, 472, 114, 25);
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setEnabled(false);
		getContentPane().add(btnAlterar);

		btnInativar = new JButton("In/Ativar");
		btnInativar.setBounds(332, 472, 114, 25);
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		btnInativar.setEnabled(false);
		getContentPane().add(btnInativar);
	}

	private void configurarDataModel() {
		dataModel = new DefaultTableModel() {
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
		this.hospedes = new HospedeControl().selectTodos();
		if (this.hospedes != null) {
			for (Hospede t : this.hospedes) {
				dataModel.addRow(new Object[] { t.getCpf(), t.getNome(),
						t.getEmail(), t.getStatus() });
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			Hospede h = consultarLista(txtCpf.getText());
			if (h != null) {
				disporHospedeEmTela(h);
				txtCpf.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnInativar.setEnabled(true);
			}
		} else if (nomeEvento.equals("btn_limpar")) {
			resetarTela();
		} else if (nomeEvento.equals("btn_cancelar")) {
			hide();
		} else if (nomeEvento.equals("btn_alterar")) {
			new HospedeControl().alterarHospede(construirObjHospede());
			atualizarModel();
		} else if (nomeEvento.equals("btn_inativar")) {
			new HospedeControl().inativarHospede(txtCpf.getText());
			atualizarModel();
		} else if (nomeEvento.equals("btn_buscar_cep")) {
			Endereco end = new EnderecoControl().selectCep(txtCep.getText());
			if (end != null) {
				disporEnderecoEmTela(end);
			}
		}
	}

	private void resetarTela() {
		txtCpf.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnInativar.setEnabled(false);
		txtCpf.setText("");
		txtNome.setText("");
		txtDataNasc.setText("");
		txtCelular.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtNumero.setText("");
		txtCep.setText("");
		txtRua.setText("");
		txtCidade.setText("");
		txtBairro.setText("");
		txtUf.setText("");
	}

	private void disporHospedeEmTela(Hospede h) {
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
	
	private void disporEnderecoEmTela(Endereco end) {
		txtRua.setText(end.getRua());
		txtBairro.setText(end.getBairro());
		txtCidade.setText(end.getCidade());
		txtUf.setText(end.getUf());
	}

	private Hospede construirObjHospede() {
		Hospede h = new Hospede();

		h.setCpf(txtCpf.getText());
		h.setEndereco(new EnderecoControl().selectCep(txtCep.getText()));
		h.setNome(txtNome.getText());
		h.setTelefone(txtTelefone.getText());
		h.setCelular(txtCelular.getText());
		String strDate = txtDataNasc.getText();
		Date nasc = null;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			nasc = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Formate a data, por favor.",
					"Data inadequada", JOptionPane.ERROR_MESSAGE);
		}
		h.setNumResidencia(Integer.parseInt(txtNumero.getText()));
		h.setEmail(txtEmail.getText());
		h.setDataNascimento(nasc);
		h.setStatus('A');
		return h;
	}

	private Hospede consultarLista(String cpf) {
		for (Hospede f : hospedes) {
			if (f.getCpf().equals(cpf)) {
				return f;
			}
		}
		return null;
	}
}
