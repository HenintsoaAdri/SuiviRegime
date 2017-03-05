package s6.suiviRegime.modele;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import s6.suiviRegime.utilitaire.StringUtil;

public class Utilisateur {
	int id;
	String nom;
	String prenom;
	LocalDate dateNaissance; 
	String sexe;
	String identifiant="";
	String password;
	String adresse;
	String email;
	List<Regime> regime;

	public Utilisateur() {}

	public Utilisateur(int id, String nom, String prenom, LocalDate dateNaissance, String sexe, String email, String identifiant, String password, String adresse) throws Exception {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaissance);
		this.setSexe(sexe);
		this.setEmail(email);
		this.setIdentifiant(identifiant);
		this.setPassword(password);
		this.setAdresse(adresse);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws Exception{
		if(!StringUtil.fullLetter(nom)) throw new Exception("Votre nom contient des caract\u00e8res sp\u00e9ciaux");
		else if(nom.isEmpty()) throw new Exception("Veuillez ins\u00e9rer un nom");
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws Exception {
		if(!StringUtil.fullLetter(prenom)) throw new Exception("Votre pr\u00e9nom contient des caract\u00e8res sp\u00e9ciaux");
		this.prenom = prenom;
	}
	public String getFullName() {
		return getNom()+" "+getPrenom();
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public String getDateNaissanceString(){
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(getDateNaissance());
	}
	public String getFullDateNaissanceString(){
		String ne = "N\u00e9";
		if(isFemme()) ne+="e";
		return ne+" le "+getDateNaissanceString();
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) throws Exception{
		try{
			setDateNaissance(LocalDate.parse(dateNaissance));
		} catch (DateTimeParseException e) {
			try{
				DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMMM, uuuu").toFormatter(Locale.FRANCE);
				setDateNaissance(LocalDate.parse(dateNaissance, format));
			}catch(Exception e1){
				e1.printStackTrace();
				throw new Exception("Format de date non support\u00e9");				
			}
		}
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

	public void setSexe(String sexe) {
		if(sexe.startsWith("F")||sexe.startsWith("f")) {
			this.sexe = "F";
		}else{
			this.sexe = "M";
		}
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
		if(!StringUtil.isEmail(email))throw new Exception("Votre email est invalide");
		this.email = email;
	}
	
	public String getIdentifiant() {
		if(identifiant.isEmpty()) return getDefaultIdentifiant();
		return identifiant;
	}
	public String getIdentifiantString(){
		return "@"+getIdentifiant();
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
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
		if(password.compareTo(confirmpassword)!=0){
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
