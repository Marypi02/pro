package it.unisa.PetParadise.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.ProductBean;



public class productDAO {
  
	private static DataSource ds;
    
    static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage2");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

    public List<ProductBean> getProductsHomepage() throws SQLException {
        List<ProductBean> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM product WHERE categoria=?";
        
        connection = ds.getConnection();
        preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int code = rs.getInt("code");
                String img = rs.getString("nome_immagine");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                ProductBean product = new ProductBean();
                products.add(product);
            }
            return products;
        }

        
  
    
    
    public List<ProductBean> getAccessori_cane() throws SQLException {
        List<ProductBean> products = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String selectSQL = "SELECT * FROM product WHERE specie='Cani'and categoria='accessori'";

        preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int code = rs.getInt("code");
                String img = rs.getString("nome_immagine");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                ProductBean product = new ProductBean();
                products.add(product);
            }
            return products;
        }

}
