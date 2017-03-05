package s6.suiviRegime.modele;

public class AlimentationConseil {
	int id;
	String matin;
	String midi;
	String soir;
	String nom;
		
	public AlimentationConseil(int id, String matin, String midi, String soir, String nom) {
		super();
		this.id = id;
		this.matin = matin;
		this.midi = midi;
		this.soir = soir;
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}