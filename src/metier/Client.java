package metier;

public class Client
{
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private String telClient;
	private String cpAdrClient;
	private String rueAdrClient;
	private String numAdrClient;
	private String motDePasse;
	private String pseudo;
	
	public Client(int idClient, String nomClient, String prenomClient,
			String telClient, String cpAdrClient, String rueAdrClient,
			String numAdrClient, String motDePasse, String pseudo)
	{
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.telClient = telClient;
		this.cpAdrClient = cpAdrClient;
		this.rueAdrClient = rueAdrClient;
		this.numAdrClient = numAdrClient;
		this.motDePasse = motDePasse;
		this.pseudo = pseudo;
	}

	public int getIdClient()
	{
		return idClient;
	}

	public void setIdClient(int idClient)
	{
		this.idClient = idClient;
	}

	public String getNomClient()
	{
		return nomClient;
	}

	public void setNomClient(String nomClient)
	{
		this.nomClient = nomClient;
	}

	public String getTelClient()
	{
		return telClient;
	}

	public void setTelClient(String telClient)
	{
		this.telClient = telClient;
	}
	
	public String getPseudo()
	{
		return pseudo;
	}
	
	
	


	
	
}
