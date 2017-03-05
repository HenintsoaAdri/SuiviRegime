package s6.suiviRegime.modele;

import java.time.LocalDate;

public class Alimentation {
	int id;
	Regime regime;
	String repas;
	String boisson;
	int periode;
	LocalDate date;
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
	public String getRepas() {
		return repas;
	}
	public void setRepas(String repas) {
		this.repas = repas;
	}
	public String getBoisson() {
		return boisson;
	}
	public void setBoisson(String boisson) {
		this.boisson = boisson;
	}
	public int getPeriode() {
		return periode;
	}
	public void setPeriode(int periode) {
		this.periode = periode;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
