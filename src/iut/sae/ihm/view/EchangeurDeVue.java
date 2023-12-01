/*
 * EchangeurDeVue.java                                    26 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import iut.sae.ihm.controleur.ControleurChoixQuestionExport;
import iut.sae.ihm.controleur.ControleurModifierCategorie;
import iut.sae.ihm.controleur.ControleurModifierQuestion;
import iut.sae.ihm.controleur.Lanceur;
import iut.sae.modele.Categorie;
import iut.sae.modele.Question;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Classe permettant l'échange entre les vues affichées par la scène de
 * l'application
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class EchangeurDeVue {

    /** Scene courante, ou scène qui est associée à la fenêtre principale */
    private static Scene sceneCourante;

    /**
     * Méthode qui permet de définir une nouvelle scène
     * 
     * @param nouvelleScene la nouvelle scène
     */
    public static void setSceneCourante(Scene nouvelleScene) {
        sceneCourante = nouvelleScene;
    }

    /**
     * Change la scène affichée à l'utilisateur par la vue définie la classe 
     * {@code EnsembleDesVues}
     * 
     * @param codeVue le numéro de la vue défini dans {@code EnsembleDesVues}
     */
    public static void echangerAvec(int codeVue) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException(
                    "Echange de vue impossible. Pas de scène courante.");
        }
        try {
            Parent racine; // recevra le conteneur racine de la vue à afficher

            racine = FXMLLoader.load(
                    EchangeurDeVue.class.getResource(
                            EnsembleDesVues.getNomVue(codeVue)));
            // ajout de la vue à la table cache

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
            // Lanceur.resizeScene(sceneCourante.getWidth(),
            // sceneCourante.getHeight());
        } catch (IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println("Echec du chargement de la vue de code " 
                            + codeVue);
            erreur.printStackTrace();
        }
    }

    /**
     * Change la scène affichée à l'utilisateur par la scène de modification
     * de catégorie en lui passant la catégorie à modifier en paramètre
     * 
     * @param laCategorie la catégorie à modifier
     */
    public static void echangerAvec(Categorie laCategorie) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException(
                    "Echange de vue impossible. Pas de scène courante.");
        }
        try {
            // recevra le conteneur racine de la vue à afficher
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = new File(
                    "src/iut/sae/ihm/view/modifierCategorie.fxml").toURI()
                    .toURL();

            loader.setLocation(fxmlUrl);
            Parent racine = (Parent) loader.load();
            ControleurModifierCategorie controllerRef = 
                    (ControleurModifierCategorie) loader.getController();

            controllerRef.setCategorie(laCategorie);

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();

        } catch (IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println(
                    "Echec du chargement de la vue de code modifier categorie");
            erreur.printStackTrace();
        }
    }

    /**
     * Change la scène affichée à l'utilisateur par la scène de modification
     * de question en lui passant la question à modifier en paramètre
     * 
     * @param laQuestion la question à modifier
     */
    public static void echangerAvec(Question laQuestion) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException(
                    "Echange de vue impossible. Pas de scène courante.");
        }
        try {
            // recevra le conteneur racine de la vue à afficher
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = new File(
                    "src/iut/sae/ihm/view/modifierQuestion.fxml").toURI()
                    .toURL();

            loader.setLocation(fxmlUrl);
            Parent racine = (Parent) loader.load();
            ControleurModifierQuestion controllerRef = 
                    (ControleurModifierQuestion) loader.getController();

            controllerRef.setQuestion(laQuestion);

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();

        } catch (IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println(
                    "Echec du chargement de la vue de code modifier question");
            erreur.printStackTrace();
        }
    }

    /**
     * Change la scène affichée à l'utilisateur par celle du menu de sélection 
     * des questions à exporter en lui passant en paramètre le fichier d'exportation.
     * 
     * @param f le fichier d'exportation dans lequel les questions sélectionnées
     * seront écrites
     */
    public static void echangerAvec(File f) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException(
                    "Echange de vue impossible. Pas de scène courante.");
        }
        try {
            // recevra le conteneur racine de la vue à afficher
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = new File(
                    "src/iut/sae/ihm/view/ChoixQuestionsExportation.fxml")
                    .toURI().toURL();

            loader.setLocation(fxmlUrl);
            Parent racine = (Parent) loader.load();
            ControleurChoixQuestionExport controllerRef = 
                    (ControleurChoixQuestionExport) loader.getController();

            controllerRef.setExportFile(f);

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
        } catch (IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println(
                    "Echec du chargement de la vue pour choisir la question");
            erreur.printStackTrace();
        }
    }
}