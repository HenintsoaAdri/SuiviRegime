package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;
import s6.suiviRegime.utilitaire.StringUtil;

public class AnalyseRegime extends Regime{
	
	private int minuteSportTotal;
	private float poidsTotalPerdu;
	private int jourRestant;
	private int jourPasse;
	private int idutilisateur;
	
	
	public AnalyseRegime() {
		super();
		setActive(true);
	}
	public AnalyseRegime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial, boolean active) throws Exception {
		super(id, utilisateur, debut, fin, poidsObjectif, poidsInitial,active);
	}
	public AnalyseRegime(int id) {
		super(id);
		setActive(true);
	}

	public AnalyseRegime(int id, Utilisateur utilisateur, Date debut, Date fin, float poidsObjectif, float poidsInitial,
			int minuteSportTotal, float poidsTotalPerdu, boolean active) throws Exception {
		super(id, utilisateur, debut, fin, poidsObjectif, poidsInitial, active);
		
		setMinuteSportTotal(minuteSportTotal);
		setPoidsTotalPerdu(poidsTotalPerdu);
	}
	
	@Override
	public void setDebut(Date debut) throws Exception {
		super.setDebut(debut);
		setJourPasse(DateUtil.getInstance().getUntilNow(debut));
	}
	@Override
	public void setFin(Date fin) throws Exception {
		super.setFin(fin);
		setJourRestant(DateUtil.getInstance().getBetweenNow(getDebut(), fin));
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
		return StringUtil.getInstance().formatFloatSign(-getPoidsPerduParSemaine()) + " kg";
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
	public Regime getRegime() throws Exception{
		return new Regime(this.getId(),
				this.getUtilisateur(),
				this.getDebut(),
				this.getFin(),
				this.getPoidsObjectif(),
				this.getPoidsInitial(),
				this.isActive());
	}
	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
}
