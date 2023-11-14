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
    public static final File FICH_QUESTIONS = new File("donnees/questions.data");

    /**
     * Le chemin dans lequel les catégories sont sauvegardées.
     */
    public static final File FICH_CATEGORIES = new File("donnees/categories.data");

    /** Liste de Categorie */
    public static ArrayList<Categorie> listeCategorie;

    /** Liste de Categorie */
    public static ArrayList<Question> listeQuestions;

    /** Enregistre le numéro scène que le bouton annuler de categorie doit renvoyer */
    public static int numScenePrecedenteCategorie;

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
                listeCategorie = (ArrayList<Categorie>) 
                        chargerSauvegarde(FICH_CATEGORIES);
            }
            if (FICH_QUESTIONS.exists()) {
                listeQuestions = (ArrayList<Question>) 
                        chargerSauvegarde(FICH_QUESTIONS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            donneesChargees = false;
        }
        // Cas par défaut
        if (listeCategorie == null) {
            listeCategorie = new ArrayList<Categorie>();
            listeCategorie.add(new Categorie("Général"));
            donneesChargees = false;
        }
        if (listeQuestions == null) {
            listeQuestions = new ArrayList<Question>();
            donneesChargees = false;
        }
        afficherDonnees();
        return donneesChargees;
    }

    /**
     * Charge le fichier au chemin donné et renvoie la valeur stockée.
     * @param fichier le chemin du fichier à ouvrir
     * @return l'objet stocké
     * @throws FileNotFoundException si le fichier n'existe pas
     * @throws IOException si une erreur d'entrée / sortie se produit
     * @throws ClassNotFoundException s'il est impossible de convertir l'objet
     */
    private static Object chargerSauvegarde(File fichier) throws FileNotFoundException, 
            IOException, ClassNotFoundException {
        try (ObjectInputStream readerCategories = new ObjectInputStream(
                new FileInputStream(fichier))) {
            return readerCategories.readObject();
        }
    }
    
    private static void afficherDonnees() {
        System.out.println("CATEGORIES");
        for (Categorie cat : listeCategorie) {
            System.out.println(" - " + cat.getLibelle());
        }
        System.out.println("QUESTIONS");
        for (Question q : listeQuestions) {
            System.out.println(" - " + q.getLibelle());
        }
   }
    
   /** 
    * Vérifie que la catégorie ajoutée n'est pas un double 
    * @param aVerifier la catégorie que l'on souhaite vérifier
    * @return true si la catégorie est un doublon, false sinon.
    */
    public static boolean verifDoubleCategorie(Categorie aVerifier) {
    	boolean doubleOk = false;
    	for (int i = 0; i < listeCategorie.size() && !doubleOk; i++) {
    		doubleOk = listeCategorie.get(i).equals(aVerifier);
    	}
    	return doubleOk;
    }
    
    /** 
     * Vérifie que la question ajoutée n'est pas un double
     * @param aVerifier la question que l'on souhaite vérifier
     * @return true si la question est un doublon, false sinon
     */
    public static boolean verifDoubleQuestion(Question aVerifier) {
    	boolean doubleOk = false;
    	for (int i = 0; i < listeQuestions.size() && !doubleOk; i++) {
    		doubleOk = listeQuestions.get(i).equals(aVerifier);
    	}
    	return doubleOk;
    }
}
