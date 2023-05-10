package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
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
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MySQLUtenteDS data = new MySQLUtenteDS();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String indirizzo = request.getParameter("indirizzo");
		String citta = request.getParameter("citta'");
		//boolean admin= Boolean.parseBoolean("admin");
		
		try {
			if (utenteDAO.isEmailPresent(email)) {
			    // L'email è già presente, mostra un messaggio all'utente o lancia un'eccezione
				RequestDispatcher dispatcher = request.getRequestDispatcher("RegistrationError.html");
			    dispatcher.forward(request, response);
			}else {
				//data.createUtente(new Utente(nome, cognome, password, email, indirizzo, false));
				

				Utente utente = new Utente();
				utente.setEmail(email);
				utente.setPassword(password);
				utente.setNome(nome);
				utente.setCognome(cognome);
				utente.setIndirizzo(indirizzo);
				utente.setCitta(citta);
				//utente.setAdmin(admin);
				
				utenteDAO.createUtente(utente);
				
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		try {
			
			//data.createUtente(new Utente(nome, cognome, password, email, indirizzo, false));
			

			Utente utente = new Utente();
			utente.setEmail(email);
			utente.setPassword(password);
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setIndirizzo(indirizzo);
			utente.setCitta(citta);
			//utente.setAdmin(admin);
			
			utenteDAO.createUtente(utente);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("login.html").forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
