package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.PagamentoBean;
import it.unisa.model.Utente;
import it.unisa.PetParadise.DAO.PagamentoDAO;
import it.unisa.PetParadise.DAO.UtenteDAO;
import it.unisa.PetParadise.DAO.MySQLUtenteDM;

@WebServlet("/Salva_carta_pagamento")
public class Salva_carta_pagamento extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");

    	
    	if (action.equals("salvaPagamento")) {
    	    PagamentoBean bean = new PagamentoBean();
    	    PagamentoDAO pdao = new PagamentoDAO();

    	    bean.setNominativo(request.getParameter("intestatario"));
    	    bean.setCodice_carta(request.getParameter("cod_carta"));
    	    bean.setCVV(Integer.parseInt(request.getParameter("cod_cvv")));
    	    bean.setMeseScadenza(Integer.parseInt(request.getParameter("month").substring(5, 7)));
    	    bean.setAnnoScadenza(Integer.parseInt(request.getParameter("month").substring(0, 4)));

    	    Utente ut = (Utente) request.getSession().getAttribute("utente");

    	    try {
    	        pdao.doSave(bean, ut);

    	        response.sendRedirect(request.getContextPath() + "/utente.jsp");
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    
    	}
    	} else{
    	    Utente utente = (Utente) request.getSession().getAttribute("utente");

    	    try {
    	        PagamentoDAO pagamentoDAO = new PagamentoDAO();
    	        List<PagamentoBean> pagamenti = (List<PagamentoBean>) pagamentoDAO.doRetrieveByUtente(utente.getEmail());
    	        if(pagamenti != null) {
					request.setAttribute("pagamenti", pagamenti);
				}else {
					request.setAttribute("pagamenti", null);
				}

    	        //request.getSession().setAttribute("utente", utente);

    	        response.sendRedirect(request.getContextPath() + "/utente.jsp");
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		doGet(request, response);
    	}
}
