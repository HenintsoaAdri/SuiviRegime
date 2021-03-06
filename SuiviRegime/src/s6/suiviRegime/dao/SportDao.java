package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Sport;

public class SportDao {

	public  void save(Sport model) throws Exception{
		
    	String query = "INSERT INTO SPORT(SPORT, ACTIVITES)"
    				+ "VALUES (?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setString(1, model.getLibelle());
	    	statement.setString(2, model.getActivite());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Sport contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public  void update(Sport model) throws Exception {
		
		String query = "UPDATE SPORT SET SPORT = ?, ACTIVITES = ? WHERE IDSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setString(2, model.getLibelle());
	    	statement.setString(3, model.getActivite());
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

	public  void delete(Sport model) throws Exception {
		
		String query = "DELETE FROM SPORT WHERE IDSPORT= ?";
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
	public  List<Sport> findAll() throws Exception {
		
		String query = "SELECT * FROM SPORT";
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
	public  List<Sport> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM SPORT LIMIT 10 OFFSET ?";
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
	
	public  Sport findById(int id) throws Exception {
		
		String query = "SELECT * FROM SPORT WHERE IDSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerSport(res);
			}
			throw new Exception("Ce Sport est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}

	 List<Sport> DBToModel(ResultSet res)throws Exception{
		try{
			List<Sport> model = new Vector<Sport>();
			while(res.next()){
				model.add(Creation.creerSport(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
	}
}
