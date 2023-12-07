package iut.sae.ihm.controleur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

/** TODO comment class responsibility (SRP)
 * @author catal
 *
 */
public class testText extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        Label warningLabel = new Label("Veuillez entrer une valeur valide.");
        warningLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold");

        // Crée un TextFormatter avec un UnaryOperator pour valider la saisie
        TextFormatter<String> textFormatter = new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String newText = change.getControlNewText();

                // Exemple de condition : la saisie doit contenir au moins 5 caractères
                if (newText.length() >= 5) {
                    warningLabel.setVisible(false);
                    return change;
                } else {
                    warningLabel.setVisible(true);
                    return change;
                }
            }
        });

        textField.setTextFormatter(textFormatter);

        VBox root = new VBox(10);
        root.getChildren().addAll(textField, warningLabel);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("TextField Validation Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
