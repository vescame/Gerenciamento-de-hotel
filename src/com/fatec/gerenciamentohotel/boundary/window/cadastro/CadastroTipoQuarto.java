package src.com.fatec.gerenciamentohotel.boundary.window.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import jdk.nashorn.internal.runtime.ParserException;
import src.com.fatec.gerenciamentohotel.control.TipoDeQuartoControl;
import src.com.fatec.gerenciamentohotel.entity.TipoDeQuarto;

public class CadastroTipoQuarto extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JTextField txtQtdeAdultos;
	private JTextField txtQtdeCriancas;
	
	private JLabel lblDescricao;
	private JLabel lblValorDiaria;
	private JLabel lblQuantidadeAdultos;
	private JLabel lblQuantidadeCriancas;
	
	private JButton btnCadastrar;
	private JButton btnCancelar;

	public CadastroTipoQuarto() {
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(10, 12, 66, 15);
		lblDescricao.setSize(150, lblDescricao.getHeight());
		getContentPane().add(lblDescricao);

		lblValorDiaria = new JLabel("Valor Diaria:");
		lblValorDiaria.setBounds(10, 49, 86, 15);
		lblValorDiaria.setSize(150, lblValorDiaria.getHeight());
		getContentPane().add(lblValorDiaria);

		lblQuantidadeAdultos = new JLabel("Quantidade Adultos:");
		lblQuantidadeAdultos.setBounds(10, 86, 165, 15);
		getContentPane().add(lblQuantidadeAdultos);

		lblQuantidadeCriancas = new JLabel("Quantidade Criancas:");
		lblQuantidadeCriancas.setBounds(10, 123, 165, 15);
		getContentPane().add(lblQuantidadeCriancas);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(150, 7, 211, 25);
		getContentPane().add(txtDescricao);

		try {
			txtValor = new JFormattedTextField(new MaskFormatter("###.##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtValor.setBounds(150, 44, 78, 25);
		getContentPane().add(txtValor);

		txtQtdeAdultos = new JTextField();
		txtQtdeAdultos.setColumns(10);
		txtQtdeAdultos.setBounds(150, 81, 78, 25);
		getContentPane().add(txtQtdeAdultos);

		txtQtdeCriancas = new JTextField();
		txtQtdeCriancas.setColumns(10);
		txtQtdeCriancas.setBounds(150, 118, 78, 25);
		getContentPane().add(txtQtdeCriancas);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(this);
		btnCadastrar.setActionCommand("btn_cadastrar");
		btnCadastrar.setBounds(314, 231, 114, 25);
		getContentPane().add(btnCadastrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(181, 231, 114, 25);
		btnCancelar.setActionCommand("btn_cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String nomeEvento = e.getActionCommand();
		if (nomeEvento.equals("btn_cadastrar")) {
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
		} else if (nomeEvento.equals("btn_buscar")){
			
		} else if (nomeEvento.equals("btn_cancelar")){
			dispose();
		}
		
	}

}
