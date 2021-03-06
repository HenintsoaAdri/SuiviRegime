package s6.suiviRegime.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.dao.*;
import s6.suiviRegime.modele.*;

public class TestDAO {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAlimentationConseilFind() throws Exception {
		AlimentationConseilDao dao = new AlimentationConseilDao();
		AlimentationConseil a = dao.findById(1);
		assertEquals("Minceur Ventre Plat", a.getNom());		
	}
	@Test
	public void testAlimentationFind() throws Exception {
		AlimentationDao dao = new AlimentationDao();
		Alimentation a = dao.findById(1);
		assertEquals("Henintsoa", a.getRegime().getUtilisateur().getNom());
		assertEquals(50, a.getRegime().getPoidsObjectif(),0.1);
		assertEquals(Date.valueOf("2017-03-01"), a.getDate());
	}
	@Test
	public void testPoidsFind() throws Exception {
		PoidsDao dao = new PoidsDao();
		Poids p = dao.findById(1);
		assertEquals("Henintsoa", p.getRegime().getUtilisateur().getNom());
		assertEquals(50, p.getRegime().getPoidsObjectif(),0.1);
		assertEquals(57, p.getValeur(),0.1);
	}
	@Test
	public void testRegimeFind() throws Exception {
		RegimeDao dao = new RegimeDao();
		Regime r = dao.findById(1);
		assertEquals("Henintsoa", r.getUtilisateur().getNom());
		assertEquals(Date.valueOf("2017-06-01"), r.getFin());
		assertEquals(50, r.getPoidsObjectif(),0.1);
	}
	@Test
	public void testSportFind() throws Exception {
		SportDao dao = new SportDao();
		Sport s = dao.findById(1);
		assertEquals("Fitness", s.getLibelle());
		assertEquals("Corde � sauter", s.getActivite());
	}
	@Test
	public void testSportRegimeFind() throws Exception {
		SportRegimeDao dao = new SportRegimeDao();
		RegimeDao regime = new RegimeDao();
		SportDao sport = new SportDao();
		SportRegime s = dao.findBySportRegime(sport.findById(8),regime.findById(1));
		assertEquals(Date.valueOf("2017-03-01"), s.getDate());
		assertEquals(30, s.getRythme(),0.1);
		assertEquals("Running",s.getSport().getLibelle());
		assertEquals("Marche � pied",s.getSport().getActivite());
	}
	@Test
	public void testSportConseilFind() throws Exception {
		SportConseilDao dao = new SportConseilDao();
		SportConseil s = dao.findById(1);
		assertEquals("Fitness", s.getSport().getLibelle());
		assertEquals("Corde � sauter", s.getSport().getActivite());
	}
	@Test
	public void testUtilisateurFind() throws Exception {
		UtilisateurDao dao = new UtilisateurDao();
		Utilisateur u = dao.findById(1);
		assertEquals("Henintsoa", u.getNom());
		assertEquals("Adri", u.getPrenom());
		assertEquals("Femme", u.getSexeString());
		assertEquals("20 ao�t 1996", u.getDateNaissanceString());
	}
}
