package ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DaoOpticien;
import metier.Client;
import metier.Facture;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListeClients extends JInternalFrame {
	
	private final JPanel contentPanel = new JPanel();
	
	private ArrayList<JLabel> refs;
	private ArrayList<JLabel> pseudos;
	private ArrayList<JLabel> noms;
	private ArrayList<JLabel> tels;
	private ArrayList<JButton> details;
	private ArrayList<JButton> factures;
	
	/**
	 * Create the frame.
	 */
	public ListeClients() {
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
		pseudos = new ArrayList<JLabel>();
		noms = new ArrayList<JLabel>();
		tels = new ArrayList<JLabel>();
		details = new ArrayList<JButton>();
		
		factures = new ArrayList<JButton>();
		
		this.changePage(1);
		
		
	}
	
	private void changePage(int page){
		
		contentPanel.removeAll();
		
		int nbRowPerPage = 8;
		int x = (page-1)*nbRowPerPage;
		
		
		ArrayList<Client> lesClients = DaoOpticien.getLesClients();
		
		JLabel lblRef = new JLabel("Ref");
		lblRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef.setBounds(0, 28, 58, 14);
		contentPanel.add(lblRef);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPseudo.setBounds(70, 28, 58, 14);
		contentPanel.add(lblPseudo);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setBounds(150, 28, 58, 14);
		contentPanel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Tel");
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenom.setBounds(218, 28, 74, 14);
		contentPanel.add(lblPrenom);
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(lesClients.size()>page*nbRowPerPage)
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
		
		while(x < lesClients.size() && x < page*nbRowPerPage){
			Client leClient = lesClients.get(x);
			pos += 30;
			
			JLabel ref = new JLabel(String.valueOf(leClient.getIdClient()));
			ref.setHorizontalAlignment(SwingConstants.CENTER);
			ref.setBounds(0, pos, 58, 14);
			contentPanel.add(ref);
			refs.add(ref);
			
			JLabel pseudo = new JLabel(leClient.getPseudo());
			pseudo.setHorizontalAlignment(SwingConstants.CENTER);
			pseudo.setBounds(68, pos, 74, 14);
			contentPanel.add(pseudo);
			pseudos.add(pseudo);
			
			JLabel nom = new JLabel(leClient.getNomClient());
			nom.setHorizontalAlignment(SwingConstants.CENTER);
			nom.setBounds(152, pos, 58, 14);
			contentPanel.add(nom);
			noms.add(nom);
			
			JLabel tel = new JLabel(leClient.getTelClient());
			tel.setHorizontalAlignment(SwingConstants.CENTER);
			tel.setBounds(220, pos, 58, 14);
			contentPanel.add(tel);
			tels.add(tel);
			
			JButton btnDetails = new JButton("Details");
			btnDetails.setBounds(300, pos-3, 100, 23);
			btnDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					changePage(page);
				}
			});
			details.add(btnDetails);
			contentPanel.add(btnDetails);
			
			JButton btnFactures = new JButton("Factures");
			btnFactures.setBounds(382, pos-3, 100, 23);
			btnFactures.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					changePage(page);
				}
			});
			factures.add(btnFactures);
			contentPanel.add(btnFactures);
			
			x++;
		}
		
		this.repaint();
	}

}
