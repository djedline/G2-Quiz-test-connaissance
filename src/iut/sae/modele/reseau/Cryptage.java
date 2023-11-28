/*
 * Cryptage.java                                    14 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.lang.Math;
import iut.sae.modele.reseau.DiffieHellman;

/**
 * Classe qui permet le cryptage d'un message grâce à la génération d'une clé
 * puis le décryptage de ce même message
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class Cryptage {

    /** La taille maximale de l'ensemble de caractères */
    public final static int TAILLE_ENSEMBLE = 880;

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
        creerCleDiffie();
        
        String message = "Le cryptage c'est compliqué.";
        System.out.println("Message : " + message);
        String cle = genereCleVigenere();

        String crypte = chiffrer(message, cle);
        dechiffrer(crypte, cle);
    }

    /**
     * Méthode pour récupérer une clé selon l'algorithme de Diffie-Hellman
     * 
     * @return cleDiffieF la cle de cryptage
     */
    public static String creerCleDiffie() {
        int cleDiffieChiffre;
        int p;
        int g;
        int x;
        int gx;
        String cleDiffieF = "";
        p = DiffieHellman.genererModulo();
        System.out.println(p);
        g = DiffieHellman.genererGenerateur();
        System.out.println(g);
        x = DiffieHellman.genererX();
        System.out.println(x);
        gx = DiffieHellman.calculGX(g, x);
        System.out.println(gx);
        return cleDiffieF;
    }

    /**
     * Méthode qui permet de Générer la clé de cryptage
     * 
     * @return laCle la clé de cryptage
     */
    public static String genereCleVigenere() {
        String laCle = "";
        int nombreAlea;
        final int LONGUEUR_CLE = (int) (Math.random() * MAX_LONGUEUR_CLE - MIN_LONGUEUR_CLE) + (int) MIN_LONGUEUR_CLE;

        for (int i = 0; laCle.length() < LONGUEUR_CLE; i++) {
            nombreAlea = (int) (TAILLE_ENSEMBLE * Math.random());
            if (Character.isValidCodePoint(nombreAlea) && Character.toString(nombreAlea).length() == 1) {
                laCle += Character.toString(nombreAlea);
            }
        }
        System.out.println("Clé : " + laCle + " de longueur " + laCle.length());
        return laCle;
    }

    /**
     * Méthode qui permet de crypter un message
     * 
     * @param aChiffrer le message a crypter
     * @param cle       la clé de cryptage
     * @return msgCrypte le message crypté grâce à la clé
     */
    public static String chiffrer(String aChiffrer, String cle) {
        String msgCrypte = "";
        int indexCle = 0;

        for (int c = 0; c < aChiffrer.length(); c++, indexCle++) {
            int codeLettre = Character.codePointAt(aChiffrer, c);
            int codeCle = Character.codePointAt(cle, indexCle);
            if (indexCle == cle.length() - 1) {
                indexCle = -1;
            }
            int charCrypte = (codeLettre + codeCle) % TAILLE_ENSEMBLE;
            msgCrypte += Character.toString(charCrypte);
        }
        System.out.println("Message crypté : " + msgCrypte);
        return msgCrypte;
    }

    /**
     * Méthode qui permet de décrypter un message
     * 
     * @param aDechiffrer le message a décrypter
     * @param cle         la clé de cryptage
     * @return msgDecrypte le message décrypté
     */
    public static String dechiffrer(String aDechiffrer, String cle) {
        String msgDecrypte = "";
        int indexCle = 0;

        for (int c = 0; c < aDechiffrer.length(); c++, indexCle++) {
            int codeLettre = Character.codePointAt(aDechiffrer, c);
            int codeCle = Character.codePointAt(cle, indexCle);
            if (indexCle == cle.length() - 1) {
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
}
