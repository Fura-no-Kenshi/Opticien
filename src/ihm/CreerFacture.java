package ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import metier.Facture;
import metier.Monture;
import metier.Produit;
import metier.Verre;
import dao.DaoOpticien;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreerFacture extends JInternalFrame
{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtQuantite;
	private Facture  laFacture;
	private HashMap<Produit, Integer> lesProduits;


	public CreerFacture() throws SQLException
	{
		
		DaoOpticien.charger();
		lesProduits = new HashMap<Produit, Integer>();
		laFacture = new Facture();
		
		laFacture.setIdFacture(1);
		laFacture.setDate(null);
		laFacture.setLeClient(null);
		laFacture.setLeMagasin(null);
		
		
		Container contenu = this.getContentPane();
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);
		setBounds(100, 100, 506, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea txtDescription = new JTextArea();		
		JScrollPane sc = new JScrollPane(txtDescription);
		sc.setBounds(100, 180, 286, 180);
		contentPanel.add(sc);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contenu.removeAll();
				AcceuilOpticien AcceuilOpticien =
				null;
				AcceuilOpticien = new AcceuilOpticien();
				AcceuilOpticien.setBounds(contenu.getBounds());
				AcceuilOpticien.setBorder(null);
				contenu.add(AcceuilOpticien);
				AcceuilOpticien.setVisible(true);
				AcceuilOpticien.repaint();
			}
		});
		btnRetour.setBounds(320, 385, 91, 23);
		contentPanel.add(btnRetour);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				laFacture.setLesProduits(lesProduits);
				DaoOpticien.creerFacture(new Date(new java.util.Date().getTime()), 1, 1, lesProduits);
			}
		});
		btnCreer.setBounds(178, 385, 91, 23);
		contentPanel.add(btnCreer);
		
		JLabel lblQuantite = new JLabel("Quantit\u00E9");
		lblQuantite.setBounds(10, 133, 46, 14);
		contentPanel.add(lblQuantite);
		
		txtQuantite = new JTextField();
		txtQuantite.setText("0");
		txtQuantite.setBounds(85, 130, 86, 20);
		contentPanel.add(txtQuantite);
		txtQuantite.setColumns(10);
		
		JComboBox optMonture = new JComboBox(DaoOpticien.getVecteurMonture());
		optMonture.setBounds(10, 26, 194, 22);
		contentPanel.add(optMonture);
		
		JComboBox optVerre = new JComboBox(DaoOpticien.getVecteurVerres());
		optVerre.setBounds(10, 78, 194, 22);
		contentPanel.add(optVerre);
		
		JButton btnAjouterMonture = new JButton("Ajouter Monture");
		btnAjouterMonture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtDescription.setText(txtDescription.getText()+((Monture) optMonture.getSelectedItem()).getRefProduit()+" - - - - Quantité: "+txtQuantite.getText()+"\n");
				lesProduits.put((Monture) optMonture.getSelectedItem(), Integer.parseInt(txtQuantite.getText()));
			}
		});
		btnAjouterMonture.setBounds(261, 26, 121, 23);
		contentPanel.add(btnAjouterMonture);
		
		JButton btnAjouterVerre = new JButton("Ajouter Verre");
		btnAjouterVerre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtDescription.setText(txtDescription.getText()+((Verre) optVerre.getSelectedItem()).getRefProduit()+" - - - - Quantité: "+txtQuantite.getText()+"\n");
				lesProduits.put((Verre) optVerre.getSelectedItem(), Integer.parseInt(txtQuantite.getText()));
				
			
			}
		});
		btnAjouterVerre.setBounds(261, 78, 121, 23);
		contentPanel.add(btnAjouterVerre);

		
		
				
	}
}
