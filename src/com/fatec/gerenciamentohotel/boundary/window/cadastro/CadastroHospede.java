package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.control.HospedeControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class CadastroHospede extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtTelefone;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtUf;
	private JTextField txtDataNascimento;
	private JTextField txtEmail;
	private JPanel panelEndereco;
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblCelular;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;
	private JLabel lblNumero;
	private JLabel lblTelefone;
	private JLabel lblDatNasc;
	private JLabel lblEmail;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JButton btnCadastrar;
	private JButton btnNovoEndereco;
	private CadastroEndereco dialog;

	private Endereco end = null;

	public CadastroHospede() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Hospede");
		setBounds(100, 100, 450, 487);
		getContentPane().setLayout(null);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 14, 66, 15);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(61, 12, 157, 25);
		getContentPane().add(txtNome);

		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(281, 12, 147, 25);
		getContentPane().add(txtCPF);

		lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(236, 14, 66, 15);
		getContentPane().add(lblCPF);

		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(236, 54, 66, 15);
		getContentPane().add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(304, 49, 124, 25);
		getContentPane().add(txtCelular);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(81, 49, 137, 25);
		getContentPane().add(txtTelefone);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 54, 66, 15);
		getContentPane().add(lblTelefone);

		panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(12, 86, 416, 153);
		getContentPane().add(panelEndereco);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 22, 66, 15);
		panelEndereco.add(lblCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(12, 54, 66, 15);
		panelEndereco.add(lblRua);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 86, 66, 15);
		panelEndereco.add(lblBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 120, 66, 15);
		panelEndereco.add(lblCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(290, 120, 66, 15);
		panelEndereco.add(lblUf);

		lblNumero = new JLabel("N:");
		lblNumero.setBounds(290, 54, 30, 15);
		panelEndereco.add(lblNumero);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(47, 17, 154, 25);
		panelEndereco.add(txtCep);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(213, 17, 93, 25);
		btnBuscar.setActionCommand("btn_buscar_endereco");
		btnBuscar.addActionListener(this);
		panelEndereco.add(btnBuscar);

		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(47, 49, 221, 25);
		panelEndereco.add(txtRua);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(67, 81, 337, 25);
		panelEndereco.add(txtBairro);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(67, 115, 201, 25);
		panelEndereco.add(txtCidade);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(338, 49, 66, 25);
		panelEndereco.add(txtNumero);

		txtUf = new JTextField();
		txtUf.setColumns(10);
		txtUf.setBounds(338, 115, 66, 25);
		panelEndereco.add(txtUf);

		btnNovoEndereco = new JButton("Novo");
		btnNovoEndereco.addActionListener(this);
		btnNovoEndereco.setActionCommand("btn_novo_endereco");
		btnNovoEndereco.setBounds(311, 17, 93, 25);
		panelEndereco.add(btnNovoEndereco);

		lblDatNasc = new JLabel("Data de Nascimento:");
		lblDatNasc.setBounds(12, 256, 147, 15);
		getContentPane().add(lblDatNasc);

		// txtDataNascimento = new JTextField();
		try {
			txtDataNascimento = new JFormattedTextField(
					new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(178, 251, 124, 25);
		getContentPane().add(txtDataNascimento);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(81, 283, 221, 25);
		getContentPane().add(txtEmail);

		lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(12, 288, 66, 15);
		getContentPane().add(lblEmail);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(188, 418, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.setBounds(314, 418, 114, 25);
		getContentPane().add(btnCadastrar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cadastrar")) {
			Hospede h = new Hospede();
			h.setCpf(txtCPF.getText());
			h.setCelular(txtCelular.getText());
			h.setEndereco(end);
			String strDate = txtDataNascimento.getText();
			Date nasc = null;
			try {
				DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				nasc = sdf.parse(strDate);
			} catch (ParseException except) {
				JOptionPane.showMessageDialog(null, "Senhas não conferem",
						"Senha", JOptionPane.ERROR_MESSAGE);
				return;
			}
			h.setDataNascimento(nasc);
			h.setNumResidencia(Integer.parseInt(txtNumero.getText()));
			h.setEmail(txtEmail.getText());
			h.setNome(txtNome.getText());
			h.setTelefone(txtTelefone.getText());
			h.setStatus('A');
			new HospedeControl().insert(h);
		} else if (nomeEvento.equals("btn_novo_endereco")) {
			if (dialog == null) {
				dialog = new CadastroEndereco();
			}
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} else if (nomeEvento.equals("btn_buscar_endereco")) {
			final String cep = txtCep.getText().trim();
			if (!cep.equals("")) {
				this.end = new EnderecoControl().selectCep(cep);
				if (end != null) {
					txtRua.setText(end.getRua());
					txtBairro.setText(end.getBairro());
					txtCidade.setText(end.getCidade());
					txtUf.setText(end.getUf());
				}
			}
		} else if (nomeEvento.equals("btn_cancelar")) {
			dispose();
		}

	}

}
