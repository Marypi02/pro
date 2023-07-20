package it.unisa.control;

import it.unisa.model.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDM;
import it.unisa.model.ProductModelDS;

/**
 * Servlet implementation class CartControl
 */

@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 static boolean isDataSource = true;
	
	static ProductModel model;
	
	static {
		// Verifica se si sta utilizzando una sorgente dati (DataSource)
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
    
 public CartControl() {
     super();
 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
     cart cart = (cart) session.getAttribute("cart");
     synchronized(session) {
     	// Se il carrello non esiste nella sessione, lo creiamo
     	if (cart == null) {
             cart = new cart();
             session.setAttribute("cart", cart);
         }
     	
     	String action = request.getParameter("action");
     	
     	try {
     		if(action != null) {
     			if (action.equalsIgnoreCase("addC")) {
 					int id = Integer.parseInt(request.getParameter("productCode"));
 					// Aggiunge il prodotto al carrello
 					cart.addProduct(model.doRetrieveByKey(id));
 					request.setAttribute("cart", cart);
 					session.setAttribute("cart", cart);
 					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
 				    dispatcher.forward(request, response);
 				} else if (action.equalsIgnoreCase("deleteC")) {
 					int id = Integer.parseInt(request.getParameter("productCode"));
 					// Rimuove il prodotto dal carrello
 					cart.deleteProduct(model.doRetrieveByKey(id));
 					session.setAttribute("cart", cart);
 		            request.setAttribute("cart", cart);
 		            // Se il carrello è vuoto, reindirizza alla pagina ProductView.jsp
 		            if (cart.getProducts().isEmpty()) {
 	                    response.sendRedirect(request.getContextPath() + "/ProductView.jsp");
 	                } else {
 	                    RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
 	                    dispatcher.forward(request, response);
 	                }
 				} else if (action.equalsIgnoreCase("setNumOrder")) {
 			    	int id = Integer.parseInt(request.getParameter("productCode"));
 					String quantityString = request.getParameter("quantity");
 					int quantity = 0;
 					if (quantityString != null && !quantityString.isEmpty()) {
 					    quantity = Integer.parseInt(quantityString);
 					    // Imposta il numero di prodotti ordinati nel carrello
 					    cart.setNumOrdered(model.doRetrieveByKey(id), quantity);
 					    session.setAttribute("cart", cart);
 					    request.setAttribute("cart", cart);
 					} else {
 						System.out.println("Errore!");
 					}
 					
 					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
	                    dispatcher.forward(request, response);
 			    } else if (action.equalsIgnoreCase("comeBack")) {
 			    	// Reindirizza alla pagina ProductView.jsp
 			    	RequestDispatcher dispatcher = request.getRequestDispatcher("ProductView.jsp");
	                    dispatcher.forward(request, response);
 			    }
 					
 				}
     		
     	} catch (SQLException e) {
 			System.out.println("Error:" + e.getMessage());
 		}
     }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
