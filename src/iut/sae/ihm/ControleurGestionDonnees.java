package iut.sae.ihm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        
        File fxmlFile = new File("src/iut/sae/ihm/creerCategorie.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
        
    }

    @FXML
    void clicPageQuest(ActionEvent event) throws Exception {
        File fxmlFile = new File("src/iut/sae/ihm/creerQuestion.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
    }

    @FXML
    void clicImpExp(ActionEvent event) {

    }

    @FXML
    void clicShareData(ActionEvent event) {

    }
    
    @FXML
    void clicQuitter(ActionEvent event) {

    }

}
