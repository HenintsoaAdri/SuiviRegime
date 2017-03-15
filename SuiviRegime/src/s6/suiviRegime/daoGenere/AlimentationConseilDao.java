package s6.suiviRegime.daoGenere;
import java.sql.*;
import java.util.*;
import s6.suiviRegime.modele.*;
public class AlimentationConseilDao{ 
	public  void save(AlimentationConseil model) throws Exception{
 		String query = "INSERT INTO ALIMENTATIONCONSEIL (NOM, MATIN, MIDI, SOIR) VALUES(?, ?, ?, ?)";
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
			throw new Exception(" AlimentationConseil contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(AlimentationConseil model) throws Exception{

		String query = "UPDATE ALIMENTATIONCONSEIL SET NOM= ?, MATIN= ?, MIDI= ?, SOIR= ? WHERE IDALIMENTATIONCONSEIL = ?";
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
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public void delete(AlimentationConseil model) throws Exception{

		String query = "DELETE FROM ALIMENTATIONCONSEIL WHERE IDALIMENTATIONCONSEIL = ?";
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
	public List<AlimentationConseil> findAll() throws Exception{

		String query = "SELECT * FROM ALIMENTATIONCONSEIL";
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
	public List<AlimentationConseil> findAll(int offset) throws Exception{

		String query = "SELECT * FROM ALIMENTATIONCONSEIL";
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
	public AlimentationConseil findById(int id) throws Exception{

		String query = "SELECT * FROM ALIMENTATIONCONSEIL WHERE IDALIMENTATIONCONSEIL = ?";
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
		 throw new Exception("Ce AlimentationConseil est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<AlimentationConseil> DBToModel(ResultSet res) throws Exception{

		try{
			 List<AlimentationConseil> model = new Vector<AlimentationConseil>();
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
	public AlimentationConseil creer(ResultSet res) throws Exception{

		AlimentationConseil model = new AlimentationConseil();
			 return model;
	}
}