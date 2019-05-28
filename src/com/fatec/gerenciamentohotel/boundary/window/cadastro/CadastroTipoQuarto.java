package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroTipoQuarto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8935897060030865111L;
	private JTextField textFieldID;
	private JTextField textFieldDescricao;
	private JTextField textFieldValor;
	private JTextField textFieldQtdeAdultos;
	private JTextField textFieldQtdeCriancas;
	private JLabel lblId;
	private JLabel lblDescricao;
	private JLabel lblValorDiaria;
	private JLabel lblQuantidadeAdultos;
	private JLabel lblQuantidadeCriancas;
	private JButton btnConfirmar;
	private JButton btnCancelar;

	public CadastroTipoQuarto() {
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblId = new JLabel("ID");
		lblId.setBounds(12, 12, 66, 15);
		getContentPane().add(lblId);

		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(145, 12, 66, 15);
		getContentPane().add(lblDescricao);

		lblValorDiaria = new JLabel("Valor Diária:");
		lblValorDiaria.setBounds(125, 49, 86, 15);
		getContentPane().add(lblValorDiaria);

		lblQuantidadeAdultos = new JLabel("Quantidade Adultos:");
		lblQuantidadeAdultos.setBounds(46, 86, 165, 15);
		getContentPane().add(lblQuantidadeAdultos);

		lblQuantidadeCriancas = new JLabel("Quantidade Crianças:");
		lblQuantidadeCriancas.setBounds(46, 123, 165, 15);
		getContentPane().add(lblQuantidadeCriancas);

		textFieldID = new JTextField();
		textFieldID.setBounds(49, 7, 78, 25);
		getContentPane().add(textFieldID);
		textFieldID.setColumns(10);

		textFieldDescricao = new JTextField();
		textFieldDescricao.setColumns(10);
		textFieldDescricao.setBounds(217, 7, 211, 25);
		getContentPane().add(textFieldDescricao);

		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(217, 44, 78, 25);
		getContentPane().add(textFieldValor);

		textFieldQtdeAdultos = new JTextField();
		textFieldQtdeAdultos.setColumns(10);
		textFieldQtdeAdultos.setBounds(217, 81, 78, 25);
		getContentPane().add(textFieldQtdeAdultos);

		textFieldQtdeCriancas = new JTextField();
		textFieldQtdeCriancas.setColumns(10);
		textFieldQtdeCriancas.setBounds(217, 118, 78, 25);
		getContentPane().add(textFieldQtdeCriancas);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(314, 231, 114, 25);
		getContentPane().add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(181, 231, 114, 25);
		getContentPane().add(btnCancelar);

	}

}
