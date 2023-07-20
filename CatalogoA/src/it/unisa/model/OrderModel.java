package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface OrderModel {
	
	public int createOrdine(ProductOrder order) throws SQLException;
	public ProductOrder doRetrieveByKey(int idordine) throws SQLException;
	public boolean doDelete(int idordine) throws SQLException;
	public void doUpdate (ProductOrder var) throws SQLException;
	public Collection<ProductOrder> doRetrieveAll() throws SQLException;
	public Collection<ProductOrder> doRetrieveAllByUtente(String email) throws SQLException;
	public Collection<ProductOrder> getOrdersByDateRange(Date fromDate, Date toDate) throws SQLException;
	
}
