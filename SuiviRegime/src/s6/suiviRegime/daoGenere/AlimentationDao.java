package s6.suiviRegime.daoGenere;

import java.sql.*;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.*;

public class AlimentationDao{ 
	public  void save(Alimentation model) throws Exception{
 		String query = "INSERT INTO ALIMENTATION (IDREGIME, REPASALIMENTATION, BOISSONALIMENTATION, PERIODEALIMENTATION, DATEALIMENTATION) VALUES(?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setString(2, model.getRepas());
			 statement.setString(3, model.getBoisson());
			 statement.setInt(4, model.getPeriode());
			 statement.setDate(5, new Date(model.getDate().getTime()));
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(" Alimentation contenant des erreurs, cr�ation �chou�e.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(Alimentation model) throws Exception{

		String query = "UPDATE ALIMENTATION SET IDREGIME= ?, REPASALIMENTATION= ?, BOISSONALIMENTATION= ?, PERIODEALIMENTATION= ?, DATEALIMENTATION= ? WHERE IDALIMENTATION = ?";
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 statement.setInt(1, model.getRegime().getId());
			 statement.setString(2, model.getRepas());
			 statement.setString(3, model.getBoisson());
			 statement.setInt(4, model.getPeriode());
			 statement.setDate(5, new Date(model.getDate().getTime()));
			 statement.setInt(6, model.getId());
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
	public void delete(Alimentation model) throws Exception{

		String query = "DELETE FROM ALIMENTATION WHERE IDALIMENTATION = ?";
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
	public List<Alimentation> findAll() throws Exception{

		String query = "SELECT * FROM ALIMENTATION";
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
	public List<Alimentation> findAll(int offset) throws Exception{

		String query = "SELECT * FROM ALIMENTATION LIMIT 10 OFFSET = ?";
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
	public Alimentation findById(int id) throws Exception{

		String query = "SELECT * FROM ALIMENTATION WHERE IDALIMENTATION = ?";
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
		 throw new Exception("Ce Alimentation est introuvable ou a �t� retir�");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<Alimentation> DBToModel(ResultSet res) throws Exception{

		try{
			 List<Alimentation> model = new Vector<Alimentation>();
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
	public Alimentation creer(ResultSet res) throws Exception{

		Alimentation model = new Alimentation();
		model.setId(res.getInt("IDALIMENTATION"));
		model.setRegime(new RegimeDao().findById(res.getInt("IDREGIME")));
		model.setRepas(res.getString("REPASALIMENTATION"));
		model.setBoisson(res.getString("BOISSONALIMENTATION"));
		model.setPeriode(res.getInt("PERIODEALIMENTATION"));
		model.setDate(res.getDate("DATEALIMENTATION"));
		return model;
	}
}