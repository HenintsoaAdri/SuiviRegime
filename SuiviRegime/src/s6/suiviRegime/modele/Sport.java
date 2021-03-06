package s6.suiviRegime.modele;

import java.util.List;

public class Sport extends BaseModele{
	private String libelle;
	private String activite;
	private List<SportConseil> conseils;
	private List<SportRegime> regime;
	
	public Sport() {}
	
	public Sport(int id) {
		super(id);
	}

	public Sport(int id, String libelle, String activite) {
		super();
		this.setId(id);
		this.setLibelle(libelle);
		this.setActivite(activite);
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getActivite() {
		return activite;
	}
	public void setActivite(String activite) {
		this.activite = activite;
	}
	
	public List<SportConseil> getConseils() {
		return conseils;
	}
	public void setConseils(List<SportConseil> conseils) {
		this.conseils = conseils;
	}
	
	public List<SportRegime> getRegime() {
		return regime;
	}
	public void setRegime(List<SportRegime> regime) {
		this.regime = regime;
	}

	@Override
	public String toString() {
		return "[" + libelle + ", " + activite + "]";
	}
	
}
