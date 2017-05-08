package s6.suiviRegime.service;
import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.Utilisateur;

public class UtilisateurService {
	private HibernateDao dao;
	private UtilisateurService(){
		if(dao == null) dao = new HibernateDao();
	}
	private static class Holder
	{		
		private final static UtilisateurService instance = new UtilisateurService();
	}
	public static UtilisateurService getInstance(){
		return Holder.instance;
	}
	public void inscription(String nom, String prenom, String dateNaissance, String sexe, 
			String email, String password, String confirmPassword, String adresse) throws Exception{
		Utilisateur u = new Utilisateur();
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setDateNaissance(dateNaissance);
		u.setSexe(sexe);
		u.setEmail(email);
		u.setPassword(password, confirmPassword);
		u.setAdresse(adresse);
		HibernateDao dao = new HibernateDao();
		dao.save(u);
	}
	public Utilisateur login(String email, String password) throws Exception{
		Utilisateur u = new Utilisateur();
		u.setEmail(email);
		u.setPassword(password);
		Utilisateur user = dao.login(u);
		if(user == null) throw new Exception("Vos identifiants sont incorrectes ou on ne vous connait pas encore !");
		return (Utilisateur)user;
	}
	public Utilisateur getUtilisateur(String utilisateur) throws Exception{
		try{
			int id = Integer.parseInt(utilisateur.trim());
			Utilisateur u = new Utilisateur(id);
			dao.findById(u);
			return u;
		}catch(NumberFormatException e){
			throw new Exception("Utilisateur introuvable, valeur incorrecte");
		}
	}
	public void deleteUtilisateur(String utilisateur) throws Exception{
		Utilisateur u = getUtilisateur(utilisateur);
		dao.delete(u);
	}
	public void updateUtilisateur(Utilisateur utilisateur) throws Exception{
		dao.update(utilisateur);
	}
}