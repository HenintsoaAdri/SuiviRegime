package s6.suiviRegime.utilitaire;

import java.util.*;

public class ClasseAGenerer {
	
	String nomModele;
	List<String[]> attribut = new Vector<String[]>();
	String packages;
	String saveQuery = "\"INSERT INTO %s (%s) VALUES(%s)\"";
	String updateQuery = "\"UPDATE %s SET %s WHERE ID%s = ?\"";
	String deleteQuery = "\"DELETE FROM %s WHERE ID%s = ?\"";
	String findAllQuery = "\"SELECT * FROM %s\"";
	String findAllOffsetQuery = "\"SELECT * FROM %s LIMIT 10 OFFSET = ?\"";
	String findByIdQuery = "\"SELECT * FROM %s WHERE ID%s = ?\"";
	boolean containsAttributObject = false;
	
	public ClasseAGenerer(String nomModele, List<String[]> attribut) {
		this.setNomModele(nomModele);
		this.setAttribut(attribut);
	}
	public ClasseAGenerer(String nomModele) {
		this.setNomModele(nomModele);
	}

	public String getNomModele() {
		return nomModele;
	}
	public void setNomModele(String nomModele) {
		this.nomModele = nomModele.trim().replace(".java", "");
	}
	public List<String[]> getAttribut() {
		return attribut;
	}
	public void setAttribut(List<String[]> attribut) {
		this.attribut = attribut;
	}
	public void addAttribut(String type, String nom) {
		type = type.trim();
		nom = nom.trim();
		if(!StringUtil.isPrimitif(type)){
			nom = "id".concat(type.toLowerCase());
			type = "int";
			containsAttributObject = true;
		}
		String[] attribut = {type,nom};
		addAttribut(attribut);
	}
	public void addAttribut(String[] attribut){
		getAttribut().add(attribut);
	}
	public String getNomTable(){
		return getNomModele().toUpperCase();
	}
	public String getNomDao(){
		return getNomModele().concat("Dao");
	}
	public String getNomFichier(){
		return getNomDao().concat(".java");
	}

	public String getSaveQuery() {
		String[] list = {"",""};
		for(String[] s : getAttribut()){
			if(s[1].compareToIgnoreCase("id")==0) continue;
			String nom = s[1].toUpperCase()+ getNomTable();
			if(s[1].startsWith("id") 
					&& s[0].equalsIgnoreCase("int")) nom = nom.replace(getNomTable(), "");
			list[0] += nom;
			list[0] += ", ";
			list[1] += "?, ";
		}
		list[0] = list[0].substring(0, list[0].lastIndexOf(','));
		list[1] = list[1].substring(0, list[1].lastIndexOf(','));
		String save = String.format(saveQuery, getNomTable(), list[0],list[1]);
		return save;
	}
	
	public String getUpdateQuery(){
		String list = "";
		for(String[] s : getAttribut()){
			if(s[1].compareToIgnoreCase("id")==0) continue;
			String nom = s[1].toUpperCase()+ getNomTable();
			if(s[1].startsWith("id") 
					&& s[0].equalsIgnoreCase("int")) nom = nom.replace(getNomTable(), "");
			list += nom;
			list += "= ?, ";
		}
		list = list.substring(0, list.lastIndexOf(','));
		String update = String.format(updateQuery, getNomTable(), list, getNomTable());
		return update;
	}
	public String getDeleteQuery(){
		return String.format(deleteQuery, getNomTable(), getNomTable());
	}
	public String getFindAllOffsetQuery(){
		return String.format(findAllOffsetQuery, getNomTable());
	}
	public String getFindAllQuery(){
		return String.format(findAllQuery, getNomTable());
	}
	public String getFindByIdQuery(){
		return String.format(findByIdQuery, getNomTable(), getNomTable());
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages.replace(";", ".*");
	}
	public String getNonPrimitifMethod(String attribut){
		if(containsAttributObject)return StringUtil.firstUpper(attribut.replace("id", "")).concat("().getId");
		else return attribut;
	}
	public String getNonPrimitifNom(String attribut){
		if(containsAttributObject)return StringUtil.firstUpper(attribut.replace("id", ""));
		else return attribut;
	}
	public boolean containsAttributObject() {
		return containsAttributObject;
	}
}
