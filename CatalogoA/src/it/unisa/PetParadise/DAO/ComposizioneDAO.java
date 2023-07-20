package it.unisa.PetParadise.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.ComposizioneBean;
import it.unisa.model.DriverManagerConnectionPool;

public class ComposizioneDAO {
	private static final String TABLE_NAME = "composizione";

    public synchronized void createComposizione(ComposizioneBean composizione) throws SQLException {
    	
    	Connection connection = null;
    	
        String query = "INSERT INTO " + ComposizioneDAO.TABLE_NAME + "(cod_prodotto, num_ordine, quantita, iva, prezzo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
        	
        	connection = DriverManagerConnectionPool.getConnection();
        	
            statement = connection.prepareStatement(query);
            statement.setInt(1, composizione.getCodi_prodotto());
            statement.setInt(2, composizione.getNum_ordine());
            statement.setDouble(3, composizione.getQuantita());
            statement.setDouble(4, composizione.getIva());
            statement.setDouble(5, composizione.getPrezzo());

            statement.executeUpdate();
            connection.commit();
        } finally {
        	try {
            	if (statement != null)
					statement.close();
			} finally {
				if(connection != null) {
					connection.close();
				}
			}
        }
    }

    public List<ComposizioneBean> getComposizioniByOrdine(int num_ordine) throws SQLException {
    	Connection connection = null;
        
        List<ComposizioneBean> composizioni = new ArrayList<>();
        String query = "SELECT * FROM" + ComposizioneDAO.TABLE_NAME + "WHERE num_ordine = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
        	connection = DriverManagerConnectionPool.getConnection();
        	
            statement = connection.prepareStatement(query);
            statement.setInt(1, num_ordine);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ComposizioneBean composizione = new ComposizioneBean();
                composizione.setCodi_prodotto(resultSet.getInt("cod_prodotto"));
                composizione.setNum_ordine(resultSet.getInt("num_ordine"));
                composizione.setQuantita(resultSet.getDouble("quantita"));
                composizione.setPrezzo(resultSet.getDouble("prezzo"));

                composizioni.add(composizione);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return composizioni;
    }

    // Altri metodi del DAO (ad esempio: updateComposizione, deleteComposizione, ecc.)
}

