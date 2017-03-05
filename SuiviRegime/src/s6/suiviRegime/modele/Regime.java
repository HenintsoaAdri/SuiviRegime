package s6.suiviRegime.modele;

import java.time.LocalDate;
import java.util.List;

public class Regime {
	int id;
	Utilisateur utilisateur;
	LocalDate debut;
	LocalDate fin;
	float poidsObjectif;
	List<Poids> poids;
	List<Alimentation> alimentation;
	List<SportRegime> sport;
	
	public Regime(){}
	
	public Regime(int id, Utilisateur utilisateur, LocalDate debut, LocalDate fin, float poidsObjectif) {
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
	public LocalDate getDebut() {
		return debut;
	}
	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}
	public LocalDate getFin() {
		return fin;
	}
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	public float getPoidsObjectif() {
		return poidsObjectif;
	}
	public void setPoidsObjectif(float poidsObjectif) {
		this.poidsObjectif = poidsObjectif;
	}
	
}
