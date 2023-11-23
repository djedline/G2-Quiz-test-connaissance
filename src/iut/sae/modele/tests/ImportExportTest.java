package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;
import iut.sae.modele.Question;

class ImportExportTest {

	private static final File FICHIER_IMPORT_QUEST_JAVA
		= new File("src/iut/sae/modele/tests/questionsbasiques.csv");

	private static final File FICHIER_IMPORT_QUEST_ORTHO 
		= new File("src/iut/sae/modele/tests/questionsorthographe.csv");

	private static final File FICHIER_EXPORT_QUEST 
		= new File("src/iut/sae/modele/tests/exporte.csv");

	@Test
	void testImportJava() {
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
		Donnees.afficherDonnees();

	}

	@Test
	void testImportOrthographe() {
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
	}

	@Test
	void testExport() throws IOException {
		String EXPORT_VIDE = "Catégorie;Niveau;Libellé;Vrai;Faux1;Faux2;Faux3;Faux4;Feedback;\n";
		
		Donnees.reinitialiserDonnees();
		FICHIER_EXPORT_QUEST.delete();
		ImportExport.exporter(FICHIER_EXPORT_QUEST);
		String contenu = lireFichier(FICHIER_EXPORT_QUEST);
		
		// fichier vide
		assertEquals(EXPORT_VIDE, contenu);
		
		Categorie nvCategorie = new Categorie("Cat");
		Donnees.listeCategorie.add(nvCategorie);
		String[] repFausses = {
				"Rouge",
				"Vert",
				"Bleu",
				"Argenté"
		};
		Question nvQuestion = new Question("Maquestion", nvCategorie, "JeSuisJuste", repFausses, "Feedback", 1);
		Donnees.listeQuestions.add(nvQuestion);
		
		FICHIER_EXPORT_QUEST.delete();
		ImportExport.exporter(FICHIER_EXPORT_QUEST);
		contenu = lireFichier(FICHIER_EXPORT_QUEST);
		
		String EXPORT_PAS_VIDE = EXPORT_VIDE
				+ "Cat;1;Maquestion;JeSuisJuste;Rouge;Vert;Bleu;Argenté;Feedback;\n";
		assertEquals(EXPORT_PAS_VIDE , contenu);
	}

	private String lireFichier(File f) throws IOException {
			StringBuffer sb = new StringBuffer();
			FileReader fr = new FileReader(f, Charset.forName("UTF-8"));
			while (fr.ready()) {
				sb.append(Character.toString(fr.read()));
			}
			fr.close();
			return sb.toString();
	}

}
