package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

@WebServlet("/UserList")
public class UserList extends HttpServlet {
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

    public UserList() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.contentEquals("elencoUtenti")) {
                    List<Utente> userList = utenteDAO.getAllUtenti();
                    if (userList != null) {
                        request.setAttribute("UserList", userList);
                    } else {
                        request.setAttribute("UserList", null);
                    }
                } else if (action.equalsIgnoreCase("comeBack")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("protected.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else if (action.equalsIgnoreCase("searchUser")) {
                    String email = request.getParameter("email");
                    List<Utente> userList = utenteDAO.getAllUtenti();
                    if (userList != null) {
                        request.setAttribute("UserList", userList);
                    } else {
                        request.setAttribute("UserList", null);
                    }
                    
                        Utente user = utenteDAO.getUtente(email);
                        request.setAttribute("searchResult", user);
                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("ElencoUtenti.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
