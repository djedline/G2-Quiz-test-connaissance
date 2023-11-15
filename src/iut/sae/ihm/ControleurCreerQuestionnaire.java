package iut.sae.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
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
