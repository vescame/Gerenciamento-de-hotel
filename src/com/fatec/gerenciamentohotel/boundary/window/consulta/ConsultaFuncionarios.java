package src.com.fatec.gerenciamentohotel.boundary.window.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class ConsultaFuncionarios extends JInternalFrame {

	private static final long serialVersionUID = 8887992485161706734L;
	private JTextField textDoc;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textCelular;
	private JTextField textDataNasc;
	private JTable tableFuncionarios;
	private JTextField textCEP;
	private JTextField textRua;
	private JTextField textNum;
	private JTextField textBairro;
	private JTextField textCidade;
	private JTextField textUF;


	public ConsultaFuncionarios() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Funcionarios");
		setBounds(100, 100, 467, 463);
		setTitle("Consulta Funcion\u00E1rios");
		setBounds(100, 100, 467, 494);
		getContentPane().setLayout(null);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(10, 13, 63, 13);
		getContentPane().add(lblCPF);
		
		textDoc = new JTextField();
		textDoc.setBounds(56, 10, 163, 20);
		getContentPane().add(textDoc);
		textDoc.setColumns(10);
		
		JButton btnBusca = new JButton("Busca");
		btnBusca.setBounds(229, 9, 85, 21);
		btnBusca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Funcionario f = new FuncionarioControl().selectCPF(textDoc.getText());
				if (f != null) {
					textNome.setText(f.getNome());
					textDataNasc.setText(String.valueOf(f.getDataNascimento()));
					textCelular.setText(f.getCelular());
					textTelefone.setText(f.getTelefone());
					textCEP.setText(f.getEndereco().getCep());
					textRua.setText(f.getEndereco().getRua());
					textCidade.setText(f.getEndereco().getCidade());
					textBairro.setText(f.getEndereco().getBairro());
					textUF.setText(f.getEndereco().getUf());
					textNum.setText(String.valueOf(f.getNumResidencia()));
				}
			}
		});
		getContentPane().add(btnBusca);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 45, 46, 13);
		getContentPane().add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(56, 42, 165, 19);
		getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 82, 46, 13);
		getContentPane().add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(56, 79, 118, 19);
		getContentPane().add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(242, 82, 46, 13);
		getContentPane().add(lblCelular);
		
		textCelular = new JTextField();
		textCelular.setBounds(299, 79, 146, 19);
		getContentPane().add(textCelular);
		textCelular.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(242, 45, 103, 13);
		getContentPane().add(lblDataDeNascimento);
		
		textDataNasc = new JTextField();
		textDataNasc.setBounds(349, 42, 96, 19);
		getContentPane().add(textDataNasc);
		textDataNasc.setColumns(10);
		
		tableFuncionarios = new JTable();
		tableFuncionarios.setToolTipText("");
		tableFuncionarios.setBounds(10, 255, 435, 147);
		getContentPane().add(tableFuncionarios);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setToolTipText("");
		panelEndereco.setBounds(10, 105, 435, 140);
		getContentPane().add(panelEndereco);
		panelEndereco.setLayout(null);
		
		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(10, 19, 46, 13);
		lblCEP.setVerticalAlignment(SwingConstants.TOP);
		panelEndereco.add(lblCEP);
		
		textCEP = new JTextField();
		textCEP.setBounds(50, 16, 130, 19);
		panelEndereco.add(textCEP);
		textCEP.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 45, 30, 13);
		panelEndereco.add(lblRua);
		
		textRua = new JTextField();
		textRua.setBounds(50, 42, 215, 19);
		panelEndereco.add(textRua);
		textRua.setColumns(10);
		
		JLabel lblNum = new JLabel("N\u00B0:");
		lblNum.setBounds(289, 42, 30, 13);
		panelEndereco.add(lblNum);
		
		textNum = new JTextField();
		textNum.setBounds(329, 39, 96, 19);
		panelEndereco.add(textNum);
		textNum.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 74, 40, 13);
		panelEndereco.add(lblBairro);
		
		textBairro = new JTextField();
		textBairro.setBounds(50, 71, 215, 19);
		panelEndereco.add(textBairro);
		textBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 104, 46, 13);
		panelEndereco.add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setBounds(50, 104, 215, 19);
		panelEndereco.add(textCidade);
		textCidade.setColumns(10);
		
		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(289, 104, 30, 13);
		panelEndereco.add(lblUf);
		
		textUF = new JTextField();
		textUF.setBounds(329, 104, 96, 19);
		panelEndereco.add(textUF);
		textUF.setColumns(10);
		
		JButton btnInativar = new JButton("Inativar");
		btnInativar.setBounds(331, 425, 114, 25);
		getContentPane().add(btnInativar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(205, 425, 114, 25);
		getContentPane().add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(79, 425, 114, 25);
		getContentPane().add(btnCancelar);

	}
}
