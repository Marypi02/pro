package it.unisa.PetParadise.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.*;

public class MySQLUtenteDM implements UtenteDAO {
	
	/** La query per l'inserimento di un nuovo cliente */
    private static final String CREATE_QUERY = "INSERT INTO utente (nome, cognome, password, email, valuta, indirizzo, admin) VALUES (?,?,?,?,?,?,?)";
    
    /** La query per la lettura di un singolo cliente. */
    private static final String READ_QUERY = "SELECT * FROM utente WHERE idutente = ?";
    
    /** La query per la lettura di tutti i clienti. */
    private static final String READ_ALL_QUERY = "SELECT * FROM utente";
    
    /** La query per l'aggiornamento di un singolo cliente. */
    private static final String UPDATE_QUERY = "UPDATE utente SET nome=?, cognome=?, username=?, password=?, email=?, indirizzo=?, admin=? WHERE idutente = ?";
    
    /** La query per la cancellazione di un singolo cliente. */
    private static final String DELETE_QUERY = "DELETE FROM utente WHERE idutente = ?";

    @Override
	public synchronized void createUtente(Utente utente) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
   
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getPassword());
            preparedStatement.setString(4, utente.getEmail());
            preparedStatement.setString(6, utente.getIndirizzo());
            preparedStatement.setBoolean(7, utente.isAdmin());
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
        		
        		utente.setIdutente(result.getInt("idutente"));
        		utente.setNome(result.getString("nome"));
        		utente.setCognome(result.getString("cognome"));
        		utente.setPassword(result.getString("password"));
        		utente.setEmail(result.getString("email"));
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
	public synchronized Utente getUtente(int idutente) throws SQLException {
		
		Connection con = null;
		PreparedStatement statement = null;
		Utente utente = new Utente();
		
		 try {
	            con = DriverManagerConnectionPool.getConnection();
	            statement = con.prepareStatement(READ_QUERY);
	            statement.setInt(1, idutente);
	            
	            ResultSet result = statement.executeQuery();
	 
	            while (result.next()) {
	        		utente.setIdutente(result.getInt("idutente"));
	        		utente.setNome(result.getString("nome"));
	        		utente.setCognome(result.getString("cognome"));
	        		utente.setPassword(result.getString("password"));
	        		utente.setEmail(result.getString("email"));
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
        	statement.setString(1, utente.getNome());
            statement.setString(2, utente.getCognome());
            statement.setString(4, utente.getPassword());
            statement.setString(5, utente.getEmail());
            statement.setString(7, utente.getIndirizzo());
            statement.setBoolean(8, utente.isAdmin());
            
            statement.setInt(9, utente.getIdutente());
            
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
	public synchronized boolean deleteUtente(int idutente) throws SQLException{
		Connection con = null;
        PreparedStatement statement = null;
        
        int result = 0;
        
        try {
            con = DriverManagerConnectionPool.getConnection();
            statement = con.prepareStatement(DELETE_QUERY);
            statement.setInt(1, idutente);
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

