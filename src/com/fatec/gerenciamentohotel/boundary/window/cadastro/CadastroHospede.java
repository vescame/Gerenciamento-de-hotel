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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import src.com.fatec.gerenciamentohotel.entity.Endereco;
import src.com.fatec.gerenciamentohotel.entity.Hospede;

public class CadastroHospede extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtTelefone;
	private JTextField txtdCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtUf;
	private JTextField txtDataNascimento;
	private JTextField txtEmail;
	private JLabel labelNome;
	private JLabel labelCPF;
	private JLabel labelCelular;
	private JPanel panelEndereco;
	private JLabel labelCep;
	private JLabel labelRua;
	private JLabel labelBairro;
	private JLabel labelCidade;
	private JLabel labelUf;
	private JLabel labelNumero;
	private JLabel labelTelefone;
	private JButton buttonBuscar;
	private JLabel labelDataNascimento;
	private JLabel labelEmail;
	private JButton buttonCancelar;
	private JButton buttonCadastrar;
	private JButton buttonNovo;
	private CadastroEndereco dialog;

	public CadastroHospede() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Hóspede");
		setBounds(100, 100, 450, 487);
		getContentPane().setLayout(null);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(12, 14, 66, 15);
		getContentPane().add(labelNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(61, 12, 157, 25);
		getContentPane().add(txtNome);

		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(281, 12, 147, 25);
		getContentPane().add(txtCPF);

		labelCPF = new JLabel("CPF:");
		labelCPF.setBounds(236, 14, 66, 15);
		getContentPane().add(labelCPF);

		labelCelular = new JLabel("Celular:");
		labelCelular.setBounds(236, 54, 66, 15);
		getContentPane().add(labelCelular);

		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(304, 49, 124, 25);
		getContentPane().add(txtCelular);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(81, 49, 137, 25);
		getContentPane().add(txtTelefone);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(12, 54, 66, 15);
		getContentPane().add(labelTelefone);

		panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(12, 86, 416, 153);
		getContentPane().add(panelEndereco);

		labelCep = new JLabel("CEP:");
		labelCep.setBounds(12, 22, 66, 15);
		panelEndereco.add(labelCep);

		labelRua = new JLabel("Rua:");
		labelRua.setBounds(12, 54, 66, 15);
		panelEndereco.add(labelRua);

		labelBairro = new JLabel("Bairro:");
		labelBairro.setBounds(12, 86, 66, 15);
		panelEndereco.add(labelBairro);

		labelCidade = new JLabel("Cidade:");
		labelCidade.setBounds(12, 120, 66, 15);
		panelEndereco.add(labelCidade);

		labelUf = new JLabel("UF:");
		labelUf.setBounds(290, 120, 66, 15);
		panelEndereco.add(labelUf);

		labelNumero = new JLabel("Nº:");
		labelNumero.setBounds(290, 54, 30, 15);
		panelEndereco.add(labelNumero);

		txtdCep = new JTextField();
		txtdCep.setColumns(10);
		txtdCep.setBounds(47, 17, 154, 25);
		panelEndereco.add(txtdCep);

		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setBounds(213, 17, 93, 25);
		panelEndereco.add(buttonBuscar);

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

		buttonNovo = new JButton("Novo");
		buttonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialog == null) {
					dialog = new CadastroEndereco();
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		buttonNovo.setBounds(311, 17, 93, 25);
		panelEndereco.add(buttonNovo);

		labelDataNascimento = new JLabel("Data de Nascimento:");
		labelDataNascimento.setBounds(12, 256, 147, 15);
		getContentPane().add(labelDataNascimento);

		txtDataNascimento = new JTextField();
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(178, 251, 124, 25);
		getContentPane().add(txtDataNascimento);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(81, 283, 221, 25);
		getContentPane().add(txtEmail);

		labelEmail = new JLabel("E-Mail:");
		labelEmail.setBounds(12, 288, 66, 15);
		getContentPane().add(labelEmail);

		buttonCancelar = new JButton("Cancelar");
		buttonCancelar.setBounds(188, 418, 114, 25);
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(buttonCancelar);

		buttonCadastrar = new JButton("Cadastrar");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Endereco end = new Endereco();
				Hospede h = new Hospede();
				
				end.setCep(txtdCep.getText());
				end.setBairro(txtBairro.getText());
				end.setCidade(txtCidade.getText());
				end.setRua(txtRua.getText());
				end.setUf(txtUf.getText());
								
				h.setCpf(txtCPF.getText());
				h.setCelular(txtCelular.getText());
				h.setDataNascimento(customStrToDate(txtDataNascimento.getText()));
				h.setNumResidencia(Integer.parseInt(txtNumero.getText()));
				h.setEmail(txtEmail.getText());
				h.setNome(txtNome.getText());
				h.setTelefone(txtTelefone.getText());
				
			}
			public Date customStrToDate(String strDate) {
				Date nasc = null;
				try {
					DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					nasc = sdf.parse(strDate);
				} catch (ParseException except) {
					except.printStackTrace();
				}
				return nasc;
			}
		});
		buttonCadastrar.setBounds(314, 418, 114, 25);
	
		getContentPane().add(buttonCadastrar);
	}

}
