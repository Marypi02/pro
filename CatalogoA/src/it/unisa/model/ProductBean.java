package it.unisa.model;


import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String categoria; 
	String specie;
	int code;
	String name;
	String description;
	double price;
	String nomeImg;
	int quantity;

	

	public ProductBean() {
		specie = "";
		code = -1;
		name = "";
		description = "";
		quantity = 0;
	}
	
	public ProductBean(int code, String name) {
        this.code = code;
        this.name = name;
        // Inizializza gli altri campi con valori di default
        specie = "";
        description = "";
        price = 0.0;
        nomeImg = "";
        quantity = 0;
    }
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public String getNomeImg() {
		return nomeImg;
	}

	public void setNomeImg(String nomeImg) {
		this.nomeImg = nomeImg;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantity + ". " + description;
	}

}
