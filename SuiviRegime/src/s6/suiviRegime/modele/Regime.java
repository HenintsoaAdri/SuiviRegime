package s6.suiviRegime.modele;

import java.util.Date;
import java.util.List;

public class Regime {
	int id;
	Utilisateur utilisateur;
	Date debut;
	Date fin;
	float poidsObjectif;
	List<Poids> poids;
	List<Alimentation> alimentation;
	List<SportRegime> sport;
	
	public Regime(){}
	public Regime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif) {
		super();
		this.setId(id);
		this.setUtilisateur(utilisateur);
		this.setDebut(debut);
		this.setFin(fin);
		this.setPoidsObjectif(poidsObjectif);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public float getPoidsObjectif() {
		return poidsObjectif;
	}
	public void setPoidsObjectif(float poidsObjectif) {
		this.poidsObjectif = poidsObjectif;
	}
	
}
