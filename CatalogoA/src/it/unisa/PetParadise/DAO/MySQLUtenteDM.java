package it.unisa.PetParadise.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.*;

public class MySQLUtenteDM implements UtenteDAO {
	
	//private static final String TABLE_NAME = "utente";
	
	/** La query per l'inserimento di un nuovo cliente */
    //private static final String CREATE_QUERY = "INSERT INTO utente (nome, cognome, password, email, valuta, indirizzo, admin) VALUES (?,?,?,?,?,?,?)";
    
    /** La query per la lettura di un singolo cliente. */
    private static final String READ_QUERY = "SELECT * FROM utente WHERE idutente = ?";
    
    /** La query per la lettura di tutti i clienti. */
    private static final String READ_ALL_QUERY = "SELECT * FROM utente";
    
    /** La query per l'aggiornamento di un singolo cliente. */
    private static final String UPDATE_QUERY = "UPDATE utente SET email=?, password=?, nome=?, cognome=?, username=?,  indirizzo=?, admin=? WHERE code = ?";
    
    /** La query per la cancellazione di un singolo cliente. */
    private static final String DELETE_QUERY = "DELETE FROM utente WHERE code = ?";

    @Override
	public synchronized void createUtente(Utente utente) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String insertSQL = "INSERT INTO utente (email, password, nome, cognome,  indirizzo, admin) VALUES (?, ?, ?, ?, ?, ?)";
   
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
           
            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getPassword());
            preparedStatement.setString(3, utente.getNome());
            preparedStatement.setString(4, utente.getCognome());
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setBoolean(6, utente.isAdmin());
            preparedStatement.executeUpdate();
            
            connection.commit();
        } finally {
            try {
            	if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
        }
 

	}
    
	@Override
	public synchronized List<Utente> getAllUtenti() throws SQLException{
		
		Connection con = null;
		PreparedStatement statement = null;
        
        
        List<Utente> utenti = new ArrayList<Utente>();
		
        
        try {
        	con = DriverManagerConnectionPool.getConnection();
        	statement = con.prepareStatement(READ_ALL_QUERY);
        	statement.execute();
        	ResultSet result = statement.executeQuery();
        	
        	while(result.next()) {
        		Utente utente = new Utente();
        		
        		utente.setIdutente(result.getInt("code"));
        		utente.setEmail(result.getString("email"));
        		utente.setPassword(result.getString("password"));
        		utente.setNome(result.getString("nome"));
        		utente.setCognome(result.getString("cognome"));
        		utente.setIndirizzo(result.getString("indirizzo"));
        		
        		utenti.add(utente);
        	}
        	
        } finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return utenti;
	}
	

	@Override
	public synchronized Utente getUtente(int code) throws SQLException {
		
		Connection con = null;
		PreparedStatement statement = null;
		Utente utente = new Utente();
		
		 try {
	            con = DriverManagerConnectionPool.getConnection();
	            statement = con.prepareStatement(READ_QUERY);
	            statement.setInt(1, code);
	            
	            ResultSet result = statement.executeQuery();
	 
	            while (result.next()) {
	        		utente.setIdutente(result.getInt("code"));
	        		utente.setEmail(result.getString("email"));
	        		utente.setPassword(result.getString("password"));
	        		utente.setNome(result.getString("nome"));
	        		utente.setCognome(result.getString("cognome"));
	        		utente.setIndirizzo(result.getString("indirizzo"));
	        		
	        		
	            } 
	        } finally {
				try {
					if (statement != null)
						statement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(con);
				}
			}
	 
		return utente;
	}
	
	

	@Override
	public boolean updateUtente(Utente utente) {
		Connection con = null;
        PreparedStatement statement = null;
        try {
        	con = DriverManagerConnectionPool.getConnection();
        	statement = con.prepareStatement(UPDATE_QUERY);
        	//statement.setInt(0, utente.getIdUtente());
        	statement.setString(1, utente.getEmail());
        	statement.setString(2, utente.getPassword());
        	statement.setString(3, utente.getNome());
            statement.setString(4, utente.getCognome());
            statement.setString(5, utente.getIndirizzo());
            statement.setBoolean(6, utente.isAdmin());
            
            
        	statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            	statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
	}

	@Override
	public synchronized boolean deleteUtente(int code) throws SQLException{
		Connection con = null;
        PreparedStatement statement = null;
        
        int result = 0;
        
        try {
            con = DriverManagerConnectionPool.getConnection();
            statement = con.prepareStatement(DELETE_QUERY);
            statement.setInt(1, code); //prima era 1
            result = statement.executeUpdate();
            
            
        } finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return (result != 0);
	}
}

