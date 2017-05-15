package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.Utilisateur;

public class UtilisateurSectionAction extends ActionSupport implements SessionAware{
	private Utilisateur user;
	private String erreur;
	private AnalyseRegime regimeActif;
	private int page;
				
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
