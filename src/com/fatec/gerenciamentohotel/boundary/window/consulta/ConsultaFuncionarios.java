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

import src.com.fatec.gerenciamentohotel.control.FuncionarioControl;
import src.com.fatec.gerenciamentohotel.entity.Funcionario;

public class ConsultaFuncionarios extends JInternalFrame
		implements ActionListener {

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
	
	private List<Funcionario> funcionarios;

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

		JButton btnBuscar = new JButton("Busca");
		btnBuscar.setBounds(229, 9, 85, 21);
		btnBuscar.setActionCommand("btn_buscar");
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 45, 46, 13);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(56, 42, 165, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 79, 56, 19);
		getContentPane().add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(66, 79, 118, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(242, 82, 46, 13);
		getContentPane().add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setBounds(299, 79, 146, 19);
		getContentPane().add(txtCelular);
		txtCelular.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel("Dat. Nasc.:");
		lblDataDeNascimento.setBounds(242, 45, 103, 13);
		getContentPane().add(lblDataDeNascimento);

		try {
			txtDataNasc = new JFormattedTextField(
					new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtDataNasc.setBounds(315, 42, 96, 19);
		getContentPane().add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		tblFuncionarios = new JTable(dataModelFuncionarios());
		JScrollPane scrollPane = new JScrollPane(tblFuncionarios);
		scrollPane.setVisible(true);
		scrollPane.setBounds(10, 255, 435, 147);
		tblFuncionarios.setFillsViewportHeight(true);
		getContentPane().add(scrollPane);

		JPanel panelEndereco = new JPanel();
		panelEndereco.setToolTipText("");
		panelEndereco.setBounds(10, 105, 435, 140);
		getContentPane().add(panelEndereco);
		panelEndereco.setLayout(null);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(10, 18, 46, 13);
		lblCEP.setVerticalAlignment(SwingConstants.TOP);
		panelEndereco.add(lblCEP);

		txtCEP = new JTextField();
		txtCEP.setBounds(55, 18, 130, 19);
		panelEndereco.add(txtCEP);
		txtCEP.setColumns(10);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 45, 30, 19);
		panelEndereco.add(lblRua);

		txtRua = new JTextField();
		txtRua.setBounds(55, 45, 215, 19);
		panelEndereco.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNum = new JLabel("N:");
		lblNum.setBounds(300, 45, 30, 19);
		panelEndereco.add(lblNum);

		txtNum = new JTextField();
		txtNum.setBounds(329, 45, 56, 19);
		panelEndereco.add(txtNum);
		txtNum.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 75, 40, 19);
		panelEndereco.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(55, 75, 215, 19);
		panelEndereco.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 105, 46, 19);
		panelEndereco.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(55, 105, 215, 19);
		panelEndereco.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(300, 105, 30, 19);
		panelEndereco.add(lblUf);

		txtUF = new JTextField();
		txtUF.setBounds(329, 105, 46, 19);
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
	
	private DefaultTableModel dataModelFuncionarios() {
		DefaultTableModel dataModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "cpf", "nome", "email", "tipo_funcionario",
			"status" };
			
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

	private void atualizarDadosTabela (DefaultTableModel m) {
		this.funcionarios = new FuncionarioControl().selectTodos();
		for (Funcionario t: this.funcionarios) {
			m.addRow(new Object[] {
					t.getCpf(),
					t.getNome(),
					t.getEmail(),
					t.getTipoFuncionario().role,
					t.getStatus()});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_buscar")) {
			if (!txtCpf.getText().trim().isEmpty()) {
				Funcionario f = new FuncionarioControl()
						.selectCPF(txtCpf.getText());
				if (f != null) {
					txtNome.setText(f.getNome());
					txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy")
							.format(f.getDataNascimento()));
					txtCelular.setText(f.getCelular());
					txtTelefone.setText(f.getTelefone());
					txtCEP.setText(f.getEndereco().getCep());
					txtRua.setText(f.getEndereco().getRua());
					txtCidade.setText(f.getEndereco().getCidade());
					txtBairro.setText(f.getEndereco().getBairro());
					txtUF.setText(f.getEndereco().getUf());
					txtNum.setText(String.valueOf(f.getNumResidencia()));
				}
			}
		} else if (nomeEvento.equals("btn_inativar")) {

		} else if (nomeEvento.equals("btn_alterar")) {

		} else if (nomeEvento.equals("cancelar")) {
			dispose();
		}

	}
}
