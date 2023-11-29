/*
 * ControleurGestionDonnees.java                           9 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 * Controleur de la scène GestionDonnees
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurChoixQuestionExport {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnAnnuler"
	private Button btnAnnuler; // Value injected by FXMLLoader

	@FXML // fx:id="treeViewData"
	private TreeView<Object> treeViewData; // Value injected by FXMLLoader

	private File fichierAExporter;
	
	public void setExportFile(File f) {
		this.fichierAExporter = f;
	}
	
	@FXML
	void retourArriere(ActionEvent event) {
		EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_EXPORT);
	}

	@FXML
	private Button btnValider;

	@FXML
	void valider(ActionEvent event) {
		List<Question> selectionnees = new ArrayList<>();
		
		for (TreeItem<Object> itemCat : treeViewData.getRoot().getChildren()) {
			CheckBoxTreeItem<Object> convertiCat = (CheckBoxTreeItem<Object>) itemCat;
			if (convertiCat.isSelected()) {
				String nomCat = (String) convertiCat.getValue();
				selectionnees.addAll(Donnees.getQuestionOfCategorie(nomCat));
			} else if (convertiCat.isIndeterminate()){
				for (TreeItem<Object> itemQ : itemCat.getChildren()) {
					CustomCheckboxTreeItem<Object> ccti = 
							(CustomCheckboxTreeItem<Object>) itemQ;
					if (ccti.isSelected() && ccti.getUserData() != null) {
						Question q = (Question) ccti.getUserData();
						selectionnees.add(q);
					}
				}
			}
		}
		ImportExport.exporter(selectionnees);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		
		// creation d'une arraylist qui contiendra les treeitem de categorie
		List<CustomCheckboxTreeItem<Object>> listeTreeItem = new ArrayList<>();
		for (Categorie item : Donnees.listeCategorie) {

			// ajoute le treeitem
			CustomCheckboxTreeItem<Object> checkable = new CustomCheckboxTreeItem<>(item);

			// ajout d'un treeitem avec en parametre la categorie et le menuButton
			listeTreeItem.add(checkable);

			for (Question laQuestion : Donnees.listeQuestions) {
				// si la categorie et la categorie de la question sont la meme
				if (item.toString().equals(laQuestion.getCategorie().toString())) {
					// on cree un treeitem avec en parametre la question et le menuButton
					// et on l'ajoute au treeitem de la categorie
					checkable.getChildren().add(new CheckBoxTreeItem<>(laQuestion));
				}
			}
		}
		// on cree le root du treeview puis on le cache
		treeViewData.setCellFactory(CheckBoxTreeCell.<Object>forTreeView());
		treeViewData.setRoot(new TreeItem<>());
		treeViewData.setShowRoot(false);
		// on ajoute tout les treeitem des categorie
		treeViewData.getRoot().getChildren().addAll(listeTreeItem);
	}

}
