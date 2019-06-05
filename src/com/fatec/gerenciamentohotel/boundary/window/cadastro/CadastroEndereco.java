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
	private JTextField textFieldCep;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldUf;
	private JPanel panel;
	private JLabel labelCep;
	private JLabel labelRua;
	private JLabel labelBairro;
	private JLabel labelCidade;
	private JLabel labelUf;
	private JPanel buttonPane;
	private JButton btnCadastrar;
	private JButton cancelButton;

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

		labelCep = new JLabel("CEP:");
		labelCep.setBounds(12, 22, 66, 15);
		panel.add(labelCep);

		labelRua = new JLabel("Rua:");
		labelRua.setBounds(12, 54, 66, 15);
		panel.add(labelRua);

		labelBairro = new JLabel("Bairro:");
		labelBairro.setBounds(12, 86, 66, 15);
		panel.add(labelBairro);

		labelCidade = new JLabel("Cidade:");
		labelCidade.setBounds(12, 120, 66, 15);
		panel.add(labelCidade);

		labelUf = new JLabel("UF:");
		labelUf.setBounds(290, 120, 66, 15);
		panel.add(labelUf);

		textFieldCep = new JTextField();
		textFieldCep.setColumns(10);
		textFieldCep.setBounds(47, 17, 154, 25);
		panel.add(textFieldCep);

		textFieldRua = new JTextField();
		textFieldRua.setColumns(10);
		textFieldRua.setBounds(47, 49, 221, 25);
		panel.add(textFieldRua);

		textFieldBairro = new JTextField();
		textFieldBairro.setColumns(10);
		textFieldBairro.setBounds(67, 81, 337, 25);
		panel.add(textFieldBairro);

		textFieldCidade = new JTextField();
		textFieldCidade.setColumns(10);
		textFieldCidade.setBounds(67, 115, 201, 25);
		panel.add(textFieldCidade);

		textFieldUf = new JTextField();
		textFieldUf.setColumns(10);
		textFieldUf.setBounds(338, 115, 66, 25);
		panel.add(textFieldUf);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("cancelar");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setActionCommand("cad_end");
		btnCadastrar.addActionListener(this);
		buttonPane.add(btnCadastrar);
		getRootPane().setDefaultButton(btnCadastrar);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String idDoEvento = e.getActionCommand();
		if (idDoEvento.equals("cad_end")) {
			Endereco end = new Endereco();
			end.setCep(textFieldCep.getText());
			end.setBairro(textFieldBairro.getText());
			end.setRua(textFieldRua.getText());
			end.setCidade(textFieldCidade.getText());
			end.setUf(textFieldUf.getText());

			new EnderecoControl().insert(end);
		} else if (idDoEvento.equals("cancelar")) {
			dispose();
		}
		
	}

}
