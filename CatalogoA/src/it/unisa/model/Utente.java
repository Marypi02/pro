package it.unisa.model;

public class Utente {
	
	public Utente() {
		super();
	}
	
	/**
	 * @param idutente
	 * @param nome
	 * @param cognome
	 * @param username
	 * @param password
	 * @param email
	 * @param valuta
	 * @param indirizzo
	 * @param admin
	 */
	public Utente(int idutente, String nome, String cognome, String password, String email,
			int valuta, String indirizzo, boolean admin) {
		super();
		this.idutente = idutente;
		this.nome = nome;
		this.cognome = cognome;
		this.username = email;
		this.password = password;
		this.email = email;
		this.valuta = valuta;
		this.indirizzo = indirizzo;
		this.admin = admin;
	}
	
	/**
	 * @param nome
	 * @param cognome
	 * @param username
	 * @param password
	 * @param email
	 * @param valuta
	 * @param indirizzo
	 * @param admin
	 */
	public Utente(String nome, String cognome, String password, String email,
			int valuta, String indirizzo, boolean admin) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = email;
		this.password = password;
		this.email = email;
		this.valuta = valuta;
		this.indirizzo = indirizzo;
		this.admin = admin;
	}
	
	
	/**
	 * @return the idutente
	 */
	public int getIdutente() {
		return idutente;
	}
	
	/**
	 * @param idutente the idutente to set
	 */
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the valuta
	 */
	public int getValuta() {
		return valuta;
	}
	
	/**
	 * @param valuta the valuta to set
	 */
	public void setValuta(int valuta) {
		this.valuta = valuta;
	}
	
	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	private int idutente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private int valuta;
	private String indirizzo;
	private boolean admin;

}
