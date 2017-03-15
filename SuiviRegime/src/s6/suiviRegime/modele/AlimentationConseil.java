package s6.suiviRegime.modele;

public class AlimentationConseil extends BaseModele{
	String nom;
	String matin;
	String midi;
	String soir;
	
	
	public AlimentationConseil() {}
	public AlimentationConseil(int id, String nom, String matin, String midi, String soir) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setMatin(matin);
		this.setMidi(midi);
		this.setSoir(soir);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getMatin() {
		return matin;
	}
	public void setMatin(String matin) {
		this.matin = matin;
	}
	
	public String getMidi() {
		return midi;
	}
	public void setMidi(String midi) {
		this.midi = midi;
	}
	
	public String getSoir() {
		return soir;
	}
	public void setSoir(String soir) {
		this.soir = soir;
	}
}
