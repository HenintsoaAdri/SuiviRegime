package s6.suiviRegime.modele;

import java.util.Date;

import s6.suiviRegime.utilitaire.DateUtil;

public class Admin extends BaseModele {
	String identifiant;
	String password;
	Date lastLogin;
	
	public Admin() {
		super();
	}
	public Admin(int id) {
		super(id);
	}
	public Admin(int id, String identifiant, String password, Date lastLogin) {
		super(id);
		setIdentifiant(identifiant);
		setPassword(password);
		setLastLogin(lastLogin);
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public String getLastLoginString(){
		return DateUtil.getInstance().DateTimeToString(getLastLogin());
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void setLastLogin(String lastLogin) throws Exception {
		setLastLogin(DateUtil.getInstance().stringToDate(lastLogin));
	}
	public void loggedIn(){
		setLastLogin(new Date());
	}
	
}
