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
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class XMLForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablexml;
	public DefaultTableModel tablemodel;
	private JTextField txtSesin;

	/**
	 * Launch the application.
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
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.tablemodelxml(tablexml);
			}
		});
		btnNewButton.setBounds(316, 410, 112, 21);
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(Color.decode("#FBCF4C"));

		JButton btnGenerarSesion = new JButton("Generar Sesion");
		btnGenerarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGenerarSesion.setBounds(301, 441, 145, 32);
		contentPane.add(btnGenerarSesion);
		btnGenerarSesion.setBackground(Color.decode("#FBCF4C"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
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