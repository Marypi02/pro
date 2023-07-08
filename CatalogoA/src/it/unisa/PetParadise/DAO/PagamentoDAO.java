package it.unisa.PetParadise.DAO;


import it.unisa.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PagamentoDAO {
	
	private static final String TABLE_NAME = "metodo_pagamento";
	private static final String TABLE_NAME2 = "utente";
	
	public synchronized  void doSave(PagamentoBean user, Utente ut) throws SQLException
	 {
	  Connection connection = null;
	  PreparedStatement preparedStatement = null;
	  

	  String insertSQL = "INSERT INTO " + PagamentoDAO.TABLE_NAME
	     + " (nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)"
	     + " VALUES (?, ?, ?, ?, ?, ?)";

	  try
	  {
	   connection = DriverManagerConnectionPool.getConnection();
	   
	   preparedStatement = connection.prepareStatement(insertSQL);
	   
	   preparedStatement.setString(1, user.getNominativo());
	   preparedStatement.setInt(2, user.getCVV());
	   preparedStatement.setInt(3, user.getMeseScadenza());
	   preparedStatement.setString(4, user.getCodice_carta());
	   preparedStatement.setInt(5, user.getAnnoScadenza());
	   preparedStatement.setInt(6, ut.getIdutente());
	 
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
	
	public synchronized PagamentoBean doRetrieveByKey(int idpagamento) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PagamentoBean bean = new PagamentoBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_pagamento = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idpagamento);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				
				bean.setNominativo(rs.getString("nominativo"));
				bean.setCVV(rs.getInt("CVV"));
				bean.setMeseScadenza(rs.getInt("meseScadenza"));
				bean.setCodice_carta(rs.getString("codice_carta"));
				bean.setAnnoScadenza(rs.getInt("annoScadenza"));
			
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
	
	public synchronized boolean doDelete(int idpagamento) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PagamentoDAO.TABLE_NAME + " WHERE id_pagamento = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idpagamento);

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
	
public synchronized ArrayList<PagamentoBean> doRetrieveByUtente(String user) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PagamentoBean> arr = new ArrayList<PagamentoBean>();

		String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME + " JOIN " + PagamentoDAO.TABLE_NAME2 +
		           " ON metodo_pagamento.e_utente = utente.code WHERE utente.email = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.execute();
			preparedStatement.setString(1, user);
			System.out.println("Query: " + preparedStatement.toString()); 

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				PagamentoBean bean = new PagamentoBean();
				
				
				bean.setNominativo(rs.getString("nominativo"));
				bean.setCVV(rs.getInt("CVV"));
				bean.setMeseScadenza(rs.getInt("meseScadenza"));
				bean.setCodice_carta(rs.getString("codice_carta"));
				bean.setAnnoScadenza(rs.getInt("annoScadenza"));
				
				MySQLUtenteDM udao = new MySQLUtenteDM();
				Utente ubean = udao.getUtente(rs.getInt("e_utente"));
				bean.setUtente(ubean);
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

public synchronized ArrayList<PagamentoBean> doRetrieveByUtente(int code) throws SQLException{
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	ArrayList<PagamentoBean> arr = new ArrayList<PagamentoBean>();

	String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE e_utente = ?";

	try 
	{
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.execute();
		//preparedStatement.setInt(1, code);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) 
		{
			PagamentoBean bean = new PagamentoBean();
			
			
			bean.setNominativo(rs.getString("nominativo"));
			bean.setCVV(rs.getInt("CVV"));
			bean.setMeseScadenza(rs.getInt("meseScadenza"));
			bean.setCodice_carta(rs.getString("codice_carta"));
			bean.setAnnoScadenza(rs.getInt("annoScadenza"));
			
			MySQLUtenteDM udao = new MySQLUtenteDM();
			Utente ubean = udao.getUtente(rs.getInt("e_utente"));
			bean.setUtente(ubean);
			
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
	
public synchronized Collection<PagamentoBean> doRetrieveAll() throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<PagamentoBean> pagamento = new ArrayList<PagamentoBean>();
	
	String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME;
	
	try 
	{
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) 
		{
			PagamentoBean bean = new PagamentoBean();
			
			
			
			bean.setNominativo(rs.getString("nominativo"));
			bean.setCVV(rs.getInt("CVV"));
			bean.setMeseScadenza(rs.getInt("meseScadenza"));
			bean.setCodice_carta(rs.getString("codice_carta"));
			bean.setAnnoScadenza(rs.getInt("annoScadenza"));
			
			MySQLUtenteDM utentedao = new MySQLUtenteDM();
			Utente utente = utentedao.getUtente(rs.getInt("e_utente"));
		
			bean.setUtente(utente);
			
			
			pagamento.add(bean);
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
	return pagamento;
	
	
	
	
}


	

}



