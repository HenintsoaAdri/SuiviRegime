package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.Utilisateur;

public class RegimeDao {

	public  void save(Regime model) throws Exception{
		
    	String query = "INSERT INTO REGIME(IDUTILISATEUR, DATEDEBUT, DATEFIN, POIDSOBJECTIF) VALUES (?, ?, ?, ?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getUtilisateur().getId());
	    	statement.setDate(2, new Date(model.getDebut().getTime()));
	    	statement.setDate(3, new Date(model.getFin().getTime()));
	    	statement.setFloat(4, model.getPoidsObjectif());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Regime contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public  void update(Regime model) throws Exception {
		
		String query = "UPDATE REGIME "
				+ "SET IDUTILISATEUR = ?, "
				+ "DATEDEBUT = ?, "
				+ "DATEFIN = ?, "
				+ "POIDSOBJECTIF = ? "
				+ "WHERE IDREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
	    	statement.setInt(1, model.getUtilisateur().getId());
	    	statement.setDate(2, new Date(model.getDebut().getTime()));
	    	statement.setDate(3, new Date(model.getFin().getTime()));
	    	statement.setFloat(4, model.getPoidsObjectif());
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

	public  void delete(Regime model) throws Exception {
		
		String query = "DELETE FROM REGIME WHERE IDREGIME = ?";
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

	public  List<Regime> findAll() throws Exception {
		
		String query = "SELECT * FROM REGIME_UTILISATEUR";
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

	public  List<Regime> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM REGIME_UTILISATEUR LIMIT 10 OFFSET ?";
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
	public  List<Regime> findByUtilisateur(Utilisateur utilisateur) throws Exception {
		
		String query = "SELECT * FROM REGIME_UTILISATEUR LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, utilisateur.getId());
			return DBToRegime(statement.executeQuery(), utilisateur);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public  Regime findById(int id) throws Exception {
		
		String query = "SELECT * FROM REGIME_UTILISATEUR WHERE IDREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerRegime(res);
			}
			throw new Exception("Ce Regime est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null) res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	 List<Regime> DBToRegime(ResultSet res, Utilisateur utilisateur) throws Exception {
		try{
			List<Regime> model = new Vector<Regime>();
			while(res.next()){
				model.add(Creation.creerRegime(res, utilisateur));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
	}

	 List<Regime> DBToModel(ResultSet res)throws Exception{
		try{
			List<Regime> model = new Vector<Regime>();
			while(res.next()){
				model.add(Creation.creerRegime(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}	
	
}
