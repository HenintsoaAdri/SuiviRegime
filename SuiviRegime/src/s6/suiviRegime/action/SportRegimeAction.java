package s6.suiviRegime.action;

import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s6.suiviRegime.service.BaseService;
import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.modele.BaseModele;
import s6.suiviRegime.modele.BaseModelePagination;
import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportRegime;

public class SportRegimeAction extends UtilisateurSectionAction{
	private SportRegime sport;
	private List<BaseModele> all;
	private String rythme;
	private BaseModelePagination liste;
	private String date;
	
	public String index()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setListe(new BaseModelePagination(SportRegime.class, 10, getPage()));
			service.findAllByRegime(getRegimeActif(), getListe());
			if(getListe().getNombrePage()< getPage()) return NONE;
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
	public String add()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			setAll(service.findAll(Sport.class));
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
			getSport().setDate(getDate());
			getSport().setRythme(getRythme());
			service.addDetailRegime(getRegimeActif(), getSport());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			BaseService service = (BaseService)context.getBean("baseService");
			setAll(service.findAll(Sport.class));
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
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setSport((SportRegime)service.getDetailRegime(getSport(), getRegimeActif()));
			BaseService base = (BaseService)context.getBean("baseService");
			setAll(base.findAll(Sport.class));
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
			if(getSport() == null) return NONE;
			RegimeService service = (RegimeService)context.getBean("regimeService");
			getSport().setRegime(getRegimeActif());
			getSport().setDate(getDate());
			getSport().setRythme(getRythme());
			service.updateDetailRegime(getSport(), getRegimeActif());
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			if(context != null){
				BaseService base = (BaseService)context.getBean("baseService");
				setAll(base.findAll(Sport.class));				
			}
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
			RegimeService service = (RegimeService)context.getBean("regimeService");
			service.deleteDetailRegime(getSport(), getRegimeActif());
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
	
	public SportRegime getSport() {
		return sport;
	}
	public void setSport(SportRegime sport) {
		this.sport = sport;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public List<BaseModele> getAll() {
		return all;
	}
	public void setAll(List<BaseModele> all) {
		this.all = all;
	}
	
	public String getRythme() {
		return rythme;
	}
	public void setRythme(String rythme) {
		this.rythme = rythme;
	}
	
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}
	
}
