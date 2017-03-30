package s6.suiviRegime.daoGenere;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.*;

public class RegimeDao{ 
	public  void save(Regime model) throws Exception{
 		String query = "INSERT INTO REGIME (IDUTILISATEUR, DEBUTREGIME, FINREGIME, POIDSOBJECTIFREGIME) VALUES(?, ?, ?, ?)";
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
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" Regime contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(Regime model) throws Exception{

		String query = "UPDATE REGIME SET IDUTILISATEUR= ?, DEBUTREGIME= ?, FINREGIME= ?, POIDSOBJECTIFREGIME= ? WHERE IDREGIME = ?";
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
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public void delete(Regime model) throws Exception{

		String query = "DELETE FROM REGIME WHERE IDREGIME = ?";
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
	public List<Regime> findAll() throws Exception{

		String query = "SELECT * FROM REGIME";
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
	public List<Regime> findAll(int offset) throws Exception{

		String query = "SELECT * FROM REGIME LIMIT 10 OFFSET = ?";
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
	public Regime findById(int id) throws Exception{

		String query = "SELECT * FROM REGIME WHERE IDREGIME = ?";
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
		 throw new Exception("Ce Regime est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Regime> DBToModel(ResultSet res) throws Exception{

		try{
			 List<Regime> model = new Vector<Regime>();
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
	public Regime creer(ResultSet res) throws Exception{

		Regime model = new Regime();
		model.setId(res.getInt("IDREGIME"));
		model.setUtilisateur(new UtilisateurDao().findById(res.getInt("IDUTILISATEUR")));
		model.setDebut(res.getDate("DEBUTREGIME"));
		model.setFin(res.getDate("FINREGIME"));
		model.setPoidsObjectif(res.getFloat("POIDSOBJECTIFREGIME"));
		return model;
	}
}