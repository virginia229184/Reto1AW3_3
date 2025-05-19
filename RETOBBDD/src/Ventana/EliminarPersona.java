package Ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Coordinador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Esta es la ventana para eliminar el Empleado
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class EliminarPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField txtRegistrar;

	/**
	 * Launch the application.
	 * @param args: es un array que recibe datos 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarPersona frame = new EliminarPersona();
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
	public EliminarPersona() {
		
		//imagen
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
		lblNewLabel.setBounds(319, 272, 71, 40);
		contentPane.add(lblNewLabel);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(386, 281, 124, 27);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JButton btnRegistrar = new JButton("Eliminar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.addActionListener(new ActionListener() {
			/**
			 * Esto permite ejecutar la funcion eliminar al darle al boton
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				Coordinador.EliminarEmpleado(textFieldDNI);
				
			}
		});
		btnRegistrar.setBounds(640, 436, 117, 40);
		contentPane.add(btnRegistrar);
		btnRegistrar.setBackground(Color.decode("#FBCF4C"));

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
		btnSalir.setBounds(640, 486, 117, 40);
		contentPane.add(btnSalir);
		btnSalir.setBackground(Color.decode("#FBCF4C"));
		
		txtRegistrar = new JTextField();
		txtRegistrar.setForeground(new Color(255, 255, 255));
		txtRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistrar.setText("Eliminar");
		txtRegistrar.setBounds(0, 0, 767, 70);
		contentPane.add(txtRegistrar);
		txtRegistrar.setColumns(10);
		txtRegistrar.setBackground(Color.decode("#002069"));
		txtRegistrar.setFont(new Font("Tahoma", Font.BOLD, 38)); 
		txtRegistrar.setEditable(false); // No permite editar el texto
		
	}
}
