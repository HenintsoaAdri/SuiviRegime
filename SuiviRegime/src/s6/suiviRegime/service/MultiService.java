package s6.suiviRegime.service;
import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.AlimentationConseil;

public class MultiService {
	private HibernateDao dao;
	private MultiService(){
		if(dao == null) dao = new HibernateDao();
	}
	private static class Holder
	{		
		private final static MultiService instance = new MultiService();
	}
	public static MultiService getInstance(){
		return Holder.instance;
	}
	public void addAlimentationConseil(String nom, String matin, String midi, String soir) throws Exception{
		AlimentationConseil model = new AlimentationConseil();
		model.setNom(nom);
		model.setMatin(matin);
		model.setMidi(midi);
		model.setSoir(soir);
		dao.save(model);
	}
	public AlimentationConseil getAlimentationConseil(String alimentation) throws Exception{
		try{
			int id = Integer.parseInt(alimentation.trim());
			AlimentationConseil model = new AlimentationConseil(id);
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Conseil d'alimentation introuvable, valeur incorrecte");
		}
	}
	public void updateAlimentationConseil(String id, String nom, String matin, String midi, String soir) throws Exception{
		AlimentationConseil model = getAlimentationConseil(id);
		model.setNom(nom);
		model.setMatin(matin);
		model.setMidi(midi);
		model.setSoir(soir);
		dao.update(model);
	}	
	public void deleteAlimentationConseil(String id) throws Exception{
		dao.delete(getAlimentationConseil(id));
	}
	
	public AlimentationConseil getRandomTips(){
		return (AlimentationConseil)dao.getRandom(new AlimentationConseil());
	}
}
