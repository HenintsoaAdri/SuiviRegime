package s6.suiviRegime.action;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s6.suiviRegime.service.RegimeService;
import s6.suiviRegime.modele.Alimentation;
import s6.suiviRegime.modele.BaseModelePagination;

public class AlimentationAction extends UtilisateurSectionAction{
	private Alimentation alimentation;
	private BaseModelePagination liste;
	
	public String execute() throws Exception{
		if(getRegimeActif() != null) return SUCCESS;
		return ERROR;
	}
	public String index()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setListe(new BaseModelePagination(Alimentation.class, 10, getPage()));
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
	public String save()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			service.addDetailRegime(getRegimeActif(), getAlimentation());
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
	public String edit()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			RegimeService service = (RegimeService)context.getBean("regimeService");
			setAlimentation((Alimentation)service.getDetailRegime(getAlimentation(), getRegimeActif()));
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
			RegimeService service = (RegimeService)context.getBean("regimeService");
			if(getAlimentation() == null) return NONE;
			service.updateDetailRegime(getAlimentation(), getRegimeActif());
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
			RegimeService service = (RegimeService)context.getBean("regimeService");
			service.deleteDetailRegime(getAlimentation(), getRegimeActif());
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
	
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}
	
	public Alimentation getAlimentation() {
		return alimentation;
	}
	public void setAlimentation(Alimentation alimentation) {
		this.alimentation = alimentation;
	}
}
