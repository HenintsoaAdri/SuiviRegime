package s6.suiviRegime.service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import s6.suiviRegime.modele.Alimentation;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.BaseModele;
import s6.suiviRegime.modele.BaseModelePagination;
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
	public void addDetailRegime(Regime regime,BaseModele model) throws Exception{		
		if(!regime.isActive()) throw new Exception("Ce r�gime est inactif, veuillez l'activer pour y enregister un poids");
		Date now = new Date();
		if(regime.getDebut().after(now)) throw new Exception("Le d�but du r�gime n'a pas encore commenc�");
		Method getDate = model.getClass().getMethod("getDate");
		Date date = (Date)getDate.invoke(model);
		if(date.before(regime.getDebut())){
			regime.setDebut(date);
			getService().update(((AnalyseRegime)regime).getRegime());
		} else if(date.after(now)){
			throw new Exception("Ce jour n'est pas encore arriv� !");
		}
		Method setRegime = model.getClass().getMethod("setRegime", Regime.class);
		setRegime.invoke(model, regime);
		getService().save(model);
	}
	public void addRegime(Utilisateur utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		Regime regime = new Regime();
		regime.setUtilisateur(utilisateur);
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsInitial(poidsInitial);
		regime.setPoidsObjectif(poidsObjectif);
		getService().save(regime);
	}
	public void addRegime(String utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		addRegime(UtilisateurService.getInstance().getUtilisateur(utilisateur), debut, fin, poidsObjectif, poidsInitial);
	}
	public void addPoids(Regime regime,String date, String poids) throws Exception{		
		
		Poids p = new Poids();
		p.setDate(date);
		p.setValeur(poids);
		if(p.getDate().equals(regime.getDebut())){
			throw new Exception("Plus besoin de rajouter le poids � la date du d�but du r�gime! Ca correspond au poids initial! Remplacez la date sinon!");
		}
		addDetailRegime(regime, p);
	}
	public void addPoids(String regime, String date, String poids) throws Exception{
		addPoids(getRegime(regime), date, poids);
	}
	public void addAlimentation(Regime regime, String date, String repas, String boisson, String periode) throws Exception {
		Alimentation a = new Alimentation();
		a.setBoisson(boisson);
		a.setDate(date);
		a.setPeriode(periode);
		a.setRepas(repas);
		addDetailRegime(regime, a);
	}
	public void addAlimentation(String regime, String date, String repas, String boisson, String periode) throws Exception {
		addAlimentation(getRegime(regime), date, repas, boisson, periode);
	}
	public void addSport(Regime regime, String date, String sport, String rythme) throws Exception{
		SportRegime s = new SportRegime();
		s.setDate(date);
		s.setSport(SportService.getInstance().getSport(sport));
		s.setRythme(rythme);
		addDetailRegime(regime,s);
	}
	public void addSport(String regime, String date, String sport, String rythme) throws Exception{
		addSport(getRegime(regime), date, sport, rythme);
	}
	
	public Regime getRegime(String regime) throws Exception{
		try{
			return (Regime) getService().get(regime, new Regime());
		}catch(Exception e){
			throw new Exception("Regime introuvable, valeur incorrecte");
		}
	}
	public void getRegime(Regime regime) throws Exception{
		try{
			getService().get(regime);
		}catch(Exception e){
			throw new Exception("Regime introuvable, valeur incorrecte");
		}
	}
	public AnalyseRegime findActive(Utilisateur model) throws Exception{
		AnalyseRegime regime = getService().getDao().findActiveRegime(model);
		if(regime != null && regime.getFin().before(new Date())){
			regime.setActive(false);
			getService().update(regime);
			return null;
		}
		return regime;
	}
	public void findAllUtilisateur(Utilisateur u, BaseModelePagination pagination) throws Exception{
		getService().getDao().findAllRegime(u, pagination, false);
	}
	public void findAllByRegime(Regime r, BaseModelePagination pagination) throws Exception{
		 getService().getDao().findAllByRegime(r, pagination);
	}
	public void desactive(Regime regime)throws Exception{
		if(regime instanceof AnalyseRegime) regime = ((AnalyseRegime) regime).getRegime();
		regime.setActive(false);
		getService().update(regime);
	}
	public AnalyseRegime active(String id)throws Exception{
		Regime r = (Regime) getService().get(id, new Regime());
		return active(r, true);
	}
	public AnalyseRegime active(Regime regime, boolean full)throws Exception{
		if(!full)getService().get(regime);
		if(regime.getFin().before(new Date())){
			throw new Exception("Veuillez modifier la date de fin de ce r�gime en une date ult�rieure");
		}
		AnalyseRegime pre = findActive(regime.getUtilisateur());
		if(pre != null){
			return getService().getDao().active(pre, regime);
		}
		regime.setActive(true);
		getService().update(regime);
		if(regime instanceof AnalyseRegime) return (AnalyseRegime)regime;
		return findActive(regime.getUtilisateur());
	}
	public BaseModele getDetailRegime(BaseModele model, Regime regime) throws Exception{
		try{
			getService().get(model);
			Method setRegime = model.getClass().getMethod("setRegime", Regime.class);
			setRegime.invoke(model, regime);
			BaseModele m = getService().getDao().findDetailRegime(model);
			if(m == null) throw new Exception("Informations introuvables, valeur incorrecte");
			return m;
		}catch(Exception e){
			throw e;
		}
	}

	public Poids getPoids(String poids) throws Exception{
		try{
			return (Poids) getService().get(poids, new Poids());
		}catch(Exception e){
			throw new Exception("Poids introuvable, valeur incorrecte");
		}
	}
	public void getPoids(Poids poids, Regime regime) throws Exception{
		try{
			getService().get(poids);
			Poids rightPoids = new Poids(poids.getId(), regime, poids.getDate(), poids.getValeur());
			if(getService().getDao().findPoids(rightPoids) == null) throw new Exception("Poids introuvable, valeur incorrecte");
		}catch(Exception e){
			throw new Exception("Poids introuvable, valeur incorrecte");
		}
	}
	
	public Alimentation getAlimentation(String alimentation) throws Exception{
		try{
			return (Alimentation) getService().get(alimentation, new Alimentation());
		}catch(Exception e){
			throw new Exception("Alimentation introuvable, valeur incorrecte");
		}
	}
	public SportRegime getSportRegime(String sportRegime) throws Exception{
		try{
			return (SportRegime) getService().get(sportRegime, new SportRegime());
		}catch(Exception e){
			throw new Exception("Sport introuvable, valeur incorrecte");
		}
	}
	
	public void deleteDetailRegime(BaseModele modele, Regime regime) throws Exception{
		modele = getDetailRegime(modele, regime);
		getService().delete(modele);
	}

	public void deleteRegime(String regime) throws Exception{
		getService().delete(regime, new Regime());
	}
	public void deletePoids(String poids) throws Exception{
		getService().delete(poids, new Poids());
	}
	public void deleteAlimentation(String alimentation) throws Exception{
		getService().delete(alimentation, new Alimentation());
	}
	
	public void deleteSportRegime(String sportRegime) throws Exception{
		getService().delete(sportRegime, new SportRegime());
	}

	public void updateDetailRegime(BaseModele model, Regime regime) throws Exception {
		model = getDetailRegime(model, regime);
		Method setRegime = model.getClass().getMethod("setRegime", Regime.class);
		setRegime.invoke(model, regime);
		service.update(model);
	}

	public void updateRegime(String id, String utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		updateRegime(id, UtilisateurService.getInstance().getUtilisateur(utilisateur), debut, fin, poidsObjectif, poidsInitial);
	}
	public void updateRegime(String id, Utilisateur utilisateur, String debut, String fin, String poidsObjectif, String poidsInitial) throws Exception{
		Regime regime = getRegime(id);
		regime.setUtilisateur(utilisateur);
		regime.setDebut(debut);
		regime.setFin(fin);
		regime.setPoidsObjectif(poidsObjectif);
		regime.setPoidsInitial(poidsInitial);
		getService().update(regime);
	}
	public void updatePoids(String id, String regime,String date, String poids) throws Exception{
		Poids p = getPoids(id);
		p.setRegime(getRegime(regime));
		p.setDate(date);
		p.setValeur(poids);
		getService().update(p);
	}
	public void updateAlimentation(String id, String regime, String date, String repas, String boisson, String periode) throws Exception {
		Alimentation a = getAlimentation(id);
		a.setBoisson(boisson);
		a.setRegime(getRegime(regime));
		a.setDate(date);
		a.setPeriode(periode);
		a.setRepas(repas);
		getService().save(a);
	}

	public void updateSport(String id, String regime, String date, String sport, String rythme) throws Exception{
		SportRegime s = new SportRegime();
		s.setRegime(getRegime(regime));
		s.setDate(date);
		s.setSport(SportService.getInstance().getSport(sport));
		s.setRythme(rythme);
		getService().update(s);
	}

	public String getEvolutionPoids(Regime regime) {
		List<Poids> poids = getService().getDao().findAllPoids(regime);
		String evolution = "[";
		evolution += "{y: '" + regime.getDebut() + "',a: '" + regime.getPoidsInitial() + "'}";
		for(int i = 0; i<poids.size();i++){
			evolution += ",{"
						+"y: '" + poids.get(i).getDate() + "',"
						+"a: '" + poids.get(i).getValeur() + "'"
						+"}";
		}
		evolution += "]";
		return evolution;
	}
}
