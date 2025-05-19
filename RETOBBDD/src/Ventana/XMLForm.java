package Ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Esta es la ventana de Reserva en donde el Empleado cargara un fichero XML
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class XMLForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablexml;
	public DefaultTableModel tablemodel;

	/**
	 * Launch the application.
	 * @param args: es un Array que carga datos
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
	public XMLForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 636, 264);
		contentPane.add(scrollPane);
		
		tablemodel = new DefaultTableModel(
				new Object[] { "IdSesion", "hora", "dia", "aforo", "IdPelicula", "Cif"}, 0);

		tablexml = new JTable(tablemodel);
		scrollPane.setViewportView(tablexml);

		JButton btnNewButton = new JButton("CARGAR ");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Esto conecta la funcion TableModelXML al boton Cargar
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.tablemodelxml(tablexml);
			}
		});
		btnNewButton.setBounds(277, 294, 95, 21);
		contentPane.add(btnNewButton);

		JButton btnGenerarSesion = new JButton("Generar Sesion");
		btnGenerarSesion.setBounds(264, 340, 126, 21);
		contentPane.add(btnGenerarSesion);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			/**
			 * Esta funcion hace que al darle al boton, se salga del programa
			 */
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnSalir.setBounds(543, 358, 103, 21);
		contentPane.add(btnSalir);
	}
}
