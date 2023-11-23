/*
 * ControleurGestionDonnees.java                           9 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.CustomBtn;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;


/** 
 * Controleur de la scène MenuGestionDonnees
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurGestionDonnees {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnAnnuler"
	private Button btnAnnuler; // Value injected by FXMLLoader

	@FXML // fx:id="treeViewData"
	private TreeView<CustomBtn> treeViewData; // Value injected by FXMLLoader

	@FXML
	void retourArriere(ActionEvent event) {
		EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_MENU_GESTION_DONNEES);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnAnnuler != null : "fx:id=\"btnAnnuler\" was not injected: check your FXML file 'GestionDonnees.fxml'.";
		assert treeViewData != null : "fx:id=\"treeViewData\" was not injected: check your FXML file 'GestionDonnees.fxml'.";

		// Créez les racines pour le TreeView

		EventHandler<ActionEvent> modifierCat = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				Categorie laCategorie = (Categorie)((MenuItem)e.getSource()).getUserData();
				if (laCategorie != Donnees.listeCategorie.getFirst()) {
					EchangeurDeVue.echangerAvec(laCategorie);
				}else {
					Alert erreur = new Alert(AlertType.WARNING);
					erreur.setContentText("Vous ne pouvez pas modifier la categorie Général");
					erreur.showAndWait();
				}
				
			}
		};
		EventHandler<ActionEvent> modifierQuest = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				Question laQuestion = (Question) ((MenuItem)e.getSource()).getUserData();
				EchangeurDeVue.echangerAvec(laQuestion);
			}
		};
		EventHandler<ActionEvent> suprimerCat = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				Categorie laCategorie = (Categorie)((MenuItem)e.getSource()).getUserData();
				
				
				if (laCategorie != Donnees.listeCategorie.getFirst()) {
					if (Donnees.isCategorieVide(laCategorie)) {
						Donnees.suprimerCategorie(laCategorie);
					}else {
						Alert confirmation = new Alert(AlertType.CONFIRMATION);
						Optional<ButtonType> result = confirmation.showAndWait();
						if(result.get() == ButtonType.OK) {
							Donnees.suprimerCategorie(laCategorie);
						}
					}
					EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
					
				}else {
					Alert erreur = new Alert(AlertType.WARNING);
					erreur.setContentText("Vous ne pouvez pas suprimer la categorie Général");
					erreur.showAndWait();
				}
			}
		};
		EventHandler<ActionEvent> suprimerQuest = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				Question laQuestion = (Question) ((MenuItem)e.getSource()).getUserData();
				Donnees.suprimerQuestion(laQuestion);
				EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
			}
		};


		List<TreeItem<CustomBtn>> listeTreeItem = new ArrayList<>();
		for (Categorie item : Donnees.listeCategorie) {
			MenuButton btnModifierCategorie = new MenuButton("Gérer");
			MenuItem menuItemModifier = new MenuItem("Modifier"); 
			MenuItem menuItemSuprimer = new MenuItem("Suprimer");
			menuItemModifier.setUserData(item);
			menuItemSuprimer.setUserData(item);
			menuItemModifier.setOnAction(modifierCat);
			menuItemSuprimer.setOnAction(suprimerCat);
			btnModifierCategorie.getItems().add(menuItemModifier); 
			btnModifierCategorie.getItems().add(menuItemSuprimer);
			listeTreeItem.add(new TreeItem<CustomBtn>(new CustomBtn(item, btnModifierCategorie)));
		}

		for (TreeItem<CustomBtn> leTreeItem : listeTreeItem) {
			for (Question laQuestion : Donnees.listeQuestions) {

				Categorie cat = new Categorie("erreur");
				for (Categorie laCategorie : Donnees.listeCategorie) {
					if (leTreeItem.getValue().getString()==laCategorie.toString()) {
						cat = laCategorie;
					}
				}

				if (cat.toString().equals(laQuestion.getCategorie().toString())) {
					MenuButton btnModifierQuestion = new MenuButton("Gérer");
					MenuItem menuItemModifier = new MenuItem("Modifier"); 
					MenuItem menuItemSuprimer = new MenuItem("Suprimer");
					menuItemModifier.setUserData(laQuestion);
					menuItemSuprimer.setUserData(laQuestion);
					menuItemModifier.setOnAction(modifierQuest);
					menuItemSuprimer.setOnAction(suprimerQuest);
					btnModifierQuestion.getItems().add(menuItemModifier);
					btnModifierQuestion.getItems().add(menuItemSuprimer);
					leTreeItem.getChildren().add(new TreeItem<CustomBtn>(new CustomBtn(laQuestion, btnModifierQuestion)));
				}

			}
		}

		// Créez le TreeView avec les racines
		treeViewData.setRoot(new TreeItem<>());
		treeViewData.setShowRoot(false);
		treeViewData.getRoot().getChildren().addAll(listeTreeItem);

		/* initialize TreeItem<CustomBtn> as container for CustomBtn object */
		//TreeItem<CustomBtn> node = new TreeItem<CustomBtn>(new CustomBtn(new Label("Node 1"), new Button("Button 1")));

	}



}
