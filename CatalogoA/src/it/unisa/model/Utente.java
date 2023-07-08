package it.unisa.model;

import java.util.ArrayList;

public class Utente {
	
	public Utente() {
		super();
	}
	
	
	private int code;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	
	private String indirizzo;
	private String citta;
	static final boolean admin = false;

	ArrayList<PagamentoBean> Pagamento = new ArrayList<>();
	ArrayList<ConsegnaBean> Consegna = new ArrayList<>();
	
	

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
	public Utente(int code, String nome, String cognome, String password, String email, String indirizzo, String citta) {
		super();
		this.code = code;
		this.nome = nome;
		this.cognome = cognome;
		this.username = email;
		this.password = password;
		this.email = email;
		this.indirizzo = indirizzo;
		this.citta = citta;
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
	public Utente(String nome, String cognome, String password, String email, String indirizzo, String citta) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = email;
		this.password = password;
		this.email = email;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}
	
	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @return the idutente
	 */
	public int getIdutente() {
		return code;
	}
	
	/**
	 * @param idutente the idutente to set
	 */
	public void setIdutente(int code) {
		this.code = code;
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
	
	public ArrayList<PagamentoBean> getPagamento() {
		return Pagamento;
	}

	public void setPagamento(ArrayList<PagamentoBean> pagamento) {
		Pagamento = pagamento;
	}

	public ArrayList<ConsegnaBean> getConsegna() {
		return Consegna;
	}

	public void setConsegna(ArrayList<ConsegnaBean> consegna) {
		Consegna = consegna;
	}
	
}
