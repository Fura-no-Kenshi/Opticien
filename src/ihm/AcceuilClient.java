package ihm;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.stream.Stream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import dao.DaoOpticien;

public class AcceuilClient extends JInternalFrame
{
	//private JComboBox <Verre> cboListeVerre;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcceuilClient() throws SQLException
	{
		
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
		
		JComboBox comboBox = new JComboBox(DaoOpticien.getVecteurVerres());
		comboBox.setBounds(10, 284, 456, 22);
		contentPanel.add(comboBox);
		
		
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(58, 51, 135, 119);
		DaoOpticien.chargerImage(DaoOpticien.getLesMontures().get(0));
		
		Icon icon = new ImageIcon(DaoOpticien.getLesMontures().get(0).getImageMonture().getBytes(1, (int)DaoOpticien.getLesMontures().get(0).getImageMonture().length()));
		lblImage.setIcon(icon);
		contentPanel.add(lblImage);
		
		//this.cboListeVerre = new JComboBox (DaoOpticien.getVecteurJouets());
		//this.cboListeVerre.setBounds(20, 20, 450, 20);
		
		
	}
}
