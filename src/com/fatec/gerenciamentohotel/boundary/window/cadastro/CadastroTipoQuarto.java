package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroTipoQuarto extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8935897060030865111L;
	private JTextField txtID;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtQtdeAdultos;
	private JTextField txtQtdeCriancas;
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

		txtID = new JTextField();
		txtID.setBounds(49, 7, 78, 25);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(217, 7, 211, 25);
		getContentPane().add(txtDescricao);

		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(217, 44, 78, 25);
		getContentPane().add(txtValor);

		txtQtdeAdultos = new JTextField();
		txtQtdeAdultos.setColumns(10);
		txtQtdeAdultos.setBounds(217, 81, 78, 25);
		getContentPane().add(txtQtdeAdultos);

		txtQtdeCriancas = new JTextField();
		txtQtdeCriancas.setColumns(10);
		txtQtdeCriancas.setBounds(217, 118, 78, 25);
		getContentPane().add(txtQtdeCriancas);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoDeQuarto t = new TipoDeQuarto();
				t.setId(Long.parseLong(txtID.getText()));
				t.setQuantidadeAdultos(Short.parseShort(txtQtdeAdultos.getText()));
				t.setQuantidadeCriancas(Short.parseShort(txtQtdeCriancas.getText()));
				t.setTipo(txtDescricao.getText());
				t.setValorDiaria(Float.parseFloat(txtValor.getText()));
				new TipoDeQuartoControl().insert(t);
			}
		});
		btnConfirmar.setBounds(314, 231, 114, 25);
		getContentPane().add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(181, 231, 114, 25);
		getContentPane().add(btnCancelar);

	}

}
