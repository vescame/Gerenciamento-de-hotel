package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import src.com.fatec.gerenciamentohotel.boundary.utils.JTextFieldLimit;
import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class CadastroEndereco extends JDialog implements ActionListener {
	private static final long serialVersionUID = -8951536181922325988L;
	private JPanel contentPanel = new JPanel();
	private JPanel panel;

	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;

	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;

	private JButton btnCancelar;
	private JButton btnCadastrar;

	public CadastroEndereco() {
		setResizable(false);
		setTitle("Cadastro de Endereco");
		setBounds(100, 100, 450, 235);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						"Endereco", TitledBorder.LEFT, TitledBorder.TOP));
		panel.setBounds(12, 12, 416, 153);
		contentPanel.add(panel);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 22, 66, 15);
		panel.add(lblCep);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(12, 54, 66, 15);
		panel.add(lblRua);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 86, 66, 15);
		panel.add(lblBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 120, 66, 15);
		panel.add(lblCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(290, 120, 66, 15);
		panel.add(lblUf);

		txtCep = new JTextField();
		txtCep.setBounds(47, 17, 154, 25);
		txtCep.setDocument(new JTextFieldLimit(8));
		panel.add(txtCep);

		txtRua = new JTextField();
		txtRua.setDocument(new JTextFieldLimit(50));
		txtRua.setBounds(47, 49, 221, 25);
		panel.add(txtRua);

		txtBairro = new JTextField();
		txtBairro.setDocument(new JTextFieldLimit(50));
		txtBairro.setBounds(67, 81, 337, 25);
		panel.add(txtBairro);

		txtCidade = new JTextField();
		txtCidade.setDocument(new JTextFieldLimit(50));
		txtCidade.setBounds(67, 115, 201, 25);
		panel.add(txtCidade);

		txtUf = new JTextField();
		txtUf.setDocument(new JTextFieldLimit(2));
		txtUf.setBounds(338, 115, 66, 25);
		panel.add(txtUf);

		JPanel buttonsPane = new JPanel();
		// panel dos botoes com layout direcionado pra direita
		buttonsPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// adicionar painel ao sul
		getContentPane().add(buttonsPane, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		buttonsPane.add(btnCancelar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.addActionListener(this);
		buttonsPane.add(btnCadastrar);
		getRootPane().setDefaultButton(btnCadastrar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cadastrar")) {
			Endereco end = new Endereco();

			end.setCep(txtCep.getText());
			end.setBairro(txtBairro.getText());
			end.setRua(txtRua.getText());
			end.setCidade(txtCidade.getText());
			end.setUf(txtUf.getText());

			new EnderecoControl().insert(end);
		} else if (nomeEvento.equals("btn_cancelar")) {
			setVisible(false);
		}

	}

}
