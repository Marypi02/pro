package it.unisa.model;


import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel {
	public void doSave(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;
	
	public boolean updateProduct(ProductBean product) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public Collection<ProductBean> get_prodotti() throws SQLException;
	
	public Collection<ProductBean> getAccessori_cane() throws SQLException;
	public Collection<ProductBean> getAccessori_gatto() throws SQLException;
	public Collection<ProductBean> getAccessori_pesci() throws SQLException;
	public Collection<ProductBean> getAccessori_uccelli() throws SQLException;
	
	public Collection<ProductBean> getCibo_cane() throws SQLException;
	public Collection<ProductBean> getCibo_gatto() throws SQLException;
	public Collection<ProductBean> getCibo_pesci() throws SQLException;
	public Collection<ProductBean> getCibo_uccelli() throws SQLException;
	
	public Collection<ProductBean> getGiocattoli_cane() throws SQLException;
	public Collection<ProductBean> getGiocattoli_gatto() throws SQLException;
	public Collection<ProductBean> getGabbie_uccelli() throws SQLException;
	
	public Collection<ProductBean> getIgiene_cane() throws SQLException;
	public Collection<ProductBean> getIgiene_gatto() throws SQLException;
	public Collection<ProductBean> getIgiene_uccelli() throws SQLException;
}


