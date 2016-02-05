package ihm;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DaoOpticien;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import metier.Client;
import metier.Monture;

public class CreerClient extends JInternalFrame
{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNum;
	private JTextField txtPseudo;
	private JTextField txtMDP;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtTel;
	private JTextField txtCP;
	private JTextField txtRue;

	public CreerClient() throws SQLException
	{
		DaoOpticien.charger();
		
		
		Container contenu = this.getContentPane();
		BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
		ui.setNorthPane(null);
		setBounds(100, 100, 450, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPseudo.setBounds(10, 47, 86, 14);
		contentPanel.add(lblPseudo);
		
		txtNum = new JTextField();
		txtNum.setBounds(318, 80, 86, 20);
		contentPanel.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotDePasse.setBounds(10, 83, 86, 14);
		contentPanel.add(lblMotDePasse);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(10, 121, 86, 14);
		contentPanel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrenom.setBounds(10, 161, 86, 14);
		contentPanel.add(lblPrenom);
		
		JLabel lblTel = new JLabel("Telephone");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setBounds(10, 194, 86, 14);
		contentPanel.add(lblTel);
		
		JLabel lblCP = new JLabel("Code postal");
		lblCP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCP.setBounds(202, 47, 86, 14);
		contentPanel.add(lblCP);
		
		JLabel lblRue = new JLabel("Rue");
		lblRue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRue.setBounds(202, 121, 86, 14);
		contentPanel.add(lblRue);
		
		JLabel lblNum = new JLabel("Numero adresse");
		lblNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNum.setBounds(212, 80, 86, 20);
		contentPanel.add(lblNum);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(106, 44, 86, 20);
		contentPanel.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		txtMDP = new JTextField();
		txtMDP.setBounds(106, 80, 86, 20);
		contentPanel.add(txtMDP);
		txtMDP.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(106, 118, 86, 20);
		contentPanel.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(106, 158, 86, 20);
		contentPanel.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(106, 191, 86, 20);
		contentPanel.add(txtTel);
		txtTel.setColumns(10);
		
		txtCP = new JTextField();
		txtCP.setBounds(318, 44, 86, 20);
		contentPanel.add(txtCP);
		txtCP.setColumns(10);
		
		txtRue = new JTextField();
		txtRue.setBounds(318, 118, 86, 20);
		contentPanel.add(txtRue);
		txtRue.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valider = true;
				for(Client leClient : DaoOpticien.getLesClients())
				{
					if(txtPseudo.getText().equals(leClient.getPseudo()))
					{
						JOptionPane.showMessageDialog(null, "Doublon sur pseudo");
						valider = false;
					}
				}
				
				if(txtRue.getText().isEmpty() || txtCP.getText().isEmpty() || txtTel.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtNom.getText().isEmpty() || txtMDP.getText().isEmpty() || txtPseudo.getText().isEmpty() || txtNum.getText().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Saisissez tous les champs");
					valider = false;
					
				}
				
				if(valider == true)
				{
					DaoOpticien.creerClient(txtPseudo.getText(),txtMDP.getText(),txtNom.getText(),txtPrenom.getText(),txtTel.getText(), txtCP.getText(), txtRue.getText(), txtNum.getText());
					JOptionPane.showMessageDialog(null, "Client creé");
				}
				
			}
		});
		btnEnregistrer.setBounds(106, 256, 91, 23);
		contentPanel.add(btnEnregistrer);
		
		
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
		btnRetour.setBounds(290, 256, 91, 23);
		contentPanel.add(btnRetour);
		

	}
}
