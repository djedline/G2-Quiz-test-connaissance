package iut.sae.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utilise le format CSV avec la structure : Catégorie, Niveau, Libellé, Vrai,
 * Faux1, Faux2, Faux3, Faux4, Feedback Les réponses optionnelles ne sont pas
 * omises, juste vides.
 * 
 * @author baudr
 */
public class ImportExport {

	public static final char DELIMITEUR = ';';
	public static final String NEW_LINE = "/n";

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
		for (Question q : Donnees.listeQuestion) {
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

	public static void importer(String chemin) throws IOException {
		File aImporter = new File(chemin);

		if (!aImporter.exists()) {
			throw new IOException("Ce fichier ne peut être lu car il n'existe pas");
		}
		if (aImporter.canRead()) {
			throw new IOException("Vous ne disposez pas des droits pour lire ce fichier.");
		}
		if (aImporter.isDirectory()) {
			throw new IOException("Impossible de lire un dossier. Indiquez un fichier.");
		}

		BufferedReader bf = new BufferedReader(new FileReader(aImporter));
		int noLigne = 1; // s'adressant à des non-informaticiens, on préfèrera commencer à 1 comme sur
							// Excel
		while (bf.ready()) {
			String ligne = bf.readLine();
			if (noLigne != 1) {
				importerLigne(ligne, noLigne); // ignorer l'en-tête
			}
			noLigne++;
		}
	}

	private static void importerLigne(String ligne, int noLigne) throws FichierMalFormeException {
		String[] colonnes = ligne.split(";");
		if (colonnes.length != 9) {
			throw new FichierMalFormeException("Nombre de colonnes insuffisant. "
					+ "Vérifiez la structure.");
		}

		// vérification que la difficulté est bien un nombre entre 1 et 3
		int diff = 0; // soit modifié soit erreur
		try {
			diff = Integer.parseInt(colonnes[1]);
			if (diff < 1 || diff > 3) {
				throw new FichierMalFormeException(
						"Difficulté hors des bornes (1, 2 ou 3) à la ligne " + noLigne);
			}
		} catch (Exception e) {
			throw new FichierMalFormeException(
					"La difficulté n'est pas un champ numérique à la ligne " + noLigne);
		}

		// ajoute la catégorie si n'existe pas déjà
		String catImportee = colonnes[0];
		Categorie bonneCategorie = null;
		for (Categorie cat : Donnees.listeCategorie) {
			if (cat.getLibelle().equalsIgnoreCase(catImportee)) {
				bonneCategorie = cat;
			}
		}
		if (bonneCategorie == null) {
			bonneCategorie = new Categorie(catImportee);
			Donnees.listeCategorie.add(bonneCategorie);
		}
		
		// générer le tableau de réponses fausses
		String[] fausses = new String[] {
				colonnes[5],
				colonnes[6],
				colonnes[7],
				colonnes[8]
		};
		
		
		Question questionGeneree = new Question(colonnes[2], bonneCategorie, 
				colonnes[3], fausses, colonnes[8], diff);
		Donnees.listeQuestion.add(questionGeneree);
	}

}
