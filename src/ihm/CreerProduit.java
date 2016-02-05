package ihm;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import sun.awt.IconInfo;

import com.sun.prism.Graphics;

import dao.DaoOpticien;

import javax.swing.SwingConstants;

import metier.Monture;
import metier.Verre;

public class CreerProduit extends JInternalFrame
{

private final JPanel contentPanel = new JPanel();
private final ButtonGroup buttonGroup = new ButtonGroup();
private JTextField txtRef;
private JTextField txtPrix;
private JTextField txtStock;
private JTextField txtTeinte;
private JTextField txtCorrection;
private String img;
private JTextField txtLibelle;
	
	public CreerProduit() throws SQLException
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
		
		
		
		
		
		JLabel lblTypeProduit = new JLabel("Type Produit");
		lblTypeProduit.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTypeProduit.setBounds(29, 11, 77, 14);
		contentPanel.add(lblTypeProduit);
		
		
		JRadioButton rdbtnVerre = new JRadioButton("Verre");
		rdbtnVerre.setSelected(true);
		buttonGroup.add(rdbtnVerre);
		rdbtnVerre.setBounds(136, 7, 63, 23);
		contentPanel.add(rdbtnVerre);
		
		JRadioButton rdbtnMonture = new JRadioButton("Monture");
		buttonGroup.add(rdbtnMonture);
		rdbtnMonture.setBounds(212, 7, 77, 23);
		contentPanel.add(rdbtnMonture);
		
		
		JLabel lblRef = new JLabel("Reference");
		lblRef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRef.setBounds(29, 95, 63, 14);
		contentPanel.add(lblRef);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrix.setBounds(29, 120, 63, 14);
		contentPanel.add(lblPrix);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStock.setBounds(29, 148, 63, 14);
		contentPanel.add(lblStock);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescription.setBounds(29, 199, 63, 14);
		contentPanel.add(lblDescription);
		
		txtRef = new JTextField();
		txtRef.setBounds(125, 89, 86, 20);
		contentPanel.add(txtRef);
		txtRef.setColumns(10);
		
		txtPrix = new JTextField();
		txtPrix.setBounds(125, 117, 86, 20);
		contentPanel.add(txtPrix);
		txtPrix.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(125, 145, 86, 20);
		contentPanel.add(txtStock);
		txtStock.setColumns(10);

		JTextArea txtDescription = new JTextArea();		
		JScrollPane sc = new JScrollPane(txtDescription);
		sc.setBounds(125, 180, 286, 56);
		contentPanel.add(sc);
		
		JLabel lblTeinte = new JLabel("Teinte");
		lblTeinte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTeinte.setBounds(225, 49, 64, 14);
		contentPanel.add(lblTeinte);
		
		JLabel lblCorrection = new JLabel("Correction");
		lblCorrection.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCorrection.setBounds(225, 83, 64, 14);
		contentPanel.add(lblCorrection);
		
		JCheckBox chkReflet = new JCheckBox("AntiReflet");
		chkReflet.setBounds(219, 116, 97, 23);
		contentPanel.add(chkReflet);
		
		JCheckBox chkRayure = new JCheckBox("AntiRayure");
		chkRayure.setBounds(314, 116, 97, 23);
		contentPanel.add(chkRayure);
		
		txtTeinte = new JTextField();
		txtTeinte.setBounds(311, 46, 86, 20);
		contentPanel.add(txtTeinte);
		txtTeinte.setColumns(10);
		
		txtCorrection = new JTextField();
		txtCorrection.setBounds(311, 83, 86, 20);
		contentPanel.add(txtCorrection);
		txtCorrection.setColumns(10);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(273, 35, 77, 65);
		contentPanel.add(lblImg);
		lblImg.setVisible(false);
		
		
		JButton btnAjouterImage = new JButton("ajouter Image");
		btnAjouterImage.setVisible(false);
		btnAjouterImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(null);
				img = String.valueOf(file.getSelectedFile());
				try
				{
					lblImg.setIcon( new ImageIcon(ImageIO.read( new File(img))));
				} catch (IOException e1)
				{
					// TODO Bloc catch généré automatiquement
					e1.printStackTrace();
				}
				
	
			}
		});
		btnAjouterImage.setBounds(225, 116, 172, 23);
		contentPanel.add(btnAjouterImage);
		
		
		rdbtnMonture.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				lblTeinte.setVisible(false);
				txtTeinte.setVisible(false);
				lblCorrection.setVisible(false);
				txtCorrection.setVisible(false);
				chkReflet.setVisible(false);
				chkRayure.setVisible(false);
				btnAjouterImage.setVisible(true);
				lblImg.setVisible(true);
			
				
				
			}
		});
		
		
		rdbtnVerre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				lblTeinte.setVisible(true);
				txtTeinte.setVisible(true);
				lblCorrection.setVisible(true);
				txtCorrection.setVisible(true);
				chkReflet.setVisible(true);
				chkRayure.setVisible(true);
				btnAjouterImage.setVisible(false);
				lblImg.setVisible(false);
					
			}
		});
		
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean valider = true;
				for(Monture laMonture : DaoOpticien.getLesMontures())
				{
					if(txtRef.getText().equals(laMonture.getRefProduit()))
					{
						JOptionPane.showMessageDialog(null, "Doublon sur Ref");
						valider = false;
					}
				}
				
				for(Verre leVerre : DaoOpticien.getLesVerres())
				{
					if(txtRef.getText().equals(leVerre.getRefProduit()))
					{
						JOptionPane.showMessageDialog(null, "Doublon sur Ref");
						valider = false;
					}
				}
				
				if(rdbtnVerre.isSelected())
				{
					if (txtRef.getText().isEmpty() || txtLibelle.getText().isEmpty() || txtPrix.getText().isEmpty() || txtStock.getText().isEmpty() || txtDescription.getText().isEmpty() || txtTeinte.getText().isEmpty() || txtCorrection.getText().isEmpty() )
					{
						JOptionPane.showMessageDialog(null, "Saisissez tous les champs");
						valider = false;
					}
					else
					{
						try 
						{ 
							Double i = Double.parseDouble(txtPrix.getText());
							try 
							{ 
								int j = Integer.parseInt(txtStock.getText()); 
								try 
								{ 
									Double k = Double.parseDouble(txtCorrection.getText()); 
								} 
								catch (Exception e) 
								{ 
									JOptionPane.showMessageDialog(null, "Correction doit etre un nombre entier");
									valider = false; 
								}	
							} 
							catch (Exception e) 
							{ 
								JOptionPane.showMessageDialog(null, "Stock doit etre un nombre entier");
								valider = false; 
							}	
						} 
						catch (Exception e) 
						{ 
							
							JOptionPane.showMessageDialog(null, "Prix doit etre un nombre");
							valider = false; 
						}
					}
					
					
					if(valider == true)
					{
						try
						{
							DaoOpticien.creerVerre(txtRef.getText(),txtLibelle.getText(), Double.parseDouble(txtPrix.getText()), Integer.parseInt(txtStock.getText()), txtDescription.getText(), txtTeinte.getText(), Double.parseDouble(txtCorrection.getText()), chkReflet.isSelected(), chkRayure.isSelected() );
							JOptionPane.showMessageDialog(null, "Produit creé");
						} catch (SQLException e)
						{
							// TODO Bloc catch généré automatiquement
							e.printStackTrace();
						}
					}
					
				}
				else
				{
					if (txtRef.getText().isEmpty() || txtLibelle.getText().isEmpty() || txtPrix.getText().isEmpty() || txtStock.getText().isEmpty() || txtDescription.getText().isEmpty() )
					{
						JOptionPane.showMessageDialog(null, "Saisissez tous les champs");
						valider = false;
					}
					else
					{
						try 
						{ 
							Double i = Double.parseDouble(txtPrix.getText());
							try 
							{ 
								int j = Integer.parseInt(txtStock.getText()); 
							} 
							catch (Exception e) 
							{ 
								JOptionPane.showMessageDialog(null, "Stock doit etre un nombre entier");
								valider = false; 
							}	
						} 
						catch (Exception e) 
						{ 
							
							JOptionPane.showMessageDialog(null, "Prix doit etre un nombre");
							valider = false; 
						}
					}
					
					
					if(valider == true)
					{
						try
						{
							DaoOpticien.creerMonture(txtRef.getText(),txtLibelle.getText(), Double.parseDouble(txtPrix.getText()), Integer.parseInt(txtStock.getText()), txtDescription.getText(), img);
							JOptionPane.showMessageDialog(null, "Produit creé");
						} catch (SQLException e)
						{
							// TODO Bloc catch généré automatiquement
							e.printStackTrace();
						}
					}
				}
			}
		});
		btnCreer.setBounds(225, 258, 91, 23);
		contentPanel.add(btnCreer);
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnRetour.setBounds(320, 258, 91, 23);
		contentPanel.add(btnRetour);
		
		JLabel lblLibelle = new JLabel("Libelle");
		lblLibelle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLibelle.setBounds(46, 61, 46, 14);
		contentPanel.add(lblLibelle);
		
		txtLibelle = new JTextField();
		txtLibelle.setBounds(125, 58, 86, 20);
		contentPanel.add(txtLibelle);
		txtLibelle.setColumns(10);
		
	}
	
	public static boolean isInt(String chaine)
	{ 
		boolean valeur = true; 
		char[] tab = chaine.toCharArray(); 

		for(char carac : tab)
		{ 
			if((!Character.isDigit(carac) || carac == '.') && valeur)
			{ 
				valeur = false;
			} 
		} 

		return valeur; 
	} 
	
	
	
}

