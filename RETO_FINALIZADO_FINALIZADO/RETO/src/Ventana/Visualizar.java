package Ventana;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controlador.Coordinador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color; 


 
 
/**
 * Ventana que permite visualizar usuarios, buscarlos y manejar copias de seguridad
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public class Visualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal que contiene los componentes visuales
	 */
	private JPanel contentPane;

	/**
	 * Tabla para mostrar datos en formato tabular
	 */
	private JTable table;

	/**
	 * Modelo de datos de la tabla, permite manipular el contenido
	 */
	public DefaultTableModel TableModel;

	/**
	 * Lista desplegable para seleccionar clientes
	 */
	public JComboBox comboCliente;

	/**
	 * Campo de texto para realizar búsquedas
	 */
	public JTextField textFieldBuscador;

	/**
	 * Botón para crear una copia de seguridad
	 */
	public JButton BotonCopiaSeg;
	/**
	 * campo de texto en el que esta escrito el titulo de la ventana
	 */
	private JTextField txtVisualizar;

	


	/**
	 * Launch the application.
	 */
	/**
	 * Método que muestra la ventana Visualizar
	 * 
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizar frame = new Visualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	/**
	 * Contructor para crear la ventana y sus componentes
	 */
	public Visualizar() {
		
		setIconImage(
				new ImageIcon("D:/PROG/RETO/bin/Ventana/cineyelmo_logo.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 98, 814, 173);
		contentPane.add(scrollPane);
		
		TableModel = new DefaultTableModel(new Object[] {"DNI", "nombre", "apellido", "rol", "email", "telefono", "contrasena"}, 0);
		
		table = new JTable(TableModel);
		scrollPane.setViewportView(table);
		
		
		
		JComboBox comboCliente = new JComboBox<Object>();
		comboCliente.addActionListener(new ActionListener() {
			/**
			 * Llama al método para mostrar los datos del empleado seleccionado
			 * 
			 * @param e Evento de selección en el JComboBox
			 */
			public void actionPerformed(ActionEvent e) {
				Coordinador.getVisualizarEmpleado(TableModel, comboCliente);
			}
		});
		comboCliente.setBounds(369, 304, 105, 29);
		contentPane.add(comboCliente);
		Coordinador.VisualizarEmpleadoComboBox(comboCliente);
		
		
		JLabel lblDNI = new JLabel("Datos:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDNI.setBounds(238, 357, 105, 22);
		contentPane.add(lblDNI);
		
		
		JButton ButtonCargar = new JButton("Cargar Copia de \r\nSeguridad");
		ButtonCargar.addActionListener(new ActionListener() {
			/**
			 * Carga datos desde una copia de seguridad
			 * 
			 * @param e Evento de clic en el botón
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Coordinador.VisualizarFicheroBinario(ButtonCargar,TableModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonCargar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ButtonCargar.setBounds(667, 475, 185, 55);
		contentPane.add(ButtonCargar);
		ButtonCargar.setBackground(Color.decode("#FBCF4C"));
		
		JButton BotonCopiaSeg = new JButton("Copia de Seguridad");
		BotonCopiaSeg.addActionListener(new ActionListener() {
			/**
			 * Guarda los datos actuales en una copia de seguridad.
			 * 
			 * @param e Evento de clic en el botón
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Coordinador.CargarFicherosBinarios(BotonCopiaSeg);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		BotonCopiaSeg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BotonCopiaSeg.setBounds(667, 410, 185, 55);
		contentPane.add(BotonCopiaSeg);
		BotonCopiaSeg.setBackground(Color.decode("#FBCF4C"));
		
		textFieldBuscador = new JTextField();
		textFieldBuscador.addKeyListener(new KeyAdapter() {
			/**
			 * Filtra los datos en la tabla al escribir en el campo buscador
			 * 
			 * @param e Señal que indica que se ha soltado una tecla
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				Controlador.Coordinador.buscarDatos(TableModel, table, textFieldBuscador);
			}
		});
		textFieldBuscador.setBounds(335, 362, 216, 19);
		contentPane.add(textFieldBuscador);
		textFieldBuscador.setColumns(10);
		

		txtVisualizar = new JTextField();
		txtVisualizar.setForeground(new Color(255, 255, 255));
		txtVisualizar.setFont(new Font("Tahoma", Font.BOLD, 38));
		txtVisualizar.setHorizontalAlignment(SwingConstants.CENTER);
		txtVisualizar.setText("Visualizar");
		txtVisualizar.setBounds(0, 0, 1003, 73);
		contentPane.add(txtVisualizar);
		
		
		
		txtVisualizar.setColumns(10);
		txtVisualizar.setBackground(Color.decode("#002069"));
		txtVisualizar.setEditable(false); // No permite editar el texto
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu frame = new Menu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBackground(new Color(251, 207, 76));
		btnVolver.setBounds(667, 540, 185, 55);
		contentPane.add(btnVolver);
		
	}
}