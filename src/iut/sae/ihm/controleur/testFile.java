/*
 * testFile.java                                    14 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;

import javafx.application.Application;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** 
 * TODO comment class responsibility (SRP)
 * @author djedline.boyer
 *
 */
public class testFile extends Application {
    
    @Override 
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        // Ajout d'un filtre pour ne montrer que certains fichiers
        ExtensionFilter extFilter = new ExtensionFilter("Fichiers texte(*.txt)", "*.txt");
        fileChooser.setTitle("Choisir un fichier");
        fileChooser.getExtensionFilters().add(extFilter);
        //Afficher la boîte de dialogue de choix de fichier
        File fichierSelectionner = fileChooser.showOpenDialog(primaryStage);
        
    }
    
    
    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
