/*
 * ControleurCreerQuestion.java                                    24 Oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package iut.sae.ihm.controleur;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import iut.sae.ihm.view.EchangeurDeVue;
import iut.sae.ihm.view.EnsembleDesVues;
import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.Question;

/**
 * TODO comment class responsibility (SRP)
 * 
 * @author tany.catalabailly
 *
 */
public class ControleurModifierQuestion {

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

    private ArrayList<TextField> lesTxtFaux = new ArrayList<TextField>();

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

    private Question laQuestion;

    /**
     * Méthode permettant de modifier les arguments d'une question
     * 
     * @param laquest la question a modifier
     */
    public void setQuestion(Question laquest) {
        this.laQuestion = laquest;

        this.txtIntitule.setText(laQuestion.getLibelle());
        this.choiceCategorie.setValue(laQuestion.getCategorie());
        this.choiceDifficulte.setValue(choiceDifficulte.getItems().get(laQuestion.getDifficulte() - 1));
        this.txtRepJuste.setText(laQuestion.getPropositionJuste());

        int compteur = 0;
        for (String laReponse : laQuestion.getPropositionFausse()) {
            System.out.println(laReponse);
            lesTxtFaux.get(compteur).setText(laReponse);

            compteur++;
        }
        this.txtFeedback.setText(laQuestion.getFeedback());
    }

    /**
     * TODO comment method role
     * 
     */
    @FXML
    void initialize() {
        choiceDifficulte.getItems().add("Facile");
        choiceDifficulte.getItems().add("Moyen");
        choiceDifficulte.getItems().add("Difficile");
        choiceDifficulte.setValue("Facile");

        for (Categorie element : Donnees.listeCategorie) {
            choiceCategorie.getItems().add(element);
        }
        choiceCategorie.setValue(Donnees.listeCategorie.getFirst());

        lesTxtFaux.add(txtRepFausse1);
        lesTxtFaux.add(txtRepFausse2);
        lesTxtFaux.add(txtRepFausse3);
        lesTxtFaux.add(txtRepFausse4);

    }

    /**
     * Méthode permettant de répertorier toute les réponses fausses d'une question
     * dans un tableau
     * 
     * @return repFausse La ou les réponses fausses
     */
    public String[] tableauReponseFausse() {
        ArrayList<String> listeIntermediaire = new ArrayList<>();
        String[] repFausse;
        boolean repFausseNbValide = false;

        for (TextField laZoneTexte : lesTxtFaux) {
            if (laZoneTexte.getText() != null && !laZoneTexte.getText().isBlank()) {
                listeIntermediaire.add(laZoneTexte.getText());
                repFausseNbValide = true;
            }
        }
        if (!repFausseNbValide) {
            throw new IllegalArgumentException();
        }
        repFausse = new String[listeIntermediaire.size()];
        repFausse = listeIntermediaire.toArray(repFausse);
        return repFausse;

    }

    @FXML
    void creerQuestion(ActionEvent event) {
        int laDifficulte;
        switch (choiceDifficulte.getValue()) {

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
            if (txtFeedback.getText() == null) {
                txtFeedback.setText("");
            }
            /*
             * Question nouvelleQuestion = new Question(txtIntitule.getText(),
             * choiceCategorie.getValue(), txtRepJuste.getText(), tableauReponseFausse(),
             * txtFeedback.getText(), laDifficulte);
             */

            laQuestion.setLibelle(txtIntitule.getText());
            laQuestion.setCategorie(choiceCategorie.getValue());
            laQuestion.setDifficulte(laDifficulte);
            laQuestion.setPropositionJuste(txtRepJuste.getText());
            laQuestion.viderPropositionFausse();
            for (TextField repFausse : lesTxtFaux) {
                if (!repFausse.getText().isBlank()) {
                    laQuestion.addPropositionFausse(repFausse.getText());
                }

            }
            laQuestion.setFeedback(txtFeedback.getText());
            EchangeurDeVue.echangerAvec(EnsembleDesVues.VUE_GESTION_DONNEES);

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
