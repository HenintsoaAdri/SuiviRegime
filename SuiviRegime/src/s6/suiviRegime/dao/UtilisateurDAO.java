package s6.suiviRegime.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.Utilisateur;

public class UtilisateurDAO {
	
	public static void save(Utilisateur model) throws Exception{
		
    	String query = "INSERT INTO UTILISATEUR (NOM,PRENOM,DATENAISSANCE,SEXE,"
    			+ "EMAIL,IDENTIFIANT,PASSWORD,ADRESSE) "
    			+ "VALUES (?,?,?,?,?,?,?,?)";
	    Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
	    	con.setAutoCommit(false);
			statement.setString(2, model.getNom());
			statement.setString(3, model.getPrenom());
			statement.setDate(4, Date.valueOf(model.getDateNaissance()));
			statement.setString(5, model.getSexe());
			statement.setString(6, model.getEmail());
			statement.setString(7, model.getIdentifiant());
			statement.setString(8, model.getPassword());
			statement.setString(9, model.getAdresse());
			statement.execute();
			con.commit();
		}
	            catch(SQLException exception){
	                if(exception.getSQLState().compareTo("23505") == 0){
				throw new Exception("Cet adresse email est d\u00e9j\u00e0 associ\u00e9 \u00e0 un compte O\u00f9 Aiza");
			}else throw exception;
	            }
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Utilisateur contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}

	public static void update(Utilisateur model) throws Exception {
		
		String query = "UPDATE UTILISATEUR"
					+ "SET NOM = ?, "
					+ "PRENOM = ?, "
					+ "DATENAISSANCE = ?, "
					+ "SEXE=?, "
					+ "IDENTIFIANT = ?," 
					+ "PASSWORD = ?, "
					+ "ADRESSE = ?, "
					+ "EMAIL = ?"
					+ " WHERE IDUTILISATEUR = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setString(1, model.getNom());
			statement.setString(2, model.getPrenom());
			statement.setDate(3, Date.valueOf(model.getDateNaissance()));
			statement.setString(4, model.getSexe());
			statement.setString(5, model.getIdentifiant());
			statement.setString(6, model.getPassword());
			statement.setString(7, model.getAdresse());
			statement.setString(8, model.getEmail());
			statement.setInt(9, model.getId());
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

	public static void delete(Utilisateur model) throws Exception {
		
		String query = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
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

	public static List<Utilisateur> findAll(int offset) throws Exception {
		
		String query = "SELECT * FROM UTILISATEUR LIMIT 10 OFFSET ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, offset);
			return DBToUtilisateur(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	
	public static Utilisateur findById(int id) throws Exception {
		
		String query = "SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerUtilisateur(res);
			}
			throw new Exception("Ce Utilisateur est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null)res.close();
			if(statement != null) statement.close();
			if(con != null)	con.close();
		}
	}
	public static Utilisateur findByIdentifiant(String identifiant) throws Exception {
		
		String query = "SELECT * FROM UTILISATEUR WHERE IDENTIFIANT = ? ";
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		try{
			con = UtilDB.getConnPostgre();
			statement = con.prepareStatement(query);
			statement.setString(1, identifiant);
			res = statement.executeQuery();
			if(res.next()){
				return Creation.creerUtilisateur(res);
			}
			throw new Exception("Ce Utilisateur est introuvable ou a \u00e9t\u00e9 retir\u00e9");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res!=null)res.close();
			if(statement != null)statement.close();
			if(con != null)con.close();
		}
	}
	
	
//		public static Utilisateur connexion(String email, String password) throws Exception {
//	
//		String query = "SELECT * FROM UTILISATEUR WHERE IDENTIFIANT = ? AND PASSWORD = ?";
//		Connection con = null;
//		PreparedStatement statement = null;
//		ResultSet res = null;
//		try {
//			statement.setString(1, email);
//			statement.setString(2, password);
//			res = statement.executeQuery();
//			if(res.next()){
//				return Creation.creerUtilisateur(res);
//			}
//			throw new Exception("Vos identifiants sont incorrects ou nous ne vous connaissons pas encore");
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(res!=null)res.close();
//			if(statement != null)statement.close();
//			if(con != null)con.close();
//		}
//	}
	static List<Utilisateur> DBToUtilisateur(ResultSet res)throws Exception{
		try{
			List<Utilisateur> model = new Vector<Utilisateur>();
			while(res.next()){
				model.add(Creation.creerUtilisateur(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			if(res != null) res.close();
		}
		
	}
}
