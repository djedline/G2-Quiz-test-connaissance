/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;

import iut.sae.modele.reseau.Client;
import iut.sae.modele.reseau.Serveur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** 
 * Controleur Serveur
 * @author djedline.boyer
 *
 */
public class ControleurServeur {

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private TextField idNom;

    @FXML
    private Button btnQuitter;

    @FXML
    private Label adresseIPServeur;
    
    @FXML
    private Label FichierAExporter;

    @FXML
    private Button btnValider;
    
    @FXML
    void clicDemarrer(ActionEvent event) {
        String message;
        String s;
        s ="";
        message ="";
        File fichierATraiter;
        fichierATraiter = new File(FichierAExporter.getText());
        System.out.println(Serveur.preparerServeur());
        /*try {
            message = Client.construireMessage(fichierATraiter);
            Client.envoyerMessage(message.getBytes());
            s = Client.recevoirMessage();
            Client.fermerSocket();
        } catch (IOException | InterruptedException e) {
            System.out.println("Problème avec le fichier");
            e.printStackTrace();
        }*/
    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
