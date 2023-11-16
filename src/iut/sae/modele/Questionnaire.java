/* Questionnaire.java 												14/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele;

import java.util.ArrayList;
import static iut.sae.modele.Donnees.getQuestionOfCategorie;

/**
 * Modele de référence d'un questionnaire
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly, 
 * 		   leo.cheikh-boukal 
 */
public class Questionnaire {

    /** Liste de question choisi */
    private ArrayList<Question> listeQuestion;

    /** Liste de reponse donné par l'utilisateur */
    private ArrayList<String> listeReponseDonnee;

    /**
     * Constructeur sans nombre de question
     * @param nivDifficulte 
     * @param categorie 
     */
    public Questionnaire(int nivDifficulte, String categorie) {

        ArrayList<Question> listeQuestionOfCategorie = 
                getQuestionOfCategorie(categorie);

        System.out.println(listeQuestionOfCategorie.size());

        ArrayList<Question> listeQuestionCategorieEtDifficulte 
        = new ArrayList<Question>();

        for( Question laQuestion : listeQuestionOfCategorie ) {
            if(laQuestion.getDifficulte() == nivDifficulte) {
                listeQuestionCategorieEtDifficulte.add(laQuestion);
            }
        }

        if (listeQuestionCategorieEtDifficulte.size() == 0) {
            throw new IllegalArgumentException("La liste de question est vide");
        }

        listeQuestion = listeQuestionCategorieEtDifficulte;
        listeReponseDonnee = new ArrayList<String>();

        for (int i = 0; i < listeQuestion.size(); i++) {
            listeReponseDonnee.add("");
        }
    }

    /**
     * Constructeur avec nombre de question
     * @param nivDifficulte 
     * @param categorie 
     * @param nbQuestion 
     */
    public Questionnaire(int nivDifficulte, String categorie, int nbQuestion) {

        ArrayList<Question> listeQuestionOfCategorie = 
                getQuestionOfCategorie(categorie);

        ArrayList<Question> listeQuestionCategorieEtDifficulte 
        = new ArrayList<Question>();

        for( Question laQuestion : listeQuestionOfCategorie ) {
            if(laQuestion.getDifficulte() == nivDifficulte) {
                listeQuestionCategorieEtDifficulte.add(laQuestion);
            }
        }

        int tailleListe = listeQuestionCategorieEtDifficulte.size();

        if (tailleListe < nbQuestion) {
            throw new IllegalArgumentException("La liste de question "
                    + "ne contient pas assez de question");
        }
        if (tailleListe == nbQuestion) {
            listeQuestion = listeQuestionCategorieEtDifficulte;
        } else {
            listeQuestion = new ArrayList<Question>();
            int i = 1;

            while (i <= nbQuestion) {
                int n = (int)(Math.random() * tailleListe);
                Question laQuestion = listeQuestionCategorieEtDifficulte.get(n);
                if (!listeQuestion.contains(laQuestion)) {
                    listeQuestion.add(laQuestion);
                    i++;
                }
            }
        }

        listeReponseDonnee = new ArrayList<String>();

        for (int i = 0; i < listeQuestion.size(); i++) {
            listeReponseDonnee.add("");
        }
    }


    /**
     * methode permettant de rajouter une réponse a une question par rapport 
     * a sa place dans la liste
     * @param i indice de la reponse dans la liste
     * @param reponseDonnee reponse a mettre dans la liste
     */
    public void stockerReponse(int i, String reponseDonnee) {
        // TODO 
    }

    /**
     * methode qui permet d'obtenir le taux de reussite du questionnaire
     * @return tauxDeReussite taux de reussite du questionnaire apres rendu
     */
    public double leTauxDeReussite() {
        return 0.0; // Bouchon
    }

    /**
     * Methode qui recupere l'objet Question a l'indice demander en parametre
     * dans la liste des questions du questionnaire
     * @param i indice de la question dans la liste
     * @return laQuestion la question a l'indice i
     */
    public Question getQuestion(int i) {
        return null; // Bouchon
    }


    /**
     * Getter pour la liste de Question du questionnaire
     * @return la liste des Questions du questionnaire
     */
    public ArrayList<Question> getListeQuestion() {
        return (ArrayList<Question>) listeQuestion;
    }

    /**
     * Getter pour la liste des reponses donnée
     * @return la liste des reponses données actuellement
     */
    public ArrayList<String> getListeReponseDonnee() {
        return listeReponseDonnee;
    }
}
