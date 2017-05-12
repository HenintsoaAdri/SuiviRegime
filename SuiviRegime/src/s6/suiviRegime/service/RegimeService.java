package s6.suiviRegime.service;

import s6.suiviRegime.modele.Alimentation;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.Poids;
import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.SportRegime;
import s6.suiviRegime.modele.Utilisateur;

public class RegimeService {
	private BaseService service;
	
	private RegimeService(){}
	private static class Holder
	{		
		private final static RegimeService instance = new RegimeService();
	}
	public static RegimeService getInstance(){
		return Holder.instance;
	}
	
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}

	public void addRegime(Utilisateur utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		Regime regime = new Regime();
		regime.setUtilisateur(utilisateur);
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsObjectif(poidsObjectif);
		regime.setPoidsInitial(poidsInitial);
		service.save(regime);
	}
	public void addRegime(String utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		addRegime(UtilisateurService.getInstance().getUtilisateur(utilisateur), debut, fin, poidsObjectif, poidsInitial);
	}
	public void addPoids(Regime regime,String date, String poids) throws Exception{
		Poids p = new Poids();
		p.setRegime(regime);
		p.setDate(date);
		p.setValeur(poids);
		service.save(p);
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
		service.save(a);
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
		service.save(s);
	}
	public void addSport(String regime, String date, String sport, String rythme) throws Exception{
		addSport(getRegime(regime), date, sport, rythme);
	}
	
	public Regime getRegime(String regime) throws Exception{
		try{
			return (Regime) service.get(regime, new Regime());
		}catch(Exception e){
			throw new Exception("Regime introuvable, valeur incorrecte");
		}
	}
	public AnalyseRegime findUnclosed(Utilisateur model) throws Exception{
		return getService().getDao().findUnclosedRegime(model);
	}
	public Poids getPoids(String poids) throws Exception{
		try{
			return (Poids) service.get(poids, new Poids());
		}catch(Exception e){
			throw new Exception("Poids introuvable, valeur incorrecte");
		}
	}
	public Alimentation getAlimentation(String alimentation) throws Exception{
		try{
			return (Alimentation) service.get(alimentation, new Alimentation());
		}catch(Exception e){
			throw new Exception("Alimentation introuvable, valeur incorrecte");
		}
	}
	public SportRegime getSportRegime(String sportRegime) throws Exception{
		try{
			return (SportRegime) service.get(sportRegime, new SportRegime());
		}catch(Exception e){
			throw new Exception("Sport introuvable, valeur incorrecte");
		}
	}
	
	public void deleteRegime(String regime) throws Exception{
		service.delete(regime, new Regime());
	}
	public void deletePoids(String poids) throws Exception{
		service.delete(poids, new Poids());
	}
	public void deleteAlimentation(String alimentation) throws Exception{
		service.delete(alimentation, new Alimentation());
	}
	public void deleteSportRegime(String sportRegime) throws Exception{
		service.delete(sportRegime, new SportRegime());
	}

	public void updateRegime(String id, String utilisateur, String debut, String fin, String poidsObjectif) throws Exception{
		Regime regime = getRegime(id);
		regime.setUtilisateur(UtilisateurService.getInstance().getUtilisateur(utilisateur));
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsObjectif(poidsObjectif);
		service.update(regime);
	}
	public void updatePoids(String id, String regime,String date, String poids) throws Exception{
		Poids p = getPoids(id);
		p.setRegime(getRegime(regime));
		p.setDate(date);
		p.setValeur(poids);
		service.update(p);
	}
	public void updateAlimentation(String id, String regime, String date, String repas, String boisson, String periode) throws Exception {
		Alimentation a = getAlimentation(id);
		a.setBoisson(boisson);
		a.setRegime(getRegime(regime));
		a.setDate(date);
		a.setPeriode(periode);
		a.setRepas(repas);
		service.save(a);
	}
	public void updateSport(String id, String regime, String date, String sport, String rythme) throws Exception{
		SportRegime s = new SportRegime();
		s.setRegime(getRegime(regime));
		s.setDate(date);
		s.setSport(SportService.getInstance().getSport(sport));
		s.setRythme(rythme);
		service.update(s);
	}
}
