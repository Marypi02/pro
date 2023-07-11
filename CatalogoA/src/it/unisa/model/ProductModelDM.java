package it.unisa.model;



import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProductModelDM implements ProductModel {

	private static final String TABLE_NAME = "product";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDM.TABLE_NAME
				+ " (NAME, DESCRIPTION, PRICE, QUANTITY, NOME_IMMAGINE) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setString(5, product.getNomeImg());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME + " WHERE CODE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getInt("CODE"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				bean.setQuantity(rs.getInt("QUANTITY"));
				bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDM.TABLE_NAME + " WHERE CODE = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public boolean updateProduct(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE product SET name=?, description=?, price=?, quantity=?, nome_immagine=? WHERE code=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, product.getName());
	        preparedStatement.setString(2, product.getDescription());
	        preparedStatement.setDouble(3, product.getPrice());
	        preparedStatement.setInt(4, product.getQuantity());
	        preparedStatement.setString(5, product.getNomeImg());
	        preparedStatement.setInt(6, product.getCode());  //individua il prodotto
	        preparedStatement.executeUpdate();
			
			int result = preparedStatement.executeUpdate();
			
			return result > 0; //return true if at least one row has been update
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	    
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getInt("CODE"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				bean.setQuantity(rs.getInt("QUANTITY"));
				bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}

	@Override
    public synchronized Collection<ProductBean> get_prodotti() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product ";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}
	
	@Override
    public synchronized Collection<ProductBean> getAccessori_cane() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Cani'and categoria='accessori'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}
	
	@Override
    public synchronized Collection<ProductBean> getAccessori_gatto() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Gatti'and categoria='accessori'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		}
	
	@Override
	public synchronized Collection<ProductBean> getAccessori_pesci() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Pesci'and categoria='accessori'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getAccessori_uccelli() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Uccelli'and categoria='accessori'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public  synchronized Collection<ProductBean> getCibo_cane() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Cani'and categoria='cibo'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getCibo_gatto() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Gatti'and categoria='cibo'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getCibo_pesci() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Pesci'and categoria='cibo'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
	}
	@Override
	public synchronized Collection<ProductBean> getCibo_uccelli() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Uccelli'and categoria='cibo'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getGiocattoli_cane() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Cani'and categoria='giocattoli'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getGiocattoli_gatto() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Gatti'and categoria='giocattoli'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getGabbie_uccelli() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Uccelli'and categoria='gabbia'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
		
	}
	@Override
	public synchronized Collection<ProductBean> getIgiene_cane() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Cani'and categoria='igiene'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
	}
	@Override
	public synchronized Collection<ProductBean> getIgiene_gatto() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Gatti'and categoria='igiene'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
	}
	@Override
	public synchronized Collection<ProductBean> getIgiene_uccelli() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
   
		Collection<ProductBean> products = new LinkedList<ProductBean>();
	        
	        String selectSQL = "SELECT * FROM product WHERE specie='Uccelli'and categoria='igiene'";

	        try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
					ProductBean bean = new ProductBean();

					bean.setCode(rs.getInt("CODE"));
					bean.setName(rs.getString("NAME"));
					bean.setDescription(rs.getString("DESCRIPTION"));
					bean.setPrice(rs.getDouble("PRICE"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setNomeImg(rs.getString("NOME_IMMAGINE"));
					products.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			return products;
	}

}