package Ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Coordinador;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Modificar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldRol;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldContrasena;
	public JButton ButtonModificar;
	public DefaultTableModel TableModel;
	private JTable tabla;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar frame = new Modificar();
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
	public Modificar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelDNI = new JLabel("DNI:");
		LabelDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelDNI.setBounds(21, 32, 32, 28);
		contentPane.add(LabelDNI);
		
		JLabel LabelNombre = new JLabel("Nombre:");
		LabelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelNombre.setBounds(21, 82, 64, 28);
		contentPane.add(LabelNombre);
		
		JLabel LabelApellido = new JLabel("Apellido:");
		LabelApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelApellido.setBounds(21, 135, 57, 28);
		contentPane.add(LabelApellido);
		
		JLabel LabelRol = new JLabel("Rol:");
		LabelRol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelRol.setBounds(21, 183, 43, 28);
		contentPane.add(LabelRol);
		
		JLabel LabelEmail = new JLabel("Email:");
		LabelEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelEmail.setBounds(21, 234, 49, 28);
		contentPane.add(LabelEmail);
		
		JLabel LabelTelefono = new JLabel("Telefono:");
		LabelTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelTelefono.setBounds(21, 282, 70, 28);
		contentPane.add(LabelTelefono);
		
		JLabel LabelContrasena = new JLabel("Contrasena:");
		LabelContrasena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelContrasena.setBounds(21, 333, 84, 28);
		contentPane.add(LabelContrasena);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(92, 38, 175, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(95, 88, 172, 19);
		contentPane.add(textFieldNombre);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(95, 141, 172, 19);
		contentPane.add(textFieldApellido);
		
		textFieldRol = new JTextField();
		textFieldRol.setColumns(10);
		textFieldRol.setBounds(95, 189, 172, 19);
		contentPane.add(textFieldRol);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(95, 240, 172, 19);
		contentPane.add(textFieldEmail);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(95, 288, 172, 19);
		contentPane.add(textFieldTelefono);
		
		textFieldContrasena = new JTextField();
		textFieldContrasena.setColumns(10);
		textFieldContrasena.setBounds(95, 339, 172, 19);
		contentPane.add(textFieldContrasena);
		
		JButton buttonSalir = new JButton("Salir");
		buttonSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonSalir.setBounds(760, 499, 108, 43);
		contentPane.add(buttonSalir);
		
		JButton buttonModificar = new JButton("Modificar");
		buttonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonModificar.setBounds(641, 499, 108, 43);
		contentPane.add(buttonModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 10, 591, 480);
		contentPane.add(scrollPane);
		
		
		TableModel = new DefaultTableModel(new Object[] {"DNI","nombre", "apellido", "rol", "email", "telefono", "contrasena"}, 0);
		
		tabla = new JTable(TableModel);
		scrollPane.setViewportView(tabla);
		Coordinador.getVisualizarEmpleado(TableModel, null);
		
		
		
		
		buttonSalir.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				System.exit(0);

			}
		});
	}
}
