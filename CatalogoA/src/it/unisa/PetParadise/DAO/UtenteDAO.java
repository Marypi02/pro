package it.unisa.PetParadise.DAO;

import java.sql.SQLException;
import java.util.List;

import it.unisa.model.*;

public interface UtenteDAO {
	
	/** Recupera tutti gli utenti dal DB. */
	public List<Utente> getAllUtenti() throws SQLException;
	
	/** Recupera un oggetto Utente esistente a partire dalla email. */
	public Utente getUtente(String email) throws SQLException;
	
	/** Crea un oggetto Utente 
	 * @throws SQLException */
	public void createUtente(Utente utente) throws SQLException;
	
	/** Aggiorna un oggetto Utente esistente. 
	 * @throws SQLException */
	public void updateUtente(Utente utente) throws SQLException;
	
	/** Cancella un oggetto Utente esistente. */
	public  boolean deleteUtente(int idutente) throws SQLException;
	
	// Vede se una mail � gi� presente
	public boolean isEmailPresent(String email) throws SQLException;

}
