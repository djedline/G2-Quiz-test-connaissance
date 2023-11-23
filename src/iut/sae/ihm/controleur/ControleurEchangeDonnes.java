/*
 * ControleurImpExp.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/** 
 * Classe controleur permettant de gérer la page de gestion d'échange des données
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurEchangeDonnes {

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
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PARTAGER);
    }
    
    @FXML
    void infoServeur(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_SERVEUR);
    }
    
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
