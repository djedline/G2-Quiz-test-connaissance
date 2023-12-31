/* ControleurExport.java                                            13 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.io.File;
import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Classe controleur permettant de gérer l'exportation de données
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
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
	
    @FXML
    private Button btnSelectQuestions;

	private File destination = new File(
	        "fichiers_sauvegarde_partage/sauvegardeQuiz.csv");

	@FXML
	void chercherFichier(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		// Ajout d'un filtre pour ne montrer que certains fichiers
		ExtensionFilter extFilter = new ExtensionFilter(
		        "Fichier CSV UTF-8 séparateur point-virgule(*.csv)",
		        "*.csv");
		fileChooser.setInitialDirectory(
		        new File("fichiers_sauvegarde_partage/"));
		fileChooser.setInitialFileName("sauvegardeQuiz");
		fileChooser.getExtensionFilters().add(extFilter);

		// Afficher la boîte de dialogue de sauvegarde de fichier
		File fichierChoisi = fileChooser.showSaveDialog(Lanceur.getStage());
		if (fichierChoisi != null) {
			destination = fichierChoisi;
			fichierAExporter.setText(destination.getAbsolutePath());
		}
	}

	@FXML
	void clicSuivant(ActionEvent event) {
		if (destination == null || destination.isDirectory()) {
			new Alert(AlertType.INFORMATION, 
			        "Choisissez d'abord un fichier à écrire.")
			        .show();
		} else {
			EchangeurDeVue.echangerAvec(destination);
		}
	}

	@FXML
	void clicQuitter(ActionEvent event) {
		EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_IMPEXP);
	}
	
	@FXML
	void initialize(){
		fichierAExporter.setText(destination.getAbsolutePath());
	}

}
