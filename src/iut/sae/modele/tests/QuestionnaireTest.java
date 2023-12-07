/* QuestionnaireTest.java											15/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright, ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import iut.sae.modele.Questionnaire;

/**
 * Test Unitaire de la Classe Questionnaire.java dans "iut.sae.modele"
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 */
class QuestionnaireTest {

    public static Categorie categorieSansQuestion = 
            new Categorie("sansQuestion");
    
    public static Categorie categorieAvecQuestion = 
            new Categorie("avecQuestion");
    
    public static String[] reponseFausse = { "non", "non" };
    
    public static Questionnaire leQuestionnaire1;
    
    public static Questionnaire leQuestionnaire2;
    
    public static Question question1 = new Question(
            "La question 1 ?", categorieAvecQuestion, "oui", reponseFausse,
            "pas de feedback", 1);
    
    public static Question question2 = new Question(
            "La question 2 ?", categorieAvecQuestion, "oui", reponseFausse,
            "pas de feedback", 1);
    
    public static Question question3 = new Question(
            "La question 3 ?", categorieAvecQuestion, "oui", reponseFausse,
            "pas de feedback", 1);
    
    public static Question question4 = new Question(
            "La question 4 ?", categorieAvecQuestion, "oui", reponseFausse,
            "pas de feedback", 1);

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        Donnees.listeCategorie.add(categorieSansQuestion);
        Donnees.listeCategorie.add(categorieAvecQuestion);

        Donnees.listeQuestions.add(question1);
        Donnees.listeQuestions.add(question2);
        Donnees.listeQuestions.add(question3);
        Donnees.listeQuestions.add(question4);

        System.out.println(Donnees.listeQuestions.toString());
        System.out.println(Donnees.listeQuestions.size());

        leQuestionnaire1 = new Questionnaire(1, "avecQuestion");
        leQuestionnaire2 = new Questionnaire(1, "avecQuestion");
    }

    @Test
    void testQuestionnaireIntString() {
        System.out.println("voila la size");
        assertThrows(IllegalArgumentException.class, () -> {
            new Questionnaire(1, "sansQuestion");
        });
        try {
            new Questionnaire(1, "avecQuestion");
        } catch (IllegalArgumentException e) {
            fail("Renvoie une erreur alors qu'il ne devrait pas");
        }
    }

    @Test
    void testQuestionnaireIntStringInt() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Questionnaire(1, "sansQuestion", 5);
        });
        Question question5 = new Question(
                "La question 4 ?", categorieAvecQuestion, "oui", reponseFausse,
                "pas de feedback", 1);
        Donnees.listeQuestions.add(question5);
        try {
            new Questionnaire(1, "avecQuestion", 5);
        } catch (IllegalArgumentException e) {
            fail("Renvoie une erreur alors qu'il ne devrait pas");
        }
    }

    @Test
    void testStockerReponse() {
        leQuestionnaire1.stockerReponse(0, "la reponse 1");
        assertNotEquals(leQuestionnaire1.getListeReponseDonnee().get(0), "");
        assertEquals(leQuestionnaire1.getListeReponseDonnee().get(0), 
                "la reponse 1");

        assertThrows(IllegalArgumentException.class, () -> {
            leQuestionnaire1.stockerReponse(-1, "la reponse ?");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            leQuestionnaire1.stockerReponse(1, null);
        });

    }

    @Test
    void testLeTauxDeReussite() {
        assertEquals(leQuestionnaire1.leTauxDeReussite(), 0.0);
        leQuestionnaire1.stockerReponse(0, "oui");

        assertEquals(leQuestionnaire1.leTauxDeReussite(), 25.0);
        leQuestionnaire1.stockerReponse(1, "oui");
        leQuestionnaire1.stockerReponse(2, "oui");
        leQuestionnaire1.stockerReponse(3, "oui");
        assertEquals(leQuestionnaire1.leTauxDeReussite(), 100.0);
    }

    @Test
    void testGetQuestion() {
        assertThrows(IllegalArgumentException.class, () -> {
            leQuestionnaire1.getQuestion(-1);
        });
        assertEquals(leQuestionnaire1.getQuestion(1), question2);
    }

    @Test
    void testGetListeQuestion() {
        ArrayList<Question> listeTest = new ArrayList<>();
        listeTest.add(question1);
        listeTest.add(question2);
        listeTest.add(question3);
        listeTest.add(question4);

        assertTrue(leQuestionnaire1.getListeQuestion().containsAll(listeTest));
    }

    @Test
    void testGetListeReponseDonnee() {
        ArrayList<String> listeTest = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listeTest.add("");
        }
        assertTrue(leQuestionnaire2.getListeReponseDonnee()
                .containsAll(listeTest));
    }

}
