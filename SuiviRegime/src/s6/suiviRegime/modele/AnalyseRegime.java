package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;
import s6.suiviRegime.utilitaire.StringUtil;

public class AnalyseRegime extends Regime{
	
	private int minuteSportTotal;
	private float poidsTotalPerdu;
	private int jourRestant;
	private int jourPasse;
	
	
	public AnalyseRegime() {
		super();
		setClosed(false);
	}
	public AnalyseRegime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial, boolean closed) {
		super(id, utilisateur, debut, fin, poidsObjectif, poidsInitial);
	}
	public AnalyseRegime(int id) {
		super(id);
		setClosed(false);
	}

	public AnalyseRegime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial,
			int minuteSportTotal, float poidsTotalPerdu, boolean closed) {
		super(id, utilisateur, debut, fin, poidsObjectif, poidsInitial);
		
		setMinuteSportTotal(minuteSportTotal);
		setPoidsTotalPerdu(poidsTotalPerdu);
	}
	
	@Override
	public void setDebut(Date debut) {
		super.setDebut(debut);
		setJourPasse(DateUtil.getInstance().getUntilNow(debut));
	}
	@Override
	public void setFin(Date fin) {
		super.setFin(fin);
		setJourRestant(DateUtil.getInstance().getBetweenNow(fin));
	}
	
	public int getMinuteSportTotal() {
		return minuteSportTotal;
	}
	public void setMinuteSportTotal(int minuteSportTotal) {
		this.minuteSportTotal = minuteSportTotal;
	}
	public float getMinuteSportParSemaine(){
		return getMinuteSportTotal()/(getJourPasse()/7f);
	}
	public String getMinuteSportParSemaineString() {
		return StringUtil.getInstance().formatFloat(getMinuteSportParSemaine()) + " minutes";
	}
	
	public float getPoidsTotalPerdu() {
		return poidsTotalPerdu;
	}
	public void setPoidsTotalPerdu(float poidsTotalPerdu) {
		this.poidsTotalPerdu = poidsTotalPerdu;
	}
	
	public float getPoidsPerduParSemaine(){
		return getPoidsTotalPerdu()/(getJourPasse()/7f);
	}
	public String getPoidsPerduParSemaineString(){
		return StringUtil.getInstance().formatFloat(getPoidsPerduParSemaine()) + " kg";
	}
	
	public int getJourRestant() {
		return jourRestant;
	}
	public void setJourRestant(int jourRestant) {
		this.jourRestant = jourRestant;
	}

	public int getJourPasse() {
		return jourPasse;
	}
	public void setJourPasse(int jourPasse) {
		this.jourPasse = jourPasse;
	}
}
