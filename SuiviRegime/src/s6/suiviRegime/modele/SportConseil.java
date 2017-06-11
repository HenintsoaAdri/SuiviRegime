package s6.suiviRegime.modele;

public class SportConseil extends BaseModele{
	
	private Sport sport;
	private int rythme;
	private String details;
	
	public SportConseil() {}
	
	public SportConseil(int id) {
		super(id);
	}

	public SportConseil(int id, int idSport, String libelle, String activite, int rythme, String details) {
		this.setId(id);
		this.setSport(new Sport(idSport, libelle, activite));
		this.setRythme(rythme);
		this.setDetails(details);
	}
	public SportConseil(int id, Sport sport, int rythme, String details) {
		this.setId(id);
		this.setSport(sport);
		this.setRythme(rythme);
		this.setDetails(details);
	}
	
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public int getRythme() {
		return rythme;
	}
	public void setRythme(int rythme) {
		this.rythme = rythme;
	}
	public void setRythme(String rythme)throws Exception{
		try {
			setRythme(Integer.parseInt(rythme));
		} catch (NumberFormatException e) {
			throw new Exception("Valeur de rythme invalide");
		} 
	}

	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
