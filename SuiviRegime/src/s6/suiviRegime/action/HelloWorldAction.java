package s6.suiviRegime.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import s6.suiviRegime.service.BaseService;

public class HelloWorldAction extends ActionSupport {
	private String name;
	public String execute()throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("list-beans.xml");
		BaseService service = (BaseService) context.getBean("baseService");
		setName("Adri :D");
		return "success";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
