package s6.suiviRegime.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s6.suiviRegime.modele.*;
import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.service.UtilisateurService;

public class UtilisateurCrudAction extends CrudAction{
	private Utilisateur utilisateur;
	private AnalyseRegime regime;
	private BaseModelePagination liste;
	
	public String detailRegime(){
		ConfigurableApplicationContext context = null;
		try{
			Class<? extends BaseModele> classe = (Class<? extends BaseModele>) Class.forName(getClasse()); 
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			BaseService base = (BaseService)context.getBean("baseService");
			setListe(new BaseModelePagination(classe, 10, getPage()));
			service.getRegime(getRegime());
			setUtilisateur(new Utilisateur(getRegime().getIdutilisateur()));
			base.get(getUtilisateur());
			service.findAllByRegime(getRegime(), getListe());
			if(getListe().getNombrePage()< getPage() - 1) return NONE;
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
	public String detailUtilisateur(){
		ConfigurableApplicationContext context = null;
		try{ 
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService)context.getBean("utilisateurService");
			setListe(new BaseModelePagination(AnalyseRegime.class, 10, getPage()));
			service.getUtilisateur(getUtilisateur());
			service.findAllRegime(getUtilisateur(), getListe());
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
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public AnalyseRegime getRegime() {
		return regime;
	}
	public void setRegime(AnalyseRegime regime) {
		this.regime = regime;
	}
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}
	
}
