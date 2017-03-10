package s6.suiviRegime.modele;

import java.util.Date;

public class SportRegime extends Sport {
	
	Regime regime;
	Date date;
	float rythme;
	
	public SportRegime(int id, String sport, String activite) {
		super(id, sport, activite);
	}
	
	public SportRegime(int id, String sport, String activite, Regime regime, Date date, float rythme) {
		super(id, sport, activite);
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
