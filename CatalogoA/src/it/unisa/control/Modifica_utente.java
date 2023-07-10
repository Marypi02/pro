package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.PetParadise.DAO.MySQLUtenteDM;
import it.unisa.PetParadise.DAO.MySQLUtenteDS;
import it.unisa.PetParadise.DAO.UtenteDAO;
import it.unisa.model.Utente;

@WebServlet("/Modifica_utente")
public class Modifica_utente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	
	static UtenteDAO modifier;
	
	static {
		if(isDataSource) {
			modifier = new MySQLUtenteDS();
		}else {
			modifier = new MySQLUtenteDM();
		}
	}
       
    public Modifica_utente() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("conferma")) {
            
            try {
	            int id = Integer.parseInt(request.getParameter("code").trim());
	            Utente obj = new Utente();
	            obj.setIdutente(id);
	            obj.setEmail(request.getParameter("email"));
	            obj.setPassword(request.getParameter("password"));
	            obj.setNome(request.getParameter("nome"));
	            obj.setCognome(request.getParameter("cognome"));
	            obj.setIndirizzo(request.getParameter("indirizzo"));
	            obj.setCitta(request.getParameter("citta"));
	            
	            modifier.updateUtente(obj);
				request.getSession().setAttribute("utente", obj);
				response.sendRedirect("utente.jsp");
	            
            } catch (SQLException e) {
                e.printStackTrace();
                // Gestione dell'errore
                response.sendRedirect("errore.jsp");
            }
        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
