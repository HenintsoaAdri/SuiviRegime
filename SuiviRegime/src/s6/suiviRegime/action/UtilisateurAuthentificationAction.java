package s6.suiviRegime.action;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.service.UtilisateurService;

public class UtilisateurAuthentificationAction extends ActionSupport implements SessionAware{
	private SessionMap<String, Object> session;
	private Utilisateur utilisateur;
	private String erreur;
	private String dateNaissance;
	private String password;
	private String confirmPassword;

	public String execute()throws Exception{
		if(session.get("user") != null) return ERROR;
		return SUCCESS;
	}
	public String inscription()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService) context.getBean("baseService");
			getUtilisateur().setDateNaissance(dateNaissance);
			getUtilisateur().setPassword(getPassword(), getConfirmPassword());
			service.save(getUtilisateur());
			session.put("user", getUtilisateur());
			return SUCCESS;
			
		}catch(Exception e){
			setErreur("Utilisateur non cr��. Des erreurs ont d�t�ct�es");
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String update()throws Exception {
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService) context.getBean("baseService");
			getUtilisateur().setDateNaissance(dateNaissance);
			getUtilisateur().setPassword(getPassword(), getConfirmPassword());
			service.update(getUtilisateur());
			return SUCCESS;
			
		}catch(Exception e){
			setErreur(e.getMessage()+" Utilisateur non modifi�. Des erreurs ont d�t�ct�es");
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public String login()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(session.get("user") != null) return SUCCESS;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService) context.getBean("utilisateurService");
			RegimeService regimeService = (RegimeService) context.getBean("regimeService");
			setUtilisateur(service.login(getUtilisateur()));
			session.put("user", getUtilisateur());
			session.put("regime", regimeService.findActive(getUtilisateur()));
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
	
	public String logout()throws Exception{
		try{
			if(session.get("user") != null) session.invalidate();
			return "success";
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>)session;
	}	
}
