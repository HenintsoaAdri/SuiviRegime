package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;

public class SportRegime extends BaseModele{
	
	private Regime regime;
	private Sport sport;
	private Date date;
	private int rythme;
	
	public SportRegime() {}
	public SportRegime(int id) {
		super(id);
	}
	public SportRegime(int id, Regime regime, Sport sport, Date date, int rythme) throws Exception {
		this.setId(id);
		this.setSport(sport);
		this.setRegime(regime);
		this.setDate(date);
		this.setRythme(rythme);
	}

	public SportRegime(int id, int idSport, String libelle, String activite, Regime regime, Date date, int rythme) throws Exception {
		this.setId(id);
		this.setSport(new Sport(idSport, libelle, activite));
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
	public String getDateString(){
		return DateUtil.getInstance().DateToString(getDate());
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String date) throws Exception {
		if(date == null || date.isEmpty()) setDate(new Date());
		else setDate(DateUtil.getInstance().stringToDate(date));
	}
	
	public int getRythme() {
		return rythme;
	}
	public void setRythme(int rythme) throws Exception {
		if(rythme < 1) throw new Exception("Valeur de rythme invalide");
		this.rythme = rythme;
	}
	public void setRythme(String rythme)throws Exception{
		try {
			setRythme(Integer.parseInt(rythme));
		} catch (NumberFormatException e) {
			throw new Exception("Valeur de rythme invalide");
		} 
	}
}
