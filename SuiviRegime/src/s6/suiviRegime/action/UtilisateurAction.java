package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.AlimentationConseil;
import s6.suiviRegime.modele.AnalyseRegime;
import s6.suiviRegime.modele.BaseModelePagination;
import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.MultiService;
import s6.suiviRegime.service.RegimeService;

public class UtilisateurAction extends ActionSupport implements SessionAware{
	private Utilisateur user;
	private String erreur;
	private AlimentationConseil	randomTips;
	private AnalyseRegime regimeActif;
	private BaseModelePagination listeRegime;
	private int page;
	
	public String index()throws Exception{
		
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			MultiService service = (MultiService)context.getBean("multiService");
			RegimeService regimeService = (RegimeService)context.getBean("regimeService");
			if(getRegimeActif() == null){
				setListeRegime(new BaseModelePagination(Regime.class, 10, getPage()));
				regimeService.findAllUtilisateur(getUser(), getListeRegime());
			}
			setRandomTips(service.getRandomTips());
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
	
	public AlimentationConseil getRandomTips() {
		return randomTips;
	}
	public void setRandomTips(AlimentationConseil randomTips) {
		this.randomTips = randomTips;
	}
	public AnalyseRegime getRegimeActif() {
		return regimeActif;
	}
	public void setRegimeActif(AnalyseRegime regimeActif) {
		this.regimeActif = regimeActif;
	}
	
	public BaseModelePagination getListeRegime() {
		return listeRegime;
	}
	public void setListeRegime(BaseModelePagination listeRegime) {
		this.listeRegime = listeRegime;
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