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

/**
 * Centralise les données de l'application.
 * 
 * @author djedline.boyer
 */
public class Donnees {

    /**
     * Le chemin dans lequel les questions sont sauvegardées.
     */
    private static String CHEMIN_QUESTIONS = "donnees/questions.data";

    /**
     * Le chemin dans lequel les données sont sauvegardées.
     */
    private static String CHEMIN_CATEGORIES = "donnees/categories.data";

    /** Liste de Categorie */
    public static ArrayList<Categorie> listeCategorie = new ArrayList<>();

    /** Liste de Categorie */
    public static ArrayList<Question> listeQuestions = new ArrayList<>();

    /** TODO comment field role (attribute, association) */
    public static int numScenePrecedenteCategorie;

    /**
     * Sauvegarde la base de questions et de catégories.
     * 
     * @return true si la sauvegarde a réussi, false sinon
     */
    public static boolean sauvegarder() {
        try {
            creerSauvegarde(CHEMIN_CATEGORIES, listeCategorie);
            creerSauvegarde(CHEMIN_QUESTIONS, listeQuestions);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Sauvegarde un objet dans un fichier.
     * @param chemin         le chemin du fichier à écrire
     * @param donneesAEcrire les données que l'on souhaite sauvegarder
     * @throws IOException si l'enregistrement est impossible
     */
    private static void creerSauvegarde(String chemin, Object donneesAEcrire) throws IOException {
        File f = new File(chemin);
        if (!f.exists()) {
            f.createNewFile();
        }

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(chemin));
        writer.writeObject(donneesAEcrire);
        writer.close();
    }

    /**
     * Charge la base de questions et les catégories.
     * 
     * @return true si la sauvegarde a réussi, false sinon
     */
    public static boolean charger() {
        try {
            listeCategorie = (ArrayList<Categorie>) chargerSauvegarde(CHEMIN_CATEGORIES);
            listeQuestions = (ArrayList<Question>) chargerSauvegarde(CHEMIN_QUESTIONS);
        } catch (Exception e) {
            e.printStackTrace();
            if (listeCategorie == null) {
                listeCategorie = new ArrayList<Categorie>();
                listeCategorie.add(new Categorie("Tous"));
            }
            if (listeQuestions == null) {
                listeQuestions = new ArrayList<Question>();
            }
            return false;
        }
        return true;
    }

    /**
     * Charge le fichier au chemin donné et renvoie la valeur stockée.
     * @param chemin le chemin du fichier à ouvrir
     * @return l'objet stocké
     * @throws FileNotFoundException si le fichier n'existe pas
     * @throws IOException si une erreur d'entrée / sortie se produit
     * @throws ClassNotFoundException s'il est impossible de convertir l'objet
     */
    private static Object chargerSauvegarde(String chemin) throws FileNotFoundException, 
            IOException, ClassNotFoundException {
        try (ObjectInputStream readerCategories = new ObjectInputStream(
                new FileInputStream(chemin))) {
            return readerCategories.readObject();
        }
    }
}
