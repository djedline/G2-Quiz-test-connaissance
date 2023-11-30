/*
 * DonneesTest.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Persistance;
import iut.sae.modele.Question;

/**
 * Classe unitaire de la classe Donnees.
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
class PersistanceTest {

    /**
     * Crée les fixtures de test. ATTENTION ces tests réunitialise les sauvegardes.
     * 
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        Persistance.effacerSauvegarde();
        Donnees.reinitialiserDonnees();
    }

    @Test
    public void testAppliVide() {
        // vérification de l'existence de Général au démarrage
        assertEquals(1, Donnees.listeCategorie.size());
        assertEquals("Général", Donnees.listeCategorie.get(0).getLibelle());

        fermetureAppli();

        // Vérifie l'existence des fichiers
        assertTrue(Persistance.FICH_CATEGORIES.exists());
        assertTrue(Persistance.FICH_QUESTIONS.exists());
    }

    /**
     * Vérifie qu'une question ajoutée est bien sauvegardée et restituée
     */
    @Test
    public void testAjoutQuestion() {
    	Persistance.chargerSansImport();
        creerQuestion(Donnees.listeCategorie.get(0));
        fermetureAppli();

        Persistance.chargerSansImport();
        assertEquals(1, Donnees.listeQuestions.size());
        assertEquals("Intitulé", Donnees.listeQuestions.get(0).getLibelle());
        assertEquals(Donnees.listeCategorie.get(0), 
        		Donnees.listeQuestions.get(0).getCategorie());
        fermetureAppli();
    }

    /**
     * Vérifie qu'une catégorie ajoutée est bien sauvegardée et restituée
     */
    @Test
    public void testAjoutCategorie() {
        Persistance.chargerSansImport();
        creerCategorie("Catégorie de test");
        fermetureAppli();

        Persistance.chargerSansImport();
        assertEquals(2, Donnees.listeCategorie.size());
        assertEquals("Catégorie de test", 
        		Donnees.listeCategorie.get(1).getLibelle());
        fermetureAppli();
    }

    /**
     * Réplique les actions de sauvegarde à la
     */
    private void fermetureAppli() {
        Persistance.sauvegarder();
    }

    /**
     * Réplique la création d'une catégorie depuis l'IHM
     * 
     * @param nom le nom de la catégorie
     */
    private void creerCategorie(String nom) {
        Categorie nouvelleCategorie = new Categorie(nom);
        if (!Donnees.verifDoubleCategorie(nouvelleCategorie)) {
            Donnees.listeCategorie.add(nouvelleCategorie);
        }
    }

    /**
     * Réplique la création d'une question depuis l'IHM
     * 
     * @param cat la catégorie de la question
     */
    private void creerQuestion(Categorie cat) {
        Question nouvelleQuestion = new Question("Intitulé", cat, "Juste", new String[] { "Faux" }, "Feedback", 1);

        if (!Donnees.verifDoubleQuestion(nouvelleQuestion)) {
            Donnees.listeQuestions.add(nouvelleQuestion);
        }
    }

}
