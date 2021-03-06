package s6.suiviRegime.modele;

import java.util.Date;

public class AnalyseUtilisateur extends Utilisateur {
	private int regimeTotal;
	
	public AnalyseUtilisateur() {
		super();
	}
	public AnalyseUtilisateur(int id, String nom, String prenom, Date dateNaissance, String sexe, String email,
			String password, String adresse) throws Exception {
		super(id, nom, prenom, dateNaissance, sexe, email, password, adresse);
	}
	public AnalyseUtilisateur(int id) {
		super(id);
	}
	
	public AnalyseUtilisateur(int id, String nom, String prenom, Date dateNaissance, String sexe, String email,
			String password, String adresse, int regimeTotal) throws Exception {
		super(id, nom, prenom, dateNaissance, sexe, email, password, adresse);
		setRegimeTotal(regimeTotal);
	}
	public int getRegimeTotal() {
		return regimeTotal;
	}
	public void setRegimeTotal(int regimeTotal) {
		this.regimeTotal = regimeTotal;
	}
	
}
