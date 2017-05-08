package s6.suiviRegime.daoGenere;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.*;

public class SportRegimeDao{ 
	public  void save(SportRegime model) throws Exception{
 		String query = "INSERT INTO SPORTREGIME (IDREGIME, IDSPORT, DATESPORTREGIME, RYTHMESPORTREGIME) VALUES(?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setInt(2, model.getSport().getId());
			 statement.setDate(3, new Date(model.getDate().getTime()));
			 statement.setFloat(4, model.getRythme());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" SportRegime contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(SportRegime model) throws Exception{

		String query = "UPDATE SPORTREGIME SET IDREGIME= ?, IDSPORT= ?, DATESPORTREGIME= ?, RYTHMESPORTREGIME= ? WHERE IDSPORTREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setInt(2, model.getSport().getId());
			 statement.setDate(3, new Date(model.getDate().getTime()));
			 statement.setFloat(4, model.getRythme());
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
	public void delete(SportRegime model) throws Exception{

		String query = "DELETE FROM SPORTREGIME WHERE IDSPORTREGIME = ?";
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
	public List<SportRegime> findAll() throws Exception{

		String query = "SELECT * FROM SPORTREGIME";
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
	public List<SportRegime> findAll(int offset) throws Exception{

		String query = "SELECT * FROM SPORTREGIME LIMIT 10 OFFSET = ?";
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
	public SportRegime findById(int id) throws Exception{

		String query = "SELECT * FROM SPORTREGIME WHERE IDSPORTREGIME = ?";
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
		 throw new Exception("Ce SportRegime est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<SportRegime> DBToModel(ResultSet res) throws Exception{

		try{
			 List<SportRegime> model = new Vector<SportRegime>();
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
	public SportRegime creer(ResultSet res) throws Exception{

		SportRegime model = new SportRegime();
		model.setId(res.getInt("IDSPORTREGIME"));
		model.setRegime(new RegimeDao().findById(res.getInt("IDREGIME")));
		model.setSport(new SportDao().findById(res.getInt("IDSPORT")));
		model.setDate(res.getDate("DATESPORTREGIME"));
		model.setRythme(res.getInt("RYTHMESPORTREGIME"));
		return model;
	}
}