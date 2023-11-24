/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
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
 * Classe controleur de la page Serveur
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
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
    private Button btnDemarrer;

    boolean allumageOk = false;

    @FXML
    void clicDemarrer(ActionEvent event) {
        if (!allumageOk) {
            adresseIPServeur.setText(Serveur.preparerServeur());
            btnDemarrer.setText("Eteindre");
            allumageOk = true;
        } else {
            Serveur.fermetureServeur();
            allumageOk = false;
            btnDemarrer.setText("Demarrer");
            adresseIPServeur.setText("");
        }
        /*
         * try { message = Client.construireMessage(fichierATraiter);
         * Client.envoyerMessage(message.getBytes()); s = Client.recevoirMessage();
         * Client.fermerSocket(); } catch (IOException | InterruptedException e) {
         * System.out.println("Problème avec le fichier"); e.printStackTrace(); }
         */
    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
