package iut.sae.ihm;

import java.io.File;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class ControleurMenuPrincipal {

    @FXML
    private Pane idPane;

    @FXML
    private Button btnGestion;

    @FXML
    private Label idTitre;

    @FXML
    private Button btnQuizStart;

    @FXML
    void clicStart(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_QUESTIONNAIRE);
    }

    @FXML
    void clicGerer(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
    } 
    @FXML
    void clicDon(ActionEvent event)throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }


}
