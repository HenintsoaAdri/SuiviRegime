package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.modele.AlimentationConseil;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.BaseModelePagination;
import s6.suiviRegime.modele.SportConseil;

public class ConseilAction extends ActionSupport implements SessionAware{
	private Utilisateur user;
	private String erreur;
	private AnalyseRegime regimeActif;
	private BaseModelePagination conseil;
	private int page;
	
	public String index()throws Exception{
		return SUCCESS;
	}
	public String sport()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			setConseil(new BaseModelePagination(SportConseil.class, 10, getPage()));
			service.findAll(getConseil());
			if(getConseil().getNombrePage()<= getPage()) return NONE;
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
	public String alimentation()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			setConseil(new BaseModelePagination(AlimentationConseil.class, 10, getPage()));
			service.findAll(getConseil());
			if(getConseil().getNombrePage()<= getPage()) return NONE;
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
		
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user){
		this.user = user;
	}
	
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	
	public BaseModelePagination getConseil() {
		return conseil;
	}
	public void setConseil(BaseModelePagination conseil) {
		this.conseil = conseil;
	}
	
	public AnalyseRegime getRegimeActif() {
		return regimeActif;
	}
	public void setRegimeActif(AnalyseRegime regimeActif) {
		this.regimeActif = regimeActif;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page - 1;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		setUser((Utilisateur)session.get("user"));
		setRegimeActif((AnalyseRegime)session.get("regime"));
	}
	
}