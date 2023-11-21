/*
 * ControleurQuestionnaire.java                                    21 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.util.ArrayList;

import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;

/**
 * Classe controlleur de la vue "Questionnaire.fxml"
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 *         leo.cheikh-boukal
 */
public class ControleurQuestionnaire {
    
    /** Nombre de question du questionnaire */
    public static int nbQuestion;

    /** Numéro de la question affiché */
    public static int numQuestion;
    
    /** List des boutons radio */
    public static ArrayList<RadioButton> listeRadioButton = new ArrayList<>();
    
    @FXML
    private static RadioButton idReponse2;

    @FXML
    private static RadioButton idReponse1;

    @FXML
    private Button idPasser;

    @FXML
    private static Label idQuestion;

    @FXML
    private Button idValider;

    @FXML
    private static RadioButton idReponse5;

    @FXML
    private static RadioButton idReponse4;

    @FXML
    private static RadioButton idReponse3;

    @FXML
    static void initialize() {
        nbQuestion = Donnees.QuestionnaireGénéré.getListeQuestion().size();
        numQuestion = -1; // -1 est la pour que je puisse charger la question 
                          // suivante
        chargerQuestionSuivante();
        listeRadioButton.add(idReponse1);
        listeRadioButton.add(idReponse2);
        listeRadioButton.add(idReponse3);
        listeRadioButton.add(idReponse4);
        listeRadioButton.add(idReponse5);
        
    }
    
    @FXML
    static void actionPasser(ActionEvent event) {
        chargerQuestionSuivante();
    }

    @FXML
    static void actionValider(ActionEvent event) {
        validerReponse();
        chargerQuestionSuivante();
    }
    
    /**
     * Méthode permettant le changement de l'affichage pour afficher la question
     * suivante
     */
    public static void chargerQuestionSuivante() {
        numQuestion ++;
        if (numQuestion >= nbQuestion ) {
            // TODO : Charger Vue du resultat du questionnaire
        } else {
            Question laQuestionSuivante =
                    Donnees.QuestionnaireGénéré.getQuestion(numQuestion);
            ArrayList<String> listeReponses = new ArrayList<>();
            listeReponses.add(laQuestionSuivante.getPropositionJuste());
            for(String element : laQuestionSuivante.getPropositionFausse()) {
                listeReponses.add(element);
            }
            listeReponses = melangerReponses(listeReponses);
            
        }
    }
    
    /** TODO comment method role
     * @param listeReponses
     * @return
     */
    private static ArrayList<String> melangerReponses(ArrayList<String> listeReponses) {
        // TODO Auto-generated method stub
        return listeReponses;
    }

    /**
     * Méthode permettant d'enregistrer la réponse choisi 
     */
    public static void validerReponse() {
        //TODO
    }

}
