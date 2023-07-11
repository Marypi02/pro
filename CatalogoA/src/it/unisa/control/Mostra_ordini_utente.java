package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.*;
import it.unisa.PetParadise.DAO.OrdineDAO;

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
            
          
        }

        RequestDispatcher rs = request.getRequestDispatcher("utente.jsp");
        rs.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
