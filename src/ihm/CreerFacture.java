package ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DaoOpticien;
import metier.Client;
import metier.Facture;
import metier.Monture;
import metier.Opticien;
import metier.Produit;
import metier.Verre;

public class CreerFacture extends JInternalFrame
{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtQuantite;
	private Facture  laFacture;
	private HashMap<Produit, Integer> lesProduits;
	private Client leClient;

	private JTextArea txtDescription;

	public CreerFacture(Client leClient) {
		try {
			DaoOpticien.charger();
			this.lesProduits = new HashMap<Produit, Integer>();
			this.laFacture = null;
			this.leClient = leClient;
			
			this.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CreerFacture(Facture laFacture) {
		try {
			this.lesProduits = laFacture.getLesProduits();
			this.laFacture = laFacture;
			
			this.init();
			
			for (Entry<Produit, Integer> entry : lesProduits.entrySet()) {
				Produit leProduit = entry.getKey();
				int nbProduit = entry.getValue();
				System.out.println(leProduit);
				txtDescription.setText(txtDescription.getText()+(leProduit.getRefProduit()+ " - - - - Quantité: " + String.valueOf(nbProduit) +"\n"));
				lesProduits.put(leProduit, nbProduit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init() throws SQLException{
		
		Container contenu = this.getContentPane();
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);
		setBounds(100, 100, 506, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtDescription = new JTextArea();		
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
				
				
				if(laFacture == null){
					laFacture = new Facture();
					laFacture.setDate(new Date(new java.util.Date().getTime()));
					laFacture.setLeClient(leClient);
					laFacture.setLeMagasin(Opticien.getConnected());
					laFacture.setLesProduits(lesProduits);
					DaoOpticien.creerFacture(laFacture);
				}else{
					DaoOpticien.modifierFacture(laFacture);
				}
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
