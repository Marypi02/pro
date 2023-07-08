package it.unisa.model;

public class RecensioneBean {
	
	private int idRecensione,voto;
	private Utente utente;
	private ProductBean prodotto;
	private String testo;
	
	public RecensioneBean() {
	}

	public int getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(int idRecensione) {
		this.idRecensione = idRecensione;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public ProductBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProductBean prodotto) {
		this.prodotto = prodotto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	
	
	
	

}

