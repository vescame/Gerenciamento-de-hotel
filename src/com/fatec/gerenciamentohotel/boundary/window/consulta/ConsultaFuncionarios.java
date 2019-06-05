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

public class ConsultaFuncionarios extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 8887992485161706734L;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtDataNasc;
	private JTable tblFuncionarios;
	private JTextField txtCEP;
	private JTextField txtRua;
	private JTextField txtNum;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUF;


	public ConsultaFuncionarios() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Funcionarios");
		setBounds(100, 100, 467, 494);
		getContentPane().setLayout(null);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(10, 13, 63, 13);
		getContentPane().add(lblCPF);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(56, 10, 163, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		
		JButton btnBusca = new JButton("Busca");
		btnBusca.setBounds(229, 9, 85, 21);
		btnBusca.addActionListener(this);
		getContentPane().add(btnBusca);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 45, 46, 13);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(56, 42, 165, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 82, 46, 13);
		getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(56, 79, 118, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(242, 82, 46, 13);
		getContentPane().add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setBounds(299, 79, 146, 19);
		getContentPane().add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(242, 45, 103, 13);
		getContentPane().add(lblDataDeNascimento);
		
		txtDataNasc = new JTextField();
		txtDataNasc.setBounds(349, 42, 96, 19);
		getContentPane().add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		tblFuncionarios = new JTable();
		tblFuncionarios.setToolTipText("");
		tblFuncionarios.setBounds(10, 255, 435, 147);
		getContentPane().add(tblFuncionarios);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setToolTipText("");
		panelEndereco.setBounds(10, 105, 435, 140);
		getContentPane().add(panelEndereco);
		panelEndereco.setLayout(null);
		
		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(10, 19, 46, 13);
		lblCEP.setVerticalAlignment(SwingConstants.TOP);
		panelEndereco.add(lblCEP);
		
		txtCEP = new JTextField();
		txtCEP.setBounds(50, 16, 130, 19);
		panelEndereco.add(txtCEP);
		txtCEP.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 45, 30, 13);
		panelEndereco.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setBounds(50, 42, 215, 19);
		panelEndereco.add(txtRua);
		txtRua.setColumns(10);
		
		JLabel lblNum = new JLabel("N:");
		lblNum.setBounds(289, 42, 30, 13);
		panelEndereco.add(lblNum);
		
		txtNum = new JTextField();
		txtNum.setBounds(329, 39, 96, 19);
		panelEndereco.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 74, 40, 13);
		panelEndereco.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(50, 71, 215, 19);
		panelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 104, 46, 13);
		panelEndereco.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(50, 104, 215, 19);
		panelEndereco.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(289, 104, 30, 13);
		panelEndereco.add(lblUf);
		
		txtUF = new JTextField();
		txtUF.setBounds(329, 104, 96, 19);
		panelEndereco.add(txtUF);
		txtUF.setColumns(10);
		
		JButton btnInativar = new JButton("Inativar");
		btnInativar.setBounds(331, 425, 114, 25);
		btnInativar.setActionCommand("btn_inativar");
		btnInativar.addActionListener(this);
		getContentPane().add(btnInativar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(205, 425, 114, 25);
		btnAlterar.setActionCommand("btn_alterar");
		btnAlterar.addActionListener(this);
		getContentPane().add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(79, 425, 114, 25);
		getContentPane().add(btnCancelar);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_busca")) {
			if (!txtCpf.getText().trim().isEmpty()) {
				Funcionario f = new FuncionarioControl().selectCPF(txtCpf.getText());
				if (f != null) {
					txtNome.setText(f.getNome());
					txtDataNasc.setText(String.valueOf(f.getDataNascimento()));
					txtCelular.setText(f.getCelular());
					txtTelefone.setText(f.getTelefone());
					txtCEP.setText(f.getEndereco().getCep());
					txtRua.setText(f.getEndereco().getRua());
					txtCidade.setText(f.getEndereco().getCidade());
					txtBairro.setText(f.getEndereco().getBairro());
					txtUF.setText(f.getEndereco().getUf());
					txtNum.setText(String.valueOf(f.getNumResidencia()));
				}
			} else {
				// trazer lista de funcionarios
			}
			
		} else if (nomeEvento.equals("btn_inativar")) {
			
		} else if (nomeEvento.equals("btn_alterar")) {
			
		} else if (nomeEvento.equals("cancelar")) {
			dispose();
		}
		
	}
}
