package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.AlimentationConseil;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.MultiService;
import s6.suiviRegime.service.UtilisateurService;

public class RegimeAction extends ActionSupport implements SessionAware{
	private Utilisateur user;
	private String erreur;
	private Regime regime;
	
	public String add()throws Exception{
		
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("multiService");
			getRegime().setUtilisateur(getUser());
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
	
	public Regime getRegime() {
		return regime;
	}
	public void setRegime(AnalyseRegime regime) {
		this.regime = regime;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		setUser((Utilisateur)session.get("user"));
		setRegime((AnalyseRegime)session.get("regime"));
	}
	
}
