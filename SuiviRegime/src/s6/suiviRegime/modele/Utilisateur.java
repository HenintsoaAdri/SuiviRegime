package s6.suiviRegime.modele;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import s6.suiviRegime.utilitaire.DateUtil;
import s6.suiviRegime.utilitaire.StringUtil;

public class Utilisateur extends BaseModele{
	private String nom;
	private String prenom;
	private Date dateNaissance; 
	private String sexe;
	private String password;
	private String adresse;
	private String email;
	private List<Regime> regime;

	public Utilisateur() {}
	
	public Utilisateur(int id) {
		super(id);
	}

	public Utilisateur(int id, String nom, String prenom, Date dateNaissance, String sexe, String email, String password, String adresse) throws Exception {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaissance);
		this.setSexe(sexe);
		this.setEmail(email);
		this.setPassword(password);
		this.setAdresse(adresse);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws Exception{
		if(!StringUtil.getInstance().fullLetter(nom)) throw new Exception("Votre nom contient des caract\u00e8res sp\u00e9ciaux");
		else if(nom.isEmpty()) throw new Exception("Veuillez ins\u00e9rer un nom");
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws Exception {
		if(!StringUtil.getInstance().fullLetter(prenom)) throw new Exception("Votre pr\u00e9nom contient des caract\u00e8res sp\u00e9ciaux");
		this.prenom = prenom;
	}
	public String getFullName() {
		return getNom()+" "+getPrenom();
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	public String getDateNaissanceString(){
		return DateFormat.getDateInstance().format(getDateNaissance());
	}
	public String getFullDateNaissanceString(){
		String ne = "N\u00e9";
		if(isFemme()) ne+="e";
		return ne+" le "+getDateNaissanceString();
	}
	public int getAge(){
		return DateUtil.getInstance().getUntilNow(getDateNaissance())/365;
	}
	public String getAgeString(){
		return getAge() + " ans";
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) throws Exception{
		setDateNaissance(DateUtil.getInstance().stringToDate(dateNaissance));
	}
	public String getSexe() {
		return sexe;
	}
	public String getSexeString(){
		switch (getSexe()) {
		case "M":
			return "Homme";
		default:
			return "Femme";
		}
	}

	public void setSexe(String sexe) throws Exception {
		if(sexe.toUpperCase().startsWith("F")) {
			this.sexe = "F";
		}else if(sexe.toUpperCase().startsWith("M")||sexe.toUpperCase().startsWith("H")){
			this.sexe = "M";
		}
		else throw new Exception("Sexe incorrect");
	}
	public boolean isFemme(){
		return getSexe().startsWith("F");
	}
	public boolean isHomme(){
		return getSexe().startsWith("M");
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if(!StringUtil.getInstance().isEmail(email))throw new Exception("Votre email est invalide");
		this.email = email;
	}
	
	String getDefaultIdentifiant(){
		return getNom().toLowerCase().concat(getPrenom().toLowerCase()).replaceAll(" ", "");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws Exception {
		if(password.isEmpty())throw new Exception("Un mot de passe est requis");
		this.password = password;
	}
	public void setPassword(String password, String confirmpassword) throws Exception {
		if(!password.equals(confirmpassword)){
			throw new Exception("Veuillez reconfirmer votre mot de passe. Ils ne correspondent pas.");
		}
		setPassword(password);
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Regime> getRegime() {
		return regime;
	}
	public void setRegime(List<Regime> regime) {
		this.regime = regime;
	}
}
