package it.unisa.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.model.ProductBean;
import it.unisa.model.ProductModelDM;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("query");

        ProductModelDM productModel = new ProductModelDM();
        try {
            List<ProductBean> allProducts = (List<ProductBean>) productModel.doRetrieveAll(null);
            List<ProductBean> searchResults = filterProducts(allProducts, searchQuery);

            String jsonSearchResults = new Gson().toJson(searchResults);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(jsonSearchResults);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    
    private List<ProductBean> filterProducts(List<ProductBean> products, String searchQuery) {
        List<ProductBean> searchResults = new ArrayList<>();
        for (ProductBean product : products) {
            if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }
}
