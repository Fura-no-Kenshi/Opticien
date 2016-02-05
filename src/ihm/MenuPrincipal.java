package ihm;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MenuPrincipal
{
	
	
	public MenuPrincipal() {
	}

	private final JPanel contentPanel = new JPanel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{	
		FenetreConnexion menu = new FenetreConnexion();
		menu.setBounds(20,20,510,480);
		menu.setVisible(true);
		menu.setTitle("Opticien");
		menu.getContentPane().setLayout(new BorderLayout(0, 0));
				
	}



}
