package s6.suiviRegime.service;

import java.util.List;
import java.util.Vector;

import s6.suiviRegime.modele.BaseModele;
import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportConseil;

public class SportService {
	private BaseService service;
	private SportService(){}
	private static class Holder
	{		
		private final static SportService instance = new SportService();
	}
	public static SportService getInstance(){
		return Holder.instance;
	}
	
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}

	public Sport getSport(String sport) throws Exception{
		try{
			return (Sport)service.get(sport, new Sport());
		}catch(Exception e){
			throw new Exception("Sport introuvable, valeur incorrecte");
		}
	}
	public SportConseil getSportConseil(String sportConseil) throws Exception{
		try{
			return (SportConseil)service.get(sportConseil, new Sport());
		}catch(Exception e){
			throw new Exception("Conseil sport introuvable, valeur incorrecte");
		}
	}
	public void addSport(String libelle, String activite) throws Exception{
		Sport s = new Sport();
		s.setLibelle(libelle);
		s.setActivite(activite);
		service.save(s);
	}
	public void addSportConseil(String sport, String details, String rythme) throws Exception{
		SportConseil s = new SportConseil();
		s.setSport(getSport(sport));
		s.setDetails(details);
		s.setRythme(rythme);
		service.save(s);
	}
	
	public void updateSport(String id, String libelle, String activite) throws Exception{
		Sport s = getSport(id);
		s.setLibelle(libelle);
		s.setActivite(activite);
		service.update(s);
	}
	public void updateSportConseil(String id, String sport, String details, String rythme) throws Exception{
		SportConseil s = getSportConseil(id);
		s.setSport(getSport(sport));
		s.setDetails(details);
		s.setRythme(rythme);
		service.update(s);
	}
	
	public void deleteSport(String id) throws Exception{
		service.delete(id, new Sport());
	}
	public void deleteSportConseil(String id) throws Exception{
		service.delete(id, new SportConseil());
	}
	
	public SportConseil getRandomTips(String id){
		SportConseil conseil = null;
		try{
			conseil = getSportConseil(id);
		}catch(Exception e){
			conseil = new SportConseil();
		}
		return (SportConseil)service.getRandom(conseil);
	}
	public List<Sport> findAll() throws Exception {
		List<BaseModele> liste = service.findAll(Sport.class);
		List<Sport> listeSport = new Vector<Sport>();
		for(BaseModele m : liste){
			listeSport.add((Sport)m);
		}
		return listeSport;
	}
}
