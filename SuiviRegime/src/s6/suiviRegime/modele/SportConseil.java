package s6.suiviRegime.modele;

public class SportConseil extends Sport {
	
	int idSport;
	float rythme;
	String details;
	
	public SportConseil(int id, String sport, String activite) {
		super(id, sport, activite);
	}
	
	public SportConseil(int id, String sport, String activite, int idSport, float rythme, String details) {
		super(id, sport, activite);
		this.setIdSport(idSport);
		this.setRythme(rythme);
		this.setDetails(details);
	}

	public int getIdSport() {
		return idSport;
	}

	public void setIdSport(int idSport) {
		this.idSport = idSport;
	}

	public float getRythme() {
		return rythme;
	}

	public void setRythme(float rythme) {
		this.rythme = rythme;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
