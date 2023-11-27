/*
 * ControleurResultatQuestionnaire.java                             27 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;
import iut.sae.modele.Questionnaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** 
 * Controleur de la classe resultat du questionnaire apres l'avoir fait
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 */
public class ControleurResultatQuestionnaire {

    /** Nombre de question du questionnaire */
    public int nbQuestion;

    /** Numéro de la question affiché */
    public int numQuestion;

    /** Label qui affiche un commentaire sur le resultat */
    @FXML
    private Label idCommentaireResultat;

    /** Label qui affiche l'explication de la reponse juste */
    @FXML
    private Label idFeedBack;

    /** Label qui affiche la question courante */
    @FXML
    private Label idQuestion;

    /** Bouton qui permet d'afficher la correction de la question precedente */
    @FXML
    private Button idQuestionPrecedente;

    /** Bouton qui permet d'afficher la correction de la question suivante */
    @FXML
    private Button idQuestionSuivante;

    /** 
     * Label de la reponse qui a été choisi lors du questionnaire par le joueur
     */
    @FXML
    private Label idReponseDonnee;

    /** Label de la reponse juste de la question courante */
    @FXML
    private Label idReponseJuste;

    /** Label qui affiche le resultat en pourcentage du joueur */
    @FXML
    private Label idResultat;

    /** Bouton qui permet de revenir au menu principal */
    @FXML
    private Button idRetourMP;

    /**
     * Initialisation de la vue au moment ou elle est appelé
     */
    @FXML
    void initialize() {
        nbQuestion = Donnees.QuestionnaireGénéré.getListeQuestion().size();
        numQuestion = 0; 
        
        // Rendre le bouton inutilisable au demarage 
        idQuestionPrecedente.setDisable(true);
        
        double resultat = Donnees.QuestionnaireGénéré.leTauxDeReussite();
        System.out.println(resultat);
        idResultat.setText(String.format("%.2f", resultat) + "%");
        idCommentaireResultat.setText(genererCommentaire(resultat));
        
        afficherCorrectionQuestion();    
        
    }
    
    /** 
     * Affiche la correction de la question en place numQuestion
     */
    private void afficherCorrectionQuestion() {
        Questionnaire leQuestionnaire = Donnees.QuestionnaireGénéré;
        Question laQuestion = leQuestionnaire.getQuestion(numQuestion);
        
        idQuestion.setText(laQuestion.getLibelle());
        idReponseJuste.setText(laQuestion.getPropositionJuste());
        idReponseDonnee.setText(
                leQuestionnaire.getListeReponseDonnee().get(numQuestion));
        if (laQuestion.getFeedback().isBlank()) {
            idFeedBack.setText("Aucun FeedBack");
        } else {
            idFeedBack.setText(laQuestion.getFeedback());
        }    
    }

    /**
     * methode qui genere le commentaire sur le resultat en fonction du resultat
     * et si il est renseigné, le pseudo du joueur
     * @param resultat
     * @return le commentaire 
     */
    private String genererCommentaire(double resultat) {
        String commentaire = "";
        
        if (resultat < 25.0) {
            commentaire = "Aie... Vous allez devoir travailler un peu plus.";
        } else if (resultat >= 25.0 && resultat < 50.0) {
            commentaire = "Mmmh... Vous pouvez faire mieux faire.";
        } else if (resultat >= 50.0 && resultat < 75.0) {
            commentaire = 
                    "Bravo ! Plus de la moitié de vos reponses sont justes.";
        } else if (resultat >= 75.0 && resultat < 100.0) {
            commentaire = "Excellent résultat !";
        } else if (resultat == 100.0) {
            commentaire = "Parfait.";
        } else {
            commentaire = "Erreur sur le pourcentage.";
        }
        return commentaire;
    }

    /**
     * Methode qui charge la correction de la question precedente
     */
    @FXML
    void clicQuestionPrecedente() {
        if(numQuestion > 0) {
            numQuestion --;
            idQuestionSuivante.setDisable(false);
        } 
        if(numQuestion == 0){
            idQuestionPrecedente.setDisable(true);
        }
        afficherCorrectionQuestion();
    }
    
    /**
     * Methode qui charge la correction de la question suivante
     */
    @FXML
    void clicQuestionSuivante(ActionEvent event) {
        if(numQuestion < nbQuestion-1) {
            numQuestion ++;
            idQuestionPrecedente.setDisable(false);
        } 
        if(numQuestion == nbQuestion-1){
            idQuestionSuivante.setDisable(true);
        }
        afficherCorrectionQuestion();
    }

    /**
     * Methode qui ramène a la vue du menu principale
     */
    @FXML
    void clicRetourMP() {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
