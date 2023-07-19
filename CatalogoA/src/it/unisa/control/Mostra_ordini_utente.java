package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;
import it.unisa.PetParadise.DAO.ConsegnaDAO;
import it.unisa.PetParadise.DAO.OrdineDAO;
import it.unisa.PetParadise.DAO.PagamentoDAO;

@WebServlet("/Mostra_ordini_utente")
public class Mostra_ordini_utente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Mostra_ordini_utente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrdineDAO odao = new OrdineDAO();

        String action = request.getParameter("action");

        if (action != null && action.equals("mostra")) {
            Utente ubean = (Utente) request.getSession().getAttribute("utente");
            if (ubean != null) {
                try {
                    ArrayList<ProductOrder> ordini = (ArrayList<ProductOrder>) odao.doRetrieveAllByUtente(ubean.getEmail());
                    request.setAttribute("ordini", ordini);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if (action != null && action.equals("procediOrdine")) {
            cart cart = (cart) request.getSession().getAttribute("cart");
            Utente utente = (Utente) request.getSession().getAttribute("utente");

            if (cart != null && utente != null) {
                ProductOrder order = new ProductOrder();
                ConsegnaDAO consegna = new ConsegnaDAO();
			    PagamentoDAO pagamento = new PagamentoDAO();
                
                order.setCodUtente(utente);
                try {
					order.setCodConsegna(consegna.doRetrieveByKey(utente.getIdutente()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Impossibile recuperare il metodo di consegna!");
				}
                try {
					order.setCodPagamento(pagamento.doRetrieveByKey(utente.getIdutente()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Impossibile recuperare il metodo di pagamento!");
				}
                Date currentDate = new Date();
                order.setData_ordine(new java.sql.Date(currentDate.getTime()));
                order.setStato_ordine("In attesa");
                order.setTotalPrice(cart.getTotalCost());

                Map<ProductBean, ProductOrder> products = cart.getProducts();
                for (ProductBean productBean : products.keySet()) {
                    order.addOrderItem(productBean);
                }

                try {
                    boolean success = odao.createOrdine(order);
                    if (success) {
                        request.getSession().removeAttribute("cart");
                        request.setAttribute("message", "Ordine effettuato con successo!");
                    } else {
                    	System.out.println("Impossibile effettuare l'ordine!");
                        request.setAttribute("message", "Errore durante la creazione dell'ordine.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Errore durante la creazione dell'ordine.");
                }
            }else {
            	System.out.println("Cart e User sono null!");
            }
        }

        RequestDispatcher rs = request.getRequestDispatcher("utente.jsp");
        rs.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
