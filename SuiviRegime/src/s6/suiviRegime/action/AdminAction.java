package s6.suiviRegime.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.modele.Admin;
import s6.suiviRegime.service.BaseService;

public class AdminAction extends ActionSupport implements SessionAware{
	private Admin admin;
	private SessionMap<String, Object> session;
	private String erreur;
	public String login()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getAdmin().getId() != 0) return SUCCESS;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			BaseService service = (BaseService) context.getBean("baseService");
			setAdmin(service.loginAdmin(getAdmin()));
			getSession().put("admin", getAdmin());
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
			if(getAdmin() != null)
			getSession().invalidate();
			return SUCCESS;
		}catch(Exception e){
			setErreur(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
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
		setAdmin((Admin)session.get("admin"));
	}

}
