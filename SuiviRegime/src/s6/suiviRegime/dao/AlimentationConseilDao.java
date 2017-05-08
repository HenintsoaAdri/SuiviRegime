package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.AlimentationConseil;

public class AlimentationConseilDao {

	public  void save(AlimentationConseil model) throws Exception{
		
    	String query = "INSERT INTO ALIMENTATIONCONSEIL(NOMALIMENTATIONCONSEIL, MATINALIMENTATIONCONSEIL, MIDIALIMENTATIONCONSEIL, SOIRALIMENTATIONCONSEIL) "
    			+ "VALUES (?, ?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setString(1, model.getNom());
	    	statement.setString(2, model.getMatin());
	    	statement.setString(3, model.getMidi());
	    	statement.setString(4, model.getSoir());
			statement.execute();
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Conseil d'Alimentation contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public  void update(AlimentationConseil model) throws Exception {
		
		String query = "UPDATE ALIMENTATIONCONSEIL "
				+ "SET NOMALIMENTATIONCONSEIL = ?, "
				+ "MATIN = ?, "
				+ "MIDI = ?, "
				+ "SOIR = ? "
				+ "WHERE IDALIMENTATIONCONSEIL = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	
	    	statement.setString(1, model.getNom());
	    	statement.setString(2, model.getMatin());
	    	statement.setString(3, model.getMidi());
	    	statement.setString(4, model.getSoir());
	    	statement.setInt(5, model.getId());
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

	public  void delete(AlimentationConseil model) throws Exception {
		
		String query = "DELETE FROM ALIMENTATIONCONSEIL WHERE IDALIMENTATIONCONSEIL = ?";
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

	public  List<AlimentationConseil> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM ALIMENTATIONCONSEIL LIMIT 10 OFFSET ?";
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

	public  List<AlimentationConseil> findAll() throws Exception {
		
		String query = "SELECT * FROM ALIMENTATIONCONSEIL";
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
	
	public  AlimentationConseil findById(int id) throws Exception {
		
		String query = "SELECT * FROM ALIMENTATIONCONSEIL WHERE IDALIMENTATIONCONSEIL = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerAlimentationConseil(res);
			}
			throw new Exception("Ce Conseil Alimentation est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	 List<AlimentationConseil> DBToModel(ResultSet res)throws Exception{
		try{
			List<AlimentationConseil> model = new Vector<AlimentationConseil>();
			while(res.next()){
				model.add(Creation.creerAlimentationConseil(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}
}
