/*
<<<<<<< HEAD
ù * Cryptage.java                                    14 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
=======
 * Cryptage.java                                                    14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
>>>>>>> 9e76170f3c30abcb9ceba3a71a4610f0a101ede1
 */
package iut.sae.modele.reseau;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe qui permet le cryptage d'un message grâce à la génération d'une clé
 * puis le décryptage de ce même message
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class Cryptage {

    /** La taille maximale de l'ensemble de caractères */
    public final static int TAILLE_ENSEMBLE = 255;

    /** La longueur max de la clé */
    private final static double MAX_LONGUEUR_CLE = 100.0;

    /** La longueur min de la clé */
    private final static double MIN_LONGUEUR_CLE = 3.0;

    /**
     * Programme Principal
     * 
     * @param args
     */
    public static void main(String[] args) {
        String message = "Le cryptage c'est compliqué.";
        System.out.println("Message : " + message);
        Integer[] cle = genereCleDiffie();
        
        String crypte = chiffrer(message, cle);
        dechiffrer(crypte, cle);
    }

    /**
     * Génère la clé de cryptage grâce à la méthode de Diffie-Hellma
     * 
     * @return la clé
     */
    public static Integer[] genereCleDiffie() {
        int p = DiffieHellman.genererModulo();
        int g = DiffieHellman.genererGenerateur(p);
        int x = DiffieHellman.genererX();
        int x1 = DiffieHellman.genererX();
        int gx = DiffieHellman.puissanceModulo(g, x, p);
        int gxe = DiffieHellman.puissanceModulo(gx, x1, p);
        Integer[] cle = convertirValeurEnOffset(gxe);
        System.out.println("Clé : " + contenuCle(cle) + " de longueur " + cle.length);
        return cle;
    }

    /**
     * Méthode qui permet de Générer la clé de cryptage
     * 
     * @return laCle la clé de cryptage
     */
    public static Integer[] genereCleVigenere() {
        final int LONGUEUR_CLE = (int) (Math.random() * (MAX_LONGUEUR_CLE - MIN_LONGUEUR_CLE)) 
        		+ (int) MIN_LONGUEUR_CLE;
        
        int nombreAlea;
        Integer[] val = new Integer[LONGUEUR_CLE];
        
        for (int i = 0; i < LONGUEUR_CLE; i++) {
            nombreAlea = (int) (TAILLE_ENSEMBLE * Math.random());
            val[i] = nombreAlea;
        }
        System.out.println("Clé : " + contenuCle(val) + " de longueur " + val.length);
        return val;
    }

    /**
	 * Méthode qui permet de crypter un message
	 * 
	 * @param aChiffrer le message a crypter
         * @param offset    la clé
	 * @param cle       la clé de cryptage
	 * @return msgCrypte le message crypté grâce à la clé
	 */
	public static String chiffrer(String aChiffrer, Integer[] offset) {
	    String msgCrypte = "";
	    int indexCle = 0;
	
	    for (int c = 0; c < aChiffrer.length(); c++, indexCle++) {
	        int codeLettre = Character.codePointAt(aChiffrer, c);
	        int codeCle = offset[indexCle];
	        if (indexCle == offset.length - 1) {
	            indexCle = -1;
	        }
	        int charCrypte = (codeLettre + codeCle) % TAILLE_ENSEMBLE;
	        msgCrypte += Character.toString(charCrypte);
	    }
	    System.out.println("Message crypté : " + msgCrypte);
	    return msgCrypte;
	}
	
	/**
	 * Prend un entier et renvoie la clé sous forme d'offset
	 * @param entier
	 * @return la clé
	 */
	public static Integer[] convertirValeurEnOffset(int entier) {
		ArrayList<Integer> offset = new ArrayList<>();
		int reste;
		int divise = entier;
		do {
			reste = divise % TAILLE_ENSEMBLE;
			offset.add(reste);
			divise = divise / TAILLE_ENSEMBLE;
		} while (divise > TAILLE_ENSEMBLE);
		offset.add(divise);
		return offset.toArray(new Integer[0]);
	}
    
    /**
     * Méthode qui permet de décrypter un message
     * 
     * @param aDechiffrer le message a décrypter
     * @param offset      la clé de cryptage
     * @return msgDecrypte le message décrypté
     */
    public static String dechiffrer(String aDechiffrer, Integer[] offset) {
        String msgDecrypte = "";
        int indexCle = 0;

        for (int c = 0; c < aDechiffrer.length(); c++, indexCle++) {
            int codeLettre = Character.codePointAt(aDechiffrer, c);
            int codeCle = offset[indexCle];
            if (indexCle == offset.length - 1) {
                indexCle = -1;
            }
            int charDecrypte = (codeLettre - codeCle) % TAILLE_ENSEMBLE;
            if (charDecrypte < 0) {
                charDecrypte += TAILLE_ENSEMBLE;
            }
            msgDecrypte += Character.toString(charDecrypte);
        }
        System.out.println("Message décrypté : " + msgDecrypte);
        return msgDecrypte;
    }

    
    /**
     * représente les chiffres contenus dans une clé
     * @param offset 
     * @return le contenue de la clé
     */
    public static String contenuCle(Integer[] offset) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for (Integer chiffre : offset) {
			sb.append(chiffre).append(", ");
		}
    	sb.append("]");
    	return sb.toString();
    }
}
