package s6.suiviRegime.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.UtilisateurService;

public class UtilisateurServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() throws Exception {
		Utilisateur valeur = UtilisateurService.getInstance().login("adri@hotmail.com", "adri");
		Utilisateur u = new Utilisateur();
		u.setId(1);
		u.setNom("Henintsoa");
		u.setDateNaissance("1996-08-20");
		u.setEmail("adri@hotmail.com");
		u.setSexe("F");
		u.setPassword("adri");
		u.setAdresse("Lot VB 83 Ambatoroka");
		assertEquals(u.getNom(), valeur.getNom());
		assertEquals(u.getAdresse(), valeur.getAdresse());
	}
	@Test
	public void testInscription() throws Exception {
		HibernateDao dao = new HibernateDao();
		UtilisateurService.getInstance().inscription("Herilala","Antsa","1995-01-14","M","antsa.herilala@hotmail.com","antsalala","antsalala","Lot IAV 125 Iavoloha");
		Utilisateur valeur = new Utilisateur(2);
		dao.findById(valeur);
		assertEquals("Herilala", valeur.getNom());
		assertEquals("Lot IAV 125 Iavoloha", valeur.getAdresse());
	}
}
