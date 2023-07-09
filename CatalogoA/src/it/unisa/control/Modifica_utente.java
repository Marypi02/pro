package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.PetParadise.DAO.MySQLUtenteDM;
import it.unisa.PetParadise.DAO.MySQLUtenteDS;
import it.unisa.PetParadise.DAO.UtenteDAO;
import it.unisa.model.Utente;

/**
 * Servlet implementation class Modifica_utente
 */
@WebServlet("/Modifica_utente")
public class Modifica_utente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	
	
	static UtenteDAO utenteDAO;

    static {
        if (isDataSource) {
            utenteDAO = new MySQLUtenteDS();
        } else {
            utenteDAO = new MySQLUtenteDM();
        }
    }
       
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
        
        String action = request.getParameter("action");
        
        try {
        	if(action != null) {
        		if(action.equalsIgnoreCase("edit")) {
        			String email = request.getParameter("userEmail");
        			Utente user = utenteDAO.getUtente(email); //verificare se funziona
        			request.setAttribute("utente", user);
        			RequestDispatcher dispatcher = request.getRequestDispatcher("EditUser.jsp");
        			dispatcher.forward(request,  response);
        		}
        		if(action.equalsIgnoreCase("updateUser")) {
        			int id = Integer.parseInt(request.getParameter("id"));
        			String email = request.getParameter("email");
        			String password = request.getParameter("pwd");
        			String nome = request.getParameter("name");
        			String cognome = request.getParameter("cognome");
        			String indirizzo = request.getParameter("address");
        			String citta = request.getParameter("citta");
        			
        			Utente user = new Utente();
        			user.setIdutente(id);
        			user.setEmail(email);
        			user.setPassword(password);
        			user.setNome(nome);
        			user.setCognome(cognome);
        			user.setIndirizzo(indirizzo);
        			user.setCitta(citta);
        			
        			utenteDAO.updateUtente(user);
        			
        			//Ottieni l'utente aggiornato
        			Utente user2 = utenteDAO.getUtente(email);
        			request.getSession().setAttribute("utente", user2);
        			RequestDispatcher dispatcher = request.getRequestDispatcher("/utente.jsp");
        			dispatcher.forward(request, response);

        		}
        	}
        }catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

        /*
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
        */
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
