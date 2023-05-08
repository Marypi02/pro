package it.unisa.model;

/*NECESSARIA PER LA PAGINA DEL CARRELLO*/

public class ProductOrder {
	private ProductBean product;
	private int numItems;
	
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
	
	public double getUnitCost() {
		return(getProduct().getPrice());
	}
	
	public void incrementNumItems() {
		setNumItems(getNumItems() + 1);
	}
	
	public void cancelOrder() {
	    setNumItems(0);
	}

	public double getTotalCost() {
	    return(getNumItems() * getUnitCost());
	}
}
