package src.iut.sae.ihm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import src.iut.sae.modele.Donnees;

/**Controleur de la page pour la Gestion des Données
 * @author nael.briot
 *
 */
public class ControleurGestionDonnees {

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
    void clicPageCat(ActionEvent event) throws Exception {
        Donnees.numScenePrecedenteCategorie = EnsembleDesVues.VUE_GESTION_DONNEES;
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_CATEGORIE);
    }

    @FXML
    void clicPageQuest(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_QUESTION);
    }

    @FXML
    void clicImpExp(ActionEvent event) {

    }

    @FXML
    void clicShareData(ActionEvent event) {

    }
    
    @FXML
    void clicQuitter(ActionEvent event) throws MalformedURLException {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
