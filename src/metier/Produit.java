package metier;

public class Produit
{
	private String refProduit;
	private String libelle;
	private int stockProduit;
	private String descriptionProduit;
	private Double prixProduit;
	
	
	public Produit(String refProduit, String libelle, int stockProduit, String descriptionProduit,
			Double prixProduit)
	{
		this.refProduit = refProduit;
		this.libelle = libelle;
		this.stockProduit = stockProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixProduit = prixProduit;
	}


	public String getRefProduit()
	{
		return refProduit;
	}


	public void setRefProduit(String refProduit)
	{
		this.refProduit = refProduit;
	}
	
	public String getlibelle()
	{
		return libelle;
	}


	public void setlibelle(String libelle)
	{
		this.libelle = libelle;
	}


	public int getStockProduit()
	{
		return stockProduit;
	}


	public void setStockProduit(int stockProduit)
	{
		this.stockProduit = stockProduit;
	}


	public String getDescriptionProduit()
	{
		return descriptionProduit;
	}


	public void setDescriptionProduit(String descriptionProduit)
	{
		this.descriptionProduit = descriptionProduit;
	}


	public Double getPrixProduit()
	{
		return prixProduit;
	}


	public void setPrixProduit(Double prixProduit)
	{
		this.prixProduit = prixProduit;
	}
	
	
	
	
}
