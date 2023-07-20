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
    //private static final String CREATE_QUERY = "INSERT INTO utente (nome, cognome, password, email, valuta, indirizzo, citta, admin) VALUES (?,?,?,?,?,?,?,?)";
    
    /** La query per la lettura di un singolo cliente. */
    private static final String READ_QUERY = "SELECT * FROM utente WHERE email = ?";
    
    private static final String READ_QUERY2 = "SELECT * FROM utente WHERE code = ?";
    
    /** La query per la lettura di tutti i clienti. */
    private static final String READ_ALL_QUERY = "SELECT * FROM utente";
    
    /** La query per l'aggiornamento di un singolo cliente. */
    private static final String UPDATE_QUERY = "UPDATE utente SET email=?, password=?, nome=?, cognome=?, indirizzo=?, citta=?, admin=? WHERE code = ?";
    
    
   
    /** La query per la cancellazione di un singolo cliente. */
    private static final String DELETE_QUERY = "DELETE FROM utente WHERE code = ?";

    @Override
	public synchronized void createUtente(Utente utente) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String insertSQL = "INSERT INTO utente (email, password, nome, cognome,  indirizzo, citta, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
   
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
           
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
        		
        		//utente.setIdutente(result.getInt("code"));
        		utente.setEmail(result.getString("email"));
        		utente.setPassword(result.getString("password"));
        		utente.setNome(result.getString("nome"));
        		utente.setCognome(result.getString("cognome"));
        		utente.setIndirizzo(result.getString("indirizzo"));
        		utente.setCitta(result.getString("citta"));
        		
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
	public synchronized Utente getUtente(String email) throws SQLException {
		
		Connection con = null;
		PreparedStatement statement = null;
		Utente utente = new Utente();
		
		 try {
	            con = DriverManagerConnectionPool.getConnection();
	            statement = con.prepareStatement(READ_QUERY);
	            statement.setString(1, email);
	            
	            ResultSet result = statement.executeQuery();
	 
	            while (result.next()) {
	        		
	        		utente.setEmail(result.getString("email"));
	        		utente.setPassword(result.getString("password"));
	        		utente.setNome(result.getString("nome"));
	        		utente.setCognome(result.getString("cognome"));
	        		utente.setIndirizzo(result.getString("indirizzo"));
	        		utente.setCitta(result.getString("citta"));
	        		
	        		
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
	        		
	        		utente.setEmail(result.getString("email"));
	        		utente.setPassword(result.getString("password"));
	        		utente.setNome(result.getString("nome"));
	        		utente.setCognome(result.getString("cognome"));
	        		utente.setIndirizzo(result.getString("indirizzo"));
	        		utente.setCitta(result.getString("citta"));
	        		
	        		
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
	public void updateUtente(Utente utente) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    // Prepara l'istruzione SQL per l'aggiornamento
	    String sql = "UPDATE utente SET email = ?, password = ?, nome = ?, cognome = ?, indirizzo = ?, citta = ?, admin = ? WHERE code = ?";

	    try {
	        // Recupera la connessione al database
	        connection = DriverManagerConnectionPool.getConnection();
	        statement = connection.prepareStatement(sql);

	        // Imposta i parametri dell'istruzione SQL con i valori dell'oggetto Utente
	        statement.setString(1, utente.getEmail());
	        statement.setString(2, utente.getPassword());
	        statement.setString(3, utente.getNome());
	        statement.setString(4, utente.getCognome());
	        statement.setString(5, utente.getIndirizzo());
	        statement.setString(6, utente.getCitta());
	        statement.setBoolean(7, utente.isAdmin());
	        statement.setInt(8, utente.getIdutente());

	        // Esegui l'istruzione di aggiornamento
	        statement.executeUpdate();
	    } finally {
	        try {
	            if (statement != null)
	                statement.close();
	        } finally {
	            DriverManagerConnectionPool.releaseConnection(connection);
	        }
	    }
	}


	
	
	


	@Override
	public synchronized boolean deleteUtente(int code) throws SQLException{
		Connection con = null;
        PreparedStatement statement = null;
        
        int result = 0;
        
        try {
            con = DriverManagerConnectionPool.getConnection();
            statement = con.prepareStatement(DELETE_QUERY);
            statement.setInt(1, code); 
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
	
	public boolean isEmailPresent(String email) throws SQLException{
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count FROM utente WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }

        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return false;
    }

	
	

	
}
