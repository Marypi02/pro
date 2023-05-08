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
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        cart cart = (cart) session.getAttribute("cart");
        synchronized(session) {
        	//se il carrello non esiste nella sessione, lo creiamo
        	if (cart == null) {
                cart = new cart();
                session.setAttribute("cart", cart);
            }
        	
        
        	String action = request.getParameter("action");
        	
        	try {
        		if(action != null) {
        			if (action.equalsIgnoreCase("addC")) {
    					int id = Integer.parseInt(request.getParameter("productCode"));
    					cart.addProduct(model.doRetrieveByKey(id));
    					request.setAttribute("cart", cart);
    					session.setAttribute("cart", cart);
    					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
    				    dispatcher.forward(request, response);
    				} else if (action.equalsIgnoreCase("deleteC")) {
    					int id = Integer.parseInt(request.getParameter("productCode"));
    					cart.deleteProduct(model.doRetrieveByKey(id), response);
    					session.setAttribute("cart", cart);
    		            request.setAttribute("cart", cart);
    		            if (cart.getProducts().isEmpty()) {
    	                    response.sendRedirect(request.getContextPath() + "/ProductView.jsp");
    	                } else {
    	                    RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
    	                    dispatcher.forward(request, response);
    	                }
    					//RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
    				    //dispatcher.forward(request, response);
    					//if(cart.getProducts().isEmpty()) {
    			        //response.sendRedirect("ProductView.jsp");
    						
    			    }else if (action.equalsIgnoreCase("setNumOrder")) {
    			    	//int numItems = Integer.parseInt(request.getParameter("quantity"));
    					int id = Integer.parseInt(request.getParameter("productCode"));
    					String quantityString = request.getParameter("quantity");
    					int quantity = 0;
    					if (quantityString != null && !quantityString.isEmpty()) {
    					    quantity = Integer.parseInt(quantityString);
    					    cart.setNumOrdered(model.doRetrieveByKey(id), quantity);
    					    //response.sendRedirect("Cart.jsp");
    					    session.setAttribute("cart", cart);
    					    request.setAttribute("cart", cart);
    					}else {
    						System.out.println("Errore!");
    					}
    					
    					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
	                    dispatcher.forward(request, response);

    			    }else if (action.equalsIgnoreCase("comeBack")) {
    			    	RequestDispatcher dispatcher = request.getRequestDispatcher("ProductView.jsp");
	                    dispatcher.forward(request, response);
    			    }
    					
    				}
        		}
        	//}
        	  catch (SQLException e) {
    			System.out.println("Error:" + e.getMessage());
    		}
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
