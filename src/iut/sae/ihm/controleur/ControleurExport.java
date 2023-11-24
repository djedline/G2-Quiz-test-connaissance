/*
 * ControleurExport.java                                    13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.ImportExport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Classe controleur permettant de gérer l'exportation de données
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurExport {

	@FXML
	private Pane idPane;

	@FXML
	private Label idTitre;

	@FXML
	private TextField idNom;

	@FXML
	private Button btnQuitter;

    @FXML
    private Label idLabelNom;

    @FXML
    private Label FichierAExporter;

	@FXML
	private TextField fichierAExporter;

	@FXML
	private Button btnValider;

	private File destination;

	@FXML
	void chercherFichier(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		// Ajout d'un filtre pour ne montrer que certains fichiers
		ExtensionFilter extFilter = new ExtensionFilter("Fichier CSV UTF-8 séparateur point-virgule(*.csv)", "*.csv");
		fileChooser.setInitialFileName("sauvegardeQuiz");
		fileChooser.getExtensionFilters().add(extFilter);

		// Afficher la boîte de dialogue de sauvegarde de fichier
		destination = fileChooser.showSaveDialog(Lanceur.getStage());
		fichierAExporter.setText(destination.getAbsolutePath());
	}

	@FXML
	void clicValider(ActionEvent event) {
		if (destination != null) {
			try {
				ImportExport.exporter(destination);
				new Alert(AlertType.INFORMATION, "Exportation réussie.").show();
			} catch (IOException e) {
				new Alert(AlertType.ERROR, e.getMessage()).show();
			}
		}
	}

	@FXML
	void clicQuitter(ActionEvent event) {
		EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
	}

}
