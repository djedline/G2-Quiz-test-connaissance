/*
 * QuestionTest.java                                    24 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import iut.sae.modele.Categorie;
import iut.sae.modele.Question;

/**
 * Utilisation de la classe Question et test de ses méthodes
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
class QuestionTest {

    private static ArrayList<Question> listeQuestion = new ArrayList<>();
    
    static String libelle = "Quel question ?";
    
    static Categorie nomCategorie = new Categorie("absurde");
    
    static String propoJuste = "Bonne question ?";
    
    static String[] propoFausse = { 
            "J'ai pas la reponse", "J'ai la reponse", 
            "c'est quoi cette question ?" };
    
    static String feedback = "La réponse est 'Bonne question ?' car "
            + "la reponse est compliqué et que c'est réellement" 
            + " une bonne question";
    
    static int diff = 2;

    /**
     * 
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        listeQuestion.add(new Question(libelle, nomCategorie, propoJuste, 
                                       propoFausse, feedback, diff));

    }

    /**
     * Test method for
     * {@link src.iut.sae.modele.Question#Question
     * (java.lang.String, src.iut.sae.modele.Categorie, java.lang.String, 
     * java.lang.String[], java.lang.String, int)}.
     */
    @Test
    void testQuestion() {
        String[] tableauVide = {};
        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    "", nomCategorie, propoJuste, propoFausse, feedback, diff));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    libelle, null, propoJuste, propoFausse, feedback, diff));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    libelle, nomCategorie, "", propoFausse, feedback, diff));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    libelle, nomCategorie, propoJuste, tableauVide, feedback, 1));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    libelle, nomCategorie, propoJuste, propoFausse, feedback, 5));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            listeQuestion.add(new Question(
                    " ", null, " ", tableauVide, " ", 0));
        });

        assertTrue(listeQuestion.add(new Question(
                libelle, nomCategorie, propoJuste, propoFausse, feedback, 
                diff)));
    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#getLibelle()}.
     */
    @Test
    void testGetLibelle() {
        assertEquals(listeQuestion.get(0).getLibelle(), "Exemple libelle");
    }

    /**
     * Test method for
     * {@link src.iut.sae.modele.Question#setLibelle(java.lang.String)}.
     */
    @Test
    void testSetLibelle() {
        assertTrue(listeQuestion.get(0).setLibelle("Exemple libelle"));
        assertFalse(listeQuestion.get(0).setLibelle(""));
        assertFalse(listeQuestion.get(0).setLibelle("   "));
    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#getCategorie()}.
     */
    @Test
    void testGetCategorie() {
        assertEquals(listeQuestion.get(0).getCategorie(), nomCategorie);
    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#setCategorie()}.
     */
    @Test
    void testSetCategorie() {
        assertFalse(listeQuestion.get(0).setCategorie(null));
        assertTrue(listeQuestion.get(0).setCategorie(nomCategorie));

    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#getPropositionJuste()}
     */
    @Test
    void testGetPropositionJuste() {
        assertEquals(listeQuestion.get(0).getPropositionJuste(), 
                "Exemple proposition juste");
    }

    /**
     * Test method for
     * {@link src.iut.sae.modele.Question#setPropositionJuste(java.lang.String)}
     */
    @Test
    void testSetPropositionJuste() {
        assertTrue(listeQuestion.get(0).setPropositionJuste(
                "Exemple proposition juste"));
        assertFalse(listeQuestion.get(0).setPropositionJuste(""));
        assertFalse(listeQuestion.get(0).setPropositionJuste(" "));
    }

    /**
     * Test method for 
     * {@link src.iut.sae.modele.Question#getPropositionFausse()}
     */
    @Test
    void testGetPropositionFausse() {
        int comp = 0;
        System.out.println(listeQuestion.get(0).getPropositionFausse().size());
        for (String laRepFausse : listeQuestion.get(0).getPropositionFausse()) {
            
            assertEquals(laRepFausse, propoFausse[comp]);
            comp++;
        }

    }

    /**
     * Test method for
     * {@link src.iut.sae.modele.Question#setPropositionFausse
     * (java.lang.String[])}
     */
    @Test
    void testSetPropositionFausse() {
        String[] mauvaisePropFausse = { "a", "", "b" };
        String[] vide = {};
        assertFalse(listeQuestion.get(0).setPropositionFausse(
                mauvaisePropFausse));
        assertFalse(listeQuestion.get(0).setPropositionFausse(vide));
        assertTrue(listeQuestion.get(0).setPropositionFausse(propoFausse));

    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#getFeedback()}.
     */
    @Test
    void testGetFeedback() {
        assertEquals(listeQuestion.get(0).getFeedback(), " ");
    }

    /**
     * Test method for
     * {@link src.iut.sae.modele.Question#setFeedback(java.lang.String)}.
     */
    @Test
    void testSetFeedback() {
        assertTrue(listeQuestion.get(0).setFeedback("Exemple feedBack"));
        assertTrue(listeQuestion.get(0).setFeedback(""));
        assertTrue(listeQuestion.get(0).setFeedback(" "));
    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#getDifficulte()}.
     */
    @Test
    void testGetDifficulte() {
        assertEquals(listeQuestion.get(0).getDifficulte(), 2);
    }

    /**
     * Test method for {@link src.iut.sae.modele.Question#setDifficulte(int)}.
     */
    @Test
    void testSetDifficulte() {
        assertTrue(listeQuestion.get(0).setDifficulte(1));
        assertTrue(listeQuestion.get(0).setDifficulte(3));
        assertFalse(listeQuestion.get(0).setDifficulte(0));
        assertFalse(listeQuestion.get(0).setDifficulte(4));
    }

}
