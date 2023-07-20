package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.PetParadise.DAO.OrdineDAO;
import it.unisa.model.ProductOrder;

/**
 * Servlet implementation class OrderList
 */
@WebServlet("/OrderList")
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdineDAO odao = new OrdineDAO();
		List<ProductOrder> ordini = null;  
		String action = request.getParameter("action");
		
		if(action != null) {
			/*if(action.equalsIgnoreCase("elencoOrdini")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("ElencoOrdini.jsp");
		        dispatcher.forward(request, response);
			}*/
		}/*else*/ 
		if(action.equalsIgnoreCase("allOrders")) {  //mostra
			//Recupero tutti gli ordini
			try {
				ordini = (List<ProductOrder>) odao.doRetrieveAll();
				if(ordini != null) {
					request.setAttribute("OrderList", ordini);
				}else {
					request.setAttribute("OrderList", null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase("customerOrders")) { //mostra per cliente
			try {
				String email = request.getParameter("customerEmail");
				ordini = (List<ProductOrder>) odao.doRetrieveAllByUtente(email);
				if(ordini != null) {
					request.setAttribute("OrderList", ordini);
				}else {
					request.setAttribute("OrderList", null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("filteredOrders")) {
			try {
                String fromDateStr = request.getParameter("fromDate");
                String toDateStr = request.getParameter("toDate");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = null;
                Date toDate = null;

                
                fromDate = dateFormat.parse(fromDateStr);
                toDate = dateFormat.parse(toDateStr);
                

                ordini = (List<ProductOrder>) odao.getOrdersByDateRange(fromDate, toDate);
                if (ordini != null) {
                    request.setAttribute("OrderList", ordini);
                } else {
                    request.setAttribute("OrderList", null);
                }
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }
		}else if (action.equalsIgnoreCase("comeBack")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("protected.jsp");
            dispatcher.forward(request, response);
            return;
        }
		
		RequestDispatcher rs = request.getRequestDispatcher("ElencoOrdini.jsp");
		rs.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
