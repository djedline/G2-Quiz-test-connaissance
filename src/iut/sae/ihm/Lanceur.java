package iut.sae.ihm;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class Lanceur extends Application {

    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        //ControleurCreeCategorie controller = new ControleurCreeCategorie();
        //controller.setValue("New value");
        //loader.setController(controller);

        File fxmlFile = new File("src/iut/sae/ihm/creerQuestion.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
        loader.setLocation(fxmlUrl);

        Parent parent = (Parent) loader.load();

        ControleurCreerQuestion controllerRef = loader.getController();

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}