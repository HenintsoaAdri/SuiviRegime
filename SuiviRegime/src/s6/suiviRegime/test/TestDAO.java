package s6.suiviRegime.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

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
		AlimentationConseil a = AlimentationConseilDAO.findById(1);
		assertEquals("Minceur Ventre Plat", a.getNom());		
	}
	@Test
	public void testAlimentationFind() throws Exception {
		Alimentation a = AlimentationDAO.findById(1);
		assertEquals("Henintsoa", a.getRegime().getUtilisateur().getNom());
		assertEquals(50, a.getRegime().getPoidsObjectif(),0.1);
		assertEquals(LocalDate.of(2017,03, 01), a.getDate());
	}
	@Test
	public void testPoidsFind() throws Exception {
		Poids p = PoidsDAO.findById(1);
		assertEquals("Henintsoa", p.getRegime().getUtilisateur().getNom());
		assertEquals(50, p.getRegime().getPoidsObjectif(),0.1);
		assertEquals(57, p.getPoids(),0.1);
	}
	@Test
	public void testRegimeFind() throws Exception {
		Regime r = RegimeDAO.findById(1);
		assertEquals("Henintsoa", r.getUtilisateur().getNom());
		assertEquals(LocalDate.of(2017,06,01), r.getFin());
		assertEquals(50, r.getPoidsObjectif(),0.1);
	}
	@Test
	public void testSportFind() throws Exception {
		Sport s = SportDAO.findById(1);
		assertEquals("Fitness", s.getSport());
		assertEquals("Corde � sauter", s.getActivite());
	}
	@Test
	public void testSportRegimeFind() throws Exception {
		SportRegime s = SportRegimeDAO.findBySportRegime(SportDAO.findById(8),RegimeDAO.findById(1));
		assertEquals(LocalDate.of(2017,03, 01), s.getDate());
		assertEquals(30, s.getRythme(),0.1);
		assertEquals("Running",s.getSport());
		assertEquals("Marche � pied",s.getActivite());
	}
	@Test
	public void testSportConseilFind() throws Exception {
		SportConseil s = SportConseilDAO.findById(1);
		assertEquals("Fitness", s.getSport());
		assertEquals("Corde � sauter", s.getActivite());
	}
	@Test
	public void testUtilisateurFind() throws Exception {
		Utilisateur u = UtilisateurDAO.findById(1);
		assertEquals("Henintsoa", u.getNom());
		assertEquals("Adri", u.getPrenom());
		assertEquals("Femme", u.getSexeString());
		assertEquals("20 ao�t 1996", u.getDateNaissanceString());
	}
}