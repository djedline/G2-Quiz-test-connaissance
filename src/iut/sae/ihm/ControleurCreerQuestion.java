/*
 * ControleurCreerQuestion.java                                    24 Oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm;

import java.util.ArrayList;

import javafx.collections.ObservableList;
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
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;

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
        
        choiceCategorie.setItems(Donnees.listeCategorie);

        
    }

    /** TODO comment method role
     * @return repFausse Laou les réponses fausses
     */
    public String[] tableauReponseFausse() {
    	ArrayList<String> listeIntermediaire = new ArrayList<>();
    	String[] repFausse;
    	if (txtRepFausse1.getText() == null || txtRepFausse1.getText().isBlank()) {
    		throw new IllegalArgumentException();
    	} else {
    		listeIntermediaire.add(txtRepFausse1.getText());
    	}
    	if (txtRepFausse2.getText() != null && !txtRepFausse2.getText().isBlank()) {
    		listeIntermediaire.add(txtRepFausse2.getText());
    	}
    	if (txtRepFausse3.getText() != null && !txtRepFausse3.getText().isBlank()) {
    		listeIntermediaire.add(txtRepFausse3.getText());
    	}
    	if (txtRepFausse4.getText() != null && !txtRepFausse4.getText().isBlank()) {
    		listeIntermediaire.add(txtRepFausse4.getText());
    	}
    	repFausse = new String[listeIntermediaire.size()];
    	repFausse = listeIntermediaire.toArray(repFausse);
		return repFausse;
    	
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
        
        try {
        	System.out.println(txtRepFausse1.getText() == null);
            Question nouvelleQuestion = new Question(txtIntitule.getText(), choiceCategorie.getValue(), txtRepJuste.getText(),
            		tableauReponseFausse(), txtFeedback.getText(), laDifficulte);
            
            if (!Donnees.verifDoubleQuestion(nouvelleQuestion)) {
            	Donnees.listeQuestions.add(nouvelleQuestion);
                System.out.println(nouvelleQuestion);
            } else {
            	Alert messageErreur = new Alert(AlertType.ERROR);
                messageErreur.setContentText("La Question existe déjà.");
                messageErreur.show();
            }
            txtIntitule.setText(null);
            txtRepJuste.setText(null);
            txtFeedback.setText(null);
            txtRepFausse1.setText(null);
            txtRepFausse2.setText(null);
            txtRepFausse3.setText(null);
            txtRepFausse4.setText(null);
        } catch (IllegalArgumentException exeption) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText("Certains champs sont vides");
            messageErreur.show();
        }
    }

    @FXML
    void ajouterCategorie(ActionEvent event) {
    	Donnees.numScenePrecedenteCategorie = EnsembleDesVues.VUE_QUESTION;
    	EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_CATEGORIE);
    }

    @FXML
    void annulerQuestion(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);
    }

}
