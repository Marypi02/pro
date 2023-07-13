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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static boolean isDataSource = true;
	
	 static UtenteDAO utenteDAO;
	 
	 static {
		// Verifica se si sta utilizzando una sorgente dati (DataSource)
      if (isDataSource) {
          utenteDAO = new MySQLUtenteDS();
      } else {
          utenteDAO = new MySQLUtenteDM();
      }
	 }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		String redirectedPage = null;
		try {
			// Controllo se è l'amministratore
			checkLogin(username, password);
			request.getSession().setAttribute("adminRoles", true);
			redirectedPage = "protected.jsp";
		}catch(Exception e) {
			//CASO UTENTE NORMALE
			//controllo prima se è registrato
			try {
				//verifico che sia presente nel db e che la password corrisponda a quella usata in fase di registrazione
				if (utenteDAO.isEmailPresent(username) /*&& password.equals(utenteDAO.getUtente(username).getPassword())*/) {
				    // Utente registrato; setto la variabile adminRoles a false e definisco a chi passare il controllo
					
					// Dopo aver verificato che l'utente sia registrato nel database
					Utente utente = utenteDAO.getUtente(username);
					request.getSession().setAttribute("adminRoles", false);
					request.getSession().setAttribute("utente", utente);
					
					redirectedPage = "utente.jsp";
				}else {
					redirectedPage = "LoginError.html";
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/" + redirectedPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void checkLogin(String username, String password) throws Exception{
		if("root@root.root".equals(username) && "admin".equals(password)) {
			// Autenticazione amministratore
		}else
			throw new Exception("Invalid login and password");
	}

}