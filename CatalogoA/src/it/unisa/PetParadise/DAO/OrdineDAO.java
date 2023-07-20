package it.unisa.PetParadise.DAO;

import it.unisa.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OrdineDAO implements OrderModel { //OrdineDM
	
	private static final String TABLE_NAME = "ordine";
	private static final String TABLE_NAME2 = "utente";
	
	        private static final String CREATE_ORDINE_QUERY = "INSERT INTO ordine (data_ordine, stato_ordine, cod_consegna, cod_pagamento, cod_utente, prezzo_totale) VALUES (?, ?, ?, ?, ?, ?)";
	        //private static final String CREATE_COMPOSIZIONE_QUERY = "INSERT INTO composizione (cod_prodotto, num_ordine, quantita, iva, prezzo) VALUES (?, ?, ?, ?, ?)";
	        
	        
	        public synchronized int createOrdine(ProductOrder order) throws SQLException{
	        	Connection connection = null;
	            PreparedStatement preparedStatement = null;
	            
	            int code = 0;
	            
	            try {
	                connection = DriverManagerConnectionPool.getConnection();
	                preparedStatement = connection.prepareStatement(CREATE_ORDINE_QUERY);
	               
	                preparedStatement.setDate(1, new java.sql.Date(order.getData_ordine().getTime()));
	                preparedStatement.setString(2, order.getStato_ordine());
	                preparedStatement.setInt(3, order.getCodConsegna().getIdconsegna());
	                preparedStatement.setInt(4, order.getCodPagamento().getIdpagamento());
	                preparedStatement.setInt(5, order.getCodUtente().getIdutente());
	                preparedStatement.setDouble(6, order.getTotalPrice());
	                preparedStatement.executeUpdate();
	                
	                connection.commit();
	                
	                ResultSet keys = preparedStatement.getGeneratedKeys();
	    			keys.next();
	    			code = keys.getInt(1);
	    			
	            } finally {
	                try {
	                	if (preparedStatement != null)
	    					preparedStatement.close();
	    			} finally {
	    				if(connection != null) {
	    					connection.close();
	    				}
	    			}
	                
	            }
	            return code;
	        }
	
	public synchronized ProductOrder doRetrieveByKey(int idordine) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductOrder bean = new ProductOrder();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id_ordine = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idordine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setData_ordine(rs.getDate("data_ordine"));
				bean.setStato_ordine(rs.getString("stato_ordine"));
				bean.setTotalCost(rs.getDouble("prezzo_totale"));
				
				ConsegnaDAO cdao = new ConsegnaDAO();
				ConsegnaBean cbean = cdao.doRetrieveByKey(rs.getInt("cod_consegna"));
				bean.setCodConsegna(cbean);
				
				PagamentoDAO pdao = new PagamentoDAO();
				PagamentoBean pbean = pdao.doRetrieveByKey(rs.getInt("cod_pagamento"));
				bean.setCodPagamento(pbean);
				
				MySQLUtenteDM udao = new MySQLUtenteDM();
				Utente ubean = udao.getUtente(rs.getString("cod_utente"));
				bean.setCodUtente(ubean);
				
				/*ComposizioneDAO codao = new ComposizioneDAO();
				bean.setComposizione(codao.doRetrieveByOrdine(rs.getInt("id_ordine")));*/
				
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
	
	public synchronized boolean doDelete(int idordine) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE id_ordine = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idordine);

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
	
	public synchronized void doUpdate (ProductOrder order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String upsql = "UPDATE " + OrdineDAO.TABLE_NAME + 
						" SET data_ordine = ?, stato_ordine = ?, cod_consegna = ?, cod_pagamento = ?, cod_utente = ?, prezzo_totale = ? " + 
						"WHERE id_ordine = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(upsql);
			
			preparedStatement.setDate(1, order.getData_ordine());
			preparedStatement.setString(2, order.getStato_ordine());
			preparedStatement.setInt(3, order.getCodConsegna().getIdconsegna());
			preparedStatement.setInt(4, order.getCodPagamento().getIdpagamento());
			preparedStatement.setString(5, order.getCodUtente().getEmail());
			preparedStatement.setDouble(6, order.getTotalCost());
			
			preparedStatement.executeUpdate();
			//connection.commit();

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
	
public synchronized Collection<ProductOrder> doRetrieveAll() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductOrder> order = new ArrayList<ProductOrder>();
		
		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				ProductOrder bean = new ProductOrder();

				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setData_ordine(rs.getDate("data_ordine"));
				bean.setStato_ordine(rs.getString("stato_ordine"));
				ConsegnaDAO cdao = new ConsegnaDAO();
				ConsegnaBean cbean = cdao.doRetrieveByKey(rs.getInt("cod_consegna"));
				bean.setCodConsegna(cbean);
				
				PagamentoDAO pdao = new PagamentoDAO();
				PagamentoBean pbean = pdao.doRetrieveByKey(rs.getInt("cod_pagamento"));
				bean.setCodPagamento(pbean);
				
				MySQLUtenteDM udao = new MySQLUtenteDM();
				Utente ubean = udao.getUtente(rs.getString("cod_utente"));
				bean.setCodUtente(ubean);
				
				bean.setTotalCost(rs.getDouble("prezzo_totale"));
				
				
				/*ComposizioneDAO codao = new ComposizioneDAO();
				bean.setComposizione(codao.doRetrieveByOrdine(rs.getInt("id_ordine")));*/
				
				order.add(bean);
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
		return order;
		
		
		
		
	}

public synchronized Collection<ProductOrder> doRetrieveAllByUtente(String email) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<ProductOrder> order = new ArrayList<ProductOrder>();
	
	
	/*
	String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " JOIN " + OrdineDAO.TABLE_NAME2 +
			           " ON ordine.cod_utente = utente.code WHERE utente.email = ?";
			           */
	
	String selectSQL = "SELECT * FROM ordine JOIN utente ON ordine.cod_utente = utente.code WHERE utente.email = ?";
	
	
	try 
	{
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, email);
		
		System.out.println("Query: " + preparedStatement.toString()); // Stampa la query SQL per debug
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) 
		{
			ProductOrder bean = new ProductOrder();

			bean.setIdOrdine(rs.getInt("id_ordine"));
			bean.setData_ordine(rs.getDate("data_ordine"));
			bean.setStato_ordine(rs.getString("stato_ordine"));
			
			ConsegnaDAO cdao = new ConsegnaDAO();
			ConsegnaBean cbean = cdao.doRetrieveByKey(rs.getInt("cod_consegna"));
			bean.setCodConsegna(cbean);
			
			PagamentoDAO pdao = new PagamentoDAO();
			PagamentoBean pbean = pdao.doRetrieveByKey(rs.getInt("cod_pagamento"));
			bean.setCodPagamento(pbean);
			
			MySQLUtenteDM udao = new MySQLUtenteDM();
			Utente ubean = udao.getUtente(rs.getString("cod_utente"));
			bean.setCodUtente(ubean);
			
			bean.setTotalCost(rs.getDouble("prezzo_totale"));
			/*ComposizioneDAO codao = new ComposizioneDAO();
			bean.setComposizione(codao.doRetrieveByOrdine(rs.getInt("id_ordine")));
			*/
			order.add(bean);
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
	return order;
	
	
	
	
}
	
	public synchronized Collection<ProductOrder> getOrdersByDateRange(Date fromDate, Date toDate) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<ProductOrder> orderList = new ArrayList<>();

	    String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE data_ordine BETWEEN ? AND ?";

	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setDate(1, new java.sql.Date(fromDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(toDate.getTime()));

	        System.out.println("Query: " + preparedStatement.toString()); // Stampa la query SQL per debug
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            ProductOrder bean = new ProductOrder();

				bean.setIdOrdine(rs.getInt("id_ordine"));
				bean.setData_ordine(rs.getDate("data_ordine"));
				bean.setStato_ordine(rs.getString("stato_ordine"));
				
				ConsegnaDAO cdao = new ConsegnaDAO();
				ConsegnaBean cbean = cdao.doRetrieveByKey(rs.getInt("cod_consegna"));
				bean.setCodConsegna(cbean);
				
				PagamentoDAO pdao = new PagamentoDAO();
				PagamentoBean pbean = pdao.doRetrieveByKey(rs.getInt("cod_pagamento"));
				bean.setCodPagamento(pbean);
				
				MySQLUtenteDM udao = new MySQLUtenteDM();
				Utente ubean = udao.getUtente(rs.getString("cod_utente"));
				bean.setCodUtente(ubean);
				
				bean.setTotalCost(rs.getDouble("prezzo_totale"));
				/*ComposizioneDAO codao = new ComposizioneDAO();
				bean.setComposizione(codao.doRetrieveByOrdine(rs.getInt("id_ordine")));
				*/

	            orderList.add(bean);
	        }
	    } finally {
	        // Chiusura delle risorse (ResultSet, PreparedStatement, connessione) e rilascio della connessione al pool
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

	    return orderList;
	}

	
}