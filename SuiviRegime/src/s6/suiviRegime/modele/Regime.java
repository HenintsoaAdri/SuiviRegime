package s6.suiviRegime.modele;

import java.util.Date;
import java.util.List;

import s6.suiviRegime.utilitaire.DateUtil;
import s6.suiviRegime.utilitaire.StringUtil;

public class Regime extends BaseModele{
	private Utilisateur utilisateur;
	private Date debut;
	private Date fin;
	private float poidsObjectif;
	private float poidsInitial;
	private List<Poids> poids;
	private List<Alimentation> alimentation;
	private List<SportRegime> sport;
	private boolean active = false;
	
	public Regime(){}
	
	public Regime(int id) {
		super(id);
	}

	public Regime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial, boolean active) throws Exception {
		super(id);
		this.setUtilisateur(utilisateur);
		this.setDebut(debut);
		this.setFin(fin);
		this.setPoidsObjectif(poidsObjectif);
		this.setPoidsInitial(poidsInitial);
		this.setActive(active);
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
	public String getDebutString(){
		return DateUtil.getInstance().DateToString(getDebut());
	}
	public void setDebut(Date debut) throws Exception {
		if(debut == null) throw new Exception("Une date de d�but doit etre sp�cifi�e");
		this.debut = debut;
	}
	public void setDebut(String date) throws Exception {
		setDebut(DateUtil.getInstance().stringToDate(date));
	}
	
	public Date getFin() {
		return fin;
	}
	public String getFinString(){
		return DateUtil.getInstance().DateToString(getFin());
	}
	public void setFin(Date fin) throws Exception {
		if(fin == null) throw new Exception("Une date de fin doit etre sp�cifi�e");
		this.fin = fin;
	}
	public void setFin(String date) throws Exception {
		setFin(DateUtil.getInstance().stringToDate(date));
	}
	
	public float getPoidsInitial() {
		return poidsInitial;
	}
	public String getPoidsInitialString() {
		return StringUtil.getInstance().formatFloat(getPoidsInitial()) + " kg";
	}
	public void setPoidsInitial(float poidsInitial) throws Exception{
		if(poidsInitial<30) throw new Exception("Vous ne pouvez suivre un r�gime avec un poids en dessous de 30 kg");
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
	public String getPoidsObjectifString() {
		return StringUtil.getInstance().formatFloat(getPoidsObjectif()) + " kg";
	}
	public void setPoidsObjectif(float poidsObjectif) throws Exception{
		if(poidsObjectif<30) throw new Exception("Votre objectif est un poids inapropri�");
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

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
