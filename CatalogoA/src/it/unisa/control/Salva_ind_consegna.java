package it.unisa.control;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ConsegnaBean;
import it.unisa.model.Utente;
import it.unisa.PetParadise.DAO.ConsegnaDAO;
import it.unisa.PetParadise.DAO.MySQLUtenteDM;

/**
 * Servlet implementation class Salva_ind_consegna
 */
@WebServlet("/Salva_ind_consegna")
public class Salva_ind_consegna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salva_ind_consegna() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ConsegnaBean cbean = new ConsegnaBean();
			ConsegnaDAO cdao = new ConsegnaDAO();
			
			cbean.setVia(request.getParameter("via"));
			cbean.setCap(Integer.parseInt(request.getParameter("cap")));
			cbean.setNumero(Integer.parseInt(request.getParameter("number")));
			cbean.setCitta(request.getParameter("citta"));
			
			
			try {
				Utente ute = (Utente) request.getSession().getAttribute("utente");
				cdao.doSave(cbean, ute);
				
				
				request.getSession().setAttribute("utente", ute); // Salvo l'utente nella sessione per poter vedere subito il cambiamento
				response.sendRedirect(request.getContextPath() + "/utente.jsp");

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}