package s6.suiviRegime.modele;

import java.util.Date;

public class SportRegime{
	
	Regime regime;
	Sport sport;
	Date date;
	float rythme;
	
	
	public SportRegime() {}
	
	public SportRegime(int idSport, String libelle, String activite, Regime regime, Date date, float rythme) {
		this.setSport(new Sport(idSport, libelle, activite));
		this.setRegime(regime);
		this.setDate(date);
		this.setRythme(rythme);
	}
	public SportRegime(Sport sport, Regime regime, Date date, float rythme) {
		this.setSport(sport);
		this.setRegime(regime);
		this.setDate(date);
		this.setRythme(rythme);
	}
	
	public Regime getRegime() {
		return regime;
	}
	public void setRegime(Regime regime) {
		this.regime = regime;
	}
	
	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getRythme() {
		return rythme;
	}
	public void setRythme(float rythme) {
		this.rythme = rythme;
	}
}
