package metier;

public class Verre extends Produit
{
	private String teinteVerre;
	private double correctionVerre;
	private boolean antiReflet;
	private boolean antiRayure;
	
	
	public Verre(String refProduit, String libelle, int stockProduit, String descriptionProduit,
			Double prixProduit, String teinteVerre, double correctionVerre,
			boolean antiReflet, boolean antiRayure)
	{
		super(refProduit, libelle, stockProduit, descriptionProduit, prixProduit);
		this.teinteVerre = teinteVerre;
		this.correctionVerre = correctionVerre;
		this.antiReflet = antiReflet;
		this.antiRayure = antiRayure;
	}


	public String getTeinteVerre()
	{
		return teinteVerre;
	}


	public void setTeinteVerre(String teinteVerre)
	{
		this.teinteVerre = teinteVerre;
	}


	public double getCorrectionVerre()
	{
		return correctionVerre;
	}


	public void setCorrectionVerre(int correctionVerre)
	{
		this.correctionVerre = correctionVerre;
	}


	public boolean isAntiReflet()
	{
		return antiReflet;
	}


	public void setAntiReflet(boolean antiReflet)
	{
		this.antiReflet = antiReflet;
	}


	public boolean isAntiRayure()
	{
		return antiRayure;
	}


	public void setAntiRayure(boolean antiRayure)
	{
		this.antiRayure = antiRayure;
	}


	@Override
	public String toString()
	{
		return "Verre [ref=" + this.getRefProduit() + ", libelle="
				+ this.getlibelle() + "]";
	}
	
	
	
	
	

}
