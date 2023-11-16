/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;

import iut.sae.ihm.controleur.testFile;
import iut.sae.modele.reseau.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Node;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 *
 */
public class ControleurExport {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private TextField idNom;

    @FXML
    private Button btnQuitter;

    @FXML
    private Label idLabelNom;
    
    @FXML
    private Label FichierAExporter;

    @FXML
    private Button btnValider;

    private void choixFichier(Stage stage1) {
       /* FileChooser fileChooser = new FileChooser();
        // Ajout d'un filtre pour ne montrer que certains fichiers
        ExtensionFilter extFilter = new ExtensionFilter("Fichiers texte(*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        
        stage1.getScene().getWindow();
        //Afficher la boîte de dialogue de choix de fichier
        //File fichierSelectionner = fileChooser.showOpenDialog();*/
        testFile.main(null);
    }
    
    @FXML
    void chercherFichier (ActionEvent event) {
        System.out.print("TODO");
    }
    
    @FXML
    void clicValider(ActionEvent event) {
     
    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
