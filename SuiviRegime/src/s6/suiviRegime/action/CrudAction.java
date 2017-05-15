package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.*;
import s6.suiviRegime.service.BaseService;

public class CrudAction extends ActionSupport implements SessionAware{
	private Admin admin;
	private String erreur;
	private String classe;
	private int id;
	private BaseModelePagination liste;
	private int page;
	private SessionMap<String, Object> session;
	
	public String start()throws Exception{
		if(getAdmin() != null) return ERROR;
		return SUCCESS;
	}
	public String login()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getAdmin().getId() != 0) return SUCCESS;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService) context.getBean("baseService");
			setAdmin(service.loginAdmin(getAdmin()));
			session.put("admin", getAdmin());
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
			if(getAdmin() != null) return SUCCESS;
			session.invalidate();
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
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
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page - 1;
	}
	
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public SessionMap<String, Object> getSession() {
		return session;
	}
	public void setSession(SessionMap<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		setSession((SessionMap<String, Object>)session);
		setAdmin((Admin)session.get("Admin"));
	}

}