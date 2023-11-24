package iut.sae.ihm.controleur;

import java.io.File;
import java.net.URL;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.modele.Donnees;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Lanceur de l'application
 * 
 * @author tany.catalabailly
 *
 */
public class Lanceur extends Application {

	private static Stage stage;

	/** TODO comment method role
	 * @return la scène
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * Programme principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Donnees.charger();
		Lanceur.stage = primaryStage;

		FXMLLoader loader = new FXMLLoader();

		File fxmlFile = new File("src/iut/sae/ihm/view/MenuPrincipal.fxml");
		URL fxmlUrl = fxmlFile.toURI().toURL();
		loader.setLocation(fxmlUrl);

		Parent parent = (Parent) loader.load();

		ControleurMenuPrincipal controllerRef = (ControleurMenuPrincipal) loader.getController();

		// ControleurMenuPrincipal controllerRef = loader.getController();

		// Lance la sauvegarde lorsqu'on appuie sur la croix
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				if (!Donnees.sauvegarder()) {
					new Alert(AlertType.WARNING,
							"Impossible de sauvegarder les données ! " + "Vos données seront perdues...");
				}
			}
		});

		Scene scene = new Scene(parent);
		EchangeurDeVue.setSceneCourante(scene);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Arrête le programme
	 */
	public static void close() {
		Lanceur.stage.close();
	}

	/**
	 * Redimensionne la scène.
	 */
	public static void resizeScene() {
		stage.sizeToScene();
	}

}