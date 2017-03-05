package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Alimentation;

public class AlimentationDAO {

	public static void save(Alimentation model) throws Exception{
		
    	String query = "INSERT INTO ALIMENTATION(IDREGIME, REPAS, BOISSON, PERIODE, DATEALIMENTATION) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setString(2, model.getRepas());
	    	statement.setString(3, model.getBoisson());
	    	statement.setInt(4, model.getPeriode());
	    	statement.setDate(5, Date.valueOf(model.getDate()));
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Alimentation contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public static void update(Alimentation model) throws Exception {
		
		String query = "UPDATE ALIMENTATION "
				+ "SET IDREGIME = ?, "
				+ "REPAS = ?, "
				+ "BOISSON = ?, "
				+ "PERIODE = ?, "
				+ "DATEALIMENTATION = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getRegime().getId());
	    	statement.setString(2, model.getRepas());
	    	statement.setString(3, model.getBoisson());
	    	statement.setInt(4, model.getPeriode());
	    	statement.setDate(5, Date.valueOf(model.getDate()));
	    	statement.setInt(6, model.getId());
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

	public static void delete(Alimentation model) throws Exception {
		
		String query = "DELETE FROM ALIMENTATION WHERE IDALIMENTATION = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
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

	public static List<Alimentation> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM ALIMENTATION LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, offset);
			return DBToAlimentation(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static Alimentation findById(int id) throws Exception {
		
		String query = "SELECT * FROM ALIMENTATION WHERE IDALIMENTATION = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerAlimentation(res);
			}
			throw new Exception("Cette Alimentation est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	static List<Alimentation> DBToAlimentation(ResultSet res)throws Exception{
		try{
			List<Alimentation> model = new Vector<Alimentation>();
			while(res.next()){
				model.add(Creation.creerAlimentation(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}	
}