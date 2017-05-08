package s6.suiviRegime.service;
import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.Alimentation;
import s6.suiviRegime.modele.Poids;
import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.SportRegime;
import s6.suiviRegime.modele.Utilisateur;

public class RegimeService {
	private HibernateDao dao;
	private RegimeService(){
		if(dao == null) dao = new HibernateDao();
	}
	private static class Holder
	{		
		private final static RegimeService instance = new RegimeService();
	}
	public static RegimeService getInstance(){
		return Holder.instance;
	}
	
	public void addRegime(Utilisateur utilisateur, String debut, String fin, String poidsObjectif) throws Exception{
		Regime regime = new Regime();
		regime.setUtilisateur(utilisateur);
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsObjectif(poidsObjectif);
		dao.save(regime);
	}
	public void addRegime(String utilisateur, String debut, String fin, String poidsObjectif) throws Exception{
		addRegime(UtilisateurService.getInstance().getUtilisateur(utilisateur), debut, fin, poidsObjectif);
	}
	public void addPoids(Regime regime,String date, String poids) throws Exception{
		Poids p = new Poids();
		p.setRegime(regime);
		p.setDate(date);
		p.setValeur(poids);
		dao.save(p);
	}
	public void addPoids(String regime, String date, String poids) throws Exception{
		addPoids(getRegime(regime), date, poids);
	}
	public void addAlimentation(Regime regime, String date, String repas, String boisson, String periode) throws Exception {
		Alimentation a = new Alimentation();
		a.setBoisson(boisson);
		a.setRegime(regime);
		a.setDate(date);
		a.setPeriode(periode);
		a.setRepas(repas);
		dao.save(a);
	}
	public void addAlimentation(String regime, String date, String repas, String boisson, String periode) throws Exception {
		addAlimentation(getRegime(regime), date, repas, boisson, periode);
	}
	public void addSport(Regime regime, String date, String sport, String rythme) throws Exception{
		SportRegime s = new SportRegime();
		s.setRegime(regime);
		s.setDate(date);
		s.setSport(SportService.getInstance().getSport(sport));
		s.setRythme(rythme);
		dao.save(s);
	}
	public void addSport(String regime, String date, String sport, String rythme) throws Exception{
		addSport(getRegime(regime), date, sport, rythme);
	}
	
	public Regime getRegime(String regime) throws Exception{
		try{
			Regime model = new Regime(Integer.parseInt(regime.trim()));
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Regime introuvable, valeur incorrecte");
		}
	}
	public Poids getPoids(String poids) throws Exception{
		try{
			Poids model = new Poids(Integer.parseInt(poids.trim()));
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Poids introuvable, valeur incorrecte");
		}
	}
	public Alimentation getAlimentation(String alimentation) throws Exception{
		try{
			Alimentation model = new Alimentation(Integer.parseInt(alimentation.trim()));
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Alimentation introuvable, valeur incorrecte");
		}
	}
	public SportRegime getSportRegime(String sportRegime) throws Exception{
		try{
			SportRegime model = new SportRegime(Integer.parseInt(sportRegime.trim()));
			dao.findById(model);
			return model;
		}catch(NumberFormatException e){
			throw new Exception("Sport introuvable, valeur incorrecte");
		}
	}
	
	public void deleteRegime(String regime) throws Exception{
		dao.delete(getRegime(regime));
	}
	public void deletePoids(String poids) throws Exception{
		dao.delete(getPoids(poids));
	}
	public void deleteAlimentation(String alimentation) throws Exception{
		dao.delete(getAlimentation(alimentation));
	}
	public void deleteSportRegime(String sportRegime) throws Exception{
		dao.delete(getSportRegime(sportRegime));
	}

	public void updateRegime(String id, String utilisateur, String debut, String fin, String poidsObjectif) throws Exception{
		Regime regime = getRegime(id);
		regime.setUtilisateur(UtilisateurService.getInstance().getUtilisateur(utilisateur));
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsObjectif(poidsObjectif);
		dao.update(regime);
	}
	public void updatePoids(String id, String regime,String date, String poids) throws Exception{
		Poids p = getPoids(id);
		p.setRegime(getRegime(regime));
		p.setDate(date);
		p.setValeur(poids);
		dao.update(p);
	}
	public void updateAlimentation(String id, String regime, String date, String repas, String boisson, String periode) throws Exception {
		Alimentation a = getAlimentation(id);
		a.setBoisson(boisson);
		a.setRegime(getRegime(regime));
		a.setDate(date);
		a.setPeriode(periode);
		a.setRepas(repas);
		dao.save(a);
	}
	public void updateSport(String id, String regime, String date, String sport, String rythme) throws Exception{
		SportRegime s = new SportRegime();
		s.setRegime(getRegime(regime));
		s.setDate(date);
		s.setSport(SportService.getInstance().getSport(sport));
		s.setRythme(rythme);
		dao.update(s);
	}
}