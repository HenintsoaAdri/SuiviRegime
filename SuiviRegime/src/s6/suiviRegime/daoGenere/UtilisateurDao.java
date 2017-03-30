package s6.suiviRegime.daoGenere;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.*;

public class UtilisateurDao{ 
	public  void save(Utilisateur model) throws Exception{
 		String query = "INSERT INTO UTILISATEUR (NOMUTILISATEUR, PRENOMUTILISATEUR, SEXEUTILISATEUR, IDENTIFIANTUTILISATEUR, PASSWORDUTILISATEUR, ADRESSEUTILISATEUR, EMAILUTILISATEUR) VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setString(1, model.getNom());
			 statement.setString(2, model.getPrenom());
			 statement.setString(3, model.getSexe());
			 statement.setString(4, model.getIdentifiant());
			 statement.setString(5, model.getPassword());
			 statement.setString(6, model.getAdresse());
			 statement.setString(7, model.getEmail());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" Utilisateur contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(Utilisateur model) throws Exception{

		String query = "UPDATE UTILISATEUR SET NOMUTILISATEUR= ?, PRENOMUTILISATEUR= ?, SEXEUTILISATEUR= ?, IDENTIFIANTUTILISATEUR= ?, PASSWORDUTILISATEUR= ?, ADRESSEUTILISATEUR= ?, EMAILUTILISATEUR= ? WHERE IDUTILISATEUR = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setString(1, model.getNom());
			 statement.setString(2, model.getPrenom());
			 statement.setString(3, model.getSexe());
			 statement.setString(4, model.getIdentifiant());
			 statement.setString(5, model.getPassword());
			 statement.setString(6, model.getAdresse());
			 statement.setString(7, model.getEmail());
			 statement.setInt(8, model.getId());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public void delete(Utilisateur model) throws Exception{

		String query = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getId());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Utilisateur> findAll() throws Exception{

		String query = "SELECT * FROM UTILISATEUR";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
					 return DBToModel(statement.executeQuery());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Utilisateur> findAll(int offset) throws Exception{

		String query = "SELECT * FROM UTILISATEUR LIMIT 10 OFFSET = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 statement.setInt(1, offset);
			 return DBToModel(statement.executeQuery());
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public Utilisateur findById(int id) throws Exception{

		String query = "SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		ResultSet res = null;
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 statement.setInt(1, id);
			 res = statement.executeQuery();
			 if(res.next()){
				 return creer(res);
			 }
		 throw new Exception("Ce Utilisateur est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Utilisateur> DBToModel(ResultSet res) throws Exception{

		try{
			 List<Utilisateur> model = new Vector<Utilisateur>();
			 while(res.next()){ 
				 model.add(creer(res));
			 }
		 return model;
		}catch(Exception e){
			 throw e;
		}finally {
			 if(res != null) res.close();
		}
	}
	public Utilisateur creer(ResultSet res) throws Exception{

		Utilisateur model = new Utilisateur();
		model.setId(res.getInt("IDUTILISATEUR"));
		model.setNom(res.getString("NOMUTILISATEUR"));
		model.setPrenom(res.getString("PRENOMUTILISATEUR"));
		model.setSexe(res.getString("SEXEUTILISATEUR"));
		model.setIdentifiant(res.getString("IDENTIFIANTUTILISATEUR"));
		model.setPassword(res.getString("PASSWORDUTILISATEUR"));
		model.setAdresse(res.getString("ADRESSEUTILISATEUR"));
		model.setEmail(res.getString("EMAILUTILISATEUR"));
		return model;
	}
}