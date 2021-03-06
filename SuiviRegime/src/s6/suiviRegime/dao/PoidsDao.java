package s6.suiviRegime.dao;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Poids;
import s6.suiviRegime.modele.Regime;

public class PoidsDao {

	public  void save(Poids model) throws Exception{
		
    	String query = "INSERT INTO POIDS(IDREGIME, POIDS, DATEPOIDS) VALUES (?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setFloat(2, model.getValeur());
	    	statement.setDate(3, new Date(model.getDate().getTime()));
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Poids contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public  void update(Poids model) throws Exception {
		
		String query = "UPDATE POIDS SET IDREGIME = ?, POIDS = ?, DATEPOIDS = ? WHERE IDPOIDS = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setFloat(2, model.getValeur());
	    	statement.setDate(3, new Date(model.getDate().getTime()));
	    	statement.setInt(4, model.getId());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}

	public  void delete(Poids model) throws Exception {
		
		String query = "DELETE FROM POIDS WHERE IDPOIDS= ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, model.getId());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}

	public  List<Poids> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM POIDS LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, offset);
			return DBToModel(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	public  List<Poids> findAll() throws Exception {
		
		String query = "SELECT * FROM REGIME_POIDS";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			return DBToModel(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	public  List<Poids> findByRegime(Regime regime) throws Exception {
		
		String query = "SELECT * FROM REGIME_POIDS WHERE IDREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, regime.getId());
			return DBToModel(statement.executeQuery(),regime);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	public  Poids findById(int id) throws Exception {
		
		String query = "SELECT * FROM REGIME_POIDS WHERE IDPOIDS = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerPoids(res);
			}
			throw new Exception("Ce Poids est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	 List<Poids> DBToModel(ResultSet res)throws Exception{
		try{
			List<Poids> model = new Vector<Poids>();
			while(res.next()){
				model.add(Creation.creerPoids(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}
	 List<Poids> DBToModel(ResultSet res, Regime regime)throws Exception{
		try{
			List<Poids> model = new Vector<Poids>();
			while(res.next()){
				model.add(Creation.creerPoids(res, regime));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}	
}
