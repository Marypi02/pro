package it.unisa.PetParadise.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductBean;
import it.unisa.model.ProductModelDM;
import it.unisa.model.RecensioneBean;

public class RecensioneDAO {
    private static final String TABLE_NAME = "recensione";
    private static final String ID_COLUMN = "idRecensione";
    private static final String PRODOTTO_COLUMN = "prodotto";
    private static final String UTENTE_COLUMN = "utente";
    private static final String VOTO_COLUMN = "voto";
    private static final String TESTO_COLUMN = "testo";

    private static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " ( prodotto, utente, voto, testo) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM " + TABLE_NAME +
            " WHERE idRecensione = ?";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;

    private static final String SELECT_BY_PRODOTTO_QUERY = "SELECT * FROM " + TABLE_NAME +
            " WHERE prodotto = ?";

    private static final String SELECT_BY_UTENTE_QUERY = "SELECT * FROM " + TABLE_NAME +
            " WHERE utente = ?";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE_NAME +
            " WHERE idRecensione = ?";

    private static final String UPDATE_QUERY = "UPDATE utente SET prodotto = ?, utente = ?, voto = ?, testo = ? WHERE idRecensione = ?";

    public void insertRecensione(RecensioneBean recensione) {
        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {

            statement.setInt(1, recensione.getProdotto().getCode());
            statement.setInt(2, recensione.getUtente().getIdutente());
            statement.setInt(3, recensione.getVoto());
            statement.setString(4, recensione.getTesto());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public RecensioneBean getRecensioneById(int idRecensione) {
        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            statement.setInt(1, idRecensione);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createRecensioneFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public List<RecensioneBean> getAllRecensioni() {
        List<RecensioneBean> recensioni = new ArrayList<>();

        try {
        	Connection conn = DriverManagerConnectionPool.getConnection();
        
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_QUERY);
             statement.execute();
             ResultSet resultSet = statement.executeQuery();
             ProductModelDM prodDAO = new ProductModelDM();
		     MySQLUtenteDM daoUser = new MySQLUtenteDM();

            while (resultSet.next()) {
            	
            	 RecensioneBean recensione = new RecensioneBean(); 
            	 
            	 recensione.setProdotto(prodDAO.doRetrieveByKey(resultSet.getInt("prodotto")));
            	 recensione.setUtente(daoUser.getUtente(resultSet.getInt("utente")));
                 recensione.setVoto(resultSet.getInt("voto"));
                 recensione.setTesto(resultSet.getString("testo"));
                 
                 recensioni.add(recensione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recensioni;
    }

    public ArrayList<RecensioneBean> getRecensioniByProduct(ProductBean prod) throws Exception{
		
		ArrayList<RecensioneBean> lista = new ArrayList<>();
		RecensioneBean result = new RecensioneBean();
		
		try{ 
			Connection conn = DriverManagerConnectionPool.getConnection();
		
				PreparedStatement statement = conn.prepareStatement(SELECT_BY_PRODOTTO_QUERY);
				statement.setInt(1, prod.getCode());
				ResultSet rs = statement.executeQuery();
				ProductModelDM prodDAO = new ProductModelDM();
				MySQLUtenteDM daoUser = new MySQLUtenteDM();
				
				while(rs.next()) {
					result.setIdRecensione(rs.getInt("idRecensione"));
					result.setVoto(rs.getInt("voto"));
					result.setTesto(rs.getString("testo"));
					
					result.setProdotto(prodDAO.doRetrieveByKey(rs.getInt("prodotto")));
					
					result.setUtente(daoUser.getUtente(rs.getInt("utente")));
					
					lista.add(result);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		
		return lista;
	}

    /*public List<RecensioneBean> getRecensioniByUtente(String utente) {
        List<RecensioneBean> recensioni = new ArrayList<>();

        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_UTENTE_QUERY)) {

            statement.setString(1, utente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recensioni.add(createRecensioneFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recensioni;
    }*/

    public void deleteRecensione(int idRecensione) {
        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, idRecensione);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecensione(RecensioneBean recensione) {
        try (Connection conn = DriverManagerConnectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, recensione.getProdotto().getCode());
            statement.setInt(2, recensione.getUtente().getIdutente());
            statement.setInt(3, recensione.getVoto());
            statement.setString(4, recensione.getTesto());
            statement.setInt(5, recensione.getIdRecensione());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
