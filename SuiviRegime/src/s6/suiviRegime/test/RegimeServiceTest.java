package s6.suiviRegime.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.Alimentation;
import s6.suiviRegime.modele.Regime;
import s6.suiviRegime.modele.SportRegime;
import s6.suiviRegime.modele.Utilisateur;
import s6.suiviRegime.service.RegimeService;

public class RegimeServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRegime() throws Exception {
		HibernateDao dao = new HibernateDao();
		Utilisateur valeur = new Utilisateur(2);
		dao.findById(valeur);
		RegimeService.getInstance().addRegime(valeur,"07-05-2017","07-08-2017","50,2 ","59");
		Regime regime = new Regime(2);
		dao.findById(regime);
		assertEquals("Herilala", regime.getUtilisateur().getNom());
		assertEquals(50.2,regime.getPoidsObjectif(),1);
	}

	@Test
	public void testAddPoids() throws Exception {
		RegimeService.getInstance().addPoids("1", null, "55,2");
		Regime r = RegimeService.getInstance().getRegime("1");
		assertEquals(Date.valueOf(LocalDate.now()),r.getPoids().get(2).getDate());
		assertEquals(55.2,r.getPoids().get(2).getValeur(),1);
	}
	@Test
	public void testAddAlimentation() throws Exception {
		RegimeService.getInstance().addAlimentation("2", "2017-05-07", "Madeleine", "Lait", "1");
		HibernateDao dao = new HibernateDao();
		Alimentation a = new Alimentation(8);
		dao.findById(a);
		assertEquals("Madeleine", a.getRepas());
		assertEquals(2, a.getRegime().getId());
	}
	@Test
	public void testAddSport() throws Exception {
		RegimeService.getInstance().addSport("2","2017-05-07","7","60");
		HibernateDao dao = new HibernateDao();
		SportRegime s = new SportRegime(2);
		dao.findById(s);
		assertEquals("Running", s.getSport().getLibelle());
		assertEquals(60, s.getRythme(),1);
	}
	@Test
	public void testUpdateRegime() throws Exception {
		HibernateDao dao = new HibernateDao();
		RegimeService.getInstance().updateRegime("2","2","07-05-2017","07-07-2017","55,5","62");
		Regime regime = new Regime(2);
		dao.findById(regime);
		assertEquals(Date.valueOf("2017-07-07"), regime.getFin());
		assertEquals(55.5,regime.getPoidsObjectif(),1);
	}
	@Test
	public void testDeleteRegime() throws Exception {
		HibernateDao dao = new HibernateDao();
		RegimeService.getInstance().deleteRegime("2");
		Regime regime = new Regime(2);
		dao.findById(regime);
		assertNull(regime.getDebut());
	}
}
