/*
 * ControleurImport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Classe controleur de la page Import
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurImport {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private TextField idNom;

    @FXML
    private Button btnQuitter;

    @FXML
    private Label idLabelNom;

    @FXML
    private Button btnValider;

    @FXML
    void clicValider(ActionEvent event) {
        System.out.print("TODO");
    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
