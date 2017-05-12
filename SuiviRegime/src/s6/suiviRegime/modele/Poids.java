package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;

public class Poids extends BaseModele{
	private Regime regime;
	private Date date;
	private float valeur;
	
	public Poids() {}
	
	public Poids(int id) {
		super(id);
	}

	public Poids(int id, Regime regime, Date date, float valeur) {
		super();
		this.setId(id);
		this.setRegime(regime);
		this.setDate(date);
		this.setValeur(valeur);
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
	public void setDate(String date) throws Exception {
		if(date == null || date.isEmpty()) setDate(new Date());
		else setDate(DateUtil.getInstance().stringToDate(date));
	}
	
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	public void setValeur(String valeur) throws Exception{
		try{
			valeur = valeur.replace(',', '.');
			float poidsO = Float.parseFloat(valeur.trim());
			setValeur(poidsO);
			
		}catch(NumberFormatException e){
			throw new Exception("Valeur de poids incorrect");
		}
	}
	
}
