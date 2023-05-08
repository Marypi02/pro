package it.unisa.PetParadise.DAO;

import java.sql.SQLException;
import java.util.List;

import it.unisa.model.*;

public interface UtenteDAO {
	
	/** Recupera tutti gli utenti dal DB. */
	public List<Utente> getAllUtenti() throws SQLException;
	
	/** Recupera un oggetto Utente esistente a partire dall'id. */
	public Utente getUtente(int idutente) throws SQLException;
	
	/** Crea un oggetto Utente 
	 * @throws SQLException */
	public void createUtente(Utente utente) throws SQLException;
	
	/** Aggiorna un oggetto Utente esistente. */
	public boolean updateUtente(Utente utente);
	
	/** Cancella un oggetto Utente esistente. */
	public  boolean deleteUtente(int idutente) throws SQLException;

}
