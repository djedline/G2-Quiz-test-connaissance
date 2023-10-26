/*
 * ControleurCreeCategorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm;


import iut.sae.modele.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author djedline.boyer
 *
 */
public class ControleurCreeCategorie {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private Button btnCreer;

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

   
}