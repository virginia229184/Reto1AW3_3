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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controlador.Coordinador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JPasswordField; 
/**
 * Esta es la ventana de visualizar los datos de los empleados
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class Visualizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel TableModel;
	public JComboBox comboCliente;
	public JTextField textFieldBuscador;
	public JButton BotonCopiaSeg;
	public JButton ButtonCargar;
	

	/**
	 * Launch the application.
	 * @param args: es un array que carga datos
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
	public Visualizar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 814, 173);
		contentPane.add(scrollPane);
		
		TableModel = new DefaultTableModel(new Object[] {"DNI", "nombre", "apellido", "rol", "email", "telefono", "contrasena"}, 0);
		
		table = new JTable(TableModel);
		scrollPane.setViewportView(table);
		
		
		
		JComboBox comboCliente = new JComboBox<Object>();
		comboCliente.addActionListener(new ActionListener() {
			/**
			 * Esta funcion permite cargar los datos de los empleados en la tabla
			 * Abajo esta la funcion VisualizarEmpleadoComboBox que carga el filtro en el comboBox
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				Coordinador.getVisualizarEmpleado(TableModel, comboCliente);
			}
		});
		comboCliente.setBounds(366, 210, 105, 29);
		contentPane.add(comboCliente);
		Coordinador.VisualizarEmpleadoComboBox(comboCliente);
		
		
		JLabel lblDNI = new JLabel("Datos:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDNI.setBounds(239, 273, 105, 22);
		contentPane.add(lblDNI);
		
		
		JButton ButtonCargar = new JButton("Cargar Copia de \r\nSeguridad");
		ButtonCargar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ButtonCargar.setBounds(639, 338, 185, 55);
		contentPane.add(ButtonCargar);
		
		JButton BotonCopiaSeg = new JButton("Copia de Seguridad");
		BotonCopiaSeg.addActionListener(new ActionListener() {
			/**
			 * Esta funcion permite cargar un fichero txt con datos encriptados, al darle al boton
			 * @param e
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
		BotonCopiaSeg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		BotonCopiaSeg.setBounds(639, 273, 185, 55);
		contentPane.add(BotonCopiaSeg);
		
		textFieldBuscador = new JTextField();
		textFieldBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Controlador.Coordinador.buscarDatos(TableModel, table, textFieldBuscador);
			}
		});
		textFieldBuscador.setBounds(317, 278, 216, 19);
		contentPane.add(textFieldBuscador);
		textFieldBuscador.setColumns(10);
		
	}
}
