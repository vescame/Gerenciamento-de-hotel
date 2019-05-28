package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroEndereco extends JDialog {

	private static final long serialVersionUID = -8951536181922325988L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCep;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldNumero;
	private JTextField textFieldUf;
	private JPanel panel;
	private JLabel labelCep;
	private JLabel labelRua;
	private JLabel labelBairro;
	private JLabel labelCidade;
	private JLabel labelUf;
	private JLabel labelNumero;
	private JPanel buttonPane;
	private JButton okButton;
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Endereco", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
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

		labelNumero = new JLabel("Nº:");
		labelNumero.setBounds(290, 54, 30, 15);
		panel.add(labelNumero);

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

		textFieldNumero = new JTextField();
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(338, 49, 66, 25);
		panel.add(textFieldNumero);

		textFieldUf = new JTextField();
		textFieldUf.setColumns(10);
		textFieldUf.setBounds(338, 115, 66, 25);
		panel.add(textFieldUf);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Cadastrar");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

}
