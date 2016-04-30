package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import javax.sql.rowset.serial.SerialBlob;
import metier.Client;
import metier.Facture;
import metier.Monture;
import metier.Opticien;
import metier.Produit;
import metier.Verre;


public class DaoOpticien
{
	private static ArrayList<Client> lesClients;
	private static ArrayList<Opticien> lesOpticiens;
	private static ArrayList<Facture> lesFactures;
	private static ArrayList<Monture> lesMontures;
	private static ArrayList<Verre> lesVerres;
	
	public static void charger() throws SQLException
	{
		lesClients = new ArrayList<Client>();
		Connection connect = MySqlConnection.ConnectionMySql();
		Statement stLienBd = connect.createStatement();
		String req = "Select * from client ORDER BY 1";
		ResultSet res = stLienBd.executeQuery(req);
		while (res.next())
		{
			Client leClient = new Client (res.getInt(1), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(3), res.getString(2));
			lesClients.add(leClient);
		}
		
		lesOpticiens = new ArrayList<Opticien>();
		req = "Select * from opticien ORDER BY 1";
		res = stLienBd.executeQuery(req);
		while (res.next())
		{
			Opticien lopticien = new Opticien (res.getInt(1), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(3), res.getString(2));
			lesOpticiens.add(lopticien);
		}
		
		lesMontures = new ArrayList<Monture>();
		req = "Select * from Monture, Produit where produit = ref ORDER BY 1";
		res = stLienBd.executeQuery(req);
		while (res.next())
		{
			Monture laMonture = new Monture (res.getString(1), res.getString(4), res.getInt(6), res.getString(7), res.getDouble(5), res.getBlob(2));
			lesMontures.add(laMonture);
		}
		
		lesVerres = new ArrayList<Verre>();
		req = "Select * from Verre, Produit where produit = ref ORDER BY 1";
		res = stLienBd.executeQuery(req);
		while (res.next())
		{
			Verre leVerre = new Verre (res.getString(1), res.getString(7), res.getInt(9), res.getString(10), res.getDouble(8), res.getString(2), res.getInt(3), res.getBoolean(4), res.getBoolean(5));
			lesVerres.add(leVerre);
		}
		
		lesFactures = new ArrayList<Facture>();
		req = "Select * from Facture ORDER BY 1";
		res = stLienBd.executeQuery(req);
		while (res.next())
		{
			Client leClient = null;
			Opticien lOpticien = null;
			Produit lesProduits;

			for (Client client : lesClients) {
				if(client.getIdClient() == res.getInt(4))
					leClient = client;
			}
			for (Opticien opticien : lesOpticiens) {
				if(opticien.getIdOpticien() == res.getInt(3))
					lOpticien = opticien;
			}
			
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM produit_facture WHERE facture = ?");
			sql.setInt(1, res.getInt(1));
			ResultSet data = sql.executeQuery();
			HashMap<Produit, Integer> lesProduitsFacture = new HashMap<Produit, Integer>();
			while(data.next()){
				for (Produit produit : getLesProduits()) {
					if(produit.getRefProduit().equals(data.getString(2)))
						lesProduitsFacture.put(produit, data.getInt(3));
				}
			}
			
			Facture laFacture = new Facture (res.getInt(1), res.getDate(2), lOpticien, leClient, lesProduitsFacture);
			System.out.println(lesProduitsFacture);
			lesFactures.add(laFacture);
		}
		
	}
	
	public static Object login(String pseudo, String password) throws SQLException
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		//Statement stLienBd = connect.createStatement();
		PreparedStatement sql = connect.prepareStatement("Select * from vue_utilisateur where Lower(pseudo) = Lower(?) and password = SHA1(?)");
		sql.setString(1, pseudo);
		sql.setString(2, password);
		ResultSet data = sql.executeQuery();
		
		Object utilisateur = null;
		
		data.next();
		
		if(data.getString(5).equals("Client"))
		{
			utilisateur = new Client(data.getInt(1), data.getString(2), null, null, null, null, null, null, data.getString(3));
		}
		else if(data.getString(5).equals("Opticien"))
		{
			utilisateur = new Opticien(data.getInt(1),  data.getString(2), null, null, null, null, null, data.getString(3));
		}
		
		return utilisateur;
	}

	public static ArrayList<Client> getLesClients()
	{
		return lesClients;
	}

	public static ArrayList<Opticien> getLesOpticiens()
	{
		return lesOpticiens;
	}

	public static ArrayList<Facture> getLesFactures()
	{
		return lesFactures;
	}

	public static ArrayList<Monture> getLesMontures()
	{
		return lesMontures;
	}

	public static ArrayList<Verre> getLesVerres()
	{
		return lesVerres;
	}
	
	public static ArrayList<Produit> getLesProduits() {
		
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		
		for(Monture laMonture : lesMontures){
			lesProduits.add(laMonture);
		}
		
		for(Verre leVerre : lesVerres){
			lesProduits.add(leVerre);
		}
		
		lesProduits.sort(new Comparator<Produit>() {

			@Override
			public int compare(Produit produit1, Produit produit2) {
				// TODO Auto-generated method stub
				return produit1.getRefProduit().compareTo(produit2.getRefProduit());
			}
	    });
		
		return lesProduits;
	}

	public static Vector<Verre> getVecteurVerres()
	{
		Vector <Verre> vecteurVerres;
		vecteurVerres = new Vector <Verre>();

		
		for(Verre leVerre: lesVerres)
		{
			vecteurVerres.add(leVerre);
		}
		
		return vecteurVerres;
	}
	
	public static Vector<Monture> getVecteurMonture()
	{
		Vector<Monture> vecteurMontures;
		vecteurMontures = new Vector<Monture>();
		
		for (Monture laMonture: lesMontures)
		{
			vecteurMontures.add(laMonture);
		}
		
		return vecteurMontures;
	}

	public static void chargerImage(Monture laMonture) throws SQLException
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		PreparedStatement sql = connect.prepareStatement("Select image from Monture where produit=? ORDER BY 1");
		sql.setString(1,laMonture.getRefProduit());
		
		ResultSet data = sql.executeQuery();
		data.next();
		laMonture.setImageMonture(data.getBlob(1));	
	}
	
	
	public static void creerMonture( String refProduit, String libelle,  double Prix, int Stock, String description, String img  ) throws SQLException
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		int len;
		
		
        
        try
        {
        	 	PreparedStatement sql = connect.prepareStatement("INSERT INTO Produit VALUES(?,?,?,?,?)");
        	 	sql.setString(1,refProduit);
        	 	sql.setString(2,libelle);
        	 	sql.setDouble(3, Prix);
        	 	sql.setInt(4, Stock);
        	 	sql.setString(5, description);
        	 	sql.executeUpdate();
        	 	
        	 		File file = new File(img);
        	 		FileInputStream fis = new FileInputStream(file);
        	 		len = (int)file.length();
        	 		sql = connect.prepareStatement("INSERT INTO Monture VALUES(?,?) ");
        	 		sql.setString(1, refProduit);
        	 		if(img != null)
        	 		{
        	 			sql.setBinaryStream(2, (FileInputStream)fis, len);
        	 		}
        	 		else
        	 		{
        	 			//mettre une image de base
        	 		}
        	 		sql.executeUpdate();
        	 		String imgvalue = (img);
        	 		byte[] buff = imgvalue.getBytes();
        	 		Blob imgblob = new SerialBlob(buff);
        	 		lesMontures.add(new Monture(refProduit, libelle, Stock, description, Prix, imgblob));

        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }

		
	}
	
	public static void creerVerre( String refProduit, String libelle,  double Prix, int Stock, String description, String teinte, Double correction, boolean antiReflet, boolean antiRayure  ) throws SQLException
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		
		lesVerres.add(new Verre (refProduit, libelle, Stock, description, Prix,  teinte, correction, antiReflet, antiRayure));
        
        try
        {
        	 	PreparedStatement sql = connect.prepareStatement("INSERT INTO Produit VALUES(?,?,?,?,?)");
        	 	sql.setString(1,refProduit);
        	 	sql.setString(2,libelle);
        	 	sql.setDouble(3, Prix);
        	 	sql.setInt(4, Stock);
        	 	sql.setString(5, description);
        	 	sql.executeUpdate();

        	 	
        	 		sql = connect.prepareStatement("INSERT INTO Verre VALUES(?,?,?,?,?)");
        	 		sql.setString(1, refProduit);
        	 		sql.setString(2, teinte);
        	 		sql.setDouble(3, correction);
        	 		sql.setBoolean(4, antiReflet);
        	 		sql.setBoolean(5, antiRayure);
        	 		sql.executeUpdate();

        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }

		
	}
	
	public static void creerClient(String pseudo, String mdp, String nom, String prenom, String tel, String cp, String rue, String num)
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		lesClients.add(new Client(15, nom, prenom, tel, cp, rue, num, mdp, pseudo));
		 try
	        {
	        	 	PreparedStatement sql = connect.prepareStatement("INSERT INTO Client (pseudo, password, nom, prenom, telephone, cpAdresse, rueAdresse, numAdresse ) VALUES(?,?,?,?,?,?,?,?)");
	        	 	sql.setString(1,pseudo);
	        	 	sql.setString(2,mdp);
	        	 	sql.setString(3, nom);
	        	 	sql.setString(4, prenom);
	        	 	sql.setString(5, tel);
	        	 	sql.setString(6, cp);
	        	 	sql.setString(7, rue);
	        	 	sql.setString(8,num);
	        	 	sql.executeUpdate();

	        }
	        catch (SQLException e)
	        {
	                e.printStackTrace();
	        }
	        catch (Exception e)
	        {
	                e.printStackTrace();
	        }
		
	}
	
	public static void creerFacture(Facture laFacture)
	{
		Connection connect = MySqlConnection.ConnectionMySql();
		
		try
		{
			PreparedStatement sql = connect.prepareStatement("INSERT INTO facture (date, opticien , client) VALUES (?,?,?)");
			sql.setDate(1, (Date)laFacture.getDate());
    	 	sql.setInt(2,laFacture.getLeMagasin().getIdOpticien());
    	 	sql.setInt(3, laFacture.getLeClient().getIdClient());
    	 	sql.executeUpdate();
    	 	
    	 	int idFacture = -1;
    	 	
    	 	ResultSet generatedKeys = sql.getGeneratedKeys();
    	 	if(generatedKeys.next()){
    	 		idFacture = generatedKeys.getInt(1);
    	 	}

			for(Produit leProduit : laFacture.getLesProduits().keySet()){
	    	 	sql = connect.prepareStatement("INSERT INTO produit_facture (facture, produit, quantitee) VALUES (?,?,?)");
				sql.setInt(1, idFacture);
	    	 	sql.setString(2, leProduit.getRefProduit());
	    	 	sql.setInt(3, laFacture.getLesProduits().get(leProduit));
	    	 	sql.executeUpdate();
			}
			
			DaoOpticien.charger();
		}
		catch (SQLException e)
		{
			 e.printStackTrace();
		}
		
	}
	
	public static void modifierFacture(Facture laFacture) {
Connection connect = MySqlConnection.ConnectionMySql();
		
		try
		{
			PreparedStatement sql = connect.prepareStatement("DELETE FROM produit_facture WHERE facture = ?");
			sql.setInt(1, laFacture.getIdFacture());
			sql.executeUpdate();
			
			for(Produit leProduit : laFacture.getLesProduits().keySet()){
	    	 	sql = connect.prepareStatement("INSERT INTO produit_facture (facture, produit, quantitee) VALUES (?,?,?)");
				sql.setInt(1, laFacture.getIdFacture());
	    	 	sql.setString(2, leProduit.getRefProduit());
	    	 	sql.setInt(3, laFacture.getLesProduits().get(leProduit));
	    	 	sql.executeUpdate();
			}
			
			DaoOpticien.charger();
		}
		catch (SQLException e)
		{
			 e.printStackTrace();
		}
	}
	
	public static void supprimerFacture(Facture laFacture){
		Connection connect = MySqlConnection.ConnectionMySql();
		
		try
		{
			PreparedStatement sql = connect.prepareStatement("DELETE FROM produit_facture WHERE facture=?");
    	 	sql.setInt(1, laFacture.getIdFacture());
    	 	sql.executeUpdate();
			
			sql = connect.prepareStatement("DELETE FROM facture WHERE id=?");
    	 	sql.setInt(1, laFacture.getIdFacture());
    	 	sql.executeUpdate();
    	 	
			DaoOpticien.charger();
		}
		catch (SQLException e)
		{
			 e.printStackTrace();
		}
	}



}
