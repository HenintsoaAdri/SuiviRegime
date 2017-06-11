package s6.suiviRegime.action;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s6.suiviRegime.modele.AlimentationConseil;
import s6.suiviRegime.modele.BaseModele;
import s6.suiviRegime.modele.Sport;
import s6.suiviRegime.modele.SportConseil;
import s6.suiviRegime.service.BaseService;
import sun.print.resources.serviceui;

public class ConseilAction extends AdminAction{
	private AlimentationConseil alimentation;
	private SportConseil sport;
	private List<BaseModele> all;
	
	public String save()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			if(getAlimentation() != null)service.save(getAlimentation());
			if(getSport() != null) service.save(getSport());
			else return ERROR;
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
		BaseService service = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			service = (BaseService)context.getBean("baseService");
			if(getAlimentation() != null)service.update(getAlimentation());
			if(getSport() != null) service.update(getSport());
			else return NONE;
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			if(service != null)	setAll(service.findAll(Sport.class));
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public List<BaseModele> getAll() {
		return all;
	}
	public void setAll(List<BaseModele> all) {
		this.all = all;
	}
	public AlimentationConseil getAlimentation() {
		return alimentation;
	}
	public void setAlimentation(AlimentationConseil alimentation) {
		this.alimentation = alimentation;
	}
	public SportConseil getSport() {
		return sport;
	}
	public void setSport(SportConseil sport) {
		this.sport = sport;
	}
	
}
