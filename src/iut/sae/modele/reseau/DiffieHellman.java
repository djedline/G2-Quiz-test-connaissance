/*
 * DiffieHelman.java                                    15 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe qui permet de reproduire un échange de Diffie-Hellman pour générer une
 * clé de cryptage
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class DiffieHellman {

    private static final int MAX_P = 109;

    /**
     * Méthode qui permet de générer aléatoirement le modulo de l'échange
     * 
     * @return p le chiffre qui sert de modulo
     */
    public static int genererModulo() {
    	int p;
        do {
            p = (int) ( 1 + Math.random() * (MAX_P - 1));
        } while (!isPremier(p));
        System.out.println("P = " + p);
        return p;
    }

    /**
     * Méthode qui permet de générer aléatoirement g
     * 
     * @param p la modulo auquel on fait le calcul
     * @return g le générateur échangé par le client et le serveur
     */
    public static int genererGenerateur(int p) {
    	ArrayList<Integer> dejaFaits = new ArrayList<>();
    	final int MAX_G = p - 1;
    	int g=1;
    	boolean doublon;
    	boolean generateurValide;
    	int nbDoublons = 0;
        do {
        	g++;
        	dejaFaits.sort((o1, o2) -> (o1.compareTo(o2)));
        	/*
        	if(nbDoublons > p / 2) {
        		g = trouverEntierManquant(dejaFaits, MAX_G);
        	} else {
        		g = (int) (1 + Math.random() * (MAX_G - 1));
        	}
        	*/
            doublon = dejaFaits.contains(g);
            if (doublon) {
            	generateurValide = false;
            	nbDoublons++;
            } else {
            	dejaFaits.add(g);
                generateurValide = isGenerateur(g, p);
            }
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } while (!generateurValide && g < p);
        System.out.println(g + " est un générateur dans Z/" + p + "Z.");
        return g;
    }
    
    public static int trouverEntierManquant(ArrayList<Integer> liste, int max) {
    	boolean existe = false;
    	int entier = 0;
    	do {
    		entier++;
    		existe = liste.contains(entier);
    	} while (existe && entier < max);
    	if (entier == max) {
    		System.out.println("Max atteint modulo " + (max + 1) + ".");
    	}
    	System.out.println(entier + " n'a pas été testé modulo " + (max + 1) + ".");
    	return entier;
    }

    /**
     * Méthode qui permet de génerer aléatoirement x
     * 
     * @return x chiffre qui sert d'exposant
     */
    public static int genererX() {
        int x = (int) (1 + Math.random() * MAX_P - 1);
        return x;
    }

    /**
     * Méthode qui calcul une mise à la puissance pour un nombre et une puissance
     * donnée
     * 
     * @param nombre    le nombre entré par l'utilisateur
     * @param puissance la puissance entré par l'utilisateur
     * @param modulo    le modulo entré par l'utilisateur
     * @return le nombre passé en paramètre à la puissance rentré
     */
    public static int calculMisePuissance(int nombre, int puissance, int modulo) {
        return (int) Math.pow(nombre, puissance) % modulo;
    }

    /**
     * Méthode qui permet de déterminer si g est ou non un générateur du groupe
     * multiplicatif ℤ/pℤ
     * 
     * @param g le nombre à vérifier
     * @param p le modulo auquel on effectue les calculs
     * @return True ou False selon si le nombre choisi est un générateur ou non
     */
    public static boolean isGenerateur(int g, int p) {
        /** Ensemble des résultats de g modulo p ( g, g², g³, ..., g) */
        ArrayList<Integer> valeurGValide = new ArrayList<>();
        /** L'ensemble des valeurs dans ℤ/pℤ */
        for (int j = 1; j < p - 1; j++) {
            int valeurValide = (int) ((Math.pow(g, j)) % p); // Récupère la valeur (g, g², etc) % p
            System.out.println(g + "^" + j + " = " + valeurValide);
            if (!valeurGValide.contains(valeurValide)) { // Vérifie si le chiffre obtenu n'est pas déja présent
                valeurGValide.add(valeurValide);
                //System.out.println("Je rajoute " + valeurValide);
            } else {
            	System.out.println("Je sors parce que " + valeurValide + " est un doublon.");
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si le modulo p est un entier premier
     * 
     * @param p le modulo à vérifier
     * @return Vrai ou Faux selon si le nombre est premier ou non
     */
    public static boolean isPremier(int p) {
    	if (p == 1) {
    		return false;
    	}
        for (int i = 1; i <= Math.floor(Math.sqrt(p)); i++) {
            if (p % i == 0 && i != 1) {
            	System.out.println(p + " n'est pas premier.");
                return false;
            }
        }
        System.out.println(p + " est premier.");
        return true;
    }
}
