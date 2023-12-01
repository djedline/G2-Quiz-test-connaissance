/*
 * ImportExport.java                                                  21/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilise le format CSV (séparateur ";", encodage UTF-8) avec la structure
 * indiquée par {@code #NOM_COLONNE}
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class ImportExport {

    /** Délimiteur utilisé pour séparer les colonnes */
    public static final char DELIMITEUR = ';';
    
    /** Retour à la ligne séparant les lignes */
    public static final char NEW_LINE = '\n';

    /** Symbole pour définir une chaine de caractères */
    public static final char GUILLEMET = '"';

    /** Ordre des champs dans les fichiers CSV */
    public static final String[] NOM_COLONNE = 
        { "Catégorie", "Niveau", "Libellé", "Vrai", "Faux1", "Faux2", "Faux3",
            "Faux4", "Feedback" };


    /**
     * Envoie l'ensemble des questions de l'application dans un fichier.
     * 
     * @param aEcrire le fichier dans lequel on va écrire
     * @throws IOException s'il est impossible d'écrire les données
     */
    public static void exporter(File aEcrire) throws IOException {
        exporter(aEcrire, Donnees.listeQuestions);
    }

    /**
     * Envoie la listes des questions choisies par l'utilisateur dans un fichier
     * @param aEcrire
     * @param selectionnees
     * @throws IOException
     */
    public static void exporter(File aEcrire, List<Question> selectionnees) 
            throws IOException {

        if (aEcrire == null || selectionnees == null) {
            throw new IllegalArgumentException(
                    "Impossible d'écrire avec un fichier " 
                  + "ou des questions nulles.");
        }

        if (!aEcrire.exists()) {
            aEcrire.createNewFile();
        }

        if (!aEcrire.canWrite()) {
            throw new IOException(
                    "Impossible de modifier le fichier " 
                  + aEcrire.getAbsolutePath() + "!");
        }
        if (aEcrire.isDirectory()) {
            throw new IOException(
                    "Impossible d'écrire dans un dossier !" 
                  + "Indiquez un fichier.");
        }

        FileWriter fw = new FileWriter(aEcrire);
        fw.write(produireEntete());
        for (Question q : selectionnees) {
            fw.write(exporterQuestion(q));
            System.out.println(exporterQuestion(q));
        }
        fw.close();
    }

    private static String produireEntete() {
        StringBuilder s = new StringBuilder();
        for (String nom : NOM_COLONNE) {
            s.append(nom);
            s.append(DELIMITEUR);
        }
        s.append(NEW_LINE);
        return s.toString();
    }

    /**
     * Met un texte entre guillemets si le contenu de la string comporte des
     * caractères spéciaux
     * 
     * @param s la string à modifier
     * @return la string formatée
     */
    private static String formater(String s) {
        Pattern pat = Pattern.compile(
                "[" + NEW_LINE + DELIMITEUR + GUILLEMET + "]");
        Matcher mat = pat.matcher(s);
        if (mat.find()) {
            StringBuilder nvString = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == GUILLEMET) {
                    nvString.append(c); // dédoubler
                }
                nvString.append(c);
            }
            return GUILLEMET + nvString.toString() + GUILLEMET;
        } else {
            return s;
        }
    }

    /**
     * Méthode qui exporte une question choisi en parametre
     * @param q
     * @return
     */
    private static String exporterQuestion(Question q) {
        StringBuilder s = new StringBuilder();
        s.append(formater(q.getCategorie().getLibelle()));
        s.append(DELIMITEUR);
        s.append(q.getDifficulte());
        s.append(DELIMITEUR);
        s.append(formater(q.getLibelle()));
        s.append(DELIMITEUR);
        s.append(formater(q.getPropositionJuste()));
        s.append(DELIMITEUR);
        for (int i = 0; i < 4; i++) {
            if (i < q.getPropositionFausse().size()) {
                s.append(formater(q.getPropositionFausse().get(i)));
            }
            s.append(DELIMITEUR);
        }
        s.append(formater(q.getFeedback()));
        s.append(DELIMITEUR);
        s.append(NEW_LINE);
        return s.toString();
    }

    /**
     * Récupère l'ensemble des questions contenues dans un fichier CSV.
     * 
     * @param chemin le chemin du fichier à importer
     * @throws IOException s'il est impossible de l'importer
     */
    public static void importer(File chemin) throws IOException {

        if (!chemin.exists()) {
            throw new IOException(
                    "Le fichier " + chemin.getAbsolutePath() 
                  + " ne peut être lu car il n'existe pas");
        }
        if (!chemin.canRead()) {
            throw new IOException(
                    "Vous ne disposez pas des droits pour lire le fichier " 
                  + chemin.getAbsolutePath());
        }
        if (chemin.isDirectory()) {
            throw new IOException(
                    "Impossible de lire un dossier. Indiquez un fichier.");
        }

        BufferedReader bf = new BufferedReader(new FileReader(chemin));

        /*
         * indice pour les messages d'erreur. Commence à 1 par soucis 
         * de lisibilité pour l'utilisateur
         */
        int noLigne = 1;
        while (bf.ready()) {
            String ligne = bf.readLine();
            if (noLigne != 1) {
                importerLigne(ligne, noLigne); // ignorer l'en-tête
            }
            noLigne++;
        }
        bf.close();
    }

    private static void importerLigne(String ligne, int noLigne) 
            throws FichierMalFormeException {

        String[] colonnes = decouper(ligne);
        if (!tousVides(colonnes)) {
            // vérification que la difficulté est bien un nombre entre 1 et 3
            int diff = 0; // soit modifié soit erreur
            try {
                diff = Integer.parseInt(colonnes[1]);
                if (diff < 1 || diff > 3) {
                    throw new FichierMalFormeException(
                            "Difficulté hors des bornes (1, 2 ou 3) à la ligne " 
                          + noLigne);
                }
            } catch (Exception e) {
                throw new FichierMalFormeException(
                        "La difficulté n'est pas un champ numérique à la ligne " 
                      + noLigne);
            }

            Categorie categorie = analyserCategorieImport(colonnes[0]);

            // générer le tableau de réponses fausses
            ArrayList<String> repFausses = new ArrayList<>();
            for (int i = 4; i < 7; i++) {
                if (!colonnes[i].isBlank()) {
                    repFausses.add(colonnes[i]);
                }
            }
            // récupérer un array de réponses fausses
            String[] fausses = new String[repFausses.size()];
            fausses = repFausses.toArray(fausses);
            Question questionGeneree = new Question(colonnes[2], categorie, 
                    colonnes[3], fausses, colonnes[8], diff);
            Donnees.listeQuestions.add(questionGeneree);
        }
    }

    /**
     * Vérifie que toutes les colonnes d'une ligne sont vides
     * 
     * @param colonnes les valeurs à vérifier
     * @return true si les colonnes sont toutes vides, false sinon
     */
    private static boolean tousVides(String[] colonnes) {
        boolean tousVides = true;
        for (String valeur : colonnes) {
            tousVides &= valeur.isBlank();
        }
        return tousVides;
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
                if (catExistante.getLibelle().equals(
                        Donnees.NOM_CATEGORIE_DEFAUT)) {
                    return catExistante;
                }
            }
        }

        // création de la nouvelle catégorie
        bonneCategorie = new Categorie(catImportee);
        Donnees.listeCategorie.add(bonneCategorie);
        return bonneCategorie;
    }

    /**
     * Méthode qui prend en argument une ligne et la découpe pour récupérer les
     * valeurs comprises dans celle ci
     * 
     * @param ligne la ligne à découper
     * @return valeurs l'ensemble des valeurs découpées sur la ligne
     * @throws FichierMalFormeException si la ligne n'existe pas
     */
    public static String[] decouper(String ligne) 
            throws FichierMalFormeException {
        int NB_COLONNES = NOM_COLONNE.length;

        // initialisation du tableau des données
        String[] valeurs = new String[NB_COLONNES];
        for (int i = 0; i < valeurs.length; i++) {
            valeurs[i] = "";
        }
        int colonneARemplir = 0;
        boolean contientCaracteresSpeciaux = false;
        int nbGuillemet = 0;
        for (int c = 0; c < ligne.length(); c++) {
            char courant = ligne.charAt(c);

            if (courant == GUILLEMET) {
                nbGuillemet++;
                contientCaracteresSpeciaux = true;
                System.out.println("les guillemet " + nbGuillemet);
            }

            if (courant == DELIMITEUR && nbGuillemet % 2 == 0) {
                System.out.println("les guillemet " + nbGuillemet);
                nbGuillemet = 0;
                if (contientCaracteresSpeciaux) {
                    valeurs[colonneARemplir] = 
                            deformater(valeurs[colonneARemplir]);
                    contientCaracteresSpeciaux = false;
                }
                colonneARemplir++;
            } else {
                if (colonneARemplir < NB_COLONNES) {
                    valeurs[colonneARemplir] += courant;
                } else {
                    for (String element : valeurs) {

                        System.out.println("l'element " + element);
                    }

                    // Si on veut ajouter dans une colonne inexistante.
                    throw new FichierMalFormeException(
                            "Nombre de colonnes invalides (" 
                    + (colonneARemplir + 1) + " plutôt que " + NB_COLONNES);
                }
            }
        }
        return valeurs;
    }

    /**
     * Enlève le formatage des lignes de textes CSV (notamment dédoublement des
     * guillemets)
     * 
     * @param string la chaine à déformater
     * @return la chaine de caractère telle qu'elle peut être insérée
     */
    private static String deformater(String texte) {
        texte = texte.substring(1, texte.length() - 1);
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < texte.length(); c++) {
            char courant = texte.charAt(c);
            char precedent = c > 1 ? texte.charAt(c - 1) : ' ';
            if (courant != GUILLEMET || precedent != GUILLEMET) {
                sb.append(courant);
            }
        }
        return sb.toString();
    }
}
