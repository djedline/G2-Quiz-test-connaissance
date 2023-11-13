package iut.sae.ihm;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class ControleurCreerQuestion {

    @FXML
    private Button btnAddCategorie;

    @FXML
    private TextField txtRepJuste;

    @FXML
    private ChoiceBox<Categorie> choiceCategorie;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnCreer;

    @FXML
    private TextField txtIntitule;

    @FXML
    private TextField txtRepFausse1;

    @FXML
    private TextField txtRepFausse2;

    @FXML
    private TextField txtRepFausse3;

    @FXML
    private TextField txtRepFausse4;

    @FXML
    private Pane idPane;

    @FXML
    private Label idTitre;

    @FXML
    private ChoiceBox<String> choiceDifficulte;

    @FXML
    private Label idLabelNom;

    @FXML
    private TextArea txtFeedback;

    /** TODO comment method role
     * 
     */
    @FXML
    void initialize() {
        choiceDifficulte.getItems().add("Facile");
        choiceDifficulte.getItems().add("Moyen");
        choiceDifficulte.getItems().add("Difficile");
        choiceDifficulte.setValue("Facile");

        for(int i = 0; i < Donnees.listeCategorie.size(); i++) {
            choiceCategorie.getItems().add(Donnees.listeCategorie.get(i));
        }
        choiceCategorie.setValue(Donnees.listeCategorie.get(0));
    }

    @FXML
    void creerQuestion(ActionEvent event) {
        int laDifficulte;

        switch(choiceDifficulte.getValue()){

        case "Facile": 
            laDifficulte = 1;
            break;

        case "Moyen":
            laDifficulte = 2;
            break;

        case "Difficile":
            laDifficulte = 3;
            break;

        default:
            throw new IllegalArgumentException("Mauvaise valeur dans le choiceBox de difficulté");

        }
        String[] lesRepFausse = {txtRepFausse1.getText(),txtRepFausse2.getText(), 
                txtRepFausse3.getText(),txtRepFausse4.getText()};
        try {
            Question nouvelleQuestion = new Question(txtIntitule.getText(), choiceCategorie.getValue(), txtRepJuste.getText(),
                    lesRepFausse, txtFeedback.getText(), laDifficulte);
            Donnees.listeQuestions.add(nouvelleQuestion);
            System.out.println(nouvelleQuestion);
            txtIntitule.setText(null);
            txtRepJuste.setText(null);
            txtFeedback.setText(null);
        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Le nom ne doit pas être vide.");
            messageErreur.show();
        }
    }

    @FXML
    void ajouterCategorie(ActionEvent event) {
        Donnees.numScenePrecedenteCategorie = EnsembleDesVues.VUE_QUESTION;

    }

    @FXML
    void annulerQuestion(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
    }

}
