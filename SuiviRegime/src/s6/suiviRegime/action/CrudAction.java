package s6.suiviRegime.action;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import s6.suiviRegime.modele.*;
import s6.suiviRegime.service.BaseService;

public class CrudAction extends AdminAction{
	private String classe;
	private int id;
	private BaseModelePagination liste;
	private int page;
	private BaseModele item;
	private List<? extends BaseModele> all;
	
	public String start()throws Exception{
		if(getAdmin() != null) return ERROR;
		return SUCCESS;
	}
	public String execute()throws Exception{
		if(getAdmin() == null) return ERROR;
		return SUCCESS;
	}
	public String findAll()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			Class<? extends BaseModele> classe = (Class<? extends BaseModele>) Class.forName(getClasse());
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			setAll(service.findAll(classe));
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
	public String list()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			System.out.println(classe);
			Class<? extends BaseModele> classe = (Class<? extends BaseModele>) Class.forName(getClasse()); 
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			setListe(new BaseModelePagination(classe, 10, getPage()));
			service.findAll(getListe());
			if(getListe().getNombrePage()<= getPage()) return NONE;
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
			Class<? extends BaseModele> classe = (Class<? extends BaseModele>) Class.forName(getClasse());
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			int id = getItem().getId();
			setItem(classe.newInstance());
			getItem().setId(id);
			if(getItem() instanceof SportConseil || getItem() instanceof SportRegime){
				setClasse("s6.suiviRegime.modele.Sport");
				findAll();
			}
			service.get(getItem());
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
	public String delete()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			Class<? extends BaseModele> classe = (Class<? extends BaseModele>) Class.forName(getClasse());
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService)context.getBean("baseService");
			BaseModele object = classe.newInstance();
			object.setId(getId());
			service.get(object);
			service.delete(object);
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
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BaseModelePagination getListe() {
		return liste;
	}
	public void setListe(BaseModelePagination liste) {
		this.liste = liste;
	}

	public List<? extends BaseModele> getAll() {
		return all;
	}
	public void setAll(List<? extends BaseModele> all) {
		this.all = all;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page - 1;
	}
	
	public BaseModele getItem() {
		return item;
	}
	public void setItem(BaseModele item) {
		this.item = item;
	}
	

}
