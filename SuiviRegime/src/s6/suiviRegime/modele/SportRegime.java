package s6.suiviRegime.modele;

import java.time.LocalDate;

public class SportRegime extends Sport {
	
	Regime regime;
	LocalDate date;
	float rythme;
	
	public SportRegime(int id, String sport, String activite) {
		super(id, sport, activite);
	}
	
	public SportRegime(int id, String sport, String activite, Regime regime, LocalDate date, float rythme) {
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public float getRythme() {
		return rythme;
	}
	public void setRythme(float rythme) {
		this.rythme = rythme;
	}
}