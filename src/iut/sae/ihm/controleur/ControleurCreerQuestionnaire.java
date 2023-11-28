/* ControleurQuestionnaire.java                                     22 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.util.Optional;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Questionnaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * Classe controleur permettant de gérer la création du questionnaire
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ControleurCreerQuestionnaire {

    @FXML
    private Pane idPane;

    @FXML
    private ChoiceBox<String> idDifficulte;

    @FXML
    private ChoiceBox<String> idNbQuestion;

    @FXML
    private Button btnQuitter;

    @FXML
    private ChoiceBox<String> idCategorie;

    @FXML
    private Label idLabelNom2;

    @FXML
    private Label idLabelNom1;

    @FXML
    private Label idLabelNom;

    @FXML
    private Button btnValider;

    
    /** 
     * Initialisation de la vue
     */
    @FXML
    void initialize() {
        idDifficulte.getItems().add("Facile");
        idDifficulte.getItems().add("Moyen");
        idDifficulte.getItems().add("Difficile");
        idDifficulte.getItems().add("indifferent");
        
        idNbQuestion.getItems().add("5");
        idNbQuestion.getItems().add("10");
        idNbQuestion.getItems().add("20");
        
        for (Categorie element : Donnees.listeCategorie) {
            idCategorie.getItems().add(element.getLibelle());
        }
        idCategorie.getItems().add("indifferent");
        
        idDifficulte.setValue("Facile");
        idNbQuestion.setValue("5");
        idCategorie.setValue(Donnees.listeCategorie.getFirst().getLibelle());

    }
    
    /**
     * Generer le questionnaire a partir des paramètre sélectionner. Si cela 
     * n'est pas possible, genere un message d'alerte avec l'erreur produite et 
     * proposition de creer un questionnaire sans nombre fixe de question
     * @param event
     */
    @FXML
    void clicValider(ActionEvent event) {
        int laDifficulte = genererDifficulte();
                
        try {
            Questionnaire leQuestionnaire = 
                    new Questionnaire(laDifficulte,idCategorie.getValue(),Integer.parseInt(idNbQuestion.getValue()));
            Donnees.QuestionnaireGénéré = leQuestionnaire;
            EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_QUESTIONNAIRE);
            
        } catch (IllegalArgumentException erreur) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText(erreur.getMessage() 
                    + ", Voulez-vous créer le questionnaire quand meme ?");
            Optional<ButtonType> result = messageErreur.showAndWait();
            if(result.get() == ButtonType.OK) {
                genererQuestionSansNombreQuestion();
            }
        }
    }


    /** 
     * Methode qui converti le choix de la difficulté dans le format utilisé 
     * dans le modèle
     * @return ladifficulte entier qui represente la difficulte choisi
     */
    private int genererDifficulte() {
        int laDifficulte;
        
        switch(idDifficulte.getValue()){

        case "Facile": 
            laDifficulte = 1;
            break;

        case "Moyen":
            laDifficulte = 2;
            break;

        case "Difficile":
            laDifficulte = 3;
            break;
        case "indifferent"://TODO
            laDifficulte = 0;
            break;
        default:
            throw new IllegalArgumentException(
                    "Mauvaise valeur dans le choiceBox de difficulté");
        }
        return laDifficulte;
    }

    /** 
     * Methode qui genere un questionnaire sans un nombre fixe de question apres
     * l'erreur produite par la creation avec un nombre fixe de question.
     * Si il n'y a pas de question pour le questionnaire, un message d'alerte 
     * apparait.
     */
    private void genererQuestionSansNombreQuestion() {
        try {
            int laDifficulte = genererDifficulte();
            Questionnaire leQuestionnaire = 
                    new Questionnaire(laDifficulte,idCategorie.getValue());
            Donnees.QuestionnaireGénéré = leQuestionnaire;
            EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_QUESTIONNAIRE);
        } catch (Exception erreur) {
            Alert messageErreur = new Alert(AlertType.ERROR);
            messageErreur.setContentText(erreur.getMessage());
            messageErreur.show();
        }
        
    }

    @FXML
    void clicQuitter(ActionEvent event) {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
