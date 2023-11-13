/*
 * ControleurImpExp.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package src.iut.sae.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class ControleurImpExp {

    @FXML
    private Pane idPane;

    @FXML
    private Button btnPartDon;

    @FXML
    private Label idTitre;

    @FXML
    private Label idTitre1;

    @FXML
    private Button btnExpDon;

    @FXML
    private Button btnQuitter;

    @FXML
    private Button btnImpDon;

    @FXML
    void clicImpDon(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_IMPORT);
    }

    @FXML
    void clicExpDon(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_EXPORT);
    }

    @FXML
    void clicPartDon(ActionEvent event) {
        System.out.print("TODO");
    }
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
