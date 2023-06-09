package it.unisa.PetParadise.DAO;


import it.unisa.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsegnaDAO {
	
	private static final String TABLE_NAME = "consegna";
	
	// Salva un oggetto ConsegnaBean nel database
	public synchronized void doSave(ConsegnaBean ogg, Utente ut) throws SQLException {
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		
		String insertSQL = "INSERT INTO " + ConsegnaDAO.TABLE_NAME
				+ " (via, cap, numero, citta, e_utente)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, ogg.getVia());
			preparedStatement.setInt(2, ogg.getCap());
			preparedStatement.setInt(3, ogg.getNumero());
			preparedStatement.setString(4, ogg.getCitta());
			preparedStatement.setInt(5, ut.getIdutente());
	
			preparedStatement.executeUpdate();

			connection.commit(); //Salva le modifiche sul database
		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	// Recupera un oggetto ConsegnaBean dal database dato l'id_consegna
	public synchronized ConsegnaBean doRetrieveByKey(int id_consegna) throws SQLException 
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ConsegnaBean bean = new ConsegnaBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_consegna = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_consegna);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				bean.setIdconsegna(rs.getInt("id_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCap(rs.getInt("cap"));
				bean.setNumero(rs.getInt("numero"));
				bean.setCitta(rs.getString("citta"));
				
				MySQLUtenteDM udao = new MySQLUtenteDM();
				bean.setUtente(udao.getUtente(rs.getInt("e_utente")));
			}

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
	// Elimina un oggetto ConsegnaBean dal database dato l'id_consegna
	public synchronized boolean doDelete(int id_consegna) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ConsegnaDAO.TABLE_NAME + " WHERE id_consegna = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id_consegna);

			result = preparedStatement.executeUpdate();

		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	
	// Recupera un elenco di oggetti ConsegnaBean dal database dato l'utente
	public synchronized ArrayList<ConsegnaBean> doRetrieveByUtente(String user) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ConsegnaBean> arr = new ArrayList<ConsegnaBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE e_utente = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				ConsegnaBean bean = new ConsegnaBean();
				bean.setIdconsegna(rs.getInt("id_consegna"));
				bean.setVia(rs.getString("via"));
				bean.setCap(rs.getInt("cap"));
				bean.setNumero(rs.getInt("numero"));
				bean.setCitta(rs.getString("citta"));
				
				arr.add(bean);
			}

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return arr;
		
	}
	
	
	
}