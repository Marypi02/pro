package it.unisa.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/*NECESSARIA PER LA PAGINA DEL CARRELLO*/

public class ProductOrder {
	private ProductBean product;
	private int numItems;
	int idOrdine;
	private PagamentoBean codPagamento;
	private ConsegnaBean codConsegna;
	private Utente codUtente;
	private Date data_ordine;
	private String stato_ordine;
	private Double Prezzo_totale;
	private Double totalPrice; //serve per sapere la spesa totale dell'ordine 
	private List<ProductBean> orderItems;

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
	
	
	public void setData_ordine(Date date) {
		this.data_ordine = date;
	}
	
	
	public String getStato_ordine() {
		return stato_ordine;
	}
	
	
	public void setStato_ordine(String stato_ordine) {
		this.stato_ordine = stato_ordine;
	}
	
	
	public List<ProductBean> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(ProductBean Item) {
    	if(orderItems == null) {
    		orderItems = new ArrayList<ProductBean>();
    	}
        orderItems.add(Item);
    }
	
	public double getUnitCost() {
		return(getProduct().getPrice());
	}
	
	public void incrementNumItems() {
		setNumItems(getNumItems() + 1);
	}
	
	public void cancelOrder() {
	    setNumItems(0);
	}
	
	//serve per calcolare il costo totale del prodotto x nel carrello in base a prezzo unitario e items
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/*public void setTotalPrice() {
		this.totalPrice = 0.0;
		
        if (orderItems != null) {
            for (ProductBean product : orderItems) {
                totalPrice += (product.getPrice() * numItems);
            }
        }
	}*/
	
}
