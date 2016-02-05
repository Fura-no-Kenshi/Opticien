package ihm;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.DaoOpticien;

public class FenetreConnexion extends JFrame
{
	JTextField txtPseudo;
	JTextField txtPassword;
	Container contenu;

	/**
	 * Create the panel.
	 */
	public FenetreConnexion()
	{
		
		contenu = this.getContentPane();
		contenu.setLayout(null);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(210, 147, 127, 20);
		txtPseudo.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				if(event.getKeyCode() == KeyEvent.VK_ENTER)
					btnConnexionClick();
			}

			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				
			}

			@Override
			public void keyTyped(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				
			}
			
		});
		contenu.add(txtPseudo);
		txtPseudo.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(210, 194, 127, 20);
		txtPassword.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				if(event.getKeyCode() == KeyEvent.VK_ENTER)
					btnConnexionClick();
			}

			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				
			}

			@Override
			public void keyTyped(KeyEvent event) {
				// TODO Stub de la méthode généré automatiquement
				
			}
			
		});
		contenu.add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblPseudo = new JLabel("Pseudo:");
		lblPseudo.setBounds(72, 150, 91, 14);
		contenu.add(lblPseudo);

		JLabel lblMdp = new JLabel("Mot de passe:");
		lblMdp.setBounds(72, 197, 91, 14);
		contenu.add(lblMdp);

		JLabel lblBonjour = new JLabel("Bonjour");
		lblBonjour.setFont(new Font("Jokerman", Font.PLAIN, 20));
		lblBonjour.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonjour.setBounds(157, 51, 145, 51);
		contenu.add(lblBonjour);

		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				btnConnexionClick();
			}


		});
		btnConnexion.setBounds(241, 391, 105, 23);
		contenu.add(btnConnexion);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		btnAnnuler.setBounds(370, 391, 105, 23);
		contenu.add(btnAnnuler);

		
	}

	private void btnConnexionClick()
	{
		Object utilisateur = null;
		
		/*
		 * Récupération du compte utilisateur.
		 */
		try
		{
			utilisateur = DaoOpticien.login(txtPseudo.getText(), txtPassword.getText());
		} catch (SQLException e)
		{
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
		if(utilisateur instanceof metier.Client)//si client
		{
			contenu.removeAll();
			AcceuilClient acceuilClient = null;
			try
			{
				acceuilClient = new AcceuilClient();
			} catch (SQLException e)
			{
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			acceuilClient.setBounds(contenu.getBounds());
			acceuilClient.setBorder(null);
			contenu.add(acceuilClient);
			acceuilClient.setVisible(true);
			contenu.repaint();
		}
		else if(utilisateur instanceof metier.Opticien) //Si Opticien
		{
			contenu.removeAll();
			AcceuilOpticien.alreadyShowed = false;
			AcceuilOpticien acceuilOpticien = new AcceuilOpticien();
			acceuilOpticien.setBounds(contenu.getBounds());
			acceuilOpticien.setBorder(null);
			contenu.add(acceuilOpticien);
			acceuilOpticien.setVisible(true);
			contenu.repaint();
		}
		else
		{
			javax.swing.JOptionPane.showMessageDialog(null,"erreur"); 
		}
	}
	
}
