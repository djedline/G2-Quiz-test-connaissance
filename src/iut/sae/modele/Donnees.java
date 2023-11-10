/*
 * Donnees.java                                    10 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.util.ArrayList;

/** TODO comment class responsibility (SRP)
 * @author djedline.boyer
 *
 */
public class Donnees {
    
    /** Liste de Categorie */
    public static ArrayList <Categorie> listeCategorie = new ArrayList <>();
    
    /** Liste de Categorie */
    public static ArrayList <Question> listeQuestion = new ArrayList <>();
    
    /** TODO comment field role (attribute, association) */
    public static int numScenePrecedenteCategorie;
    
    /** TODO comment method role
     * @param args
     */
    public static void main(System[] args) {
        listeCategorie.add(new Categorie("Tous"));
    }
}
