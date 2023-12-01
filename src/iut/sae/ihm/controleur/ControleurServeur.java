/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import iut.sae.modele.reseau.DiffieHellman;
import iut.sae.modele.reseau.Serveur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
    private Label adresseIpServeur;

    @FXML
    private Label FichierAExporter;

    @FXML
    private Button btnDemarrer;

    private Serveur serveurPartage = new Serveur();
    // boolean allumageOk = false;

    /**
     * Initialise la liste déroulante
     */
    @FXML
    void initialize() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            Donnees.adresseIpServeur = ip.getHostAddress();
            adresseIpServeur.setText(Donnees.adresseIpServeur);
        } catch (UnknownHostException e) {
            System.out.println("Problème à la récupérartion de l'adresse IP");
            e.printStackTrace();
        }
        if (Donnees.serveurAllumee) {
            btnDemarrer.setText("Eteindre");
        }
    }

    @FXML
    void clicDemarrer(ActionEvent event) throws IOException, InterruptedException {
        // System.out.println(allumageOk);
        // System.out.println(!allumageOk);
        if (!Donnees.serveurAllumee) {
            String contenuFichier;
            int cle;
            System.out.println("Salut");
            btnDemarrer.setText("Eteindre");
            Donnees.serveurAllumee = true;
            // serveurPartage.preparerServeur();
            Serveur s = new Serveur();
            s.accepterConnexion();
            cle = s.envoiDonneesInitiale();
            contenuFichier = s.receptionFichier(cle);
            
        } else {
            System.out.println("Au revoir");
            serveurPartage.fermetureServeur();
            Donnees.serveurAllumee = false;
            btnDemarrer.setText("Demarrer");
            adresseIpServeur.setText("");
        }
    }
    
    /**
     * Partage un fichier
     */
   /* public void ReceptionFichier() {
        try {
            serveurPartage.accepterConnexion();
            String recu = "";
            while (recu.isEmpty()) {
                System.out.print("Génération et envoi du générateur g " 
                        + " et du modulo p");
                try {
                    System.out.println("Le serveur a envoyé la puissance x");
                    serveurPartage.envoyerMessage((msgX.getBytes()));
                 } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // recu = Serveur.recevoirMessage(FICHIER_RECEPTION, cle);
            } 
        } catch (Exception e) {
            e.printStackTrace();
      //}
    } */

    /*
     * try { message = Client.construireMessage(fichierATraiter);
     * Client.envoyerMessage(message.getBytes()); s = Client.recevoirMessage();
     * Client.fermerSocket(); } catch (IOException | InterruptedException e) {
     * System.out.println("Problème avec le fichier"); e.printStackTrace(); }
     */

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
