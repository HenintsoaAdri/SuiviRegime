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
import s6.suiviRegime.modele.Regime;

public class RegimeAction extends UtilisateurSectionAction{
	private String debut;
	private String fin;
	private String poidsInitial;
	private String poidsObjectif;
	private String active;
	private Regime regime;
	private BaseModelePagination liste;
	private SessionMap<String, Object> session;
	
	public String edit()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			service.get(getRegime());
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
	public String index()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setListe(new BaseModelePagination(AnalyseRegime.class, 10, getPage()));
			service.findAllUtilisateur(getUser(), getListe());
			if(getListe().getNombrePage() < getPage() - 1) return NONE;
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
			service.addRegime(getUser(), getDebut(), getFin(), getPoidsObjectif(), getPoidsInitial());
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
	public String activate()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			AnalyseRegime actif = service.active(getRegime(), false);
			session.put("regime", actif);
			setRegimeActif(actif);
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
	public String deactivate()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			if(getRegimeActif() != null){
				service.desactive(getRegimeActif());
			}
			session.remove("regime");
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
			if(getRegime() == null) return NONE;
			getRegime().setUtilisateur(getUser());
			getRegime().setDebut(getDebut());
			getRegime().setFin(getFin());
			getRegime().setPoidsInitial(getPoidsInitial());
			getRegime().setPoidsObjectif(getPoidsObjectif());
			if(getActive() != null && getActive().equalsIgnoreCase("false")){
				activate();
			}
			else if(getRegimeActif() != null && getRegime().getId() == getRegimeActif().getId()){
				if(getActive() == null) deactivate();
				else{
					getRegimeActif().setUtilisateur(getRegime().getUtilisateur());
					getRegimeActif().setDebut(getRegime().getDebut());
					getRegimeActif().setFin(getRegime().getFin());
					getRegimeActif().setPoidsInitial(getRegime().getPoidsInitial());
					getRegimeActif().setPoidsObjectif(getRegime().getPoidsObjectif());
				}
			}
			getRegime().setActive(getActive() != null);
			service.update(getRegime());
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
			getRegime().setUtilisateur(getUser());
			service.delete(getRegime());
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
			
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getPoidsInitial() {
		return poidsInitial;
	}
	public void setPoidsInitial(String poidsInitial) {
		this.poidsInitial = poidsInitial;
	}

	public String getPoidsObjectif() {
		return poidsObjectif;
	}
	public void setPoidsObjectif(String poidsObjectif) {
		this.poidsObjectif = poidsObjectif;
	}
		
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}
		
	public Regime getRegime() {
		return regime;
	}
	public void setRegime(Regime regime) {
		this.regime = regime;
	}
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
