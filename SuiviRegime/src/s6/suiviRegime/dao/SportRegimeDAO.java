package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.SportRegime;

public class SportRegimeDAO{

	public static void save(SportRegime model) throws Exception{
		
    	String query = "INSERT INTO SPORT_REGIME("
    				+ "IDREGIME, IDSPORT, DATESPORT, RYTHMESPORT)"
    				+ "VALUES (?, ?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setInt(2, model.getId());
	    	statement.setDate(3, Date.valueOf(model.getDate()));
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

	public static void update(SportRegime model) throws Exception {
		
		String query = "UPDATE SPORT_REGIME "
				+ "SET DATESPORT = ?, "
				+ "RYTHMESPORT = ?"
				+ "WHERE IDREGIME = ? AND ID SPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setDate(1, Date.valueOf(model.getDate()));
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

	public static void delete(SportRegime model) throws Exception {
		
		String query = "DELETE FROM SPORT_REGIME WHERE IDREGIME = ? AND IDSPORT= ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
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

	public static List<SportRegime> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM SPORT_REGIME LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, offset);
			return DBToSportRegime(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static List<SportRegime> findBySport(int id) throws Exception {
		
		String query = "SELECT * FROM SPORT_REGIME WHERE IDSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			return DBToSportRegime(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static List<SportRegime> findByRegime(int id) throws Exception {
		
		String query = "SELECT * FROM SPORT_REGIME WHERE IDREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			return DBToSportRegime(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static SportRegime findBySportRegime(int idSport, int idRegime) throws Exception {
		
		String query = "SELECT * FROM SPORT_REGIME WHERE IDREGIME = ? AND IDSPORT= ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, idRegime);
			statement.setInt(2, idSport);
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
	
	static List<SportRegime> DBToSportRegime(ResultSet res)throws Exception{
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