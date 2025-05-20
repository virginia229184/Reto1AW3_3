package Ventana;

import java.awt.Color;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Clase RegistrarPersona, consiste en una ventana para registrar una persona de la base de datos
 * tiene botones para ejecutar el registro y salir del formulario.
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */

public class RegistrarPersona extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	/**
	 * Panel principal que contiene todos los componentes visuales
	 */
	private JPanel contentPane;

	/**
	 * Campo para ingresar el DNI
	 */
	private JTextField textFieldDNI;

	/**
	 * Campo para ingresar el nombre
	 */
	private JTextField textFieldNombre;

	/**
	 * Campo para ingresar el apellido
	 */
	private JTextField textFieldApellido;

	/**
	 * Campo para ingresar el rol del usuario
	 */
	private JTextField textFieldRol;

	/**
	 * Campo para ingresar el correo electrónico
	 */
	private JTextField textFieldMail;

	/**
	 * Campo para ingresar el número de teléfono
	 */
	private JTextField textFieldTelefono;

	/**
	 * Campo seguro para ingresar la contraseña
	 */
	private JPasswordField textFieldContrasena;

	/**
	 * Campo para registrar información adicional
	 */
	private JTextField txtRegistrar;


	/**
	 * Método que muestra la ventana RegistrarPersona
	 * 
	 * @param args Argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarPersona frame = new RegistrarPersona();
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
	 * Constructor de la clase
	 */
	public RegistrarPersona() {
		// imagen
		setIconImage(
				new ImageIcon("D:/PROG/RETO/bin/Ventana/cineyelmo_logo.jpg").getImage());
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(60, 100, 71, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(60, 150, 71, 19);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("apellido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(60, 208, 74, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("rol:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(60, 256, 45, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("mail:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(60, 318, 45, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("teléfono:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(60, 358, 74, 13);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("contraseña:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(60, 401, 88, 13);
		contentPane.add(lblNewLabel_6);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(177, 113, 96, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(177, 152, 96, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(177, 207, 96, 19);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldRol = new JTextField();
		textFieldRol.setBounds(177, 255, 96, 19);
		contentPane.add(textFieldRol);
		textFieldRol.setColumns(10);

		textFieldMail = new JTextField();
		textFieldMail.setBounds(177, 317, 96, 19);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);

		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(177, 357, 96, 19);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setBounds(177, 400, 96, 19);
		contentPane.add(textFieldContrasena);
		textFieldContrasena.setColumns(10);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnRegistrar.addActionListener(new ActionListener() {
			/**
			 * Acción al hacer clic en "Registrar"
			 * Envía los datos al controlador para ser registrados
			 * 
			 * @param e evento de acción del botón
			 */

			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.Coordinador.GuardarPersona(textFieldDNI, textFieldNombre, textFieldApellido, textFieldRol,
							textFieldMail, textFieldTelefono,  textFieldContrasena);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRegistrar.setBounds(640, 436, 117, 40);
		contentPane.add(btnRegistrar);
		btnRegistrar.setBackground(Color.decode("#FBCF4C"));

		JButton btnSalir = new JButton("Volver");
		btnSalir.addActionListener(new ActionListener() {
			/**
			 * Acción al hacer clic en "Salir"
			 * Vuelve a la ventana del menú principal
			 * 
			 * @param e evento de acción del botón
			 */
			public void actionPerformed(ActionEvent e) {
				Menu frame = new Menu();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(640, 486, 117, 40);
		contentPane.add(btnSalir);
		btnSalir.setBackground(Color.decode("#FBCF4C"));
		
		txtRegistrar = new JTextField();
		txtRegistrar.setForeground(new Color(255, 255, 255));
		txtRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrar.setText("Registrar");
		txtRegistrar.setBounds(0, 0, 767, 70);
		contentPane.add(txtRegistrar);
		txtRegistrar.setColumns(10);
		txtRegistrar.setBackground(Color.decode("#002069"));
		txtRegistrar.setFont(new Font("Tahoma", Font.BOLD, 38)); 
		txtRegistrar.setEditable(false);
		
	}
}
