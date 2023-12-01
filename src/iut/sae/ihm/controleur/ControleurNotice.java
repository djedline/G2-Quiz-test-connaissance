package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author catal
 *
 */
public class ControleurNotice {

    @FXML
    private Button btnRetour;

    @FXML
    private Pane idPane;

    @FXML
    void clicRetour(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
