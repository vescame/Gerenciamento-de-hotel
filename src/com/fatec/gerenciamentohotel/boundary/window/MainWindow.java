package com.fatec.gerenciamentohotel.boundary.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -5038047263946063083L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPane, StatusBar;
	private JMenuBar menuBar;
	private JMenu menuFuncionarios, menuHospedes, menuReservas, menuQuartos, menuServicoQuarto, menuTipoQuarto;
	private JLabel lblData, lblHora;
	private JDesktopPane desktopPane;
	private JMenuItem mItemCadastroFuncionario, mItemConsultarFuncionario;
	private JMenuItem mItemCadastroDeHospede, mItemConsultaDeHospede;
	private JMenuItem mItemNovaReserva, mItemConsultarReserva, mItemCheckIn, mItemCheckOut, mItemConsultarGastos;
	private JMenuItem mItemCadastrarQuartos, mItemConsultarQuartos, mItemCadastrarTipo, mItemConsultarTipos;
	private JMenuItem mntmSolicitarServio, mntmConsultarServios;
	private JSeparator separatorReserva, separatorQuartos;
	private Timer timer;

	public MainWindow() {
		setTitle("Gerenciamento de Reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenSize.width / 2 - 800, screenSize.height / 2 - 450, 1600, 900);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				timer = new Timer(1000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						lblHora.setText("Hora: " + timeFormat.format(new Date()));
						lblData.setText("Data: " + dateFormat.format(new Date()));
					}
				});
				timer.start();
			}
		});
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuFuncionarios = new JMenu("Funcionários");
		menuBar.add(menuFuncionarios);
		mItemCadastroFuncionario = new JMenuItem("Cadastro de Funcionário");
		menuFuncionarios.add(mItemCadastroFuncionario);
		mItemConsultarFuncionario = new JMenuItem("Consultar Funcionário");
		menuFuncionarios.add(mItemConsultarFuncionario);
		menuHospedes = new JMenu("Hóspedes");
		menuBar.add(menuHospedes);
		mItemCadastroDeHospede = new JMenuItem("Cadastro de Hóspede");
		menuHospedes.add(mItemCadastroDeHospede);
		mItemConsultaDeHospede = new JMenuItem("Consulta de Hóspede");
		menuHospedes.add(mItemConsultaDeHospede);

		menuReservas = new JMenu("Reservas");
		menuBar.add(menuReservas);
		mItemNovaReserva = new JMenuItem("Nova Reserva");
		menuReservas.add(mItemNovaReserva);
		mItemConsultarReserva = new JMenuItem("Consultar Reserva");
		menuReservas.add(mItemConsultarReserva);
		separatorReserva = new JSeparator();
		menuReservas.add(separatorReserva);
		mItemCheckIn = new JMenuItem("Check-in");
		menuReservas.add(mItemCheckIn);
		mItemCheckOut = new JMenuItem("Check-out");
		menuReservas.add(mItemCheckOut);
		mItemConsultarGastos = new JMenuItem("Consultar Gastos");
		menuReservas.add(mItemConsultarGastos);

		menuQuartos = new JMenu("Quartos");
		menuBar.add(menuQuartos);
		mItemCadastrarQuartos = new JMenuItem("Cadastrar Quartos");
		menuQuartos.add(mItemCadastrarQuartos);
		mItemConsultarQuartos = new JMenuItem("Consultar Quartos");
		menuQuartos.add(mItemConsultarQuartos);
		separatorQuartos = new JSeparator();
		menuQuartos.add(separatorQuartos);

		menuTipoQuarto = new JMenu("Tipos de Quarto");
		menuQuartos.add(menuTipoQuarto);
		mItemCadastrarTipo = new JMenuItem("Cadastrar Tipo");
		menuTipoQuarto.add(mItemCadastrarTipo);
		mItemConsultarTipos = new JMenuItem("Consultar Tipos");
		menuTipoQuarto.add(mItemConsultarTipos);

		menuServicoQuarto = new JMenu("Serviço de Quarto");
		menuBar.add(menuServicoQuarto);
		mntmSolicitarServio = new JMenuItem("Solicitar Serviços");
		menuServicoQuarto.add(mntmSolicitarServio);
		mntmConsultarServios = new JMenuItem("Consultar Serviços");
		menuServicoQuarto.add(mntmConsultarServios);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.desktop);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		StatusBar = new JPanel();
		StatusBar.setBackground(SystemColor.windowBorder);
		StatusBar.setPreferredSize(new Dimension(50, 25));
		contentPane.add(StatusBar, BorderLayout.SOUTH);
		StatusBar.setLayout(new BorderLayout());

		lblData = new JLabel("Data");
		StatusBar.add(lblData, BorderLayout.EAST);

		lblHora = new JLabel("Hora");
		StatusBar.add(lblHora, BorderLayout.WEST);

	}
}
