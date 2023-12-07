/*
 * Donnees.java                                                     10 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.File;
import java.util.ArrayList;

/**
 * Centralise les données de l'application avec sa persistence.
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class Donnees {

    /** Le chemin dans lequel les questions sont sauvegardées. */
    public static final String CHOIX_INDIFFERENT = "indifferent";

    /** le nom de l'utilisateur */
    public static String nomUtilisateur = "";

    /**
     * Le nom de la catégorie par défaut existante.
     */
    public static final String NOM_CATEGORIE_DEFAUT = "Général";

    /** Liste de Categorie */
    public static ArrayList<Categorie> listeCategorie = new ArrayList<>();

    /** Liste de Questions */
    public static ArrayList<Question> listeQuestions = new ArrayList<>();

    /**
     * Enregistre le numéro scène que le bouton annuler de categorie doit 
     * renvoyer
     */
    public static int numScenePrecedenteCategorie;

    /**
     * Enregistre le numéro scène que le bouton annuler de categorie doit 
     * renvoyer
     */
    public static File fichierAPartager;

    /** adresse ip du serveur */
    public static String adresseIpServeur;

    /** Permet de savoir si le serveur est allumée */
    public static boolean serveurAllumee = false;

    /** Le questionnaire généré avant son */
    public static Questionnaire QuestionnaireGénéré;

    /**
     * le geteur de nomutilisateur
     * 
     * @return le nom de l'utilisateur
     */
    public static String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * le setteur du nom de l'utilisateur
     * 
     * @param nomUtilisateur
     */
    public static void setNomUtilisateur(String nomUtilisateur) {
        Donnees.nomUtilisateur = nomUtilisateur;
    }

    /**
     * Méthode qui permet d'afficher l'ensemble des catégories et des questions
     */
    public static void afficherDonnees() {
        System.out.println("CATEGORIES : ");
        for (Categorie cat : listeCategorie) {
            System.out.println(" - " + cat.getLibelle());
        }
        System.out.println("QUESTIONS : ");
        for (Question q : listeQuestions) {
            System.out.println(" - " + q.getLibelle());
        }
    }

    /**
     * Verifie que la categorie ajouté n'est pas un double
     * 
     * @param aVerifier la catégorie à analyser
     * @return true si aVerifier est un doublon
     */
    public static boolean verifDoubleCategorie(Categorie aVerifier) {
        boolean doubleOk = false;
        for (int i = 0; i < listeCategorie.size() && !doubleOk; i++) {
            doubleOk = listeCategorie.get(i).equals(aVerifier);
        }
        return doubleOk;
    }

    /**
     * Verifie que la categorie ajouté n'est pas un double
     * 
     * @param aVerifier la catégorie à analyser
     * @return true si aVerifier est un doublon
     */
    public static boolean verifNomCategorie(String aVerifier) {
        boolean doubleOk = false;
        for (int i = 0; i < listeCategorie.size() && !doubleOk; i++) {
            doubleOk = listeCategorie.get(i).toString().equals(aVerifier);
        }
        return doubleOk;
    }

    /**
     * Verifie que la question ajouté n'est pas un double
     * 
     * @param aVerifier la question à analyser
     * @return true si aVerifier est un doublon
     */
    public static boolean verifDoubleQuestion(Question aVerifier) {
        boolean doubleOk = false;
        for (int i = 0; i < listeQuestions.size() && !doubleOk; i++) {
            doubleOk = listeQuestions.get(i).equals(aVerifier);
        }
        return doubleOk;
    }

    /**
     * Recherche et renvoie la liste de toutes les questions d'une categorie
     * 
     * @param categorie le nom de la categorie
     * @return res la liste des questions de categorie
     */
    public static ArrayList<Question> getQuestionOfCategorie(String categorie) {
        ArrayList<Question> res = new ArrayList<Question>();
        if (categorie.equals(CHOIX_INDIFFERENT)) {
            res = listeQuestions;
        } else {
            for (Question laQuestion : listeQuestions) {
                if (laQuestion.getCategorie().getLibelle().equals(categorie)) {
                    res.add(laQuestion);
                }
            }
        }
        return res;
    }

    /**
     * Recherche et renvoie la liste de toutes les questions d'une difficulte
     * 
     * @param difficulte le numero de la difficulte
     * @return res la liste des questions de difficulte
     */
    public static ArrayList<Question> getQuestionOfDifficulte(int difficulte) {
        ArrayList<Question> res = new ArrayList<Question>();
        for (Question laQuestion : listeQuestions) {
            if (laQuestion.getDifficulte() == difficulte) {
                res.add(laQuestion);
            }
        }
        return res;
    }

    /**
     * Supprime la question mis en paramètre
     * 
     * @param laQuestion
     * @return true si ça marche, false sinon
     */
    public static boolean supprimerQuestion(Question laQuestion) {
        if (listeQuestions.contains(laQuestion)) {
            listeQuestions.remove(laQuestion);
            return true;
        }
        return false;
    }

    /**
     * Supprime la catégorie mis en paramètre
     * 
     * @param laCategorie
     * @return true si ça marche, false sinon
     */
    public static boolean suprimerCategorie(Categorie laCategorie) {
        if (!isCategorieVide(laCategorie)) {
            for (Question laQuestion : 
                getQuestionOfCategorie(laCategorie.toString())) {
                supprimerQuestion(laQuestion);
            }
        }
        if (listeCategorie.contains(laCategorie)) {
            listeCategorie.remove(laCategorie);
            return true;
        }
        return false;

    }

    /**
     * Méthode qui vérifie si la catégorie à ajouter est vide ou non
     * 
     * @param laCategorie
     * @return true ou false si la catégorie est vide ou non
     */
    public static boolean isCategorieVide(Categorie laCategorie) {
        for (Question laQuestion : listeQuestions) {
            if (laQuestion.getCategorie().equals(laCategorie)) {
                return false;
            }
        }
        return true;

    }

    /**
     * Vide les questions et catégories sauvegardées en mémoire.
     */
    public static void reinitialiserDonnees() {
        listeCategorie = new ArrayList<>();
        creerCategorieDefaut();
        listeQuestions = new ArrayList<>();
    }
    
    /**
     * Crée la catégorie par défaut.
     */
	public static void creerCategorieDefaut() {
		if (Donnees.listeCategorie.size() < 1) {
			Donnees.listeCategorie.add(new Categorie(NOM_CATEGORIE_DEFAUT));
		}
	}
}
