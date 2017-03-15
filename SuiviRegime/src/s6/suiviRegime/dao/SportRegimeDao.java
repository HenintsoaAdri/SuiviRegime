package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportRegime;

public class SportRegimeDao{

	public  void save(SportRegime model) throws Exception{
		
    	String query = "INSERT INTO SPORT_REGIME("
    				+ "IDREGIME, IDSPORT, DATESPORT, RYTHMESPORT)"
    				+ "VALUES (?, ?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setInt(2, model.getId());
	    	statement.setDate(3, new Date(model.getDate().getTime()));
	    	statement.setFloat(4, model.getRythme());
			statement.execute();
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("SportRegime contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public  void update(SportRegime model) throws Exception {
		
		String query = "UPDATE SPORT_REGIME "
				+ "SET DATESPORT = ?, "
				+ "RYTHMESPORT = ?"
				+ "WHERE IDREGIME = ? AND ID SPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setDate(1, new Date(model.getDate().getTime()));
	    	statement.setFloat(2, model.getRythme());
	    	statement.setInt(3, model.getRegime().getId());
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

	public  void delete(SportRegime model) throws Exception {
		
		String query = "DELETE FROM SPORT_REGIME WHERE IDREGIME = ? AND IDSPORT= ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, model.getRegime().getId());
			statement.setInt(2, model.getId());
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

	public  List<SportRegime> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM REGIME_SPORT LIMIT 10 OFFSET ?";
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
	
	public  List<SportRegime> findBySport(int id) throws Exception {
		
		String query = "SELECT * FROM REGIME_SPORT WHERE IDSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			return DBToModel(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public  List<SportRegime> findByRegime(Regime regime) throws Exception {
		
		String query = "SELECT * FROM REGIME_SPORT WHERE IDREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, regime.getId());
			return DBToModel(statement.executeQuery(), regime);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public  SportRegime findBySportRegime(Sport sport, Regime regime) throws Exception {
		
		String query = "SELECT * FROM REGIME_SPORT WHERE IDREGIME = ? AND IDSPORT= ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, regime.getId());
			statement.setInt(2, sport.getId());
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerSportRegime(res);
			}
			throw new Exception("Ce SportRegime est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null)res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}

	 List<SportRegime> DBToModel(ResultSet res, Regime regime) throws Exception {
		try{
			List<SportRegime> model = new Vector<SportRegime>();
			while(res.next()){
				model.add(Creation.creerSportRegime(res, regime));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
	}
	 List<SportRegime> DBToModel(ResultSet res)throws Exception{
		try{
			List<SportRegime> model = new Vector<SportRegime>();
			while(res.next()){
				model.add(Creation.creerSportRegime(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}
}