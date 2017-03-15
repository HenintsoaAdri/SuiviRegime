package s6.suiviRegime.modele;

import java.util.Date;

public class Poids extends BaseModele{
	Regime regime;
	Date date;
	float poids;
	
	public Poids() {}
	public Poids(int id, Regime regime, Date date, float poids) {
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
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
		this.poids = poids;
	}
	
}
