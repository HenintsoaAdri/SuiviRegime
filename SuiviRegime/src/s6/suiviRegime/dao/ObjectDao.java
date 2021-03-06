package s6.suiviRegime.dao;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import s6.suiviRegime.daoGenere.UtilDB;
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
			 setStatement(statement, model);
			 statement.execute();
			 con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Informations contenant des erreurs, cr�ation �chou�e.");
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
			int i = setStatement(statement, model);
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
		Field[] defaultValue = checkDefaultValue(model);
		String query = getFindAllQuery(model, defaultValue);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			con = UtilDB.getConnexion();
			statement = con.prepareStatement(query);
			setStatement(statement, defaultValue, model);
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
	public List<BaseModele> findAllByIdField(BaseModele model, Type field) throws Exception{
		return findAllByIdField(model, field, false);
	}
	public List<BaseModele> findAllByIdField(BaseModele model, Type field, boolean next) throws Exception{

		String query = getFindAllByIdFieldQuery(model, field);
		Connection con = null;
		PreparedStatement statement = null;
		try{
			 con = UtilDB.getConnexion();
			 statement = con.prepareStatement(query);
			 statement.setInt(1, model.getId());
			 return DBToModel(model, statement.executeQuery(), next);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			 if(statement != null)statement.close();
			 if(con != null)con.close();
		}
	}
	public void findById(BaseModele model) throws Exception {
		findById(model, false);
	}
	public void findById(BaseModele model, boolean next) throws Exception{

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
				 creer(model,res,next);
			 }
		 throw new Exception("Cet Objet est introuvable ou a �t� retir�");
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
				 BaseModele detail = model.getClass().newInstance();
				 creer(detail, res, next);
				 liste.add(detail);
			 }
		 return liste;
		}catch(Exception e){
			 throw e;
		}finally {
			 if(res != null) res.close();
		}
	}
	public void creer(BaseModele model, ResultSet res, Boolean next) throws Exception{

		String nomClass = getNomClasse(model);
		model.setId(res.getInt("ID".concat(nomClass).toUpperCase()));
		for(Field f : getFields(model)){
			String fieldTable = f.getName().toUpperCase().concat(getNomClasse(model).toUpperCase());
			String setter = "set".concat(StringUtil.getInstance().firstUpper(f.getName()));
			Method set = model.getClass().getMethod(setter, f.getType());
			if(f.getType() == List.class){
				if(next)
				set.invoke(model, findAllByIdField(model, getListType(f)));
			}
			else if(f.getType().getSuperclass() == BaseModele.class){
				BaseModele child = (BaseModele)f.getType().newInstance();
				child.setId(res.getInt("ID".concat(f.getName().toUpperCase())));
				if(next) findById(child);
				else set.invoke(model, child);
			}
			else if(f.getType().isPrimitive()
			 ||f.getType() == String.class
			 ||f.getType() == Date.class){
				String getMethod = "get".concat(StringUtil.getInstance().firstUpper(f.getType().getSimpleName()));
				Method get = ResultSet.class.getMethod(getMethod, String.class);
				set.invoke(model, get.invoke(res, fieldTable));
			}
			else{
				System.out.println(f.getType() == List.class);
				set.invoke(model, res.getString(fieldTable));
			}
		 }
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
			else 
				list[0] += f.getName().toUpperCase() + getNomClasse(model).toUpperCase();
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
			list += " = ?, ";
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
	public String getFindAllByIdFieldQuery(BaseModele model, Type field){
		String query = "SELECT * FROM %s WHERE ID%s = ?";
		return String.format(query, ((Class<?>)field).getSimpleName().toUpperCase(), getNomClasse(model).toUpperCase());
	}
	public String getFindAllQuery(BaseModele model, Field[] list) throws Exception{
		String query = "SELECT * FROM %s";
		int i = 0;
		for(Field f : list){
			if(i == 0) query.concat(" WHERE");
			query.concat(getFindByDefaultValue(f, i + 1 == list.length));
			i++;
		}
		return String.format(query, getNomClasse(model).toUpperCase());
	}
	public String getFindByDefaultValue(Field field, boolean end){
		String query = " %s = ? ";
		if(!end) query.concat("AND");
		return String.format(query, field.getType().getSimpleName());
	}
	public String getFindByIdQuery(BaseModele model){
		String query = "SELECT * FROM %s WHERE ID%s = ?";
		return String.format(query, getNomClasse(model).toUpperCase(), getNomClasse(model).toUpperCase());
	}
	public int setStatement(PreparedStatement statement, BaseModele model)throws Exception{
		 return setStatement(statement, getFields(model), model);
	}
	public int setStatement(PreparedStatement statement, Field[] field, BaseModele model) throws Exception{
		int i = 1;
		 try {
			 for(Field f : field){
				 String getter = "get".concat(StringUtil.getInstance().firstUpper(f.getName()));
				 Method get = model.getClass().getMethod(getter);
				 Object objGet = get.invoke(model);
				 if(f.getType().isPrimitive()
						 ||f.getType() == String.class
						 ||f.getType() == Date.class){
					 String setMethod = "set".concat(StringUtil.getInstance().firstUpper(f.getType().getSimpleName()));
					 Method set = null;
					 if(f.getType() == Date.class){
						 objGet = new java.sql.Date(((Date)objGet).getTime());
						 set = PreparedStatement.class.getMethod(setMethod,int.class, java.sql.Date.class);
					 }else 
						 set = PreparedStatement.class.getMethod(setMethod,int.class, f.getType());
					 set.invoke(statement,i, objGet);
				 }else if(f.getType().getSuperclass() == BaseModele.class){
					 BaseModele obj = (BaseModele)objGet;
					 statement.setInt(i, obj.getId());
				 }else statement.setString(i, objGet.toString());
				 i++;
			 }
			 return i;
		} catch (Exception e) {
			throw e;
		}
	}
	public Type getListType(Field field) throws Exception{
        ParameterizedType parameterized = (ParameterizedType)field.getGenericType();
        Type[] args = parameterized.getActualTypeArguments();
        for (Type t: args) {
            return t;
        }
		throw new Exception("Cette liste n'est pas parametr�e");
	}
	public Field[] checkDefaultValue(BaseModele model) throws Exception{
		List<Field> vect = new Vector<Field>();
		for(Field f : model.getClass().getDeclaredFields()){
			if(f.getType().isPrimitive() 
				|| f.getType() == String.class 
				|| f.getType() == Date.class){
				if(f.get(model) != null){
					vect.add(f);
				}
			}
		}
		Field [] defaultValue = new Field[vect.size()];
		return vect.toArray(defaultValue);
	}
}
