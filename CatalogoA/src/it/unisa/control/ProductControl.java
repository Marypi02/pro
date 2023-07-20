package it.unisa.control;

import it.unisa.model.*;
import java.io.IOException; 
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.ProductModel;
import it.unisa.model.ProductModelDM;
import it.unisa.model.ProductModelDS;


/**
 * Servlet implementation class ProductControl
 */
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ProductModelDS usa il DataSource
	// ProductModelDM usa il DriverManager	
	static boolean isDataSource = true;
	
	static ProductModel model;
	
	static {
		if (isDataSource) {
			model = new ProductModelDS();
		} else {
			model = new ProductModelDM();
		}
	}
	
	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*cart cart = (cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new cart();
			request.getSession().setAttribute("cart", cart);
		}*/
		
		String action = request.getParameter("action");

		try {
			if (action != null) {
				/*if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetrieveByKey(id));
					request.setAttribute("cart", cart);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
				    dispatcher.forward(request, response);
				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
				    dispatcher.forward(request, response);
				}else*/ if (action.equalsIgnoreCase("read")) {
				    int id = Integer.parseInt(request.getParameter("id"));
				    ProductBean product = model.doRetrieveByKey(id);
				    request.setAttribute("product", product);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("Details.jsp");
				    dispatcher.forward(request, response);
				} else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				} else if(action.equalsIgnoreCase("edit")) {
					int id = Integer.parseInt(request.getParameter("id"));
					ProductBean product = model.doRetrieveByKey(id);
					request.setAttribute("product", product);
					RequestDispatcher dispatcher = request.getRequestDispatcher("EditProduct.jsp");
					dispatcher.forward(request, response);
					
				}else if (action.equalsIgnoreCase("all_products")) {
					
					Collection<ProductBean> products = model.get_prodotti();
					request.setAttribute("products", products);
					RequestDispatcher dispatcher = request.getRequestDispatcher("ProductView.jsp");
					dispatcher.forward(request, response);
					
					
				}else if (action.equalsIgnoreCase("accessori_cane")) {
				
				    Collection<ProductBean> products = model.getAccessori_cane();
				    request.setAttribute("products", products);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				    dispatcher.forward(request, response);
				
				
				} else if (action.equalsIgnoreCase("accessori_gatto")) {
				    
				     Collection<ProductBean> products = model.getAccessori_gatto();
				     request.setAttribute("products", products);
				     RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				     dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("accessori_uccelli")) {
				    
				        Collection<ProductBean> products = model.getAccessori_uccelli();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("accessori_pesci")) {
				  
				        Collection<ProductBean> products = model.getAccessori_pesci();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				   
				} else if (action.equalsIgnoreCase("cibo_gatto")) {
				    
				        Collection<ProductBean> products = model.getCibo_gatto();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("cibo_cane")) {
				    
				        Collection<ProductBean> products = model.getCibo_cane();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				   
				} else if (action.equalsIgnoreCase("cibo_uccelli")) {
				    
				        Collection<ProductBean> products = model.getCibo_uccelli();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("cibo_pesci")) {
				    
				        Collection<ProductBean> products = model.getCibo_pesci();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("giocattoli_cane")) {
				    
				        Collection<ProductBean> products = model.getGiocattoli_cane();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				       
				} else if (action.equalsIgnoreCase("giocattoli_gatto")) {
				    
				        Collection<ProductBean> products = model.getGiocattoli_gatto();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("gabbie_uccelli")) {
				   
				        Collection<ProductBean> products = model.getGabbie_uccelli();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("igiene_gatto")) {
				   
				        Collection<ProductBean> products = model.getIgiene_gatto();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("igiene_cane")) {
				    
				        Collection<ProductBean> products = model.getIgiene_cane();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				} else if (action.equalsIgnoreCase("igiene_uccelli")) {
				   
				        Collection<ProductBean> products = model.getIgiene_uccelli();
				        request.setAttribute("products", products);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("accessori.jsp");
				        dispatcher.forward(request, response);
				    
				    
				    
				}else if(action.equalsIgnoreCase("updateProduct")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String categoria = request.getParameter("categoria");
			        String specie = request.getParameter("specie");
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					String nameImg = request.getParameter("nameImg");
					
					ProductBean bean = new ProductBean();
					bean.setCode(id);
					bean.setCategoria(categoria);
			        bean.setSpecie(specie);
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);
					bean.setNomeImg(nameImg);
					model.updateProduct(bean);
					
					// Ottieni la lista aggiornata dei prodotti
				    Collection<ProductBean> products = model.doRetrieveAll(null);

				    // Imposta l'attributo "products" nella richiesta
				    request.setAttribute("products", products);
					
				}else if (action.equalsIgnoreCase("insert")) {
					String categoria = request.getParameter("categoria");
			        String specie = request.getParameter("specie");
					String name = request.getParameter("name");
		            String description = request.getParameter("description");
		            double price = Double.parseDouble(request.getParameter("price"));
		            int quantity = Integer.parseInt(request.getParameter("quantity"));
		            String nameImg = request.getParameter("nome_immagine");
		           
		            
		            // Assuming you have a ProductBean class to represent a product entity
		            ProductBean bean = new ProductBean();
		            bean.setCategoria(categoria);
		            bean.setSpecie(specie);
		            bean.setName(name);
		            bean.setDescription(description);
		            bean.setPrice(price);
		            bean.setQuantity(quantity);
		            bean.setNomeImg(nameImg);
		            
		            
		            model.doSave(bean);
				}
			}
		}
						
		 catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		/*request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);*/
		
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		HttpSession session = request.getSession(false);
		if (session != null) {
		    Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		    if (adminRoles) {
		        // Reindirizza all'area protetta per gli amministratori
		        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/protected.jsp");
		        dispatcher.forward(request, response);
		    } else {
		        // Reindirizza all'area pubblica
		        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		        dispatcher.forward(request, response);
		    }
		} else {
		    // La sessione non ï¿½ valida, reindirizza all'area pubblica
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		    dispatcher.forward(request, response);
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}