package ihm;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSlider;

public class AcceuilOpticien extends JInternalFrame
{
	private final JPanel contentPanel = new JPanel();
	public static boolean alreadyShowed = false;
	private JInternalFrame me; 
	private Container contenu;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mbtnCreerProduit;
	private JMenuItem mbtnListerProduits;
	private JMenu mnClient;
	private JMenuItem mntmListerLesClients;
	private JMenuItem mntmCrerUnClient;
	private JMenu mnFacture;
	private JMenuItem mntmListerLesFactures;
	private JMenuItem mntmCrerUneFacture;
	private JMenu mnOptions;
	private JMenuItem mntmFichier;
	private JSeparator separator;
	private JSeparator separator_1;
	private JMenuItem mntmQuitter;
	private JMenuItem mntmVerrouiller;
	
	public AcceuilOpticien()
	{
		me = this;
		contenu = this.getContentPane();
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		if(!AcceuilOpticien.alreadyShowed)
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			mnOptions = new JMenu("Fichier");
			menuBar.add(mnOptions);
			
			mntmVerrouiller = new JMenuItem("D\u00E9connexion");
			mntmVerrouiller.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					contenu.removeAll();
					contenu.add(new FenetreConnexion().getContentPane());
					menuBar.setVisible(false);
				}
			});
			mnOptions.add(mntmVerrouiller);
			
			separator = new JSeparator();
			mnOptions.add(separator);
			
			mntmFichier = new JMenuItem("Options");
			mnOptions.add(mntmFichier);
			
			separator_1 = new JSeparator();
			mnOptions.add(separator_1);
			
			mntmQuitter = new JMenuItem("Quitter");
			mntmQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
			});
			mnOptions.add(mntmQuitter);
			
			mnNewMenu = new JMenu("Produits");
			menuBar.add(mnNewMenu);
			
			mbtnListerProduits = new JMenuItem("Lister les Produits");
			mnNewMenu.add(mbtnListerProduits);
			
			mbtnCreerProduit = new JMenuItem("Cr\u00E9er un Produit");
			mbtnCreerProduit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try
					{
						btnCreerProduitClick();
					} catch (SQLException e)
					{
						// TODO Bloc catch généré automatiquement
						e.printStackTrace();
					}
				}
			});
			mnNewMenu.add(mbtnCreerProduit);
			
			mnClient = new JMenu("Client");
			menuBar.add(mnClient);
			
			mntmListerLesClients = new JMenuItem("Lister les Clients");
			mntmListerLesClients.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try
					{
						btnListerClientsClick();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			});
			mnClient.add(mntmListerLesClients);
			
			mntmCrerUnClient = new JMenuItem("Cr\u00E9er un Client");
			mntmCrerUnClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try
					{
						btnCreerClientClick();
					} catch (SQLException e)
					{
						// TODO Bloc catch généré automatiquement
						e.printStackTrace();
					}
				}
			});
			mnClient.add(mntmCrerUnClient);
			
			mnFacture = new JMenu("Facture");
			menuBar.add(mnFacture);
			
			
			mntmListerLesFactures = new JMenuItem("Lister les Factures");
			mntmListerLesFactures.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					// TODO Auto-generated method stub
					try
					{
						btnListerFactureClick();
					} catch (SQLException e)
					{
						// TODO Bloc catch généré automatiquement
						e.printStackTrace();
					}
				}
			});
			mnFacture.add(mntmListerLesFactures);
			
			mntmCrerUneFacture = new JMenuItem("Cr\u00E9er une Facture");
			mntmCrerUneFacture.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try
					{
						btnCreerFactureClick();
					} catch (SQLException e)
					{
						// TODO Bloc catch généré automatiquement
						e.printStackTrace();
					}
				}
					
			});
			
	
			mnFacture.add(mntmCrerUneFacture);
			}
		else
		{
			
		}
		
		AcceuilOpticien.alreadyShowed = true;

	}
	public void btnListerClientsClick() throws SQLException
	{
		contenu.removeAll();
		ListeClients listeClients =	null;
		listeClients = new ListeClients();
		listeClients.setBounds(contenu.getBounds());
		listeClients.setBorder(null);
		contenu.add(listeClients);
		listeClients.setVisible(true);
		listeClients.repaint();
	}
	
	public void btnListerFactureClick() throws SQLException
	{
		contenu.removeAll();
		ListeFactures listeFactures =	null;
		listeFactures = new ListeFactures();
		listeFactures.setBounds(contenu.getBounds());
		listeFactures.setBorder(null);
		contenu.add(listeFactures);
		listeFactures.setVisible(true);
		listeFactures.repaint();
	}

	public void btnCreerProduitClick() throws SQLException
	{
		contenu.removeAll();
		CreerProduit CreerProduit =	null;
		CreerProduit = new CreerProduit();
		CreerProduit.setBounds(contenu.getBounds());
		CreerProduit.setBorder(null);
		contenu.add(CreerProduit);
		CreerProduit.setVisible(true);
		CreerProduit.repaint();
	}
	
	public void btnCreerClientClick() throws SQLException
	{
		contenu.removeAll();
		CreerClient CreerClient =	null;
		CreerClient = new CreerClient();
		CreerClient.setBounds(contenu.getBounds());
		CreerClient.setBorder(null);
		contenu.add(CreerClient);
		CreerClient.setVisible(true);
		CreerClient.repaint();
	}
	

	public void btnCreerFactureClick() throws SQLException
	{
		contenu.removeAll();
		CreerFacture CreerFacture =	null;
		CreerFacture = new CreerFacture();
		CreerFacture.setBounds(contenu.getBounds());
		CreerFacture.setBorder(null);
		contenu.add(CreerFacture);
		CreerFacture.setVisible(true);
		CreerFacture.repaint();
	}
}
