package Ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class XMLForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablexml;
	public DefaultTableModel tablemodel;

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
			public void actionPerformed(ActionEvent e) {
				Controlador.Coordinador.tablemodelxml(tablexml);
			}
		});
		btnNewButton.setBounds(277, 294, 95, 21);
		contentPane.add(btnNewButton);

		JButton btnGenerarSesion = new JButton("Generar Sesion");
		btnGenerarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controlador.Coordinador.exportadorXML();
				} catch (ParserConfigurationException | TransformerException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGenerarSesion.setBounds(264, 340, 126, 21);
		contentPane.add(btnGenerarSesion);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnSalir.setBounds(543, 358, 103, 21);
		contentPane.add(btnSalir);
	}
}
