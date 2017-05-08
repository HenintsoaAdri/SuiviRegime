package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportConseil;

public class SportConseilDao {

	public  void save(SportConseil model) throws Exception{
		
    	String query = "INSERT INTO SPORTCONSEIL(IDSPORT, RYTHMESPORTCONSEIL, DETAILSPORTCONSEIL) "
    				+ "VALUES (?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setInt(1, model.getSport().getId());
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

	public  void update(SportConseil model) throws Exception {
		
		String query = "UPDATE SPORTCONSEIL "
				+ "SET IDSPORT = ?, "
				+ "RYTHMESPORTCONSEIL = ?, "
				+ "DETAILSPORTCONSEIL = ? "
				+ "WHERE IDSPORTCONSEIL = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setInt(1, model.getSport().getId());
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

	public  void delete(SportConseil model) throws Exception {
		
		String query = "DELETE FROM SPORTCONSEIL WHERE IDSPORTCONSEIL = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
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

	public  List<SportConseil> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM SPORTCONSEIL LIMIT 10 OFFSET ?";
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
	public  List<SportConseil> findBySport(Sport sport) throws Exception {
		
		String query = "SELECT * FROM SPORTCONSEIL WHERE IDSPORT = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, sport.getId());
			return DBToSportConseil(statement.executeQuery(), sport);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	public  SportConseil findById(int id) throws Exception {
		
		String query = "SELECT * FROM CONSEIL_SPORT WHERE IDSPORTCONSEIL = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
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
	
	 List<SportConseil> DBToSportConseil(ResultSet res, Sport sport) throws Exception {
		try{
			List<SportConseil> model = new Vector<SportConseil>();
			while(res.next()){
				model.add(Creation.creerSportConseil(res, sport));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
	}

	 List<SportConseil> DBToModel(ResultSet res)throws Exception{
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
