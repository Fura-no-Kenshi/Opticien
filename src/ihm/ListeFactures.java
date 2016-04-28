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

public class ListeFactures extends JInternalFrame {
	
	private final JPanel contentPanel = new JPanel();
	
	private Client clientActif;
	
	private ArrayList<JLabel> refs;
	private ArrayList<JLabel> dates;
	private ArrayList<JLabel> opticiens;
	private ArrayList<JLabel> clients;
	private ArrayList<JButton> detail;
	private ArrayList<JButton> supprimer;
	
	/**
	 * Create the frame.
	 */
	public ListeFactures() {
		init();
	}
	
	public ListeFactures(Client unClient){
		clientActif = unClient;
		init();
	}
	
	private void init(){
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
		dates = new ArrayList<JLabel>();
		opticiens = new ArrayList<JLabel>();
		clients = new ArrayList<JLabel>();
		detail = new ArrayList<JButton>();
		supprimer = new ArrayList<JButton>();
		
		this.changePage(1);
	}
	
	private void changePage(int page){
		
		contentPanel.removeAll();
		
		int nbRowPerPage = 8;
		int x = (page-1)*nbRowPerPage;
		
		
		ArrayList<Facture> lesFactures = DaoOpticien.getLesFactures();
		
		JLabel lblRef = new JLabel("Ref");
		lblRef.setHorizontalAlignment(SwingConstants.CENTER);
		lblRef.setBounds(0, 28, 58, 14);
		contentPanel.add(lblRef);
		
		JLabel lblOptien = new JLabel("Opticien");
		lblOptien.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptien.setBounds(152, 28, 58, 14);
		contentPanel.add(lblOptien);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setBounds(220, 28, 58, 14);
		contentPanel.add(lblClient);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(68, 28, 74, 14);
		contentPanel.add(lblDate);
		
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(lesFactures.size()>page*nbRowPerPage)
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
		
		while(x < lesFactures.size() && x < page*nbRowPerPage){
			Facture laFacture = lesFactures.get(x);
			
			if(laFacture.getLeClient() == clientActif){
				pos += 30;
				
				JLabel ref = new JLabel(String.valueOf(laFacture.getIdFacture()));
				ref.setHorizontalAlignment(SwingConstants.CENTER);
				ref.setBounds(0, pos, 58, 14);
				contentPanel.add(ref);
				refs.add(ref);
				
				JLabel date = new JLabel(laFacture.getDate().toString());
				date.setHorizontalAlignment(SwingConstants.CENTER);
				date.setBounds(68, pos, 74, 14);
				contentPanel.add(date);
				dates.add(date);
				
				JLabel opticien = new JLabel(laFacture.getLeMagasin().getNomOpticien());
				opticien.setHorizontalAlignment(SwingConstants.CENTER);
				opticien.setBounds(152, pos, 58, 14);
				contentPanel.add(opticien);
				opticiens.add(opticien);
				
				JLabel client = new JLabel(laFacture.getLeClient().getNomClient());
				client.setHorizontalAlignment(SwingConstants.CENTER);
				client.setBounds(220, pos, 58, 14);
				contentPanel.add(client);
				clients.add(client);
				
				JButton btnDetail = new JButton("Detail");
				btnDetail.setBounds(288, pos-3, 90, 23);
				btnDetail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
					}
				});
				contentPanel.add(btnDetail);
				
				JButton btnSupprimer = new JButton("Supprimer");
				btnSupprimer.setBounds(382, pos-3, 100, 23);
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						DaoOpticien.supprimerFacture(laFacture);
						changePage(page);
					}
				});
				contentPanel.add(btnSupprimer);
			}
			x++;
		}
		
		this.repaint();
	}

}
