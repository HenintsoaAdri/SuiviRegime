package s6.suiviRegime.service;
import s6.suiviRegime.modele.AlimentationConseil;

public class MultiService {
	private BaseService service;
	
	private MultiService(){}
	private static class Holder
	{		
		private final static MultiService instance = new MultiService();
	}
	public static MultiService getInstance(){
		return Holder.instance;
	}
	
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}

	public void addAlimentationConseil(String nom, String matin, String midi, String soir) throws Exception{
		AlimentationConseil model = new AlimentationConseil();
		model.setNom(nom);
		model.setMatin(matin);
		model.setMidi(midi);
		model.setSoir(soir);
		service.save(model);
	}
	public AlimentationConseil getAlimentationConseil(String alimentation) throws Exception{
		try{
			return (AlimentationConseil)service.get(alimentation, new AlimentationConseil());
		}catch(Exception e){
			throw new Exception("Conseil d'alimentation introuvable, valeur incorrecte");
		}
	}
	public void updateAlimentationConseil(String id, String nom, String matin, String midi, String soir) throws Exception{
		AlimentationConseil model = getAlimentationConseil(id);
		model.setNom(nom);
		model.setMatin(matin);
		model.setMidi(midi);
		model.setSoir(soir);
		service.update(model);
	}	
	public void deleteAlimentationConseil(String id) throws Exception{
		service.delete(id, new AlimentationConseil());
	}
	
	public AlimentationConseil getRandomTips(){
		return (AlimentationConseil)service.getRandom(new AlimentationConseil());
	}
}
