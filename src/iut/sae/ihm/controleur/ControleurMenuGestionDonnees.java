/* ControleurMenuGestionDonnees.java                                 9 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;

/**
 * Controleur de la page pour la Gestion des Données
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurMenuGestionDonnees {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private Button btnPageCat;

    @FXML
    private Button btnPageQuest;

    @FXML
    private Button btnImpExp;

    @FXML
    private Button btnShareData;

    @FXML
    private Button btnQuitter;

    @FXML
    private Button btnGererDonnees;
    
    @FXML
    private Button btnModifierNom;

    @FXML
    void clicPageCat(ActionEvent event) throws Exception {
        Donnees.numScenePrecedenteCategorie = 
                EnsembleDesVues.VUE_MENU_GESTION_DONNEES;
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_CATEGORIE);
    }

    @FXML
    void clicPageQuest(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_QUESTION);
    }

    @FXML
    void clicGererDonnees(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
    }

    @FXML
    void clicQuitter(ActionEvent event) throws MalformedURLException {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

    @FXML
    void clicModifierNom(ActionEvent event) {
    	EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MODIFIER_NOM);
    }
    
}
