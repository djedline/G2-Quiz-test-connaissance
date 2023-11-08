/*
 * ControleurCreeCategorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm;


import iut.sae.modele.Categorie;
import iut.sae.modele.Question;
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
public class ControleurCreeQuestion {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;
    
    @FXML
    private Button btnCreer;

    @FXML
    private Button btnQuitter;

    @FXML
    private TextField idNom;

    @FXML
    private Label idLabelNom;

    @FXML
    Question clicCreer(ActionEvent event) {
        try {
            Question nouvelleQuestion = new Question(idNom.getText(), null, null, null, null, 0);
            System.out.println(nouvelleQuestion);
            return nouvelleQuestion;
        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Le nom ne doit pas être vide.");
            messageErreur.show();
        }
        return null;
        
    }

   
}