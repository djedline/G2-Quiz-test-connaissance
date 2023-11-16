
/*
 * Gère l'échange entre les vues affichées par la scène de l'application 05/23
 */
package iut.sae.ihm.controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class EchangeurDeVue {

    private static Map<Integer, Parent> cache;
    // création de la table cache
    static {
        cache = new HashMap<>();
    }
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
            
            cache.put(codeVue, racine);

            sceneCourante.setRoot(racine);
            Lanceur.resizeScene();
            //Lanceur.resizeScene(sceneCourante.getWidth(),sceneCourante.getHeight());
        } catch(IOException erreur) {
            // problème lors de l'accès au fichier décrivant la vue
            System.out.println("Echec du chargement de la vue de code " + codeVue);
        }
    }
}