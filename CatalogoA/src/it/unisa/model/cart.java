package it.unisa.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class cart {

	//private List<ProductBean> products;
	private Map<ProductBean, ProductOrder> products;
	
	/*public cart() {
		products = new ArrayList<ProductBean>();
	}
	
	public List<ProductBean> getProducts() {
		return  products;
	}*/
	
	public cart() {
		products = new HashMap<ProductBean, ProductOrder>();
	}
	
	public Map<ProductBean, ProductOrder> getProducts() {
		return products;
	}

	public void setProducts(Map<ProductBean, ProductOrder> products) {
		this.products = products;
	}
	
	public double getTotalPrice() {
	    double totalPrice = 0;

	    for (ProductOrder productOrder : products.values()) {
	        totalPrice += productOrder.getTotalCost();
	    }

	    return totalPrice;
	}

	
	@SuppressWarnings({ "null", "unlikely-arg-type" })
	public synchronized void addProduct(ProductBean product) {
	    /*ProductOrder order = null;
	    for(int i=0; i<products.size(); i++) {
	    	order.setProduct(products.get(i).getProduct());
	    	if (order.getProduct().getCode() == product.getCode()) {
	    		order.incrementNumItems();
	    		return;
	    		}
	    	}*/
		ProductOrder order;
		for(Map.Entry<ProductBean, ProductOrder> beancart : products.entrySet()) {
			order = beancart.getValue();
			if (order.getProduct().getCode() == product.getCode()) {
	    		order.incrementNumItems();
	    		return;
	    		}
		}
	    ProductOrder newOrder = new ProductOrder(product);
	    products.put(newOrder.getProduct(), newOrder);
	  }
	
	/* FUNZIONE PRECEDENTE X L'AGGIUNTA DEL PRODOTTO AL CARRELLO
	 
	    public void addProduct(ProductBean product) {
		products.add(product);
	}
	*/

	/*@SuppressWarnings("unlikely-arg-type")
	public void deleteProduct(ProductBean product) {
		for(Map.Entry<ProductBean, ProductOrder> beancart : products.entrySet()) {
			if(beancart.getKey().getCode() == product.getCode()) {
				beancart.getValue().cancelOrder();
				products.remove(beancart);
				break;
				}
			}
		}*/
	public void deleteProduct(ProductBean product, HttpServletResponse response) throws IOException {
	    Iterator<Map.Entry<ProductBean, ProductOrder>> iterator = products.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<ProductBean, ProductOrder> entry = iterator.next();
	        if (entry.getKey().getCode() == product.getCode()) {
	            entry.getValue().cancelOrder();
	            iterator.remove();
	            break;
	        }
	    }
	}
	   /* try {
	    	if(products.size() == 0) {
	    		response.sendRedirect("ProductView.jsp"); // reindirizza alla pagina dei prodotti
	    	}
	    } catch (IOException e) {
	        // gestione dell'eccezione
	    	System.out.println("Errore!");
	    }
	//Controlla se il carrello � vuoto
	    if (products.isEmpty()) {
	        // Reindirizza l'utente alla pagina dei prodotti
	        response.sendRedirect("ProductView.jsp");
	    } else  {
	        // Reindirizza l'utente alla pagina del carrello
	        response.sendRedirect("Cart.jsp");
	    }
	}*/

 	
	/*IN PRATICA, SE SI INSERISCE 0 COME QUANTIA' DEL PRODOTTO NEL CARRELLO,
	 * IL PRODOTTO VIENE CANCELLATO DAL CARRELLO*/
	//@SuppressWarnings({ "null", "unlikely-arg-type" })
	@SuppressWarnings("unlikely-arg-type")
	public synchronized void setNumOrdered(ProductBean product, int numOrdered) {
		 /*ProductOrder order = null;
		    for(int i=0; i<products.size(); i++) {
		    	order.setProduct(products.get(i).getProduct());
		    	if (order.getProduct().getCode() == product.getCode()) {
		    		if(numOrdered <= 0) {
		    			products.remove(i);
		    		}else {
		    			order.setNumItems(numOrdered);
		    			}
		    		return;
		    		}
		    	}*/
		ProductOrder order;
		for(Map.Entry<ProductBean, ProductOrder> beancart : products.entrySet()) {
			order = beancart.getValue();
			if (order.getProduct().getCode() == product.getCode()) {
	    		if(numOrdered <= 0) {
	    			products.remove(beancart);
	    		}else {
	    			order.setNumItems(numOrdered);
	    			}
	    		return;
	    		}
		}
		    ProductOrder newOrder = new ProductOrder(product);
		    products.put(newOrder.getProduct(), newOrder);
	 }
	
	
}
