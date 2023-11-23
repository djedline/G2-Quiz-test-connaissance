
/*
 * Gère l'échange entre les vues affichées par la scène de l'application 05/23
 */
package iut.sae.ihm.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;


import iut.sae.ihm.controleur.ControleurMenuPrincipal;
import iut.sae.ihm.controleur.ControleurModifierCategorie;
import iut.sae.ihm.controleur.ControleurModifierQuestion;
import iut.sae.ihm.controleur.Lanceur;
import iut.sae.modele.Categorie;
import iut.sae.modele.Question;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/** TODO comment class responsibility (SRP)
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * leo.cheikh-boukal
 * @version 1.0
 */
public class EchangeurDeVue {

    
    /** Scene courante, ou scène qui est associée à la fenêtre principale */
    private static Scene sceneCourante;

    /** TODO comment method role
     * @param nouvelleScene
     */
    public static void setSceneCourante(Scene nouvelleScene) {
        sceneCourante = nouvelleScene;
    }
    
    

    /** TODO comment method role
     * @param codeVue
     */
    public static void echangerAvec(int codeVue) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException("Echange de vue impossible. Pas de scène courante.");
        }
        try {
            Parent racine; // recevra le conteneur racine de la vue à afficher
	
            racine = FXMLLoader.load(
            		EchangeurDeVue.class.getResource(EnsembleDesVues.getNomVue(codeVue)));
            // ajout de la vue à la table cache
            

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
            //Lanceur.resizeScene(sceneCourante.getWidth(),sceneCourante.getHeight());
        } catch(IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println("Echec du chargement de la vue de code " + codeVue);
            erreur.printStackTrace();
        }
    }
    
    /** TODO comment method role
     * @param laCategorie 
     * @param codeVue
     */
    public static void echangerAvec(Categorie laCategorie) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException("Echange de vue impossible. Pas de scène courante.");
        }
        try {
            // recevra le conteneur racine de la vue à afficher
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = new File("src/iut/sae/ihm/view/modifierCategorie.fxml").toURI().toURL();
            
            loader.setLocation(fxmlUrl);
            Parent racine = (Parent) loader.load();
            ControleurModifierCategorie controllerRef = 
            		(ControleurModifierCategorie) loader.getController();
            
            controllerRef.setCategorie(laCategorie);
            
            
            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
            //Lanceur.resizeScene(sceneCourante.getWidth(),sceneCourante.getHeight());
        } catch(IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println("Echec du chargement de la vue de code modifier categorie");
            erreur.printStackTrace();
        }
    }
    
    /** TODO comment method role
     * @param laQuestion 
     */
    public static void echangerAvec(Question laQuestion) {
        if (sceneCourante == null) {
            // pas de scène courante : impossible de modifier sa vue
            throw new IllegalStateException("Echange de vue impossible. Pas de scène courante.");
        }
        try {
        	// recevra le conteneur racine de la vue à afficher
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = new File("src/iut/sae/ihm/view/modifierQuestion.fxml").toURI().toURL();
            
            loader.setLocation(fxmlUrl);
            Parent racine = (Parent) loader.load();
            ControleurModifierQuestion controllerRef = 
            		(ControleurModifierQuestion) loader.getController();
            
            controllerRef.setQuestion(laQuestion);
            
            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
            //Lanceur.resizeScene(sceneCourante.getWidth(),sceneCourante.getHeight());
        } catch(IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println("Echec du chargement de la vue de code modifier question");
            erreur.printStackTrace();
        }
    }
}