package metier;


import java.util.Date;
import java.util.HashMap;

public class Facture
{
	private int idFacture;
	private Date date;
	private Opticien leMagasin;
	private Client leClient;
	HashMap<Produit, Integer> lesProduits;
	
	
	public Facture()
	{
		
	}
	
	
	public Facture(int idFacture, Date date, Opticien leMagasin, Client leClient, HashMap<Produit, Integer> lesProduits)
	{
		this.idFacture = idFacture;
		this.date = date;
		this.leMagasin = leMagasin;
		this.leClient = leClient;
		this.lesProduits = lesProduits;
	}


	public int getIdFacture()
	{
		return idFacture;
	}


	public void setIdFacture(int idFacture)
	{
		this.idFacture = idFacture;
	}


	public Date getDate()
	{
		return date;
	}


	public void setDate(Date date)
	{
		this.date = date;
	}


	public Opticien getLeMagasin()
	{
		return leMagasin;
	}


	public void setLeMagasin(Opticien leMagasin)
	{
		this.leMagasin = leMagasin;
	}


	public Client getLeClient()
	{
		return leClient;
	}


	public void setLeClient(Client leClient)
	{
		this.leClient = leClient;
	}
	
	public HashMap<Produit, Integer> getLesProduits()
	{
		return lesProduits;
	}


	public void setLesProduits(HashMap<Produit, Integer> lesProduits)
	{
		this.lesProduits = lesProduits;
	}


	@Override
	public String toString()
	{
		return "Facture [idFacture=" + idFacture + ", date=" + date
				+ ", leMagasin=" + leMagasin + ", leClient=" + leClient
				+ ", lesProduits=" + lesProduits + "]";
	}
	
	
	
}
