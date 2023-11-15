/* QuestionnaireTest.java											15/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright, ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import iut.sae.modele.Questionnaire;

/**
 * Test Unitaire de la Classe Questionnaire.java dans "iut.sae.modele"
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, leo.cheikh-boukal
 */
class QuestionnaireTest {
	
	public static Categorie categorieSansQuestion = new Categorie("sansQuestion");
	public static Categorie categorieAvecQuestion = new Categorie("AvecQuestion");
	public static String[] reponseFausse = {"non","non"};

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		Question question1 = new Question("La question 1 ?", categorieAvecQuestion, "oui", reponseFausse , "pas de feedback", 1);
		Question question2 = new Question("La question 2 ?", categorieAvecQuestion, "oui", reponseFausse , "pas de feedback", 1);
		Question question3 = new Question("La question 3 ?", categorieAvecQuestion, "oui", reponseFausse , "pas de feedback", 1);
		Question question4 = new Question("La question 4 ?", categorieAvecQuestion, "oui", reponseFausse , "pas de feedback", 1);
		
		Donnees.listeCategorie.add(categorieSansQuestion);
		Donnees.listeCategorie.add(categorieAvecQuestion);
		
		Donnees.listeQuestion.add(question1);
		Donnees.listeQuestion.add(question2);
		Donnees.listeQuestion.add(question3);
		Donnees.listeQuestion.add(question4);
	}

	@Test
	void testQuestionnaireIntString() {
		assertThrows(IllegalArgumentException.class, () -> {new Questionnaire(1,"sansQuestion");});
		try {
			new Questionnaire(1,"avecQuestion");
		} catch(IllegalArgumentException e) {
			fail("Renvoie une erreur alors qu'il ne devrait pas");
		}
	}

	@Test
	void testQuestionnaireIntStringInt() {
		assertThrows(IllegalArgumentException.class, () -> {new Questionnaire(1,"sansQuestion",5);});
		Question question5 = new Question("La question 4 ?", categorieAvecQuestion, "oui", reponseFausse , "pas de feedback", 1);
		Donnees.listeQuestion.add(question5);
		try {
			new Questionnaire(1,"avecQuestion");
		} catch(IllegalArgumentException e) {
			fail("Renvoie une erreur alors qu'il ne devrait pas");
		}
	}

	@Test
	void testStockerReponse() {
		fail("Not yet implemented");
	}

	@Test
	void testLeTauxDeReussite() {
		fail("Not yet implemented");
	}

	@Test
	void testGetQuestion() {
		fail("Not yet implemented");
	}

	@Test
	void testGetListeQuestion() {
		fail("Not yet implemented");
	}

	@Test
	void testGetListeReponseDonnee() {
		fail("Not yet implemented");
	}

}
