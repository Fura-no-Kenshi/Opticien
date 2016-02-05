package metier;

import java.sql.Blob;

public class Monture extends Produit
{
	private Blob imageMonture;

	public Monture(String refProduit, String libelle, int stockProduit, String descriptionProduit, Double prix, Blob imageMonture)
	{
		super(refProduit, libelle, stockProduit, descriptionProduit, prix);
		this.imageMonture = imageMonture;
	}

	public Blob getImageMonture()
	{
		return imageMonture;
	}

	public void setImageMonture(Blob imageMonture)
	{
		this.imageMonture = imageMonture;
	}

	@Override
	public String toString()
	{
		return "Monture [ref=" + this.getRefProduit() + ", libelle="
				+ this.getlibelle() + "]";
	}
	
	
	
	
}
