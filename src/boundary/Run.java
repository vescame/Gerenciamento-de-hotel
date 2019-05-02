package boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Cliente;
import entity.Quarto;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Run extends Application implements EventHandler<ActionEvent> {
	private ObservableList<String> tamanhos = FXCollections.observableArrayList("Pequeno", "Medio", "Grande");

	/* cliente */
	private List<Cliente> clientes = new ArrayList<>();
	private TextField txtCodCli = new TextField();
	private TextField txtNomeCli = new TextField();
	private TextField txtSobrenomeCli = new TextField();
	private TextField txtSexoCli = new TextField();
	private TextField txtDataCadCli = new TextField();
	private Button btnBuscaCli = new Button("Buscar cliente");

	/* quarto */
	private TextField txtCodQuarto = new TextField();
	private TextField txtPrecoQuarto = new TextField();
	private ComboBox<String> cmbTamanhoQuarto = new ComboBox<String>(tamanhos);
	private TextField txtDatCheckInQuarto = new TextField();
	private Button btnChkInQuarto = new Button("Check In");
	private Button btnChkOutQuarto = new Button("Check Out");

	private List<Quarto> quartosEmCheckIn = new ArrayList<>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void start(Stage stage) throws Exception {
		Cliente c = new Cliente();
		c.setCodigo(0);
		c.setNome("Vinicius");
		c.setSobrenome("Escame");
		c.setSexo('M');
		clientes.add(c);

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);

		grid.paddingProperty().set(new Insets(20));
		Scene scene = new Scene(grid, 600, 250);

		Label lblCodigo = new Label("Codigo quarto: ");
		lblCodigo.setPadding(new Insets(4));
		grid.add(lblCodigo, 0, 0);
		grid.add(txtCodQuarto, 1, 0);

		Label lblCodCli = new Label("Codigo cliente: ");
		lblCodCli.setPadding(new Insets(4));
		grid.add(lblCodCli, 0, 1);
		grid.add(txtCodCli, 1, 1);

		Label lblPreco = new Label("Preco: ");
		lblPreco.setPadding(new Insets(4));
		grid.add(lblPreco, 0, 2);
		grid.add(txtPrecoQuarto, 1, 2);

		Label lblTamanho = new Label("Tamanho: ");
		lblTamanho.setPadding(new Insets(4));
		grid.add(lblTamanho, 0, 4);
		grid.add(cmbTamanhoQuarto, 1, 4);

		Label lblDtChkIn = new Label("Data de Check In:");
		lblDtChkIn.setPadding(new Insets(4));
		grid.add(lblDtChkIn, 0, 6);
		grid.add(txtDatCheckInQuarto, 1, 6);

		grid.add(btnChkInQuarto, 1, 7);
		grid.add(btnChkOutQuarto, 2, 7);

		grid.add(btnBuscaCli, 3, 1);
		btnBuscaCli.addEventFilter(ActionEvent.ACTION, this);

		btnChkInQuarto.addEventFilter(ActionEvent.ACTION, this);
		btnChkOutQuarto.addEventFilter(ActionEvent.ACTION, this);

		stage.setScene(scene);
		stage.setTitle("Gestão de Pizzas");
		stage.setResizable(false);
		stage.show();
		stage.onCloseRequestProperty().set(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if (event.getEventType().equals(WindowEvent.WINDOW_CLOSE_REQUEST)) {
					System.exit(0);
				}
			}
		});
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget() == btnChkInQuarto) {
			Quarto quarto = checkInQuarto();
			if (quarto != null) {
				// buscarCliente()
				quartosEmCheckIn.add(quarto);
				System.out.println("Em lista: " + quarto.toString());
				limparCamposQuarto();
				limparCamposCliente();
			} else {
				System.out.println("Voce precisa validar o cliente...");
			}
		} else if (event.getTarget() == btnChkOutQuarto) {
			for (Quarto q : quartosEmCheckIn) {
				if (q.getId() == Integer.parseInt((txtCodQuarto.getText()))) {
					quartoToBoundary(q);
				}
			}
		} else if (event.getTarget() == btnBuscaCli) {
			mostrarTelaCliente();
		}
	}

	public void mostrarTelaCliente() {
		Stage stgCli = stageCliente();
		stgCli.setResizable(false);
		stgCli.show();
	}

	private void quartoToBoundary(Quarto q) {
		txtCodQuarto.setText(String.valueOf(q.getId()));
		cmbTamanhoQuarto.setValue(q.getTamanho());
		txtPrecoQuarto.setText(String.format("%6.2f", q.getPreco()));
		String strData = sdf.format(q.getDataCheckIn());
		txtDatCheckInQuarto.setText(strData);
		txtCodCli.setText(String.valueOf(q.getCliente().getCodigo()));
	}

	private void limparCamposQuarto() {
		txtCodQuarto.clear();
		txtCodCli.clear();
		txtPrecoQuarto.clear();
		txtDatCheckInQuarto.clear();
		cmbTamanhoQuarto.setValue("");
	}

	private void clienteToBondary(Cliente c) {
		txtCodCli.setText(String.valueOf(c.getCodigo()));
		txtNomeCli.setText(c.getNome());
		txtSobrenomeCli.setText(c.getSobrenome());
		txtSexoCli.setText(String.valueOf(c.getSexo()));
		txtDataCadCli.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(c.getDataCadastro())));
	}

	private void limparCamposCliente() {
		txtNomeCli.clear();
		txtSobrenomeCli.clear();
		txtSexoCli.clear();
		txtDataCadCli.clear();
	}
	
	private Cliente buscarCliente(int codCli) {
		for (Cliente c : clientes) {
			if (c.getCodigo() == codCli) {
				return c;
			}
		}
		return null;
	}

	private Cliente clienteCadastrado() {
		Cliente c = new Cliente();
		c.setCodigo(Integer.parseInt(txtCodCli.getText()));
		c.setNome(txtNomeCli.getText());
		c.setSobrenome(txtSobrenomeCli.getText());
		String sexoCli = txtSexoCli.getText();
		if (sexoCli.length() == 1) {
			if (sexoCli.equals("M") || sexoCli.equals("F")) {
				c.setSexo(sexoCli.charAt(0));
			} else {
				c.setSexo('N');
			}
		} else {
			c.setSexo('N');
		}
		System.out.println("Cliente cadastrado: " + c);
		return c;
	}

	private Quarto checkInQuarto() {
		Quarto q = new Quarto();
		q.setTamanho(cmbTamanhoQuarto.getValue());
		try {
			q.setId(Long.parseLong(txtCodQuarto.getText()));
			q.setPreco(Float.parseFloat(txtPrecoQuarto.getText()));
			Cliente tmp = buscarCliente(Integer.parseInt(txtCodCli.getText()));
			if (tmp == null) {
				mostrarTelaCliente();
				return null;
			}
			/*if (clienteCadastrado().getNome().equals("")) {
				mostrarTelaCliente();
				return null;
			}*/
			q.setCliente(tmp);
			String data = txtDatCheckInQuarto.getText();
			if (!data.equals("")) {
				Date d = sdf.parse(data);
				q.setDataCheckIn(d);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return q;
	}
	
	private Stage stageCliente() {
		txtDataCadCli.setEditable(false);
		Stage stageCli = new Stage();
		if (!this.txtCodCli.getText().equals("")) {
			Cliente c = buscarCliente(Integer.parseInt((this.txtCodCli.getText()))); 
			if (c != null) {
				clienteToBondary(c);
			}
			/*for (Cliente c : clientes) {
				if (c.getCodigo() == Integer.parseInt((this.txtCodCli.getText()))) {
					clienteToBondary(c);
				}
			}*/
		}
		Button btnOk = new Button("OK");
		btnOk.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
					boolean clienteNaoExiste = false;
					Cliente cliente = buscarCliente(Integer.parseInt((txtCodCli.getText())));
					if (cliente == null) {
						clienteNaoExiste = true;
					} else {
						System.out.println("Insira outro codigo, esse ja existe.");
						return;
					}
					/*for (Cliente c : clientes) {
						if (c.getCodigo() == Integer.parseInt((txtCodCli.getText()))) {
							clienteExiste = true;
						}
					}*/
					if (clienteNaoExiste) {
						if (!txtNomeCli.getText().equals("") && !txtSobrenomeCli.getText().equals("")
								&& !txtSexoCli.getText().equals("")) {
							clientes.add(clienteCadastrado());
							stageCli.close();
						} else {
							System.out.println("Preencha todos os campos possiveis");
						}
					}
				}
			}
		});
		Button btnSair = new Button("Sair");
		btnSair.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
					limparCamposCliente();
					stageCli.close();
				}
			}
		});
		GridPane gridCli = new GridPane();
		gridCli.paddingProperty().set(new Insets(20));
		gridCli.setHgap(5);
		gridCli.setVgap(5);
		gridCli.add(new Label("Nome:"), 0, 1);
		gridCli.add(txtNomeCli, 1, 1);
		gridCli.add(new Label("Sobrenome:"), 0, 2);
		gridCli.add(txtSobrenomeCli, 1, 2);
		gridCli.add(new Label("Sexo (M ou F):"), 0, 3);
		gridCli.add(txtSexoCli, 1, 3);
		gridCli.add(new Label("Data Cadastro: "), 2, 3);
		gridCli.add(txtDataCadCli, 3, 3);
		gridCli.add(btnOk, 1, 4);
		gridCli.add(btnSair, 2, 4);
		stageCli.setScene(new Scene(gridCli, 550, 250));
		stageCli.setTitle("Registro de Clientes");
		return stageCli;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}