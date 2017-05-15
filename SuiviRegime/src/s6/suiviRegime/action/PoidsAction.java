package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.BaseModelePagination;
import s6.suiviRegime.modele.Poids;

public class PoidsAction extends UtilisateurSectionAction{
	private Poids poids;
	private String date;
	private String valeur;
	private BaseModelePagination liste;
	private String evolution;
	private SessionMap<String, Object> session;
	
	public String execute() throws Exception{
		if(getRegimeActif() != null) return SUCCESS;
		return ERROR;
	}
	public String index()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setListe(new BaseModelePagination(Poids.class, 10, getPage()));
			service.findAllByRegime(getRegimeActif(), getListe());
			setEvolution(service.getEvolutionPoids(getRegimeActif()));
			if(getListe().getNombrePage()<= getPage()) return NONE;
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String save()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			service.addPoids(getRegimeActif(), getDate(), getValeur());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String edit()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			service.get(getPoids());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String update()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			if(getPoids() == null) return NONE;
			getPoids().setRegime(getRegimeActif());
			getPoids().setDate(getDate());
			getPoids().setValeur(getValeur());
			service.update(getPoids());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public String delete(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			getPoids().setRegime(getRegimeActif());
			service.delete(getPoids());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public Poids getPoids() {
		return poids;
	}
	public void setPoids(Poids poids) {
		this.poids = poids;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}
	
	public String getEvolution() {
		return evolution;
	}
	public void setEvolution(String evolution) {
		this.evolution = evolution;
	}
	
	public SessionMap<String, Object> getSession() {
		return session;
	}
	public void setSession(SessionMap<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		setSession((SessionMap<String, Object>) session);
		setUser((Utilisateur)session.get("user"));
		setRegimeActif((AnalyseRegime)session.get("regime"));
	}
	
}
