/**
 * Sample Skeleton for 'GestionDonnees.fxml' Controller Class
 */

package iut.sae.ihm;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/** 
 * Controleur de la scène MenuGestionDonnees
 * @author djedline.boyer
 *
 */
public class ControleurGestionDonnees {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAnnuler"
    private Button btnAnnuler; // Value injected by FXMLLoader

    @FXML // fx:id="treeViewData"
    private TreeView<String> treeViewData; // Value injected by FXMLLoader

    @FXML
    void retourArriere(ActionEvent event) {
    	EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MENU_GESTION_DONNEES);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAnnuler != null : "fx:id=\"btnAnnuler\" was not injected: check your FXML file 'GestionDonnees.fxml'.";
        assert treeViewData != null : "fx:id=\"treeViewData\" was not injected: check your FXML file 'GestionDonnees.fxml'.";

        // Créez les racines pour le TreeView
        
        
        List<TreeItem<String>> listeTreeItem = new ArrayList<>();
        for (Categorie item : Donnees.listeCategorie) {
        	listeTreeItem.add(new TreeItem<>(item.toString()));
        }
        
        for (TreeItem<String> leTreeItem : listeTreeItem) {
        	for (Question laQuestion : Donnees.listeQuestion) {
        		Categorie cat = new Categorie("erreur");
        		for (Categorie laCategorie : Donnees.listeCategorie) {
        			if (leTreeItem.getValue()==laCategorie.toString()) {
        				cat = laCategorie;
        			}
        		}
        		if (laQuestion.getCategorie().toString()==cat.toString()) {
        			leTreeItem.getChildren().add(new TreeItem<String>(laQuestion.toString()));
        		}
        		
        	}
        }

        // Créez le TreeView avec les racines
        treeViewData.setRoot(new TreeItem<>());
        treeViewData.setShowRoot(false);
        treeViewData.getRoot().getChildren().addAll(listeTreeItem);

        // Définissez un gestionnaire pour gérer les événements de sélection du TreeView
        treeViewData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Élément sélectionné : " + newValue.getValue());
            }
        });
    }

}
