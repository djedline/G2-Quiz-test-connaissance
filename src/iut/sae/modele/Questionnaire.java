/* Questionnaire.java 												14/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele;

import java.util.ArrayList;
import static iut.sae.modele.Donnees.getQuestionOfCategorie;

/**
 * Modele de référence d'un questionnaire
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class Questionnaire {
    
    /** message d'erreur pour une liste de question vide */
    public static final String ERREUR_QUESTION_VIDE = 
            "La liste de question de cette categorie et difficulte "
                    + "est vide";
    
    /** 
     * Message d'erreur pour une liste de question plus petite que le nombre 
     * prevu
     */
    public static final String ERREUR_MANQUE_DE_QUESTION = 
                      "Attention, La liste de question " 
                    + "ne contient pas assez de question, Voulez-vous créer "
                    + "quand même le questionnaire ?";
    
    /** Liste de question choisi */
    private ArrayList<Question> listeQuestion;

    /** Liste de reponse donné par l'utilisateur */
    private ArrayList<String> listeReponseDonnee;

    /**
     * Constructeur sans nombre de question
     * 
     * @param nivDifficulte
     * @param categorie
     */
    public Questionnaire(int nivDifficulte, String categorie) {

        ArrayList<Question> listeQuestionOfCategorie = 
                getQuestionOfCategorie(categorie);

        System.out.println(listeQuestionOfCategorie.size());

        ArrayList<Question> listeQuestionCategorieEtDifficulte = 
                new ArrayList<Question>();

        for (Question laQuestion : listeQuestionOfCategorie) {
            if (laQuestion.getDifficulte() == nivDifficulte 
                    || nivDifficulte == 0) {
                listeQuestionCategorieEtDifficulte.add(laQuestion);
            }
        }

        int tailleListe = listeQuestionCategorieEtDifficulte.size();
        
        if (tailleListe == 0) {
            throw new IllegalArgumentException(ERREUR_QUESTION_VIDE);
        }
        
        listeQuestion = listeQuestionCategorieEtDifficulte;
        listeReponseDonnee = new ArrayList<String>();

        for (int i = 0; i < listeQuestion.size(); i++) {
            listeReponseDonnee.add("");
        }
    }

    /**
     * Constructeur avec nombre de question
     * 
     * @param nivDifficulte
     * @param categorie
     * @param nbQuestion
     */
    public Questionnaire(int nivDifficulte, String categorie, int nbQuestion) {

        ArrayList<Question> listeQuestionOfCategorie = 
                getQuestionOfCategorie(categorie);

        ArrayList<Question> listeQuestionCategorieEtDifficulte = 
                new ArrayList<Question>();

        for (Question laQuestion : listeQuestionOfCategorie) {
            if (laQuestion.getDifficulte() == nivDifficulte
                    || nivDifficulte == 0) {
                listeQuestionCategorieEtDifficulte.add(laQuestion);
            }
        }

        int tailleListe = listeQuestionCategorieEtDifficulte.size();

        if (tailleListe == 0) {
            throw new IllegalArgumentException(ERREUR_QUESTION_VIDE);
        }
        
        if (tailleListe < nbQuestion) {
            throw new IllegalArgumentException(ERREUR_MANQUE_DE_QUESTION);
        }
        
        
        listeQuestion = new ArrayList<Question>();
        int i = 1;

        while (i < nbQuestion) {
            int n = (int)(Math.random() * tailleListe);
            Question laQuestion = listeQuestionCategorieEtDifficulte.get(n);
            if (!listeQuestion.contains(laQuestion)) {
                listeQuestion.add(laQuestion);
                i++;
            }
        }
    

        listeReponseDonnee = new ArrayList<String>();

        for (int j = 0; j < listeQuestion.size(); j++) {
            listeReponseDonnee.add("");
        }
    }

    /**
     * methode permettant de rajouter une réponse a une question par rapport a 
     * sa place dans la liste
     * 
     * @param i             indice de la reponse dans la liste
     * @param reponseDonnee reponse a mettre dans la liste
     */
    public void stockerReponse(int i, String reponseDonnee) {
        if (i < 0 || i >= listeReponseDonnee.size()) {
            throw new IllegalArgumentException(
                    "Tentative d'insertion d'une reponse a une place " 
                  + "inexistante");
        }

        if (reponseDonnee == null) {
            throw new IllegalArgumentException(
                    "Tentative d'insertion d'une reponse null");
        }
        this.listeReponseDonnee.set(i, reponseDonnee);
    }

    /**
     * methode qui permet d'obtenir le taux de reussite du questionnaire en
     * pourcentage
     * 
     * @return tauxDeReussite taux de reussite du questionnaire apres rendu
     */
    public double leTauxDeReussite() {
        double nbQuestion = listeQuestion.size();
        double nbReponseJuste = 0;
        double res = 0.0;

        for (int i = 0; i < nbQuestion; i++) {
            if (listeQuestion.get(i).getPropositionJuste().
                    equals(listeReponseDonnee.get(i))) {
                nbReponseJuste++;
            }
        }
        res = (nbReponseJuste / nbQuestion) * 100.0;
        System.out.println(res);
        return res;
    }

    /**
     * Methode qui recupere l'objet Question a l'indice demander en parametre 
     * dans la liste des questions du questionnaire
     * 
     * @param i indice de la question dans la liste
     * @return laQuestion la question a l'indice i
     */
    public Question getQuestion(int i) {
        if (i < 0 || i >= listeQuestion.size()) {
            throw new IllegalArgumentException(
                    "Tentative d'insertion d'une reponse a une place " 
                            + "inexistante");
        }
        return listeQuestion.get(i);
    }

    /**
     * Getter pour la liste de Question du questionnaire
     * 
     * @return la liste des Questions du questionnaire
     */
    public ArrayList<Question> getListeQuestion() {
        return (ArrayList<Question>) listeQuestion;
    }

    /**
     * Getter pour la liste des reponses donnée
     * 
     * @return la liste des reponses données actuellement
     */
    public ArrayList<String> getListeReponseDonnee() {
        return listeReponseDonnee;
    }
}
