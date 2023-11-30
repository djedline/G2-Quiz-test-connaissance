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

/** TODO comment class responsibility (SRP)
 * @author catal
 *
 */
public class Persistance {
    
	/** Contient les questions sur Java */
	public static final File FICHIER_IMPORT_QUEST_JAVA
		= new File("fichiers_sauvegarde_partage/fichier_csv_stock/questionsbasiques.csv");
	
	/** Contient les questions d'orthographe */
	public static final File FICHIER_IMPORT_QUEST_ORTHO 
		= new File("fichiers_sauvegarde_partage/fichier_csv_stock/questionsorthographe.csv");

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
	 * Sauvegarde la base de questions et de catégories.
	 * 
	 * @return true si la sauvegarde a réussi, false sinon
	 */
	public static boolean sauvegarder() {
	    try {
	        creerSauvegarde(FICH_CATEGORIES, Donnees.listeCategorie);
	        creerSauvegarde(FICH_QUESTIONS, Donnees.listeQuestions);
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
	    boolean donneesChargees = Persistance.chargerFichiersPersistance();
	    // Cas par défaut
	    if (Donnees.listeCategorie == null || Donnees.listeCategorie.size() == 0) {
	        Donnees.listeCategorie.add(new Categorie(Donnees.NOM_CATEGORIE_DEFAUT));
	        donneesChargees = false;
	    }
	    if (Donnees.listeQuestions == null || Donnees.listeQuestions.size() == 0) {
	    	Persistance.chargerQuestionsParDefaut();
	        donneesChargees = false;
	    }
	    Donnees.afficherDonnees();
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
	    try (ObjectInputStream readerCategories 
	    		= new ObjectInputStream(new FileInputStream(fichier))) {
	        return readerCategories.readObject();
	    }
	}
	
	/**
	 * Charge les fichiers de persistance et les crée s'ils n'existent pas.
	 * @return true si les fichiers ont pu être chargés, false autrement.
	 */
	private static boolean chargerFichiersPersistance() {
		try {
	        if (Persistance.FICH_CATEGORIES.exists()) {
	            Donnees.listeCategorie = 
	            		(ArrayList<Categorie>) Persistance.chargerSauvegarde(Persistance.FICH_CATEGORIES);
	        } else {
	            Persistance.FICH_CATEGORIES.createNewFile();
	        }
	        if (Persistance.FICH_QUESTIONS.exists()) {
	            Donnees.listeQuestions = 
	            		(ArrayList<Question>) Persistance.chargerSauvegarde(Persistance.FICH_QUESTIONS);
	        } else {
	            Persistance.FICH_CATEGORIES.createNewFile();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}

	/**
	 * Charge la base de questions et les catégories sans importer les questions
	 * d'Orthographe et Java.
	 * 
	 * @return true si la sauvegarde a réussi, false sinon
	 */
	public static boolean chargerSansImport() {
	    boolean donneesChargees = chargerFichiersPersistance();
	    // Cas par défaut
	    if (Donnees.listeCategorie == null || Donnees.listeCategorie.size() == 0) {
	        Donnees.listeCategorie.add(new Categorie(Donnees.NOM_CATEGORIE_DEFAUT));
	        donneesChargees = false;
	    }
	    if (Donnees.listeQuestions == null || Donnees.listeQuestions.size() == 0) {
	        donneesChargees = false;
	    }
	    Donnees.afficherDonnees();
	    return donneesChargees;
	}

	/**
	 * Si les données n'existent pas, charge les données
	 * des banques de questions Java & Orthographe.
	 */
	public static void chargerQuestionsParDefaut() {
		Donnees.reinitialiserDonnees();
		
		try {
			ImportExport.importer(FICHIER_IMPORT_QUEST_JAVA);
			ImportExport.importer(FICHIER_IMPORT_QUEST_ORTHO);
		} catch (IOException e) {
			new Alert(AlertType.ERROR, "Impossible de charger les données "
					+ "par défaut.\n" + e.getMessage()).show();
		}
	}

	/**
	 * Vide les fichiers de sauvegarde.
	 */
	public static void effacerSauvegarde() {
	    FICH_CATEGORIES.delete();
	    FICH_QUESTIONS.delete();
	}
}
