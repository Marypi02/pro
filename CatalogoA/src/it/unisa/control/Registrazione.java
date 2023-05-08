package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.PetParadise.DAO.MySQLUtenteDS;
import it.unisa.model.Utente;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLUtenteDS data = new MySQLUtenteDS();
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String indirizzo = request.getParameter("indirizzo");
		
		
		try {
			data.createUtente(new Utente(nome, cognome, password, email, 100, indirizzo, false));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
