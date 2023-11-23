package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

/** 
 * Classe controleur permettant de gérer la création du questionnaire
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurCreerQuestionnaire {

    @FXML
    private Pane idPane;

    @FXML
    private MenuButton idDifficulte;

    @FXML
    private MenuButton idNbQuestion;

    @FXML
    private Button btnQuitter;

    @FXML
    private MenuButton idCategorie;

    @FXML
    private Label idLabelNom2;

    @FXML
    private Label idLabelNom1;

    @FXML
    private Label idLabelNom;

    @FXML
    private Button btnValider;

    
    /** TODO comment method role
     * 
     */
    @FXML
    void initialize() {
        idDifficulte.getItems().add(new MenuItem("Facile"));
        idDifficulte.getItems().add(new MenuItem("Moyen"));
        idDifficulte.getItems().add(new MenuItem("Difficile"));
        
        idNbQuestion.getItems().add(new MenuItem("5"));
        idNbQuestion.getItems().add(new MenuItem("10"));
        idNbQuestion.getItems().add(new MenuItem("20"));
        
        for (Categorie element : Donnees.listeCategorie) {
                idCategorie.getItems().add(new MenuItem(element.getLibelle()));
        }


    }
    @FXML
    void clicValider(ActionEvent event) {
        
        
        System.out.print("TODO");
    }

    @FXML
    void entreNom(ActionEvent event) {

    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
