package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;

public class Alimentation extends BaseModele{
	private Regime regime;
	private String repas;
	private String boisson;
	private int periode;
	private Date date;
	
	public Alimentation() {}
	
	public Alimentation(int id) {
		super(id);
	}

	public Alimentation(int id, Regime regime, String repas, String boisson, int periode, Date date) {
		super();
		this.setId(id);
		this.setRegime(regime);
		this.setRepas(repas);
		this.setBoisson(boisson);
		this.setPeriode(periode);
		this.setDate(date);
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
	public void setPeriode(String periode) throws Exception{
		try{
			setPeriode(Integer.parseInt(periode));
		}catch(NumberFormatException e){
			throw new Exception("Période d'alimentation invalide");
		}
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String date) throws Exception {
		if(date == null || date.isEmpty()) setDate(new Date());
		else setDate(DateUtil.getInstance().stringToDate(date));
	}
}
