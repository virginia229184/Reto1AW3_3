package Ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * La ventana de Iniciar Sesion
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class IniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldContrasena;
	private JTextField txtRegistrar;

	/**
	 * Launch the application.
	 * @param args: es un array que recibe datos 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
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
	public IniciarSesion() {
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
		lblNewLabel.setBounds(248, 178, 71, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_6 = new JLabel("contraseña:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(248, 254, 88, 13);
		contentPane.add(lblNewLabel_6);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(386, 191, 107, 27);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		textFieldContrasena = new JTextField();
		textFieldContrasena.setBounds(386, 253, 107, 27);
		contentPane.add(textFieldContrasena);
		textFieldContrasena.setColumns(10);

		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIniciarSesion.addActionListener(new ActionListener() {
			/**
			 * Esto conecta la funcion de IniciarSesion a la ventana 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.IniciarSesion(textFieldDNI, textFieldContrasena);
				
			}
		});
		btnIniciarSesion.setBounds(309, 385, 138, 40);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.setBackground(Color.decode("#FBCF4C"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			/**
			 * Esta funcion hace que al darle al boton, se salga del programa
			 */
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(309, 443, 138, 40);
		contentPane.add(btnSalir);
		btnSalir.setBackground(Color.decode("#FBCF4C"));
		
		txtRegistrar = new JTextField();
		txtRegistrar.setForeground(new Color(255, 255, 255));
		txtRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrar.setText("Iniciar Sesión");
		txtRegistrar.setBounds(0, 0, 767, 70);
		contentPane.add(txtRegistrar);
		txtRegistrar.setColumns(10);
		txtRegistrar.setBackground(Color.decode("#002069"));
		txtRegistrar.setFont(new Font("Tahoma", Font.BOLD, 38)); 
		txtRegistrar.setEditable(false); // No permite editar el texto
		
	}
	
	
}
