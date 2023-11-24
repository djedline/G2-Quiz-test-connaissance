/*
 * DiffieHelman.java                                    15 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.util.ArrayList;

/**
 * Classe qui permet de reproduire un échange de Diffie-Hellman pour générer une
 * clé de cryptage
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class DiffieHellman {

    /* Variable contenant la valeur de p, le modulo pour l'échange de données */
    private int p;

    /*
     * Variable contenant le générateur échangé par le serveur et le client en clair
     */
    private int g;

    /* Variable contenant le chiffre qui sert de premier exposant */
    private int x;

    private final int MAX_P = Cryptage.TAILLE_ENSEMBLE;

    private final int MAX_G = p - 1;

    /**
     * Méthode qui permet de générer aléatoirement le modulo de l'échange
     * 
     * @return p le chiffre qui sert de modulo
     */
    public int genererModulo() {
        p = (int) (Math.random() * MAX_P);
        return p;
    }

    /**
     * Méthode qui permet de générer aléatoirement g
     * 
     * @return g le générateur échangé par le client et le serveur
     */
    public int genererGenerateur() {
        do {
            g = (int) (1 + Math.random() * MAX_G - 1);
        } while (!isGenerateur(g));
        return g;
    }

    /**
     * Méthode qui permet de génerer aléatoirement x
     * 
     * @return x chiffre qui sert d'exposant
     */
    public int genererX() {
        x = (int) (1 + Math.random() * MAX_P - 1);
        return x;
    }

    /**
     * Méthode qui permet de calculer g à la puissance x
     * 
     * @param g le générateur échangé par le client et le serveur
     * @param x chiffre qui sert de premier exposant
     * @return ga g à la puissance x
     */
    public int calculGX(int g, int x) {
        int gx;
        gx = (int) Math.pow(g, x) % p;
        return gx;
    }

    /**
     * Méthode qui permet de calculer gXE
     * 
     * @param gx          résultat de g à la puissance x
     * @param eltArrivant chiffre qui sert de deuxième exposant reçu depuis le
     *                    serveur ou le client
     * @return gXE g à la puissance x, à la puissance E (l'élement reçu depuis le
     *         client ou le serveur
     */
    public int calculGXE(int gx, int eltArrivant) {
        int gXE;
        gXE = (int) Math.pow(gx, eltArrivant) % p;
        return gXE;
    }

    /**
     * Méthode qui permet de déterminer si g est ou non un générateur du groupe
     * multiplicatif ℤ/pℤ
     * 
     * @param g
     * @return True ou False selon si le nombre choisi est un générateur ou non
     */
    public boolean isGenerateur(int g) {
        /** Ensemble des résultats de g modulo p ( g, g², g³, ..., g) */
        ArrayList<Integer> valeurGValide = new ArrayList<>();
        /** L'ensemble des valeurs dans ℤ/pℤ */
        ArrayList<Integer> ensembleP = new ArrayList<>();

        for (int i = 1; i < ensembleP.size(); i++) {
            ensembleP.add(i);
        }
        for (int j = 1; j < valeurGValide.size(); j++) {
            int valeurValide = (int) ((Math.pow(g, j)) % p); // Récupère la valeur (g, g², etc) % p
            if (!valeurGValide.contains(valeurValide)) { // Vérifie si le chiffre obtenu n'est pas déja présent
                valeurGValide.add(valeurValide);
            }
        }
        if (valeurGValide.size() == ensembleP.size()) { // Vérifie la taille des 2 ArrayList
            return true;
        } else {
            return false;
        }
    }
}
