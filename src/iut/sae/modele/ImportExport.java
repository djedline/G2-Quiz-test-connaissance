package iut.sae.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utilise le format CSV avec la structure : Catégorie, Niveau, Libellé, Vrai,
 * Faux1, Faux2, Faux3, Faux4, Feedback Les réponses optionnelles ne sont pas
 * omises, juste vides.
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 */
public class ImportExport {

	/** Délimiteur utilisé pour séparer les colonnes */
	public static final char DELIMITEUR = ';';
	/** Retour à la ligne séparant les lignes */
	public static final String NEW_LINE = "/n";

	/** Ordre des champs dans les fichiers CSV */
	public static final String[] NOM_COLONNE = { "Catégorie", "Niveau", "Libellé", "Vrai", "Faux1", "Faux2", "Faux3",
			"Faux4", "Feedback" };

	/**
	 * Envoie l'ensemble des questions de l'application dans un fichier
	 * 
	 * @param chemin le chemin du fichier d'exportation
	 * @throws IOException s'il est impossible d'écrire les données
	 */
	public static void exporter(String chemin) throws IOException {
		File aEcrire = new File(chemin);

		if (aEcrire.exists()) {
			throw new IOException("Ce fichier existe déjà !");
		}
		if (!aEcrire.canWrite()) {
			throw new IOException("Impossible de modifier ce fichier !");
		}
		if (aEcrire.isDirectory()) {
			throw new IOException("Impossible d'écrire dans un dossier !" + "Indiquez un fichier n'existant pas déjà.");
		}
		aEcrire.createNewFile();

		FileWriter fw = new FileWriter(aEcrire);
		for (Question q : Donnees.listeQuestions) {
			fw.write(exporterQuestion(q));
		}
		fw.close();
	}

	private static String exporterQuestion(Question q) {
		StringBuilder s = new StringBuilder();
		s.append(q.getCategorie());
		s.append(DELIMITEUR);
		s.append(q.getDifficulte());
		s.append(DELIMITEUR);
		s.append(q.getLibelle());
		s.append(DELIMITEUR);
		s.append(q.getPropositionJuste());
		s.append(DELIMITEUR);
		for (int i = 0; i < 4; i++) {
			if (i < q.getPropositionFausse().length) {
				s.append(q.getPropositionFausse()[i]);
			}
			s.append(DELIMITEUR);
		}
		s.append(q.getFeedback());

		s.append(NEW_LINE);
		return s.toString();
	}

	/**
	 * Récupère l'ensemble des questions contenues dans un fichier CSV.
	 * 
	 * @param chemin le chemin du fichier à importer
	 * @throws IOException s'il est impossible de l'importer
	 */
	public static void importer(String chemin) throws IOException {
		File aImporter = new File(chemin);

		if (!aImporter.exists()) {
			throw new IOException("Ce fichier ne peut être lu car il n'existe pas");
		}
		if (!aImporter.canRead()) {
			throw new IOException("Vous ne disposez pas des droits pour lire ce fichier.");
		}
		if (aImporter.isDirectory()) {
			throw new IOException("Impossible de lire un dossier. Indiquez un fichier.");
		}
		
		BufferedReader bf = new BufferedReader(new FileReader(aImporter));
		
		/* indice pour les messages d'erreur. Commence à 1 par soucis de lisibilité
		 * pour l'utilisateur */
		int noLigne = 1;
		while (bf.ready()) {
			String ligne = bf.readLine();
			if (noLigne != 1) {
				importerLigne(ligne, noLigne); // ignorer l'en-tête
			}
			noLigne++;
		}
	}

	private static void importerLigne(String ligne, int noLigne) throws FichierMalFormeException {
		String[] colonnes = decouper(ligne);

		// vérification que la difficulté est bien un nombre entre 1 et 3
		int diff = 0; // soit modifié soit erreur
		try {
			diff = Integer.parseInt(colonnes[1]);
			if (diff < 1 || diff > 3) {
				throw new FichierMalFormeException("Difficulté hors des bornes (1, 2 ou 3) à la ligne " + noLigne);
			}
		} catch (Exception e) {
			// TODO en attente de la réponse de Mme Servieres
			diff = 2;
			//throw new FichierMalFormeException("La difficulté n'est pas un champ numérique à la ligne " + noLigne);
		}
		
		System.out.println("WAIT");

		Categorie categorie = analyserCategorieImport(colonnes[0]);

		// générer le tableau de réponses fausses
		ArrayList<String> repFausses = new ArrayList<>();
		for (int i = 5; i < 9; i++) {
			if (!colonnes[i].isBlank()) {
				repFausses.add(colonnes[i]);
				System.out.println("On rajoute : " + colonnes[i] + " dans le " + "tableau.");
			}
		}
		// récupérer un array de réponses fausses
		String[] fausses = new String[repFausses.size()];
		fausses = repFausses.toArray(fausses);
		System.out.println("Nombre de rep fausses : " + fausses.length);
		Question questionGeneree = new Question(colonnes[2], categorie, colonnes[3], fausses, colonnes[8], diff);
		Donnees.listeQuestions.add(questionGeneree);
	}

	private static Categorie analyserCategorieImport(String catImportee) {
		catImportee = catImportee.strip();
		// ajoute la catégorie si elle existe
		Categorie bonneCategorie = null;
		for (Categorie cat : Donnees.listeCategorie) {
			if (cat.getLibelle().equalsIgnoreCase(catImportee)) {
				return cat;
			}
		}

		// si vide, ajout à la catégorie par défaut
		if (catImportee.isBlank()) {
			for (Categorie catExistante : Donnees.listeCategorie) {
				if (catExistante.getLibelle().equals(Donnees.NOM_CATEGORIE_DEFAUT)) {
					return catExistante;
				}
			}
		}
		
		// création de la nouvelle catégorie
		bonneCategorie = new Categorie(catImportee);
		Donnees.listeCategorie.add(bonneCategorie);
		return bonneCategorie;
	}

	public static String[] decouper(String ligne) throws FichierMalFormeException {
		String[] valeurs = new String[9];
		for (int i = 0; i < valeurs.length; i++) {
			valeurs[i] = "";
		}

		int colonneARemplir = 0;
		boolean guillemetsOuverts = false;
		for (int c = 0; c < ligne.length(); c++) {
			char ch = ligne.charAt(c);
			if (ch == '"') {
				guillemetsOuverts = !guillemetsOuverts;
			} else if (ch == DELIMITEUR && !guillemetsOuverts) {
				colonneARemplir++;
			} else {
				valeurs[colonneARemplir] += ch;
			}
		}
		if (colonneARemplir != 8) {
			throw new FichierMalFormeException("Nombre de colonnes invalides : " + (colonneARemplir + 1));
		} else {
			for (int i = 0; i < valeurs.length; i++) {
				System.out.println(NOM_COLONNE[i] + " : " + valeurs[i]);
			}
			return valeurs;
		}
	}
}
