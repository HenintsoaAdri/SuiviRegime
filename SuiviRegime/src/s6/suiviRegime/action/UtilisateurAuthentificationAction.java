package s6.suiviRegime.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.service.UtilisateurService;

public class UtilisateurAuthentificationAction extends ActionSupport {
	private boolean inscription = true;
	private Utilisateur utilisateur;
	private String erreur;
	private String dateNaissance;
	private String email;
	private String password;
	private String confirmPassword;
	
	public String inscription()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			Map<String, Object> session = ActionContext.getContext().getSession();
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService) context.getBean("baseService");
			getUtilisateur().setDateNaissance(dateNaissance);
			getUtilisateur().setPassword(getPassword(), getConfirmPassword());
			service.save(getUtilisateur());
			setInscription(false);
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
	public String openInscription()throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("user") != null) return ERROR;
		return SUCCESS;
	}
	public String openLogin()throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("user") != null) return ERROR;
		return SUCCESS;
	}
	
	public String login()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			Map<String, Object> session = ActionContext.getContext().getSession();
			if(session.get("user") != null) return SUCCESS;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService) context.getBean("utilisateurService");
			RegimeService regService = (RegimeService)context.getBean("regimeService");
			session.put("user", service.login(getEmail(),getPassword()));
			session.put("regime", regService.findUnclosed((Utilisateur)session.get("user")));
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			setInscription(false);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public String logout()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			Map<String, Object> session = ActionContext.getContext().getSession();
			if(session.get("user") != null) session.remove("user");
			return "success";
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			setInscription(false);
			return "error";
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public boolean getInscription() {
		return inscription;
	}
	public void setInscription(boolean inscription) {
		this.inscription = inscription;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
