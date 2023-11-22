/*
 * DiffieHelman.java                                    15 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.util.ArrayList;

/** TODO comment class responsibility (SRP)
 * @author nael.briot
 * @version 1.0
 */
public class DiffieHelman {
    
    private final int MAX_P = Cryptage.TAILLE_ENSEMBLE;
    
    /*Variable contenant la valeur de p, le modulo pour l'échange de données*/
    private int p = 6;
    
    /*Variable contenant le générateur échangé par le serveur et le client en clair*/
    private int g = 5;
    
    /*Variable contenant le chiffre qui sert de premier exposant*/
    private int a;
    
    /*Variable contenant le chiffre qui sert de deuxième exposant*/
    private int b;

    /** 
     * Méthode qui permet de générer aléatoirement le modulo de l'échange
     * @return p le chiffre qui sert de modulo
     */
     /* public int genererModulo() {
        p = (int)(Math.random() * MAX_P);
        return p;
      } */
    /**
     * Méthode qui permet de générer g en fonction de p
     * @return g le générateur échangé par le client et le serveur
     */
     /*public int genererGenerateur() {      
        return g;
     }*/
    
    /** 
     * Méthode qui permet de déterminer si g est ou non un générateur du groupe
     * multiplicatif ℤ/pℤ
     * @return True ou False selon si le nombre choisi est un générateur ou non
     */
    public boolean isGenerateur() {
        /** Ensemble des résultats de g modulo p ( g, g², g³, ..., g) */
        ArrayList<Integer> valeurGValide= new ArrayList<>();
        /** L'ensemble des valeurs dans ℤ/pℤ*/
        ArrayList<Integer> ensembleP = new ArrayList<>();
        
        for (int i = 1; i < ensembleP.size(); i++) {
            ensembleP.add(i);
        }
        for (int j = 1; j < valeurGValide.size(); j++) {
            int valeurValide = (int)((Math.pow(g,j))%p); // Récupère la valeur (g, g², etc) modulo p
            if (!valeurGValide.contains(valeurValide)) { // Vérifie si le chiffre obtenu n'est pas déja présent 
                valeurGValide.add(valeurValide);
            }
        }
        if (valeurGValide.size()== ensembleP.size()) { // Vérifie la taille des 2 ArrayList 
            return true;
        } else{
            return false;
        }
    }
}    
