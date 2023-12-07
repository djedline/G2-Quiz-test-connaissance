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
import iut.sae.modele.reseau.Cryptage;
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
 * Permet de démarrer un serveur pour recevoir un fichier partager
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
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
     * Affiche l'adresse ip du serveur
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
            new Alert(AlertType.ERROR, "Problème à la récupérartion de l'adresse IP.")
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
            btnDemarrer.setText("Éteindre");
            Donnees.serveurAllumee = true;
            gestionServeur();
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
    public void gestionServeur() {
    	boolean connexionEtablie;
    	String message = "";
        try {
            serveurPartage = new Serveur();
            connexionEtablie = serveurPartage.accepterConnexion(30); // Attendre 30 secondes au maximum

            if (connexionEtablie) {
            	int cle = serveurPartage.envoiDonneesInitiale();
            	Integer[] offset = Cryptage.convertirValeurEnOffset(cle);
	            Thread.sleep(1000);
	            message = serveurPartage.receptionFichier(offset);
            } else {
            	new Alert(AlertType.ERROR, "La connexion n'a pas pu être établie"
            			+ " dans le délai spécifié.").show();
            }
            
        } catch (Exception e) {
        	new Alert(AlertType.ERROR, e.getMessage()).show();
        } finally {
        	serveurPartage.fermetureServeur();
        }
        
        try {
	        if (message != null && !message.isBlank()) {
	        	ImportExport.importer(message);
	        	new Alert(AlertType.INFORMATION, 
	            		"L'import de questions s'est correctement déroulé.")
	            		.show();
	        }
        } catch (IOException e) {
        	new Alert(AlertType.INFORMATION, 
            		"Impossible d'importer les questions, fichier invalide.")
            		.show();
        }
    }
    
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
