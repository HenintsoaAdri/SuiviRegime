package s6.suiviRegime.daoGenere;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.BaseModele;
import s6.suiviRegime.utilitaire.StringUtil;

public class ObjectDao {
	public  void save(BaseModele model) throws Exception{
 		String query = getSaveQuery(model);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 int i = 1;
			 for(Field f : getFields(model)){
				 String getter = "get".concat(StringUtil.firstUpper(f.getName()));
				 Method get = model.getClass().getMethod(getter, void.class);
				 String setMethod = "set";
				 if(f.getType().isPrimitive()
						 ||f.getType() == String.class
						 ||f.getType() == Date.class){
					 setMethod = setMethod.concat(StringUtil.firstUpper(f.getType().getSimpleName()));
					 Method set = PreparedStatement.class.getMethod(setMethod, f.getType());
					 set.invoke(statement,i, get.invoke(model, void.class));
				 }else if(f.getType().getSuperclass() == BaseModele.class){
					 BaseModele obj = (BaseModele)get.invoke(model, void.class);
					 statement.setInt(i, obj.getId());
				 }else statement.setString(i, get.invoke(model, void.class).toString());
				 i++;
			 }
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Informations contenant des erreurs, création échouée.");
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public  void update(BaseModele model) throws Exception{

		String query = getUpdateQuery(model);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 con.setAutoCommit(false);
			 int i = 1;
			 for(Field f : getFields(model)){
				 String getMethod = "get".concat(StringUtil.firstUpper(f.getName()));
				 Method get = model.getClass().getMethod(getMethod, void.class);
				 String setMethod = "set";
				 if(f.getType().isPrimitive()
						 ||f.getType() == String.class
						 ||f.getType() == Date.class){
					 setMethod = setMethod.concat(StringUtil.firstUpper(f.getType().getSimpleName()));
					 Method set = statement.getClass().getMethod(setMethod, f.getType());
					 set.invoke(statement,i, get.invoke(model, void.class));
				 }else if(f.getType().getSuperclass() == BaseModele.class){
					 BaseModele obj = (BaseModele)get.invoke(model, void.class);
					 statement.setInt(i, obj.getId());
				 }else statement.setString(i, get.invoke(model, void.class).toString());
				 i++;
			 }
			 statement.setInt(i, model.getId());
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

		String query = getDeleteQuery(model);
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
	public List<BaseModele> findAll(BaseModele model) throws Exception{

		return findAll(model, false);
	}
	public List<BaseModele> findAll(BaseModele model, boolean next) throws Exception{

		String query = getFindAllQuery(model);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			return DBToModel(model, statement.executeQuery(), next);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<BaseModele> findAll(BaseModele model, int offset) throws Exception{
		return findAll(model, offset, false);
	}
	public List<BaseModele> findAll(BaseModele model, int offset, boolean next) throws Exception{

		String query = getFindAllOffsetQuery(model);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 statement.setInt(1, offset);
			 return DBToModel(model, statement.executeQuery(), next);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public BaseModele findById(BaseModele model) throws Exception {
		return findById(model, false);
	}
	public BaseModele findById(BaseModele model, boolean next) throws Exception{

		String query = getFindByIdQuery(model);
		ResultSet res = null;
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 statement.setInt(1, model.getId());
			 res = statement.executeQuery();
			 if(res.next()){
				 return creer(model,res,next);
			 }
		 throw new Exception("Cet Objet est introuvable ou a été retiré");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(res != null) res.close();
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public List<BaseModele> DBToModel(BaseModele model, ResultSet res, Boolean next) throws Exception{

		try{
			 List<BaseModele> liste = new Vector<BaseModele>();
			 while(res.next()){ 
				 liste.add(creer(model, res, next));
			 }
		 return liste;
		}catch(Exception e){
			 throw e;
		}finally {
			 if(res != null) res.close();
		}
	}
	public BaseModele creer(BaseModele model, ResultSet res, Boolean next) throws Exception{

		BaseModele objet = model.getClass().newInstance();
		String nomClass = getNomClasse(model);
		objet.setId(res.getInt("ID".concat(nomClass).toUpperCase()));
		for(Field f : getFields(objet)){
			String fieldTable = f.getName().toUpperCase().concat(getNomClasse(model).toUpperCase());
			String setter = "set".concat(StringUtil.firstUpper(f.getName()));
			Method set = objet.getClass().getMethod(setter, f.getType());
			String getMethod = "get";
			if(f.getType().isPrimitive()
			 ||f.getType() == String.class
			 ||f.getType() == Date.class){
				getMethod = getMethod.concat(StringUtil.firstUpper(f.getType().getSimpleName()));
				Method get = ResultSet.class.getMethod(getMethod, String.class);
				set.invoke(objet, get.invoke(res, fieldTable));
			}else if(f.getType().getSuperclass() == BaseModele.class){
				BaseModele child = (BaseModele)f.getType().newInstance();
				child.setId(res.getInt("ID".concat(f.getName().toUpperCase())));
				if(next) set.invoke(objet, findById(child));
				else set.invoke(objet, child);
			}else set.invoke(objet, res.getString(fieldTable));
		 }
		return objet;
	}
	public String getNomClasse(BaseModele model){
		return model.getClass().getSimpleName();
	}
	public Field[] getFields(BaseModele model){
		return model.getClass().getDeclaredFields();
	}
	public String getSaveQuery(BaseModele model) {
		String query = "INSERT INTO %s (%s) VALUES(%s)";
		String[] list = {"",""};
		for(Field f : getFields(model)){
			if(f.getType().getSuperclass() == BaseModele.class )
				list[0] += "ID".concat(f.getName().toUpperCase());
			else list[0] += f.getName().toUpperCase() + getNomClasse(model).toUpperCase();
			list[0] += ", ";
			list[1] += "?, ";
		}
		list[0] = list[0].substring(0, list[0].lastIndexOf(','));
		list[1] = list[1].substring(0, list[1].lastIndexOf(','));
		return String.format(query, getNomClasse(model).toUpperCase(), list[0],list[1]);
	}
	public String getUpdateQuery(BaseModele model){
		String query = "UPDATE %s SET %s WHERE ID%s = ?";
		String list = "";
		for(Field f : getFields(model)){
			if(f.getType().getSuperclass() == BaseModele.class)
				list += "ID".concat(f.getName().toUpperCase());
			else
				list += f.getName().toUpperCase() + getNomClasse(model).toUpperCase();
			list += "= ?, ";
		}
		list = list.substring(0, list.lastIndexOf(','));
		String update = String.format(query, getNomClasse(model).toUpperCase(), list, getNomClasse(model).toUpperCase());
		return update;
	}
	public String getDeleteQuery(BaseModele model){
		String query = "DELETE FROM %s WHERE ID%s = ?";
		return String.format(query, getNomClasse(model).toUpperCase(), getNomClasse(model).toUpperCase());
	}
	public String getFindAllOffsetQuery(BaseModele model){
		String query = "SELECT * FROM %s LIMIT 10 OFFSET = ?";
		return String.format(query, getNomClasse(model).toUpperCase());
	}
	public String getFindAllQuery(BaseModele model){
		String query = "SELECT * FROM %s";
		return String.format(query, getNomClasse(model).toUpperCase());
	}
	public String getFindByIdQuery(BaseModele model){
		String query = "SELECT * FROM %s WHERE ID%s = ?";
		return String.format(query, getNomClasse(model).toUpperCase(), getNomClasse(model).toUpperCase());
	}
}
