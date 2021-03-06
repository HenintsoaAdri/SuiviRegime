package s6.suiviRegime.modele;


public class AnalyseSport extends Sport {
	private int conseilTotal;
	
	public AnalyseSport() {
		super();
	}
	
	public AnalyseSport(int id, String libelle, String activite) {
		super(id, libelle, activite);
	}

	public AnalyseSport(int id) {
		super(id);
	}
	
	public AnalyseSport(int id, String libelle, String activite, int conseilTotal) {
		super(id, libelle, activite);
		this.setConseilTotal(conseilTotal);
	}

	public int getConseilTotal() {
		return conseilTotal;
	}
	public void setConseilTotal(int conseilTotal) {
		this.conseilTotal = conseilTotal;
	}
	
}
