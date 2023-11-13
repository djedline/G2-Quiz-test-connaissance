package iut.sae.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class ControleurQuestionnaire {

    @FXML
    private Pane idPane;

    @FXML
    private TextField idNom;

    @FXML
    private Button btnQuitter;

    @FXML
    private Label idLabelNom2;

    @FXML
    private Label idLabelNom1;

    @FXML
    private Label idLabelNom;

    @FXML
    private Button btnValider;

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
