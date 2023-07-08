package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.PetParadise.DAO.MySQLUtenteDM;
import it.unisa.PetParadise.DAO.UtenteDAO;
import it.unisa.model.Utente;

/**
 * Servlet implementation class Modifica_utente
 */
@WebServlet("/Modifica_utente")
public class Modifica_utente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica_utente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLUtenteDM udao = new MySQLUtenteDM();
        
        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("update")) {
            Utente obj = new Utente();
            
            try {
                int id = Integer.parseInt(request.getParameter("code"));
                obj.setIdutente(id);
            } catch (NumberFormatException e) {
                
                e.printStackTrace();
                
                
            }
            
            obj.setEmail(request.getParameter("email_new"));
            obj.setPassword(request.getParameter("Password"));
            obj.setNome(request.getParameter("Nome"));
            obj.setCognome(request.getParameter("Cognome"));
            obj.setIndirizzo(request.getParameter("Indirizzo"));
            obj.setCitta(request.getParameter("Citta"));
            
            udao.updateUtente(obj);
			request.getSession().setAttribute("utente", obj);
			response.sendRedirect("./utente.jsp");
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
