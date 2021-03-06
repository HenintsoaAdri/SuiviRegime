package s6.suiviRegime.utilitaire;
import java.io.*;

public class DaoGenerateur {
	File dao = new File("src/s6/suiviRegime/daoGenere");
	File footerPreparedStatement = new File(dao,"utilitaire/footerDaoPrepared");
	File footerResultSet = new File(dao,"utilitaire/footerDaoResult");
	File footerSave = new File(dao,"utilitaire/footerSaveDaoPrepared");
	public void generateDao(File path, String packages){
		try {
			if(path.isFile()){
				generateFileDao(path, packages);
				return;
			}
			File [] liste = path.listFiles();
			for(File file : liste){
				generateFileDao(file, packages);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void generateFileDao(File file, String packages) throws Exception{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		if(file.isFile() && !file.getName().equalsIgnoreCase("BaseModele.java")){
			try {
				fileReader = new FileReader(file);
				ClasseAGenerer classe = new ClasseAGenerer(file.getName());
				bufferedReader = new BufferedReader(fileReader);
				String line;
				int compteurAccolade = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if(line.contains("{")){
						compteurAccolade++;
						continue;
					}
					if(line.contains("}")){
						compteurAccolade--;
						continue;
					}
					if(line.contains("package")){
						classe.setPackages(line.split(" ")[1]);
					}
					if((compteurAccolade - 1) == 0 && line.endsWith(";")){
						if(line.contains("="))line = line.substring(0, line.indexOf('='));
						if(line.contains("List")) continue;
							line = line.replace(";", "");
							line = line.replace("private", "");
							line = line.replace("static final", "");
							String [] attributType = line.split(" ");
							classe.addAttribut(attributType[0],attributType[1]);
					}
				}
				writeDao(classe,packages);
			} catch (Exception e) {
				throw e;
			} finally{
				if(fileReader != null) fileReader.close();
				if(bufferedReader != null) bufferedReader.close();
			}
		}
	}
	public void writeDao(ClasseAGenerer classe, String packages) throws Exception{
		FileWriter writer = null;
		BufferedReader bufferedReader = null;
		try {
			File newDao = new File(dao,classe.getNomFichier());
			dao.mkdir();
			newDao.createNewFile();
			writer = new FileWriter(newDao);
			writer.write(generateHead(classe, packages));
			writer.write(generateSave(classe));
			writer.write(generateUpdate(classe));
			writer.write(generateDelete(classe));			
			writer.write(generateFindAll(classe));			
			writer.write(generateFindAllOffset(classe));			
			writer.write(generateFindById(classe));
			writer.write(generateDBToModel(classe));
			writer.write(generateCreer(classe));
			writer.write("}");
		} catch (Exception e) {
			throw e;
		} finally {
			if(writer != null)writer.close();
			if(bufferedReader != null)bufferedReader.close();
		}
	}
	public String generateHead(ClasseAGenerer classe, String packages){
		String head =  "package " + packages + ";\n\n"
				+ "import java.sql.*;\n"
				+ "import java.util.List;\n"
				+ "import java.util.Vector;\n\n"
				+ "import "	+ classe.getPackages() + ";\n\n"
				+ "public class " + classe.getNomDao() + "{ \n\t";
				return head;
	}
	public String generateInitialisation(boolean autoCommit){
		String init =  "Connection con = null;\n\t\t"
				+ "PreparedStatement statement = null;\n\t\t"
				+ "try{\n\t\t"
				+ "\t con = UtilDB.getConnexion();\n\t\t"
				+ "\t statement = con.prepareStatement(query);\n\t\t";
				if(!autoCommit) init += "\t con.setAutoCommit(false);\n\t\t";
				return init;
	}
	public String generateEnd(boolean autoCommit, boolean resultSet, String throwDeclaration){
		String end = "";
		if(!autoCommit)end	+= "\t statement.execute();\n\t\t"
				+ "\t con.commit();\n\t\t";
		end += "}catch(Exception e){\n\t\t\t";
		if(!autoCommit)end	+= "con.rollback();\n\t\t\t";
		end += "e.printStackTrace();\n\t\t\t"
				+ throwDeclaration + ";\n\t\t"
				+ "}finally {\n\t\t";
		if(resultSet)end += "\t if(res != null) res.close();\n\t\t";
		end	+= "\t if(statement != null)statement.close();\n\t\t"
			+ "\t if(con != null)con.close();\n\t\t"
			+ "}\n\t"
			+ "}\n\t";
		return end;
	}
	
	public String generateSave(ClasseAGenerer classe){
		String function = 
		"public  void save("
		+ classe.getNomModele()
		+ " model) throws Exception{\n \t\t"
		+ "String query = " + classe.getSaveQuery() + ";\n\t\t"
		+ generateInitialisation(false);
		int i = 1;
		String type = "";
		String nom = "";
		for(String[] attribut : classe.getAttribut()){
			type = attribut[0];
			nom = attribut[1];
			if(nom.startsWith("id") 
					&& type.equalsIgnoreCase("int")) 
			nom = classe.getNonPrimitifMethod(nom);
			nom = "model.get" + StringUtil.getInstance().firstUpper(nom) + "()";
			if(type.equalsIgnoreCase("Date")) nom = "new Date(".concat(nom).concat(".getTime())");
			function += "\t statement.set";
			function += StringUtil.getInstance().firstUpper(type);
			function += "("+ i + ", ";
			function += nom;
			function += ");\n\t\t";
			i++;
		}
		function += generateEnd(false,false,"throw new Exception(\" "
				+ classe.getNomModele()
				+ " contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.\")");
		return function;
	}
	public String generateUpdate(ClasseAGenerer classe){
		String function = 
				"public  void update("
				+ classe.getNomModele()
				+ " model) throws Exception{\n\n\t\t"
				+ "String query = " + classe.getUpdateQuery() + ";\n\t\t"
				+ generateInitialisation(false);
				int i = 1;
				String type = "";
				String nom = "";
				for(String[] attribut : classe.getAttribut()){
					type = attribut[0];
					nom = attribut[1];
					if(nom.startsWith("id") 
							&& attribut[0].equalsIgnoreCase("int")) 
					nom = classe.getNonPrimitifMethod(nom);
					nom = "model.get" + StringUtil.getInstance().firstUpper(nom) + "()";
					if(type.equalsIgnoreCase("Date")) nom = "new Date(".concat(nom).concat(".getTime())");
					function += "\t statement.set";
					function += StringUtil.getInstance().firstUpper(type);
					function += "("+ i + ", " + nom;
					function += ");\n\t\t";
					i++;
				}
				function += "\t statement.setInt("+ i + ", model.getId());\n\t\t"
						+ generateEnd(false, false, "throw e");
				return function;
	}
	public String generateDelete(ClasseAGenerer classe){
		String function = 
		"public void delete("
				+ classe.getNomModele()
				+ " model) throws Exception{\n\n\t\t"
				+ "String query = " + classe.getDeleteQuery() + ";\n\t\t"
				+ generateInitialisation(false)
				+ "\t statement.setInt(1, model.getId());\n\t\t"
				+ generateEnd(false, false, "throw e");
				return function;
	}
	public String generateFindAll(ClasseAGenerer classe){
		String function = 
				"public List<"
				+ classe.getNomModele()
				+ "> findAll() throws Exception{\n\n\t\t"
				+ "String query = " + classe.getFindAllQuery() + ";\n\t\t"
				+ generateInitialisation(true) + "\t\t"
				+ "\t return DBToModel(statement.executeQuery());\n\t\t"
				+ generateEnd(true, false, "throw e");
				return function;
	}
	public String generateFindAllOffset(ClasseAGenerer classe){
		String function = 
				"public List<"
				+ classe.getNomModele()
				+ "> findAll(int offset) throws Exception{\n\n\t\t"
				+ "String query = " + classe.getFindAllOffsetQuery() + ";\n\t\t"
				+ generateInitialisation(true)
				+ "\t statement.setInt(1, offset);\n\t\t"
				+ "\t return DBToModel(statement.executeQuery());\n\t\t"
				+ generateEnd(true, false, "throw e");
				return function;
	}
	public String generateFindById(ClasseAGenerer classe){
		String function = 
				"public "
				+ classe.getNomModele()
				+ " findById(int id) throws Exception{\n\n\t\t"
				+ "String query = " + classe.getFindByIdQuery() + ";\n\t\t"
				+ "ResultSet res = null;\n\t\t"
				+ generateInitialisation(true)
				+ "\t statement.setInt(1, id);\n\t\t"
				+ "\t res = statement.executeQuery();\n\t\t"
				+ "\t if(res.next()){\n\t\t"
				+ "\t\t return creer(res);\n\t\t"
				+ "\t }\n\t"
				+ "\t throw new Exception(\"Ce "
				+ classe.getNomModele()
				+ " est introuvable ou a \u00e9t\u00e9 retir\u00e9\");\n\t\t"
				+ generateEnd(true, true, "throw e");
				return function;
	}
	public String generateDBToModel(ClasseAGenerer classe){
		String function = 
				"public List<"
				+ classe.getNomModele()
				+ "> DBToModel(ResultSet res) throws Exception{\n\n\t\t"
				+ "try{\n\t\t"
				+ "\t List<"+ classe.getNomModele() + "> model "
				+ "= new Vector<" + classe.getNomModele() + ">();\n\t\t"
				+ "\t while(res.next()){ \n\t\t"
				+ "\t\t model.add(creer(res));\n\t\t"
				+ "\t }\n\t"
				+ "\t return model;\n\t\t"
				+ "}catch(Exception e){\n\t\t"
				+ "\t throw e;\n\t\t"
				+ "}finally {\n\t\t"
				+ "\t if(res != null) res.close();\n\t\t"
				+ "}\n\t"
				+ "}\n\t";
		return function;
	}
	public String generateCreer(ClasseAGenerer classe){
		String function = 
				"public "
				+ classe.getNomModele()
				+ " creer(ResultSet res) throws Exception{\n\n\t\t"
				+ classe.getNomModele() + " model = new " + classe.getNomModele() + "();\n\t\t"
				+ "model.setId(res.getInt(\"ID"
				+ classe.getNomTable() + "\"));\n\t\t";
		String type = "";
		String nom = "";
		for(String[] attribut : classe.getAttribut()){
			type = attribut[0];
			nom = attribut[1];
			boolean nonPrimitif = attribut[1].startsWith("id") && type.equalsIgnoreCase("int") && classe.containsAttributObject();
			if(nonPrimitif) nom = classe.getNonPrimitifNom(nom);
			function += "model.set"+ StringUtil.getInstance().firstUpper(nom) 
				+ "(";
			nom = nom + classe.getNomTable();
			if(nonPrimitif){
				function += "new ".concat(nom.replace(classe.getNomTable(), "").concat("Dao().findById("));
				nom = attribut[1];
			}
			function += "res.get" + StringUtil.getInstance().firstUpper(type) + "(\""
				+ nom.toUpperCase() + "\")";
			if(nonPrimitif)
			function+= ")";
			function += ");\n\t\t";
		}
			function += "return model;\n\t"
				+ "}\n";
		return function;
	}
}
