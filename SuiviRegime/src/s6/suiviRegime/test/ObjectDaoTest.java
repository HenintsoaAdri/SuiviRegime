package s6.suiviRegime.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.dao.ObjectDao;
import s6.suiviRegime.modele.*;

public class ObjectDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=Exception.class)
	public void testFindByIdInsertBaseModele() throws Exception {
		ObjectDao dao = new ObjectDao();
		AlimentationConseil a = new AlimentationConseil(1);
		dao.findById(a);
		assertEquals("Minceur Ventre Plat", a.getNom());
		
		Poids poids = new Poids();
		poids.setValeur(55);
		poids.setDate(new Date());
		poids.setRegime(new Regime(1));
		dao.save(poids);
		Poids test = new Poids(2);
		dao.findById(test);
		assertEquals(55, test.getValeur(),1);
		assertNull(test.getRegime());
	
		dao.findById(test,true);
		assertEquals(50, test.getRegime().getPoidsObjectif(),1);
		dao.delete(test);

		Poids deleted = new Poids(2);
		dao.findById(deleted);
		assertNull(deleted.getValeur());
	
	}
	@Test
	public void testNomClasse() throws Exception {
		ObjectDao dao = new ObjectDao();
		String nom = dao.getNomClasse(new Alimentation());
		assertEquals("Alimentation", nom);
	}
	@Test
	public void testGetQuery() throws Exception {
		ObjectDao dao = new ObjectDao();
		Alimentation a = new Alimentation();
		String nom = dao.getNomClasse(a);
		assertEquals("Alimentation", nom);
		String query = dao.getSaveQuery(new Alimentation());
		assertEquals("INSERT INTO ALIMENTATION "
				+ "(IDREGIME, REPASALIMENTATION, BOISSONALIMENTATION, "
				+ "PERIODEALIMENTATION, DATEALIMENTATION) "
				+ "VALUES(?, ?, ?, ?, ?)"
				,query);
		query = dao.getUpdateQuery(a);
		assertEquals("UPDATE ALIMENTATION "
				+ "SET IDREGIME = ?, REPASALIMENTATION = ?, BOISSONALIMENTATION = ?, "
				+ "PERIODEALIMENTATION = ?, DATEALIMENTATION = ? "
				+ "WHERE IDALIMENTATION = ?"
				,query);
		query = dao.getDeleteQuery(a);
		assertEquals("DELETE FROM ALIMENTATION WHERE IDALIMENTATION = ?",query);
		query = dao.getFindAllQuery(a, new Field[0]);
		assertEquals("SELECT * FROM ALIMENTATION",query);
		query = dao.getFindAllOffsetQuery(a);
		assertEquals("SELECT * FROM ALIMENTATION LIMIT 10 OFFSET = ?",query);
		query = dao.getFindByIdQuery(a);
		assertEquals("SELECT * FROM ALIMENTATION WHERE IDALIMENTATION = ?",query);
	}

}
