/*
 * fonctionAffichage.java                                    7 déc. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/** TODO comment class responsibility (SRP)
 * @author catal
 *
 */
public class fonctionAffichage {


    /** TODO comment method role
     * @param leLabel
     * @param leTexte 
     */
    public static void btnRetourUtilisateur(Label leLabel, String leTexte) {
        
        leLabel.setText(leTexte);
        leLabel.getStyleClass().add("labelCreation");
        
        leLabel.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
                );
        visiblePause.setOnFinished(
                event -> leLabel.setVisible(false)
                );
        visiblePause.play();
    }

    /** TODO comment method role
     * @param texteDeBase 
     * @param texteAAjouter 
     * @param index
     * @return le string
     */
    public static String insertionDansString(String texteDeBase, String texteAAjouter, int index) {
        String textBegin = texteDeBase.substring(0,index);
        String textEnd = texteDeBase.substring(index);
        return textBegin + texteAAjouter + textEnd;
    }


}
