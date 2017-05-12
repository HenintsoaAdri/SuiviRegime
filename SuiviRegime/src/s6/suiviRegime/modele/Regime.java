package s6.suiviRegime.modele;

import java.util.Date;
import java.util.List;

import s6.suiviRegime.utilitaire.DateUtil;

public class Regime extends BaseModele{
	private Utilisateur utilisateur;
	private Date debut;
	private Date fin;
	private float poidsObjectif;
	private float poidsInitial;
	private List<Poids> poids;
	private List<Alimentation> alimentation;
	private List<SportRegime> sport;
	private boolean closed;
	
	public Regime(){}
	
	public Regime(int id) {
		super(id);
	}

	public Regime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial) {
		super(id);
		this.setUtilisateur(utilisateur);
		this.setDebut(debut);
		this.setFin(fin);
		this.setPoidsObjectif(poidsObjectif);
		this.setPoidsInitial(poidsInitial);
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
	public void setDebut(String date) throws Exception {
		setDebut(DateUtil.getInstance().stringToDate(date));
	}
	
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public void setFin(String date) throws Exception {
		setFin(DateUtil.getInstance().stringToDate(date));
	}
	
	public float getPoidsInitial() {
		return poidsInitial;
	}
	public void setPoidsInitial(float poidsInitial) {
		this.poidsInitial = poidsInitial;
	}
	public void setPoidsInitial(String poidsInitial) throws Exception{
		try{
			poidsInitial = poidsInitial.replace(',', '.');
			float poids = Float.parseFloat(poidsInitial.trim());
			setPoidsInitial(poids);
			
		}catch(NumberFormatException e){
			throw new Exception("Poids initial incorrect");
		}
	}

	public float getPoidsObjectif() {
		return poidsObjectif;
	}
	public void setPoidsObjectif(float poidsObjectif) {
		this.poidsObjectif = poidsObjectif;
	}
	public void setPoidsObjectif(String poidsObjectif) throws Exception{
		try{
			poidsObjectif = poidsObjectif.replace(',', '.');
			float poids = Float.parseFloat(poidsObjectif.trim());
			setPoidsObjectif(poids);
			
		}catch(NumberFormatException e){
			throw new Exception("Poids objectif incorrect");
		}
	}

	public List<Poids> getPoids() {
		return poids;
	}

	public void setPoids(List<Poids> poids) {
		this.poids = poids;
	}

	public List<Alimentation> getAlimentation() {
		return alimentation;
	}

	public void setAlimentation(List<Alimentation> alimentation) {
		this.alimentation = alimentation;
	}

	public List<SportRegime> getSport() {
		return sport;
	}

	public void setSport(List<SportRegime> sport) {
		this.sport = sport;
	}

	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
}
