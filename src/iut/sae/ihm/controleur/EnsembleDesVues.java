/*
 * Gére la correspondance entre le code d'une vue (un entier) et le nom
 * du fichier fxml décrivant cette vue 05/23
 */
package iut.sae.ihm.controleur;
/**
 * Classe outil qui établit la correspondance entre un code de vue (sous la
 * forme d'un entier) et le nom du fichier fxml contenant la vue associée
 * à ce code.
 * @author nael.briot, djedline.boyer, tany.catala-bailly
 * @version 1.0
 *
 */
public class EnsembleDesVues {
    /** Code de la vue principale */
    public static final int VUE_PRINCIPALE = 0;
    /** Code de la vue du menu de la gestion des données */
    public static final int VUE_MENU_GESTION_DONNEES = 1;
    /** Code de la vue de la création des questions */
    public static final int VUE_QUESTION = 2;
    /** Code de la vue de la création des catégories */
    public static final int VUE_CATEGORIE = 3;
    /** Code de la vue de la création du questionnaire */
    public static final int VUE_QUESTIONNAIRE = 4;
    /** Code de la vue de la gestion de l'échange de données */
    public static final int VUE_GESTION_IMPEXP = 5;
    /** Code de la vue pour l'importation de données */
    public static final int VUE_IMPORT = 6;
    /** Code de la vue pour l'exportation de données */
    public static final int VUE_EXPORT = 7;
    /** Code de la vue pour gerer les donnees*/
    public static final int VUE_GESTION_DONNEES = 8;
    
    /** Tableau contenant les noms des fichiers fxml des différentes vues
     * de l'application. Il y a une correspondance entre l'indice de la case
     * du tableau et le code de la vue défini en tant que constante
     */
    private static final String[] NOM_DES_VUES =
        { "MenuPrincipal.fxml", "MenuGestionDonnees.fxml", "creerQuestion.fxml",
          "creerCategorie.fxml",  "creerQuestionnaire.fxml", "MenuGestionImpExp.fxml", 
          "ImportDonnees.fxml", "ExportDonnees.fxml", "GestionDonnees.fxml"};
    /**
     * Renvoie le nom du fichier fxml contenant la vue dont le code est donné
     * en paramètre
     * @param codeVue code de la vue dont le fichier fxml doit être renvoyé
     * @return une chaîne contenant le nom du fichier fxml
     * @throw IllegalArgumentException levée si le code argument n'est pas valide
     */
    public static String getNomVue(int codeVue) {
        if (codeVue < 0 || codeVue >= NOM_DES_VUES.length) {
            throw new IllegalArgumentException("Code vue " + codeVue + " invalide");
        }
        return NOM_DES_VUES[codeVue];
    }
}