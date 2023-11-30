package iut.sae.ihm.controleur;

import java.io.File;
import java.net.URL;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Classe controleur du menu principal de l'application
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
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
    private Button btnNotice;
    
    @FXML
    void clicStart(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_CREER_QUESTIONNAIRE);
    }

    @FXML
    void clicGerer(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MENU_GESTION_DONNEES);
    }

    @FXML
    void clicDon(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

    @FXML
    void clicNotice(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_NOTICE);
    }
    
}
