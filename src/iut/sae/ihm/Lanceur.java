package iut.sae.ihm;

import java.io.File;
import java.net.URL;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Lanceur de l'application
 * @author tany.catalabailly
 *
 */
public class Lanceur extends Application {

    /**Programme principal
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Donnees.listeCategorie.add(new Categorie("General"));
        FXMLLoader loader = new FXMLLoader();

        File fxmlFile = new File("src/iut/sae/ihm/MenuPrincipal.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
        loader.setLocation(fxmlUrl);

        Parent parent = (Parent) loader.load();

        ControleurMenuPrincipal controllerRef = loader.getController();

        Scene scene = new Scene(parent);
        EchangeurDeVue.setSceneCourante(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}