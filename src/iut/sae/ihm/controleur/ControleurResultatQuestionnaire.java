/*
 * ControleurResultatQuestionnaire.java                             27 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.ihm.controleur;

import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
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
     * TODO comment method role
     * @param event
     */
    @FXML
    void clicQuestionPrecedente(ActionEvent event) {

    }
    
    /**
     * TODO comment method role
     * @param event
     */
    @FXML
    void clicQuestionSuivante(ActionEvent event) {

    }

    /**
     * Methode qui ramène a la vue du menu principale
     */
    @FXML
    void clicRetourMP() {
        EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_PRINCIPALE);
    }

}
