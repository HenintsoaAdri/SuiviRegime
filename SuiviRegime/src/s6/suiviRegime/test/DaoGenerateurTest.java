package s6.suiviRegime.test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s6.suiviRegime.utilitaire.DaoGenerateur;

public class DaoGenerateurTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateDao() {
		DaoGenerateur generateur = new DaoGenerateur();
		generateur.generateDao(new File("./src/s6/suiviRegime/modele"),"s6.suiviRegime.daoGenere");
	}

}
