/*
 * ControleurCreeCategorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import iut.sae.modele.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**Controleur de la page pour la création de catégories
 * @author nael.briot
 *
 */
public class ControleurCreeCategorie {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private Button btnCreerCat;
    
    @FXML
    private Button btnQuitter;

    @FXML
    private TextField idNom;

    @FXML
    private Label idLabelNom;

    @FXML
    Categorie clicCreer(ActionEvent event) {
        try {
            Categorie nouvelleCategorie = new Categorie(idNom.getText());
            System.out.println(nouvelleCategorie);
            return nouvelleCategorie;
        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Le nom ne doit pas être vide.");
            messageErreur.show();
        }
        return null;
        
    }
    @FXML
    Categorie clicQuitter(ActionEvent event) throws Exception { 
        File fxmlFile = new File("src/iut/sae/ihm/MenuGestionDonnees.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
            return null;
    }

   
}