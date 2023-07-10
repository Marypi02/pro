package it.unisa.model;
import java.sql.Date;
import java.util.*;
/*NECESSARIA PER LA PAGINA DEL CARRELLO*/

public class ProductOrder {
	private ProductBean product;
	private int numItems;
	int idOrdine;
	PagamentoBean codPagamento;
	ConsegnaBean codConsegna;
	Utente codUtente;
	Date data_ordine;
	String stato_ordine;
	Double Prezzo_totale;
	//private List<ProductOrder> orderItems;
	
	public ProductOrder(ProductBean product) {
		setProduct(product);
		setNumItems(1);
	}
	
	public ProductBean getProduct() {
		return product;
	}
	
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public PagamentoBean getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(PagamentoBean codPagamento) {
		this.codPagamento = codPagamento;
	}
	
	
	public ConsegnaBean getCodConsegna() {
		return codConsegna;
	}
	public void setCodConsegna(ConsegnaBean codConsegna) {
		this.codConsegna = codConsegna;
	}
	
	
	public Utente getCodUtente() {
		return codUtente;
	}
	
	
	public void setCodUtente(Utente codUtente) {
		this.codUtente = codUtente;
	}
	
	
	public Date getData_ordine() {
		return data_ordine;
	}
	
	
	public void setData_ordine(Date data_ordine) {
		this.data_ordine = data_ordine;
	}
	
	
	public String getStato_ordine() {
		return stato_ordine;
	}
	
	
	public void setStato_ordine(String stato_ordine) {
		this.stato_ordine = stato_ordine;
	}
	
	
	/*public List<ProductOrder> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(ProductOrder orderItem) {
        orderItems.add(orderItem);
    }
    */
    
	
	public double getUnitCost() {
		return(getProduct().getPrice());
	}
	
	public void incrementNumItems() {
		setNumItems(getNumItems() + 1);
	}
	
	public void cancelOrder() {
	    setNumItems(0);
	}



	public Double getTotalCost() {
	    return(getNumItems() * getUnitCost());
	}
	
	public Double getTotalCost2() {
		return Prezzo_totale;
	}
	
	public void setTotalCost(Double prezzo_totale) {
		Prezzo_totale = prezzo_totale;
	}
	
	public ProductOrder() {
		super();
	}
	
}
