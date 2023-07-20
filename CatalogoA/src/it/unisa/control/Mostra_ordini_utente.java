package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;
import it.unisa.PetParadise.DAO.ComposizioneDAO;
import it.unisa.PetParadise.DAO.ConsegnaDAO;
import it.unisa.PetParadise.DAO.OrdineDAO;
import it.unisa.PetParadise.DAO.PagamentoDAO;

@WebServlet("/Mostra_ordini_utente")
public class Mostra_ordini_utente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    static boolean isDataSource = true;
    ComposizioneDAO comp = new ComposizioneDAO();
	
	OrderModel model = new OrdineDAO();

    public Mostra_ordini_utente() {
        super();
    }

    @SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        
        /* x controllare se recupera correttamente l'utente
        if(utente == null) {
        	System.out.println("utente non recuperato");
        }else {
        	System.out.println("Utente recuperato!" + utente.getIdutente());
        }*/

        if (action != null && action.equals("mostra")) {
            Utente ubean = (Utente) request.getSession().getAttribute("utente");
            if (ubean != null) {
                try {
                    ArrayList<ProductOrder> ordini = (ArrayList<ProductOrder>) model.doRetrieveAllByUtente(ubean.getEmail());
                    request.setAttribute("ordini", ordini);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if(action != null && action.equalsIgnoreCase("procediOrdine")) {
        	cart cart = (cart) request.getSession().getAttribute("cart");
        	
        	Double totalPrice = cart.getTotalCost();
        	int IdOrdine = 0;
        	
        	ConsegnaDAO consegna = new ConsegnaDAO();
        	PagamentoDAO pagamento = new PagamentoDAO();
        	
        	//creazione dell'ordine
        	ProductOrder bean = new ProductOrder();
        	Date currentDate = new Date();
            bean.setData_ordine(new java.sql.Date(currentDate.getTime()));
            bean.setStato_ordine("In attesa!");
            try {
				bean.setCodConsegna(consegna.doRetrieveByKey(utente.getIdutente()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Impossibile recuperare il metodo di consegna!");
			}
            try {
				bean.setCodPagamento(pagamento.doRetrieveByKey(utente.getIdutente()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Impossibile recuperare il metodo di pagamento!");
			}
            bean.setCodUtente(utente);
            bean.setTotalPrice(totalPrice);
            
            //creazione ordine nel db e recupero id dell'ordine
            try {
				IdOrdine = model.createOrdine(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Impossibile creare l'ordine!");
			}
            
            //recupero la lista dei prodotti dal carrello
            Map<ProductBean, ProductOrder> products = cart.getProducts();
            List<ProductBean> products2 = new ArrayList<ProductBean>();
            for (ProductBean productBean : products.keySet()) {
                products2.add(productBean);
            }
            
            //creo l'oggetto composizione per ciascun prodotto facente parte dell'ordine
            Iterator<ProductBean> it = products2.iterator();
            while(it.hasNext()) {
            	ComposizioneBean composizione = new ComposizioneBean();
            	ProductBean b = it.next();
            	
            	composizione.setCodi_prodotto(b.getCode());
            	composizione.setNum_ordine(IdOrdine);
            	composizione.setQuantita(b.getQuantity());
            	composizione.setPrezzo(b.getPrice());
            	
            	try {
					comp.createComposizione(composizione);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Impossibile creare la composizione dell'ordine!");
				}
            }
            
        }else {
        	System.out.println("Cart e User sono null!");
        }

        RequestDispatcher rs = request.getRequestDispatcher("utente.jsp");
        rs.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
