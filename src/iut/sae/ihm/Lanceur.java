package iut.sae.ihm;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;

/** Lanceur de l'application
 * @author tany.catalabailly
 *
 */
public class Lanceur extends Application {

    private Stage stage;
    
    private double decorationWidth;
    private double decorationHeight;
    
    /**Programme principal
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	this.stage = primaryStage;
    	
    	
        Donnees.listeCategorie.add(new Categorie("General"));
        FXMLLoader loader = new FXMLLoader();

        File fxmlFile = new File("src/iut/sae/ihm/MenuPrincipal.fxml");
        URL fxmlUrl = fxmlFile.toURI().toURL();
        loader.setLocation(fxmlUrl);

        Parent parent = (Parent) loader.load();

        ControleurMenuPrincipal controllerRef = loader.getController();

        Scene scene = new Scene(parent);
        EchangeurDeVue.setSceneCourante(scene);
        this.stage.setScene(scene);
        this.stage.show();
        double initialSceneWidth = this.stage.getWidth();
        double initialSceneHeight = this.stage.getHeight();
        this.decorationWidth = initialSceneWidth - scene.getWidth();
        this.decorationHeight = initialSceneHeight - scene.getHeight();
        
    }
    
    /** TODO comment method role
     * @param width
     * @param height
     */
    public void resizeScene(double width, double height) {
        this.stage.setWidth(width + this.decorationWidth);
        this.stage.setHeight(height + this.decorationHeight);
    }
}