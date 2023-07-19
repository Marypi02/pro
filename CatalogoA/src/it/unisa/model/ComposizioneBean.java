package it.unisa.model;

public class ComposizioneBean {
    private int codi_prodotto;
    private int num_ordine;
    private double quantita;
    static final double iva = 22;
    private double prezzo;

    public int getCodi_prodotto() {
        return codi_prodotto;
    }

    public void setCodi_prodotto(int codi_prodotto) {
        this.codi_prodotto = codi_prodotto;
    }

    public int getNum_ordine() {
        return num_ordine;
    }

    public void setNum_ordine(int num_ordine) {
        this.num_ordine = num_ordine;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public double getIva() {
        return iva;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
