/* ControleurPartager.java                                          13 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import iut.sae.modele.reseau.Client;
import iut.sae.modele.reseau.Cryptage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Classe controleur de la page Partager
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurPartager {

    @FXML
    private Label idLabelNom;
    
    @FXML
    private TextField adresseIpServeur;
    
    @FXML
    private TextField fichier;
    
    @FXML
    private Label messageErreur;
    
    @FXML
    private Label idLabelNom1;

    @FXML
    private Button btnQuitter;

    @FXML
    private Button btnValider;

    @FXML
    private Button btnDemarrer;
    
    @FXML
    private Button parcourir;

    /** Permet de vérifier si la chaine entrée dans adresseIpServeur est juste */
    private boolean ipOk;
    
    /** Permet de vérifier si un fichier a été sélectionné */
    private boolean fichierOk;
    
    /** Crée un client pour le partage */
    private Client clientPartage;

    private File origine = new File("fichiers_sauvegarde_partage/");
    
    /** 
     * Initialise le label et les boolean de confirmation
     */
    @FXML
    void initialize() {
        ipOk = false;
        fichierOk = false;
        fichier.setText("-- Aucun fichier sélectionnée --");
        
    }

    @FXML
    /**
     * Ouvre une fenetre qui permet à l'utilisateur de sélectionner un fichier 
     * depuis l'xplorateur fichier
     * @param event quand le bouton parcourir est cliqué
     */
    void chercherFichier(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Ajout d'un filtre pour ne montrer que certains fichiers
        ExtensionFilter extFilter =
                new ExtensionFilter(
                        "Fichier CSV UTF-8 séparateur point-virgule(*.csv)",
                        "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(
                new File("fichiers_sauvegarde_partage/"));

        // Afficher la boîte de dialogue de choix de fichier
        File fichierSelectionne = fileChooser.showOpenDialog(Lanceur.getStage());
        if (fichierSelectionne != null) {
                origine = fichierSelectionne;
                fichier.setText(origine.getAbsolutePath());
        }
    }
    
    @FXML
    /**
     * Vérifie la chaine entrée 
     * Affiche un message d'erreur si la chaine n'est pas une adresse Ip
     * Enlève le message d'erreur quand la chaine est correcte
     * @param event quand une touche est enfoncé
     */
    void verifIp(KeyEvent event) {
         // pour que le nombre soit de 0 à 255
        Pattern motif = Pattern.compile(
                "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]"
                + "?).){3}(25[0-5]|2[0-4][0-9]|[0-1]?"
                + "[0-9][0-9]?)$");
        Matcher correct = motif.matcher(adresseIpServeur.getText());
        if (correct.matches()) {
            System.out.println("C'est bon");
            ipOk = true;
            messageErreur.setText("");
        } else {
        	messageErreur.setText("Une adresse IP est constitué de 4 "
        			+ "nombres entre 0 et 255 séparés par des points. \nExemple :"
        			+ "128.15.0.348");
        	messageErreur.setTextFill(Color.web("#ff0000"));
        }
    }

    /** 
     * Partage un fichier
     */
    public void partageFichier() {
        try {
            System.out.println("INITIALISATION CLIENT");
            clientPartage = new Client(adresseIpServeur.getText(), 6666);
            System.out.println("RECEPTION CLE");
            int cle = clientPartage.echangerDonneesCryptage();
            Thread.sleep(1000);
            Integer[] offset = Cryptage.convertirValeurEnOffset(cle);
            clientPartage.envoyer(Donnees.fichierAPartager, offset);
            clientPartage.fermerSocket();
            new Alert(AlertType.INFORMATION, 
            		"Le transfert s'est correctement déroulé.").show();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        } finally {
        	try {
				clientPartage.fermerSocket();
			} catch (IOException e) {
				new Alert(AlertType.WARNING, "Impossible de fermer la socket.");
				e.printStackTrace();
			}
        }
    }

    @FXML
    /** 
     * Vérifie si tous les champs sont correctement remplis 
     * et lance le partage de fichier 
     */
    void clicValider(ActionEvent event) {
        fichierOk = fichier.getText().equals("-- Aucun fichier sélectionnée --");
        if (fichierOk) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Vous devez choisir un fichier");
            messageErreur.show();
        } else {
          Donnees.fichierAPartager  = new File(fichier.getText());
        }
        if (ipOk && !fichierOk) {
            //Client.creerLiaisonServeur(adresseIpServeur.getText(), 6666);
            partageFichier();
        } else {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Tous les champs ne sont pas valides");
            messageErreur.show();
        }
    }
    
    @FXML
    /** Retour sur le menu précédent*/
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
