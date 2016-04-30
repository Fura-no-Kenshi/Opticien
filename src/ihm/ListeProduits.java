package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DaoOpticien;
import metier.Client;
import metier.Produit;

public class ListeProduits extends JInternalFrame {
	
	private final JPanel contentPanel = new JPanel();
	
	private ArrayList<JLabel> refs;
	private ArrayList<JLabel> libelles;
	private ArrayList<JLabel> prix;
	private ArrayList<JButton> details;
	
	/**
	 * Create the frame.
	 */
	public ListeProduits() {
		try
		{
			DaoOpticien.charger();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);
		setBounds(100, 100, 490, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		refs = new ArrayList<JLabel>();
		libelles = new ArrayList<JLabel>();
		prix = new ArrayList<JLabel>();
		details = new ArrayList<JButton>();
		
		this.changePage(1);
		
		
	}
	
	private void changePage(int page){
		
		contentPanel.removeAll();
		
		int nbRowPerPage = 8;
		int x = (page-1)*nbRowPerPage;
		
		
		ArrayList<Produit> lesProduits = DaoOpticien.getLesProduits();
		
		JLabel lblRef = new JLabel("Ref");
		lblRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef.setBounds(0, 28, 58, 14);
		contentPanel.add(lblRef);
		
		JLabel lblLibelle = new JLabel("Libelle");
		lblLibelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibelle.setBounds(70, 28, 58, 14);
		contentPanel.add(lblLibelle);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setBounds(150, 28, 58, 14);
		contentPanel.add(lblPrix);
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(lesProduits.size()>page*nbRowPerPage)
					changePage(page+1);
			}
		});
		btnSuivant.setBounds(240, 322, 89, 23);
		contentPanel.add(btnSuivant);
		
		JButton btnPrecedent = new JButton("Pr\u00E9c\u00E9dent");
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(page>1)
					changePage(page-1);
			}
		});
		btnPrecedent.setBounds(141, 322, 89, 23);
		contentPanel.add(btnPrecedent);
		
		int pos = lblRef.getY();
		
		while(x < lesProduits.size() && x < page*nbRowPerPage){
			Produit leProduit = lesProduits.get(x);
			pos += 30;
			
			JLabel ref = new JLabel(String.valueOf(leProduit.getRefProduit()));
			ref.setHorizontalAlignment(SwingConstants.CENTER);
			ref.setBounds(0, pos, 58, 14);
			contentPanel.add(ref);
			refs.add(ref);
			
			JLabel libelle = new JLabel(leProduit.getlibelle());
			libelle.setHorizontalAlignment(SwingConstants.CENTER);
			libelle.setBounds(68, pos, 74, 14);
			contentPanel.add(libelle);
			libelles.add(libelle);
			
			JLabel pri = new JLabel(String.valueOf(leProduit.getPrixProduit()));
			pri.setHorizontalAlignment(SwingConstants.CENTER);
			pri.setBounds(152, pos, 58, 14);
			contentPanel.add(pri);
			prix.add(pri);
			
			JButton btnDetails = new JButton("Details");
			btnDetails.setBounds(300, pos-3, 100, 23);
			btnDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					changePage(page);
				}
			});
			details.add(btnDetails);
			contentPanel.add(btnDetails);
			
			x++;
		}
		
		this.repaint();
	}

}
