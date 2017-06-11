package s6.suiviRegime.daoGenere;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.*;

public class SportConseilDao{ 
	public  void save(SportConseil model) throws Exception{
 		String query = "INSERT INTO SPORTCONSEIL (IDSPORT, RYTHMESPORTCONSEIL, DETAILSSPORTCONSEIL) VALUES(?, ?, ?)";
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
			throw new Exception(" SportConseil contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(SportConseil model) throws Exception{

		String query = "UPDATE SPORTCONSEIL SET IDSPORT= ?, RYTHMESPORTCONSEIL= ?, DETAILSSPORTCONSEIL= ? WHERE IDSPORTCONSEIL = ?";
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
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public void delete(SportConseil model) throws Exception{

		String query = "DELETE FROM SPORTCONSEIL WHERE IDSPORTCONSEIL = ?";
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
	public List<SportConseil> findAll() throws Exception{

		String query = "SELECT * FROM SPORTCONSEIL";
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
	public List<SportConseil> findAll(int offset) throws Exception{

		String query = "SELECT * FROM SPORTCONSEIL LIMIT 10 OFFSET = ?";
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
	public SportConseil findById(int id) throws Exception{

		String query = "SELECT * FROM SPORTCONSEIL WHERE IDSPORTCONSEIL = ?";
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
		 throw new Exception("Ce SportConseil est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<SportConseil> DBToModel(ResultSet res) throws Exception{

		try{
			 List<SportConseil> model = new Vector<SportConseil>();
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
	public SportConseil creer(ResultSet res) throws Exception{

		SportConseil model = new SportConseil();
		model.setId(res.getInt("IDSPORTCONSEIL"));
		model.setSport(new SportDao().findById(res.getInt("IDSPORT")));
		model.setRythme(res.getInt("RYTHMESPORTCONSEIL"));
		model.setDetails(res.getString("DETAILSSPORTCONSEIL"));
		return model;
	}
}