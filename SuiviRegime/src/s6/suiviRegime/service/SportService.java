package s6.suiviRegime.service;

import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportConseil;

public class SportService {
	private HibernateDao dao;
	private SportService(){
		if(dao == null) dao = new HibernateDao();
	}
	private static class Holder
	{		
		private final static SportService instance = new SportService();
	}
	public static SportService getInstance(){
		return Holder.instance;
	}
	
	public Sport getSport(String sport) throws Exception{
		try{
			int id = Integer.parseInt(sport.trim());
			Sport s = new Sport(id);
			dao.findById(s);
			return s;
		}catch(NumberFormatException e){
			throw new Exception("Sport introuvable, valeur incorrecte");
		}
	}
	public SportConseil getSportConseil(String sportConseil) throws Exception{
		try{
			int id = Integer.parseInt(sportConseil.trim());
			SportConseil s = new SportConseil(id);
			dao.findById(s);
			return s;
		}catch(NumberFormatException e){
			throw new Exception("Conseil sport introuvable, valeur incorrecte");
		}
	}
	public void addSport(String libelle, String activite) throws Exception{
		Sport s = new Sport();
		s.setLibelle(libelle);
		s.setActivite(activite);
		dao.save(s);
	}
	public void addSportConseil(String sport, String details, String rythme) throws Exception{
		SportConseil s = new SportConseil();
		s.setSport(getSport(sport));
		s.setDetails(details);
		s.setRythme(rythme);
		dao.save(s);
	}
	
	public void updateSport(String id, String libelle, String activite) throws Exception{
		Sport s = getSport(id);
		s.setLibelle(libelle);
		s.setActivite(activite);
		dao.update(s);
	}
	public void updateSportConseil(String id, String sport, String details, String rythme) throws Exception{
		SportConseil s = getSportConseil(id);
		s.setSport(getSport(sport));
		s.setDetails(details);
		s.setRythme(rythme);
		dao.update(s);
	}
	
	public void deleteSport(String id) throws Exception{
		dao.delete(getSport(id));
	}
	public void deleteSportConseil(String id) throws Exception{
		dao.delete(getSportConseil(id));
	}
	
	public SportConseil getRandomTips(String id){
		SportConseil conseil = null;
		try{
			conseil = getSportConseil(id);
		}catch(Exception e){
			conseil = new SportConseil();
		}
		return (SportConseil)dao.getRandom(conseil);
	}
}