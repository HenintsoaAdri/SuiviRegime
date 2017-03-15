package s6.suiviRegime.daoGenere;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import s6.suiviRegime.modele.*;
public class PoidsDao{ 
	public  void save(Poids model) throws Exception{
 		String query = "INSERT INTO POIDS (IDREGIME, DATE, POIDS) VALUES(?, ?, ?)";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setDate(2, new Date(model.getDate().getTime()));
			 statement.setFloat(3, model.getPoids());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" Poids contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(Poids model) throws Exception{

		String query = "UPDATE POIDS SET IDREGIME= ?, DATE= ?, POIDS= ? WHERE IDPOIDS = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setDate(2, new Date(model.getDate().getTime()));
			 statement.setFloat(3, model.getPoids());
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
	public void delete(Poids model) throws Exception{

		String query = "DELETE FROM POIDS WHERE IDPOIDS = ?";
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
	public List<Poids> findAll() throws Exception{

		String query = "SELECT * FROM POIDS";
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
	public List<Poids> findAll(int offset) throws Exception{

		String query = "SELECT * FROM POIDS";
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
	public Poids findById(int id) throws Exception{

		String query = "SELECT * FROM POIDS WHERE IDPOIDS = ?";
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
		 throw new Exception("Ce Poids est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Poids> DBToModel(ResultSet res) throws Exception{

		try{
			 List<Poids> model = new Vector<Poids>();
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
	public Poids creer(ResultSet res) throws Exception{

		Poids model = new Poids();
			 return model;
	}
}