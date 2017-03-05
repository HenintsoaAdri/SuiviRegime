package s6.suiviRegime.modele;

import java.time.LocalDate;

public class Poids {
	int id;
	Regime regime;
	LocalDate date;
	float poids;
		
	public Poids(int id, Regime regime, LocalDate date, float poids) {
		super();
		this.setId(id);
		this.setRegime(regime);
		this.setDate(date);
		this.setPoids(poids);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
		this.poids = poids;
	}
	
}
