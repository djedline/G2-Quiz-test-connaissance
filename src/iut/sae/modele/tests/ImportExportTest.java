package iut.sae.modele.tests;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;

class ImportExportTest {

	@Test
	void test() {
		try {
			ImportExport.importer("src/iut/sae/modele/tests/questionsbasiques.csv");
			Donnees.afficherDonnees();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
