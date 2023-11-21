package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;

class ImportExportTest {
	
	private static final String FICHIER_IMPORT_QUEST_JAVA
					= "src/iut/sae/modele/tests/questionsbasiques.csv";
	
	private static final String FICHIER_IMPORT_QUEST_ORTHO
					= "src/iut/sae/modele/tests/questionsorthographe.csv";
	
	@Test
	void testImport() {
		Donnees.reinitialiserDonnees();
		
		final int NB_CAT_JAVA = 8;
		final int NB_QUEST_JAVA = 31;
		
		try {
			ImportExport.importer(FICHIER_IMPORT_QUEST_JAVA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(NB_CAT_JAVA + 1, Donnees.listeCategorie.size());
		assertEquals(NB_QUEST_JAVA, Donnees.listeQuestions.size());
		
		Donnees.reinitialiserDonnees();
		
		final int NB_CAT_ORTHO = 0;
		final int NB_QUEST_ORTHO = 189;
		
		try {
			ImportExport.importer(FICHIER_IMPORT_QUEST_ORTHO);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(NB_CAT_ORTHO + 1, Donnees.listeCategorie.size());
		assertEquals(NB_QUEST_ORTHO, Donnees.listeQuestions.size());
		
		Donnees.afficherDonnees();
		Donnees.sauvegarder();
	}
	
	@Test
	void testExport() {
		//TODO export
	}

}
