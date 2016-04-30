package metier;

import java.util.Date;

public class Opticien
{
	private int idOpticien;
	private String nomOpticien;
	private String telOpticien;
	private String cpAdrOpticien;
	private String rueAdrOpticien;
	private String numAdrOpticien;
	private String motDePasse;
	private String pseudo;
	
	private static Opticien connectedOpticien;
	
	
	public Opticien(int idOpticien, String nomOpticien, String telOpticien,
			String cpAdrOpticien, String rueAdrOpticien, String numAdrOpticien, String motDePasse, String pseudo)
	{
		this.idOpticien = idOpticien;
		this.nomOpticien = nomOpticien;
		this.telOpticien = telOpticien;
		this.cpAdrOpticien = cpAdrOpticien;
		this.rueAdrOpticien = rueAdrOpticien;
		this.numAdrOpticien = numAdrOpticien;
		this.motDePasse = motDePasse;
		this.pseudo = pseudo;
	}


	public int getIdOpticien()
	{
		return idOpticien;
	}


	public void setIdOpticien(int idOpticien)
	{
		this.idOpticien = idOpticien;
	}


	public String getNomOpticien()
	{
		return nomOpticien;
	}


	public void setNomOpticien(String nomOpticien)
	{
		this.nomOpticien = nomOpticien;
	}


	public String getTelOpticien()
	{
		return telOpticien;
	}


	public void setTelOpticien(String telOpticien)
	{
		this.telOpticien = telOpticien;
	}
	
	public void creerFacture()
	{
		
	}


	public static Opticien getConnected() {
		return connectedOpticien;
	}
	
	public static void setConnected(Opticien opticien) {
		Opticien.connectedOpticien = opticien;
	}
}
