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

import src.com.fatec.gerenciamentohotel.control.EnderecoControl;
import src.com.fatec.gerenciamentohotel.entity.Endereco;

public class CadastroEndereco extends JDialog implements ActionListener {
	private static final long serialVersionUID = -8951536181922325988L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;

	/*private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;
	private JPanel btnPane;
	private JButton btnCadastrar;
	private JButton btnCancelar;*/

	public CadastroEndereco() {
		setResizable(false);
		setTitle("Cadastro de Endereço");
		setBounds(100, 100, 450, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true), "Endereco",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 416, 153);
		contentPanel.add(panel);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 22, 66, 15);
		panel.add(lblCep);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(12, 54, 66, 15);
		panel.add(lblRua);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 86, 66, 15);
		panel.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 120, 66, 15);
		panel.add(lblCidade);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(290, 120, 66, 15);
		panel.add(lblUf);

		JTextField txtCep = new JTextField();
		txtCep.setColumns(8);
		txtCep.setBounds(47, 17, 154, 25);
		panel.add(txtCep);

		JTextField txtRua = new JTextField();
		txtRua.setColumns(50);
		txtRua.setBounds(47, 49, 221, 25);
		panel.add(txtRua);

		JTextField txtBairro = new JTextField();
		txtBairro.setColumns(50);
		txtBairro.setBounds(67, 81, 337, 25);
		panel.add(txtBairro);

		JTextField txtCidade = new JTextField();
		txtCidade.setColumns(50);
		txtCidade.setBounds(67, 115, 201, 25);
		panel.add(txtCidade);

		JTextField txtUf = new JTextField();
		txtUf.setBounds(338, 115, 66, 25);
		panel.add(txtUf);
		txtUf.setColumns(2);

		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPane, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		btnPane.add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setActionCommand("btn_cad_endereco");
		btnCadastrar.addActionListener(this);
		btnPane.add(btnCadastrar);
		getRootPane().setDefaultButton(btnCadastrar);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cad_endereco")) {
			Endereco end = new Endereco();
			
			end.setCep(txtCep.getText());
			end.setBairro(txtBairro.getText());
			end.setRua(txtRua.getText());
			end.setCidade(txtCidade.getText());
			end.setUf(txtUf.getText());

			new EnderecoControl().insert(end);
		} else if (nomeEvento.equals("btn_cancelar")) {
			this.setVisible(false);
		}
		
	}

}
