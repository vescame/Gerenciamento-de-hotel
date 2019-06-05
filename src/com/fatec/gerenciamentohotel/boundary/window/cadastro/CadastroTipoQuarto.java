package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jdk.nashorn.internal.runtime.ParserException;
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
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtQtdeAdultos;
	private JTextField txtQtdeCriancas;
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

		lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(125, 12, 66, 15);
		lblDescricao.setSize(150, lblDescricao.getHeight());
		getContentPane().add(lblDescricao);

		lblValorDiaria = new JLabel("Valor Diária:");
		lblValorDiaria.setBounds(100, 49, 86, 15);
		lblValorDiaria.setSize(150, lblValorDiaria.getHeight());
		getContentPane().add(lblValorDiaria);

		lblQuantidadeAdultos = new JLabel("Quantidade Adultos:");
		lblQuantidadeAdultos.setBounds(46, 86, 165, 15);
		getContentPane().add(lblQuantidadeAdultos);

		lblQuantidadeCriancas = new JLabel("Quantidade Crianças:");
		lblQuantidadeCriancas.setBounds(46, 123, 165, 15);
		getContentPane().add(lblQuantidadeCriancas);

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
				t.setQuantidadeAdultos(
						Short.parseShort(txtQtdeAdultos.getText()));
				t.setQuantidadeCriancas(
						Short.parseShort(txtQtdeCriancas.getText()));
				t.setTipo(txtDescricao.getText());
				try {
					float val = Float
							.parseFloat(txtValor.getText().replace(",", "."));
					t.setValorDiaria(val);
				} catch (ParserException ex) {
					t.setValorDiaria(0F);
				}

				new TipoDeQuartoControl().insert(t);
			}
		});
		btnConfirmar.setBounds(314, 231, 114, 25);
		getContentPane().add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(181, 231, 114, 25);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);

	}

}
