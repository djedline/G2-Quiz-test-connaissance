/*
 * QuestionTest.java                                    24 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import iut.sae.modele.Categorie;
import iut.sae.modele.Question;

/**Utilisation de la classe Question et test de ses méthodes
 * @author djedline.boyer
 */
class QuestionTest {

    private static ArrayList<Question> listeQuestion = new ArrayList<>();
    static String libelle = "Quel question ?";
    static Categorie nomCategorie = new Categorie("absurde");
    static String propoJuste = "Bonne question ?";
    static String[] propoFausse = {"J'ai pas la reponse","J'ai la reponse",
    "c'est quoi cette question ?"};
    static String feedback = "La réponse est 'Bonne question ?' car "
            +"la reponse est compliqué et que c'est réellement" 
            + " une bonne question";
    static int diff = 2;
    
    /** TODO comment method role
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        listeQuestion.add(new Question(libelle, nomCategorie, propoJuste,
                 propoFausse,feedback,diff));
         
    }

    /** TODO comment method role
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * Test method for {@link iut.sae.modele.Question#Question(java.lang.String, iut.sae.modele.Categorie, java.lang.String, java.lang.String[], java.lang.String, int)}.
     */
    @Test
    void testQuestion() {
        String[] tableauVide = {};
        String[] tableauAvecRien = {" ", " "};
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question("", nomCategorie, propoJuste,
                propoFausse,feedback,diff));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(libelle, null, propoJuste,
                propoFausse,feedback,diff));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(libelle, nomCategorie, "",
                propoFausse,feedback,diff));
        });
        
       /* assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(libelle, nomCategorie, propoJuste,
                tableauVide,feedback,diff));
        });*/
        
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(libelle, nomCategorie, propoJuste,
                propoFausse,"",diff));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(libelle, nomCategorie, propoJuste,
                propoFausse,"",5));
        });
        
        assertThrows(IllegalArgumentException.class, () -> {listeQuestion.add(new Question(" ", null, " ", tableauVide," ",0));
        });
        
        assertTrue(listeQuestion.add(new Question(libelle, nomCategorie, propoJuste,
                propoFausse,feedback,diff)));
    }

    /**
     * Test method for {@link iut.sae.modele.Question#getLibelle()}.
     */
    @Test
    void testGetLibelle() {
        assertEquals(listeQuestion.get(0).getLibelle(), "Exemple libelle");       
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setLibelle(java.lang.String)}.
     */
    @Test
    void testSetLibelle() {
        assertTrue(listeQuestion.get(0).setLibelle("Exemple libelle"));
        assertFalse(listeQuestion.get(0).setLibelle(""));
        assertFalse(listeQuestion.get(0).setLibelle("   "));
    }

    /**
     * Test method for {@link iut.sae.modele.Question#getCategorie()}.
     */
    @Test
    void testGetCategorie() {
        assertEquals(listeQuestion.get(0).getCategorie(), nomCategorie);
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setCategorie()}.
     */
    @Test
    void testSetCategorie() {
        assertFalse(listeQuestion.get(0).setCategorie(null));
        assertTrue(listeQuestion.get(0).setCategorie(nomCategorie));
        
    }
    
    /**
     * Test method for {@link iut.sae.modele.Question#getPropositionJuste()}.
     */
    @Test
    void testGetPropositionJuste() {
        assertEquals(listeQuestion.get(0).getPropositionJuste(), "Exemple proposition juste");
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setPropositionJuste(java.lang.String)}.
     */
    @Test
    void testSetPropositionJuste() {
        assertTrue(listeQuestion.get(0).setPropositionJuste("Exemple proposition juste"));
        assertFalse(listeQuestion.get(0).setPropositionJuste(""));
        assertFalse(listeQuestion.get(0).setPropositionJuste(" "));
    }

    /**
     * Test method for {@link iut.sae.modele.Question#getPropositionFausse()}.
     */
    @Test
    void testGetPropositionFausse() {
        assertArrayEquals(listeQuestion.get(0).getPropositionFausse(), propoFausse);
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setPropositionFausse(java.lang.String[])}.
     */
    @Test
    void testSetPropositionFausse() {
        String[] mauvaisePropFausse = {"a", "", "b" };
        String[] vide = {};
        assertFalse(listeQuestion.get(0).setPropositionFausse(mauvaisePropFausse));
        assertFalse(listeQuestion.get(0).setPropositionFausse(vide));
        assertTrue(listeQuestion.get(0).setPropositionFausse(propoFausse));
        
    }

    /**
     * Test method for {@link iut.sae.modele.Question#getFeedback()}.
     */
    @Test
    void testGetFeedback() {
        assertEquals(listeQuestion.get(0).getFeedback(), "Exemple feedBack");
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setFeedback(java.lang.String)}.
     */
    @Test
    void testSetFeedback() {
        assertTrue(listeQuestion.get(0).setFeedback("Exemple feedBack"));
        assertFalse(listeQuestion.get(0).setFeedback(""));
        assertFalse(listeQuestion.get(0).setFeedback(" "));
    }

    /**
     * Test method for {@link iut.sae.modele.Question#getDifficulte()}.
     */
    @Test
    void testGetDifficulte() {
        assertEquals(listeQuestion.get(0).getDifficulte(), 2);
    }

    /**
     * Test method for {@link iut.sae.modele.Question#setDifficulte(int)}.
     */
    @Test
    void testSetDifficulte() {
        assertTrue(listeQuestion.get(0).setDifficulte(1));
        assertTrue(listeQuestion.get(0).setDifficulte(3));
        assertFalse(listeQuestion.get(0).setDifficulte(0));
        assertFalse(listeQuestion.get(0).setDifficulte(4));
    }

}
