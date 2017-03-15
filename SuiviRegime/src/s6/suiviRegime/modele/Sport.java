package s6.suiviRegime.modele;

public class Sport extends BaseModele{
	String sport;
	String activite;
	
	public Sport() {}
	public Sport(int id, String sport, String activite) {
		super();
		this.setId(id);
		this.setSport(sport);
		this.setActivite(activite);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
}
