package Ventana;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.sql.SQLException;

/**
 * Ventana que permite visualizar y generar sesiones de cine en formato XML.
 * Contiene una tabla donde se cargan los datos y botones para generar archivos XML,
 * cargar datos y salir de la ventana
 * 
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public class XMLForm extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal que contiene los elementos gráficos
	 */
	private JPanel contentPane;

	/**
	 * Tabla XML para mostrar datos específicos
	 */
	private JTable tablexml;

	/**
	 * Modelo de datos para la tabla XML
	 */
	public DefaultTableModel tablemodel;

	/**
	 * Campo de texto para gestionar la sesión
	 */
	private JTextField txtSesin;


	/**
	 * Launch the application.
	 */
	  /**
     * Método que muestra la ventana XMLForm 
     * 
     * @param args Argumentos de línea de comandos.
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XMLForm frame = new XMLForm();
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
     * Constructor que crea la ventana e inicializa los componentes
     * 
     */
	public XMLForm() {
		
		setIconImage(
				new ImageIcon("D:/PROG/RETO/bin/Ventana/cineyelmo_logo.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 102, 636, 264);
		contentPane.add(scrollPane);
		
		tablemodel = new DefaultTableModel(
				new Object[] { "IdSesion", "hora", "dia", "aforo", "IdPelicula", "Cif"}, 0);

		tablexml = new JTable(tablemodel);
		scrollPane.setViewportView(tablexml);

		JButton btnNewButton = new JButton("CARGAR ");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			 /**
		     * Acción al hacer clic en el botón "CARGAR"
		     * Carga los datos de sesión en la tabla desde el coordinador
		     * 
		     * @param e Evento de acción generado al pulsar el botón
		     */
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.tablemodelxml(tablexml);
			}
		});
		btnNewButton.setBounds(316, 410, 112, 21);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(Color.decode("#FBCF4C"));

		JButton btnGenerarSesion = new JButton("Generar Sesion");
		btnGenerarSesion.addActionListener(new ActionListener() {
			  /**
		     * Acción al hacer clic en "Generar Sesion"
		     * Exporta los datos mostrados en la tabla a un archivo XML
		     * 
		     * @param e Evento de acción generado al pulsar el botón
		     */
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.Coordinador.exportadorXML();
				} catch (ParserConfigurationException | TransformerException | SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnGenerarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGenerarSesion.setBounds(301, 441, 145, 32);
		contentPane.add(btnGenerarSesion);
		btnGenerarSesion.setBackground(Color.decode("#FBCF4C"));

		JButton btnSalir = new JButton("Volver");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.addActionListener(new ActionListener() {
			 /**
		     * Acción al hacer clic en "Salir".
		     * Vuelve a la ventana de inicio de sesión
		     * 
		     * @param e Evento de acción generado al pulsar el botón.
		     */
			public void actionPerformed(ActionEvent e) {
				
				IniciarSesion frame =  new IniciarSesion();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(626, 469, 103, 21);
		contentPane.add(btnSalir);
		btnSalir.setBackground(Color.decode("#FBCF4C"));
		
		txtSesin = new JTextField();
		txtSesin.setForeground(new Color(255, 255, 255));
		txtSesin.setHorizontalAlignment(SwingConstants.CENTER);
		txtSesin.setFont(new Font("Tahoma", Font.BOLD, 38));
		txtSesin.setText("Sesión");
		txtSesin.setBounds(0, 0, 777, 72);
		contentPane.add(txtSesin);
		txtSesin.setColumns(10);
		txtSesin.setBackground(Color.decode("#002069"));
		txtSesin.setEditable(false); 
	}
}