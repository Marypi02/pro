package it.unisa.PetParadise.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.*;

public class MySQLUtenteDS implements UtenteDAO {
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage2");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	/** La query per l'inserimento di un nuovo cliente */
    private static final String CREATE_QUERY = "INSERT INTO utente (email, password, nome, cognome, indirizzo, citta, admin) VALUES (?,?,?,?,?,?,?)";
    
    /** La query per la lettura di un singolo cliente. */
    private static final String READ_QUERY = "SELECT * FROM utente WHERE email = ?";
    
    /** La query per la lettura di tutti i clienti. */
    private static final String READ_ALL_QUERY = "SELECT * FROM utente";
    
    /** La query per l'aggiornamento di un singolo cliente. */
    private static final String UPDATE_QUERY = "UPDATE utente SET email=?,password=?, nome=?, cognome=?, indirizzo=?, citta=?, admin=? WHERE code = ?";
    
    /** La query per la cancellazione di un singolo cliente. */
    private static final String DELETE_QUERY = "DELETE FROM utente WHERE code = ?";

   //metodo doSave
    @Override
	public synchronized void createUtente(Utente utente) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
   
        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY);
            
            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getPassword());
            preparedStatement.setString(3, utente.getNome());
            preparedStatement.setString(4, utente.getCognome()); 
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setString(6, utente.getCitta());
            preparedStatement.setBoolean(7, utente.isAdmin());
            
            preparedStatement.executeUpdate();
            
            connection.commit();
        } finally {
            try {
            	if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				
				if (connection != null)
					connection.close();
			}
        }
 

	}
    
    //doRetrieveAll
	@Override
	public synchronized List<Utente> getAllUtenti() throws SQLException{
		
		Connection con = null;
		PreparedStatement statement = null;
		
		List<Utente> utenti = new ArrayList<Utente>();
		Utente utente = null;
        ResultSet result = null;
        
        try {
        	con = ds.getConnection();
        	statement = con.prepareStatement(READ_ALL_QUERY);
        	result = statement.executeQuery();
        	
        	while(result.next()) {
        		utente = new Utente();
        		
        		utente.setIdutente(result.getInt("code"));
        		utente.setEmail(result.getString("email"));
        		utente.setPassword(result.getString("password"));
        		utente.setNome(result.getString("nome"));
        		utente.setCognome(result.getString("cognome"));
        		utente.setIndirizzo(result.getString("indirizzo"));
        		utente.setCitta(result.getString("citta"));
        		
        		utenti.add(utente);
        	}
        	
        } 
        finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (con != null)
					con.close();
			}
        }
		
		return utenti;
	}

	@Override
	public synchronized Utente getUtente(String email) throws SQLException{
		
		Connection con = null;
		PreparedStatement statement = null;
		
		Utente utente = new Utente();
		
		
		 try {
	            con = ds.getConnection();
	            statement = con.prepareStatement(READ_QUERY);
	            statement.setString(1, email);
	            //statement.execute();
	            ResultSet result = statement.executeQuery();
	 
	            while(result.next()) {
	        		utente.setIdutente(result.getInt("code"));
	        		utente.setEmail(result.getString("email"));
	        		utente.setPassword(result.getString("password"));
	        		utente.setNome(result.getString("nome"));
	        		utente.setCognome(result.getString("cognome"));
	        		utente.setIndirizzo(result.getString("indirizzo"));
	        		utente.setCitta(result.getString("citta"));
	        		
	            } 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        } finally {
	        	try {
					if (statement != null)
						statement.close();
				} finally {
					if (con != null)
						con.close();
				}
	        }
	 
		
		return utente;
	}

		@Override
		public void updateUtente(Utente utente) {
		    Connection con = null;
		    PreparedStatement statement = null;
		    
		    try {
		        con = ds.getConnection();
		        statement = con.prepareStatement(UPDATE_QUERY);

		        statement.setString(1, utente.getEmail());
		        statement.setString(2, utente.getPassword());
		        statement.setString(3, utente.getNome());
		        statement.setString(4, utente.getCognome());
		        statement.setString(5, utente.getIndirizzo());
		        statement.setString(6, utente.getCitta());
		        statement.setBoolean(7, utente.isAdmin());
		        statement.setInt(8, utente.getIdutente());

		        int rowsUpdated = statement.executeUpdate();
		        
		        if (rowsUpdated > 0) {
		            System.out.println("Utente aggiornato correttamente nel database.");
		        } else {
		            System.out.println("Nessuna riga aggiornata nel database.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (statement != null)
		                statement.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        try {
		            if (con != null)
		                con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}



	
	//metodo doDelete
	@Override
	public synchronized boolean deleteUtente(int code) throws SQLException{
		Connection con = null;
        PreparedStatement statement = null;
        
        int result = 0;
        
        try {
            con = ds.getConnection();
            statement = con.prepareStatement(DELETE_QUERY);
            statement.setInt(1, code);  
             
            result = statement.executeUpdate();
            
            
        } 
        finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return (result != 0);
	

}
	
	public boolean isEmailPresent(String email) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count FROM utente WHERE email = ?");
	        preparedStatement.setString(1, email);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count > 0;
	        }
	    } finally {
	        // Chiusura delle risorse all'interno del blocco finally
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return false;
	}

}