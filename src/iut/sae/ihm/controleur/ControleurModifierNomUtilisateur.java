/* ControleurModifierCategorie.java                                 18 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Controleur de la page pour la modification de catégories
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurModifierNomUtilisateur {

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
    void clicCreer(ActionEvent event) {
        try {

            if (!idNom.getText().isBlank()) {
                Donnees.setNomUtilisateur(idNom.getText());
                System.out.println(idNom.getText());
            } else {
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Le nom ne doit pas être vide.");
            messageErreur.show();
        }

    }

    @FXML
    void clicQuitter(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MENU_GESTION_DONNEES);
    }

}