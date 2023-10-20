/*
 * IUT de Rodez, TPA1                                           18/10/2023
 * TestCategorie.java
 * Pas de droits d'auteur, pas de copyright ni de "copyleft"
 */

package iut.sae.modele.tests;

import iut.sae.modele.Categorie;

/**
 * Utilisation de la classe Catégorie et test de ses méthodes
 * @author INFO2 TPA1
 * @version 1.0
 */
public class TestCategorie {
         
     /**
      * tests du constructeur de la classe Catégorie 
      */
     public static void testConstructeur() {
         System.out.println("\nTest de la création d'une catégorie :\n"
                             + "-------------------------------------------\n");
             try {
                 Categorie nouvelleCategorie1 = new Categorie("Java"); 
                 System.out.println("Test 1 OK");
             } catch(IllegalArgumentException erreurProgramme) {
                 System.out.print("Le programme a eu une erreur");
             }
                 
             try {
                 Categorie nouvelleCategorie2 = new Categorie(""); 
                 System.out.println("Attention : aucune erreur n'a été renvoyé");
             } catch(IllegalArgumentException erreurLibelle) {
                 System.out.println("Test 2 OK");
             }
     }
         
         /**
          * tests du getter de la classe Catégorie
          */
         private static void testGetter() {
                 String test = "java";
                 Categorie syntaxe = new Categorie("java");
                 if (test.equals(syntaxe.getLibelle())) {
                         System.out.println("Test getter OK");
                 } else {
                         System.out.println("Test Not OK");
                 }
        }
         
         /**
          * Programme principal
          * @param args argument non utilisé
          */
         public static void main(String[] args){
                 testConstructeur();
                 testGetter();
         }
}
