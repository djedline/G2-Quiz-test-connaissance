/**
 * Sample Skeleton for 'GestionDonnees.fxml' Controller Class
 */

package iut.sae.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;

/** 
 * Controleur de la scène MenuGestionDonnees
 * @author djedline.boyer
 *
 */
public class ControleurGestionDonnees {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnnuler"
    private Button btnAnnuler; // Value injected by FXMLLoader

    @FXML // fx:id="treeViewData"
    private TreeView<?> treeViewData; // Value injected by FXMLLoader

    @FXML
    void retourArriere(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAnnuler != null : "fx:id=\"btnAnnuler\" was not injected: check your FXML file 'GestionDonnees.fxml'.";
        assert treeViewData != null : "fx:id=\"treeViewData\" was not injected: check your FXML file 'GestionDonnees.fxml'.";

    }

}
