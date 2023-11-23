/* ControleurQuestionnaire.java                                    21 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import java.util.ArrayList;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Classe controlleur de la vue "Questionnaire.fxml"
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 *         leo.cheikh-boukal
 */
public class ControleurQuestionnaire {
    
    /** Nombre de question du questionnaire */
    public int nbQuestion;

    /** Numéro de la question affiché */
    public int numQuestion;
    
    /** Liste des boutons radio */
    public ArrayList<RadioButton> listeRadioButton = new ArrayList<>();
    
    @FXML
    private RadioButton idReponse2;

    @FXML
    private RadioButton idReponse1;

    @FXML
    private Button idPasser;

    @FXML
    private Label idQuestion;

    @FXML
    private Button idValider;

    @FXML
    private RadioButton idReponse5;

    @FXML
    private RadioButton idReponse4;

    @FXML
    private RadioButton idReponse3;

    @FXML
    void initialize() {
        nbQuestion = Donnees.QuestionnaireGénéré.getListeQuestion().size();
        numQuestion = -1; // -1 est la pour que je puisse charger la question 
                          // suivante
        
        listeRadioButton.add(idReponse1);
        listeRadioButton.add(idReponse2);
        listeRadioButton.add(idReponse3);
        listeRadioButton.add(idReponse4);
        listeRadioButton.add(idReponse5);
       
        //je crée un Toggle pour empecher de selectionner tous les radiobutton
        ToggleGroup groupe = new ToggleGroup();
        groupe.getToggles().addAll(listeRadioButton);
        
        chargerQuestionSuivante();
        
    }
    
    @FXML
    void actionPasser(ActionEvent event) {
        chargerQuestionSuivante();
    }

    @FXML
    void actionValider(ActionEvent event) {
        validerReponse();
        chargerQuestionSuivante();
    }
    
    /**
     * Méthode permettant le changement de l'affichage pour afficher la question
     * suivante du questionnaire
     */
    public void chargerQuestionSuivante() {
        reinitialiserRadioButton();
        numQuestion ++;
        if (numQuestion >= nbQuestion ) {
            //TODO : Stub
            EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
        } else {
            Question laQuestionSuivante =
                    Donnees.QuestionnaireGénéré.getQuestion(numQuestion);
            ArrayList<String> listeReponses = new ArrayList<>();
            listeReponses.add(laQuestionSuivante.getPropositionJuste());
            for(String element : laQuestionSuivante.getPropositionFausse()) {
                listeReponses.add(element);
            }
            listeReponses = melangerReponses(listeReponses);
            idQuestion.setText(laQuestionSuivante.getLibelle());
            chargerReponses(listeReponses); 
        }
    }
    


    /** 
     * Methode qui desélectionne tout les radiobuttons
     */
    private void reinitialiserRadioButton() {
        for (RadioButton leBouton : listeRadioButton) {
            if(leBouton.isSelected()) {
                leBouton.setSelected(false);
            }
        } 
    }

    /** 
     * Change le texte des radiobutton pour afficher les reponses de la question
     * et rendre les radio
     * @param listeReponses
     */
    private void chargerReponses(ArrayList<String> listeReponses) {
       // je change l'affichage des reponses 
       int i = 0;
       for(;i<listeReponses.size(); i++) {
           if (!listeRadioButton.get(i).isVisible()) {
               listeRadioButton.get(i).setVisible(true); 
           }
           listeRadioButton.get(i).setText(listeReponses.get(i));
       } 
       
       // si il y a moins de 5 reponses, je rend le reste des radio button invisible
       if ( i < 4 ) {
           for (;i<listeRadioButton.size(); i++) {
               listeRadioButton.get(i).setVisible(false); 
           }
       }
        
    }

    /** 
     * Melange les réponses pour afficher les reponses dans un ordre différent
     * @param listeReponses
     * @return res liste de reponses melanger
     */
    private static ArrayList<String> melangerReponses(ArrayList<String> listeReponses) {
        ArrayList<String> res = new ArrayList<String>();
        int i = 1;
        
        while (i <= listeReponses.size()) {
            int n = (int)(Math.random() * listeReponses.size());
            String laReponse = listeReponses.get(n);
            if (!res.contains(laReponse)) {
                res.add(laReponse);
                i++;
            }
        }
        return res;
    }

    /**
     * Méthode permettant d'enregistrer la réponse choisi et le stocke dans la 
     * liste des reponses du questionnaire
     */
    public void validerReponse() {
        String reponseChoisi = "";
        
        for (RadioButton leBouton : listeRadioButton) {
            if(leBouton.isSelected()) {
                reponseChoisi = leBouton.getText();
            }
        }
        
        Donnees.QuestionnaireGénéré.stockerReponse(numQuestion, reponseChoisi);
        
    }

}
