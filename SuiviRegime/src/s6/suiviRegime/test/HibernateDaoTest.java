package s6.suiviRegime.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.dao.HibernateDao;
import s6.suiviRegime.modele.*;

public class HibernateDaoTest {

	HibernateDao dao = new HibernateDao();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testInsert() throws Exception {
//		Poids poids = new Poids();
//		poids.setValeur(56);
//		poids.setDate(new Date());
//		poids.setRegime(new Regime(1));
//		dao.save(poids);
//		Poids test = new Poids(2);
//		dao.findById(test);
//		assertEquals(56, test.getValeur(),1);
//		assertNull(test.getRegime());
//		assertEquals(50, test.getRegime().getPoidsObjectif(),1);
//	}
	@Test
	public void testFindById() throws Exception {
		AlimentationConseil a = new AlimentationConseil(1);
		dao.findById(a);
		assertEquals("Minceur Ventre Plat", a.getNom());
		
		Poids test = new Poids(2);
		dao.findById(test);
		assertEquals(56, test.getValeur(),1);
	}
	@Test
	public void testOneToMany() throws Exception {
		Regime r = new Regime(1);
		dao.findById(r);
		assertEquals("Henintsoa", r.getUtilisateur().getNom());
		assertEquals("Yaourt - Pain beurr�", r.getAlimentation().get(0).getRepas());
		assertEquals("Marche � pied", r.getSport().get(0).getSport().getActivite());
		assertEquals(Date.valueOf("2017-05-05"), r.getPoids().get(1).getDate());
		assertSame(r, r.getAlimentation().get(1).getRegime());
	}
}
