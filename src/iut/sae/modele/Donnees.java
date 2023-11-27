/*
 * Donnees.java                                    10 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Centralise les données de l'application avec sa persistence.
 * @author djedline.boyer
 * Centralise les données de l'application.
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class Donnees {

    /**
     * Le chemin dans lequel les questions sont sauvegardées.
     */
    public static final File FICH_QUESTIONS
    	= new File("fichiers_sauvegarde_partage/questions.data");

    /**
     * Le chemin dans lequel les catégories sont sauvegardées.
     */
    public static final File FICH_CATEGORIES
    	= new File("fichiers_sauvegarde_partage/categories.data");

    /**
     * Le nom de la catégorie par défaut existante.
     */
    public static final String NOM_CATEGORIE_DEFAUT = "Général";
    
    /** TODO comment field role (attribute, association) */
    public static final File FICHIER_IMPORT_QUEST_JAVA
	= new File("fichiers_sauvegarde_partage/fichier_csv_stock/questionsbasiques.csv");

    /** TODO comment field role (attribute, association) */
    public static final File FICHIER_IMPORT_QUEST_ORTHO 
	= new File("fichiers_sauvegarde_partage/fichier_csv_stock/questionsorthographe.csv");

    /** Liste de Categorie */
    public static ArrayList<Categorie> listeCategorie = new ArrayList<>();

    /** Liste de Questions */
    public static ArrayList<Question> listeQuestions = new ArrayList<>();

    /**
     * Enregistre le numéro scène que le bouton annuler de categorie doit renvoyer
     */
    public static int numScenePrecedenteCategorie;

    /** Enregistre le numéro scène que le bouton annuler de categorie doit renvoyer */
    public static File fichierAPartager;
    
    /** adresse ip du serveur */
    public static String adresseIpServeur;
    
    /** Permet de savoir si le serveur est allumée */
    public static boolean serveurAllumee = false;

    /** Le questionnaire généré avant son */
    public static Questionnaire QuestionnaireGénéré;


    /**
     * Sauvegarde la base de questions et de catégories.
     * 
     * @return true si la sauvegarde a réussi, false sinon
     */
    public static boolean sauvegarder() {
        try {
            creerSauvegarde(FICH_CATEGORIES, listeCategorie);
            creerSauvegarde(FICH_QUESTIONS, listeQuestions);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Sauvegarde un objet dans un fichier.
     * 
     * @param chemin         le chemin du fichier à écrire
     * @param donneesAEcrire les données que l'on souhaite sauvegarder
     * @throws IOException si l'enregistrement est impossible
     */
    private static void creerSauvegarde(File fichier, Object donneesAEcrire) throws IOException {
        if (!fichier.exists()) {
            fichier.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fichier));
        writer.writeObject(donneesAEcrire);
        writer.close();
    }

    /**
     * Charge la base de questions et les catégories.
     * 
     * @return true si la sauvegarde a réussi, false sinon
     */
    public static boolean charger() {
        boolean donneesChargees = true;
        try {
            if (FICH_CATEGORIES.exists()) {
                listeCategorie = (ArrayList<Categorie>) chargerSauvegarde(FICH_CATEGORIES);
            } else {
                FICH_CATEGORIES.createNewFile();
            }
            if (FICH_QUESTIONS.exists()) {
                listeQuestions = (ArrayList<Question>) chargerSauvegarde(FICH_QUESTIONS);
            } else {
                FICH_CATEGORIES.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            donneesChargees = false;
        }
        // Cas par défaut
        if (listeCategorie == null || listeCategorie.size() == 0) {
            listeCategorie.add(new Categorie(NOM_CATEGORIE_DEFAUT));
            donneesChargees = false;
        }
        if (listeQuestions == null || listeQuestions.size() == 0) {
        	chargerQuestionsParDefaut();
            donneesChargees = false;
        }
        afficherDonnees();
        return donneesChargees;
    }

    /**
     * Charge le fichier au chemin donné et renvoie la valeur stockée.
     * 
     * @param fichier le chemin du fichier à ouvrir
     * @return l'objet stocké
     * @throws FileNotFoundException  si le fichier n'existe pas
     * @throws IOException            si une erreur d'entrée / sortie se produit
     * @throws ClassNotFoundException s'il est impossible de convertir l'objet
     */
    private static Object chargerSauvegarde(File fichier)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream readerCategories = new ObjectInputStream(new FileInputStream(fichier))) {
            return readerCategories.readObject();
        }
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
        if (categorie.equals(Donnees.listeCategorie.get(0).getLibelle())) {
            res = (ArrayList<Question>) listeQuestions;
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
     * Suprime la question mis en parametre
     * 
     * @param laQuestion
     * @return true si ça marche, false sinon
     */
    public static boolean suprimerQuestion(Question laQuestion) {
        if (listeQuestions.contains(laQuestion)) {
            listeQuestions.remove(laQuestion);
            return true;
        }
        return false;
    }

    /**
     * Surpime la categorie mis en parametre
     * 
     * @param laCategorie
     * @return true si ça marche, false sinon
     */
    public static boolean suprimerCategorie(Categorie laCategorie) {
        if (!isCategorieVide(laCategorie)) {
            for (Question laQuestion : getQuestionOfCategorie(laCategorie.toString())) {
                suprimerQuestion(laQuestion);
            }
        }
        if (listeCategorie.contains(laCategorie)) {
            listeCategorie.remove(laCategorie);
            return true;
        }
        return false;

    }

    /**
     * Méthode qui vérifie si la catégorie à ajouter est
     * vide ou non
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
     * Initialise les données
     * 
     * @param args
     */
    public static void main(System[] args) {
        listeCategorie.add(new Categorie("General"));
    }

    /**
     * Vide les fichiers de sauvegarde.
     */
    public static void effacerSauvegarde() {
        FICH_CATEGORIES.delete();
        FICH_QUESTIONS.delete();
    }

    /**
     * Vide les fichiers de sauvegarde.
     */
    public static void reinitialiserDonnees() {
        listeCategorie = new ArrayList<>();
        listeCategorie.add(new Categorie(NOM_CATEGORIE_DEFAUT));
        listeQuestions = new ArrayList<>();
    }
    
    /**
     * Si les données n'existent pas, charge les données
     * des banques de questions Java & Orthographe.
     */
    public static void chargerQuestionsParDefaut() {
    	reinitialiserDonnees();
    	/*
    	try {
			ImportExport.importer(FICHIER_IMPORT_QUEST_JAVA);
			ImportExport.importer(FICHIER_IMPORT_QUEST_ORTHO);
		} catch (IOException e) {
			new Alert(AlertType.ERROR, "Impossible de charger les données "
					+ "par défaut.\n" + e.getMessage()).show();
		}
		*/
    }
}
