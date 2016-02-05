package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection
{
	public static Connection ConnectionMySql()
	{
		String nomPilote = "org.gjt.mm.mysql.Driver";
		try
		{
			Class.forName(nomPilote);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("probl�me chargement driver " + e.getMessage());
		}
		
		Connection connect = null;
		try
		{
			connect = DriverManager.getConnection("jdbc:mysql://localhost/opticien", "root", "");
		}
		catch (SQLException e)
		{
			System.out.println("pb lors de la connexion � la bd " + e.getMessage());
		}
		
		return connect;
	}
}
