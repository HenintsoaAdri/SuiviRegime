package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.SportConseil;

public class SportConseilDAO {

	public static void save(SportConseil model) throws Exception{
		
    	String query = "INSERT INTO CONSEILSPORT(IDSPORT, RYTHMECONSEIL, DETAIL) "
    				+ "VALUES (?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setInt(1, model.getIdSport());
	    	statement.setFloat(2, model.getRythme());
	    	statement.setString(3, model.getDetails());
			statement.execute();
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("SportConseil contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public static void update(SportConseil model) throws Exception {
		
		String query = "UPDATE CONSEILSPORT "
				+ "SET IDSPORT = ?, "
				+ "RYTHMECONSEIL = ?, "
				+ "DETAIL = ? "
				+ "WHERE IDCONSEILSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setInt(1, model.getIdSport());
	    	statement.setFloat(2, model.getRythme());
	    	statement.setString(3, model.getDetails());
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

	public static void delete(SportConseil model) throws Exception {
		
		String query = "DELETE FROM CONSEILSPORT WHERE IDCONSEILSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
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

	public static List<SportConseil> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM CONSEILSPORT LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, offset);
			return DBToSportConseil(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static SportConseil findById(int id) throws Exception {
		
		String query = "SELECT * FROM CONSEILSPORT WHERE IDCONSEILSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerSportConseil(res);
			}
			throw new Exception("Ce Conseil Sport est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	static List<SportConseil> DBToSportConseil(ResultSet res)throws Exception{
		try{
			List<SportConseil> model = new Vector<SportConseil>();
			while(res.next()){
				model.add(Creation.creerSportConseil(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}
}