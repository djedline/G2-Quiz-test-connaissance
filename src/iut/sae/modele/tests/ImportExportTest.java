package iut.sae.modele.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

import iut.sae.modele.Categorie;
import iut.sae.modele.Donnees;
import iut.sae.modele.ImportExport;
import iut.sae.modele.Persistance;
import iut.sae.modele.Question;

class ImportExportTest {

    
    private static final File FICHIER_IMPORT_QUEST_JAVA = 
            Persistance.FICHIER_IMPORT_QUEST_JAVA;

    private static final File FICHIER_IMPORT_QUEST_ORTHO = 
            Persistance.FICHIER_IMPORT_QUEST_ORTHO;

    private static final File FICHIER_EXPORT_QUEST = 
            new File("src/iut/sae/modele/tests/exporte.csv");

    @Test
    void testImportJava() {
        Donnees.reinitialiserDonnees();

        final int NB_CAT_JAVA = 8;
        final int NB_QUEST_JAVA = 31;

        try {
            ImportExport.importer(Persistance.FICHIER_IMPORT_QUEST_JAVA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(NB_CAT_JAVA + 1, Donnees.listeCategorie.size());
        assertEquals(NB_QUEST_JAVA, Donnees.listeQuestions.size());
        
        // Vérification de la première question
        Categorie cat = new Categorie("Commentaire");
        String[] repFausses = new String[] {
        		"le délimiteur /*",
        		"le délimiteur //",
        		"le délimiteur (*"
        };
        Question q = new Question("Quel est le délimiteur de début d'un commentaire Javadoc ?", 
        		cat,"le délimiteur /**", repFausses, "", 1);
        assertEquals(q, Donnees.listeQuestions.get(0));
        
        // Vérification de la 11e question
        String[] repFausses2 = new String[] {
        		"Un résumé très bref, pas plus d'une ligne, du rôle du programme ",
        		"Il n'y a pas de commentaire Javadoc juste avant la ligne \"public class …\"",
        		"Le nom du fichier contenant le programme"
        };
        Question q2 = new Question("Que doit décrire le texte écrit dans le "
        		+ "commentaire Javadoc situé juste avant la ligne "
        		+ "\"public class …\" ?", 
        		cat,"Le rôle du programme, en explicitant de manière précise ce rôle ", 
        		repFausses2, "Un texte libre laissé à l'appréciation du programmeur", 2);
        assertEquals(q, Donnees.listeQuestions.get(0));
        
        Donnees.afficherDonnees();

    }

    @Test
    void testImportOrthographe() {
        Donnees.reinitialiserDonnees();

        final int NB_CAT_ORTHO = 0;
        final int NB_QUEST_ORTHO = 189;

        try {
            ImportExport.importer(Persistance.FICHIER_IMPORT_QUEST_ORTHO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(NB_CAT_ORTHO + 1, Donnees.listeCategorie.size());
        assertEquals(NB_QUEST_ORTHO, Donnees.listeQuestions.size());
        
        // Vérification de la première question
        Categorie cat = Donnees.listeCategorie.get(0);
        String[] repFausses = new String[] {
        		"correct"
        };
        Question q = new Question("La panne est dûe à la vétusté de l'appareil.", 
        		cat,"incorrect", repFausses, "due", 1);
        assertEquals(q, Donnees.listeQuestions.get(0));
        Donnees.afficherDonnees();
    }

    private static final String EXPORT_VIDE = 
            "Catégorie;Niveau;Libellé;Vrai;Faux1;Faux2;Faux3;Faux4;Feedback;\n";

    @Test
    void testExportFichierVide() throws IOException {

        Donnees.reinitialiserDonnees();
        FICHIER_EXPORT_QUEST.delete();
        ImportExport.exporter(FICHIER_EXPORT_QUEST);
        String contenu = lireFichier(FICHIER_EXPORT_QUEST);

        // fichier vide
        assertEquals(EXPORT_VIDE, contenu);
    }

    private static final String EXPORT_UNE_LIGNE = EXPORT_VIDE
            +"Cat;1;Maquestion;JeSuisJuste;Rouge;Vert;Bleu;Argenté;Feedback;\n";

    @Test
    void testExportFichierUneLigne() throws IOException {
        Categorie nvCategorie = new Categorie("Cat");
        Donnees.listeCategorie.add(nvCategorie);
        String[] repFausses = { "Rouge", "Vert", "Bleu", "Argenté" };
        Question nvQuestion = new Question(
                "Maquestion", nvCategorie, "JeSuisJuste", repFausses, "Feedback",
                1);
        Donnees.listeQuestions.add(nvQuestion);

        FICHIER_EXPORT_QUEST.delete();
        ImportExport.exporter(FICHIER_EXPORT_QUEST);
        String contenu = lireFichier(FICHIER_EXPORT_QUEST);
        assertEquals(EXPORT_UNE_LIGNE, contenu);
    }

    private static final String EXPORT_SPECIAUX = EXPORT_VIDE
            + "Cat;1;\"\"\"J'aime les vacances\"\" - Molière\";\"JeSuisJuste"
            + "\"\"\"\"\";\"Hello\n\";\"\"\"Hello\"\"\";\" ; Hello\";\"\"\""
            + "Hello\"\"\";Feedback;\n";

    @Test
    void testExportFichierCaracteresSpeciaux() throws IOException {
        Categorie nvCategorie = new Categorie("Cat");
        Donnees.listeCategorie.add(nvCategorie);
        String[] repFausses = { 
                "Hello\n", "\"Hello\"", " ; Hello", "\"Hello\"" };
        Question nvQuestion = new Question(
                "\"J'aime les vacances\" - Molière", nvCategorie, "JeSuisJuste"
                        + "\"\"",
                repFausses, "Feedback", 1);
        Donnees.listeQuestions.add(nvQuestion);

        FICHIER_EXPORT_QUEST.delete();
        ImportExport.exporter(FICHIER_EXPORT_QUEST);
        String contenu = lireFichier(FICHIER_EXPORT_QUEST);
        assertEquals(EXPORT_SPECIAUX, contenu);
    }

    private String lireFichier(File f) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileReader fr = new FileReader(f, Charset.forName("UTF-8"));
        while (fr.ready()) {
            sb.append(Character.toString(fr.read()));
        }
        fr.close();
        return sb.toString();
    }

}
