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
    
    /** Enregistre le numéro scène que le bouton annuler de categorie doit renvoyer */
    public static int numScenePrecedenteCategorie;
    
   /** Verifie que la categorie ajouté n'est pas un double */
    public static boolean verifDoubleCategorie(Categorie aVerifier) {
    	boolean doubleOk = false;
    	for (int i = 0; i < listeCategorie.size() && !doubleOk; i++) {
    		doubleOk = listeCategorie.get(i).compareTo(aVerifier);
    	}
    	return doubleOk;
    }
    
    /** Verifie que la categorie ajouté n'est pas un double */
    public static boolean verifDoubleQuestion(Question aVerifier) {
    	boolean doubleOk = false;
    	for (int i = 0; i < listeQuestion.size() && !doubleOk; i++) {
    		doubleOk = listeQuestion.get(i).compareTo(aVerifier);
    	}
    	return doubleOk;
    }
    
    /** 
     * Initialise les données
     * @param args
     */
    public static void main(System[] args) {
        listeCategorie.add(new Categorie("General"));
    }
}
