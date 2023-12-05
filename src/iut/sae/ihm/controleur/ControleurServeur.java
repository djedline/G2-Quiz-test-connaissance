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
import iut.sae.modele.ImportExport;
import iut.sae.modele.reseau.Serveur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private Serveur serveurPartage;
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
            Donnees.serveurAllumee = false;
        } catch (UnknownHostException e) {
            new Alert(AlertType.ERROR, "Problème à la récupérartion de l'adresse IP")
            		.show();
        }
        if (Donnees.serveurAllumee) {
            btnDemarrer.setText("Eteindre");
        }
    }

    @FXML
    void clicDemarrer(ActionEvent event) throws IOException, InterruptedException {
        if (!Donnees.serveurAllumee) {
            System.out.println("Salut");
            testAlert();
            //btnDemarrer.setText("Éteindre");
           // Donnees.serveurAllumee = true;
            //gestionServeur();
            // serveurPartage.preparerServeur();
        } else {
            System.out.println("Au revoir");
            serveurPartage.fermetureServeur();
            Donnees.serveurAllumee = false;
            btnDemarrer.setText("Démarrer");
            adresseIpServeur.setText("");
        }
    }
    
    /**
     * Permet de démarrer le serveur et de receptionner des questions de la part d'un client
     * Erreur si aucun client trouvé
     */
    public void testAlert() {
    	Alert alert = new Alert(Alert.AlertType.WARNING);
    	alert.setTitle("Avertissement");
    	alert.setHeaderText("Ceci est un avertissement.");
    	alert.setContentText("Plus de détails peuvent être ajoutés ici.");
    	alert.showAndWait();
    }
    
    
    /**
     * Permet de démarrer le serveur et de receptionner des questions de la part d'un client
     * Erreur si aucun client trouvé
     */
    public void gestionServeur() {
    	boolean connexionEtablie;
        try {
            serveurPartage = new Serveur();
            serveurPartage.accepterConnexion(10);
            connexionEtablie = serveurPartage.accepterConnexion(30); // Attendre 30 secondes au maximum

            if (connexionEtablie) {
            	int cle = serveurPartage.envoiDonneesInitiale();
	            Thread.sleep(1000);
	            String message = serveurPartage.receptionFichier(cle);
	            ImportExport.importer(message);
	            new Alert(AlertType.INFORMATION, 
	            		"L'import de questions s'est correctement déroulé.")
	            		.show();
            } else {
            	new Alert(AlertType.ERROR, "La connexion n'a pas pu être établie dans le délai spécifié.").show();
            }
            
        } catch (Exception e) {
        	new Alert(AlertType.ERROR, e.getMessage()).show();
        } finally {
        	serveurPartage.fermetureServeur();
        }
    }
    
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
