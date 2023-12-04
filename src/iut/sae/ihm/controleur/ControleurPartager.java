/* ControleurPartager.java                                          13 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import iut.sae.modele.reseau.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe controleur de la page Partager
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurPartager {

    @FXML
    private Label idLabelNom;

    @FXML
    private TextField adresseIpServeur;

    @FXML
    private Button btnQuitter;

    @FXML
    private ChoiceBox<String> choixFichier;

    @FXML
    private Button btnValider;

    @FXML
    private Button btnDemarrer;

    boolean allumageOk = false;

    private String chemineDossier;
    private File dossier;
    private File[] listeFichier;
    private boolean ipOk;
    private boolean fichierOk;
    private Client clientPartage;




    /** 
     * Initialise la liste déroulante
     */
    @FXML
    void initialize() {
        chemineDossier = "fichiers_sauvegarde_partage/fichier_csv_stock";
        dossier = new File(chemineDossier);
        listeFichier = dossier.listFiles();
        ipOk = false;
        fichierOk = false;
        //System.out.println(listeFichier.toString());
        System.out.println(dossier.isDirectory());
        if (listeFichier == null) {
            choixFichier.getItems().add("-- Pas de fichier --");
            choixFichier.setValue("-- Pas de fichier --");
        } else {
            choixFichier.getItems().add("-- Sélectionner un fichier --");
            for (File fichier : listeFichier) {
                choixFichier.getItems().add(fichier.getName());
                System.out.println(fichier);
            }
            choixFichier.setValue("-- Sélectionner un fichier --");
        }
        /* */

    }


    @FXML
    void verifIp (ActionEvent event) {
        // pour que le nombre soit de 0 à 255
        Pattern motif = Pattern.compile(
                "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]"
                + "?).){3}(25[0-5]|2[0-4][0-9]|[0-1]?"
                + "[0-9][0-9]?)$");
        Matcher correct = motif.matcher(adresseIpServeur.getText());
        if (correct.matches()) {
            System.out.println("C'est bon");
            ipOk = true;
        } else {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Une adresse ip est constitué de 4 "
                    + "nombres entre 0 et 255 séparés par des points exemples :"
                    + "128.15.0.348");
            messageErreur.show();
            adresseIpServeur.setText("");
            idLabelNom.setStyle("-fx-text-fil:red;");
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
            clientPartage.envoyer(Donnees.fichierAPartager, cle);
            clientPartage.fermerSocket();
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void clicValider(ActionEvent event) {
        fichierOk = choixFichier.getValue().equals("-- Pas de fichier --") ||
                choixFichier.getValue().equals("-- selectionner un Fichier --");
        if (fichierOk) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Vous ne pouvez pas partager ça");
            messageErreur.show();
        } else {
          Donnees.fichierAPartager  = new File(chemineDossier + "/questionsbasiques.csv");
        }

        if (ipOk && !fichierOk) {
            //Client.creerLiaisonServeur(adresseIpServeur.getText(), 6666);
            partageFichier();
        } else {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Remplissez tous les champs");
            messageErreur.show();
        }
    }

    /*
     * A implémenter
    @FXML
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
        origine = fileChooser.showOpenDialog(Lanceur.getStage());
        fichierAExporter.setText(origine.getAbsolutePath());
    }
    */
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
