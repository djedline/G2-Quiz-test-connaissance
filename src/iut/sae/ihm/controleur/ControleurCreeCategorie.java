/*
 * ControleurCreeCategorie.java                                    18 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.modele.Categorie;
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
 * Controleur de la page pour la création de catégories
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
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
    void clicCreer(ActionEvent event) {
        try {
            Categorie nouvelleCategorie = new Categorie(idNom.getText());
            if (!Donnees.verifDoubleCategorie(nouvelleCategorie)) {
                Donnees.listeCategorie.add(nouvelleCategorie);
                System.out.println(nouvelleCategorie);
            } else {
                Alert messageErreur = new Alert(AlertType.ERROR);
                messageErreur.setContentText("La categorie existe déjà.");
                messageErreur.show();
            }
        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Le nom ne doit pas être vide.");
            messageErreur.show();
        }
        idNom.setText(null);
    }

    @FXML
    void clicQuitter(ActionEvent event) throws Exception {
        EchangeurDeVue.echangerAvec(Donnees.numScenePrecedenteCategorie);
    }

}