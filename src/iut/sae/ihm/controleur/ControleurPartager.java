/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.ihm.view.testFile;
import iut.sae.modele.Donnees;
import iut.sae.modele.reseau.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class ControleurPartager {

    
    @FXML
    private TextField adresseIpServeur;

    @FXML
    private Button btnQuitter;
    
    @FXML
    private ChoiceBox<String> choixFichier;

    @FXML
    private Button btnValider;
    
    private File fichierChoisit;

   /** 
     * Initialise la liste déroulante
     */
    @FXML
    void initialize() {
        String chemineDossier = "src/fichiers_sauvegarde_partage/fichier_csv_stock";
        File dossier = new File(chemineDossier);
        File[] listeFichier = dossier.listFiles();
        //System.out.println(listeFichier.toString());
        System.out.println(dossier.isDirectory());
        if (listeFichier == null) {
            choixFichier.getItems().add("-- Pas de fichier --");
            choixFichier.setValue("-- Pas de fichier --");
        } else {
            choixFichier.getItems().add("-- selectionner un Fichier --");
            for (File fichier : listeFichier) {
                choixFichier.getItems().add(fichier.getName());
                System.out.println(fichier);
            }
            choixFichier.setValue("-- selectionner un Fichier --");
        }
       /* */
        
    }
   
    
    @FXML
    void verifIp (ActionEvent event) {
        //(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?) pour que le nombre soit de 0 à 255
        Pattern motif = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]"
                                       + "?).){3}(25[0-5]|2[0-4][0-9]|[0-1]?"
                                       + "[0-9][0-9]?)$");
        //Matcher correct = motif.matcher(adresseIpServeur);
        System.out.print("TODO");
    }
    
    @FXML
    void choixEffectuer (ActionEvent event) {
        System.out.print("TODO");
    }
    
    @FXML
    void clicValider(ActionEvent event) {
       /* if () {
            System.out.print("TODO");
        } else {
            System.out.print("TODO");
        }*/

    }

    
    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
    }

}
