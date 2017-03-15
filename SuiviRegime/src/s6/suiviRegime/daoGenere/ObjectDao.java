package s6.suiviRegime.daoGenere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.BaseModele;

public class ObjectDao {
	public  void save(BaseModele model) throws Exception{
 		String query = "INSERT INTO "
 				+ getNomClasse(model).toUpperCase()
 				+ " (NOM, MATIN, MIDI, SOIR) "
 				+ "VALUES(?, ?, ?, ?)";
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
			throw new Exception(" AlimentationConseil contenant des erreurs, cr�ation �chou�e.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(BaseModele model) throws Exception{

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
	public void delete(BaseModele model) throws Exception{

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
	public List<BaseModele> findAll() throws Exception{

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
	public List<BaseModele> findAll(int offset) throws Exception{

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
		 throw new Exception("Ce AlimentationConseil est introuvable ou a �t� retir�");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<BaseModele> DBToModel(BaseModele model, ResultSet res) throws Exception{

		try{
			 List<BaseModele> model = new Vector<AlimentationConseil>();
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
	public BaseModele creer(BaseModele model, ResultSet res) throws Exception{

		AlimentationConseil model = new AlimentationConseil();
		model.setId(res.getInt("IDALIMENTATIONCONSEIL"));
		model.setNom(res.getString("NOM"));
		model.setMatin(res.getString("MATIN"));
		model.setMidi(res.getString("MIDI"));
		model.setSoir(res.getString("SOIR"));
		return model;
	}
	public String getNomClasse(BaseModele model){
		return model.getClass().getSimpleName();
	}
}
