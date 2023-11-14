/*
 * DonneesTest.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.sae.ihm.Lanceur;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;

/**
 * Classe unitaire de la classe Donnees.
 * 
 * @author leila.baudroit
 */
class DonneesTest {

    /**
     * Crée les fixtures de test.
     * 
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        Donnees.FICH_CATEGORIES.delete();
        Donnees.FICH_QUESTIONS.delete();

        lancementAppli();

        // vérification de l'existence de Général au démarrage
        assertEquals(Donnees.listeCategorie.size(), 1);
        assertEquals(Donnees.listeCategorie.get(0).getLibelle(), "Général");

        fermetureAppli();

        // Vérifie l'existence des fichiers
        assertTrue(Donnees.FICH_CATEGORIES.exists());
        assertTrue(Donnees.FICH_QUESTIONS.exists());
        
        testAjoutCategorie();
        testAjoutQuestion();
    }

    /** 
     * Vérifie qu'une question ajoutée est bien sauvegardée et restituée
     */
    private void testAjoutQuestion() {
        lancementAppli();
        creerQuestion(Donnees.listeCategorie.get(1));
        fermetureAppli();
        
        lancementAppli();
        assertEquals(Donnees.listeQuestions.size(), 1);
        assertEquals(Donnees.listeQuestions.get(0).getLibelle(), "Intitulé");
        assertEquals(Donnees.listeQuestions.get(0).getCategorie(), 
                Donnees.listeCategorie.get(1));
        fermetureAppli();
    }

    /**
     * Vérifie qu'une catégorie ajoutée est bien sauvegardée et restituée
     */
    private void testAjoutCategorie() {
        lancementAppli();
        creerCategorie("Catégorie de test");
        fermetureAppli();
        
        lancementAppli();
        assertTrue(Donnees.listeCategorie.size() == 2);
        assertEquals(Donnees.listeCategorie.get(1).getLibelle(), "Catégorie de test");
        fermetureAppli();
    }

    void lancementAppli() {
        Donnees.charger();
    }

    void fermetureAppli() {
        Donnees.sauvegarder();
    }
    
    void creerCategorie(String nom) {
        Categorie nouvelleCategorie = new Categorie(nom);
        if (!Donnees.verifDoubleCategorie(nouvelleCategorie)) {
            Donnees.listeCategorie.add(nouvelleCategorie);
        }
    }

    void creerQuestion(Categorie cat) {
        Question nouvelleQuestion = new Question("Intitulé", cat, "Juste", new String[] { "Faux" }, "Feedback", 1);

        if (!Donnees.verifDoubleQuestion(nouvelleQuestion)) {
            Donnees.listeQuestions.add(nouvelleQuestion);
        }
    }

}
