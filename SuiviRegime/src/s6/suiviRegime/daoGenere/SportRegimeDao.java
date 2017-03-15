package s6.suiviRegime.daoGenere;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import s6.suiviRegime.modele.*;
public class SportRegimeDao{ 
	public  void save(SportRegime model) throws Exception{
 		String query = "INSERT INTO SPORTREGIME (IDREGIME, DATE, RYTHME) VALUES(?, ?, ?)";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setDate(2, new Date(model.getDate().getTime()));
			 statement.setFloat(3, model.getRythme());
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" SportRegime contenant des erreurs, cr�ation �chou�e.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(SportRegime model) throws Exception{

		String query = "UPDATE SPORTREGIME SET IDREGIME= ?, DATE= ?, RYTHME= ? WHERE IDSPORTREGIME = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setDate(2, new Date(model.getDate().getTime()));
			 statement.setFloat(3, model.getRythme());
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

		String query = "SELECT * FROM SPORTREGIME";
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
		 throw new Exception("Ce SportRegime est introuvable ou a �t� retir�");
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
			 return model;
	}
}