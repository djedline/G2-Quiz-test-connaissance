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

    private static final int MAX_P = 5000;

    /**
     * Méthode qui permet de générer aléatoirement le modulo de l'échange
     * 
     * @return p le chiffre qui sert de modulo
     */
    public static int genererModulo() {
        int p;
        do {
            p = (int) (1 + Math.random() * (MAX_P - 1));
        } while (!isPremier(p));
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
        int g;
        boolean doublon;
        boolean generateurValide;
        int nbDoublons = 0;
        do {
            // range la liste dans l'ordre croissant
            dejaFaits.sort((o1, o2) -> (o1.compareTo(o2)));
            if (nbDoublons > p / 2) {
                g = trouverEntierManquant(dejaFaits, MAX_G);
            } else {
                g = (int) (1 + Math.random() * (MAX_G - 1));
            }
            doublon = dejaFaits.contains(g);
            if (doublon) {
                generateurValide = false;
                nbDoublons++;
            } else {
                dejaFaits.add(g);
                generateurValide = isGenerateur(g, p);
            }
        } while (!generateurValide && g < p);
        return g;
    }

    /**
     * Cherche les entiers manquants dans une liste allant de 1 à {@code max} et
     * renvoie le premier dans l'ordre croissant n'apparaissant pas.
     * 
     * @param liste la liste où l'on cherche les valeurs
     * @param max   le nombre maximum où s'arrête la liste
     * @return renvoie le premier entier absent de la liste trouvé dans l'ordre
     *         croissant n'apparaissant pas
     */
    private static int trouverEntierManquant(ArrayList<Integer> liste, int max) {
        boolean existe = false;
        int entier = 0;
        do {
            entier++;
            existe = liste.contains(entier);
        } while (existe && entier < max);
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

        for (int j = 1; j < p - 1; j++) {
            int valeurValide = (int) puissanceModulo(g, j, p); // Récupère la valeur (g, g², etc) % p
            if (!valeurGValide.contains(valeurValide)) { // Vérifie si le chiffre obtenu n'est pas déja présent
                valeurGValide.add(valeurValide);
            } else {
                return false;
            }
        }
        return true;
    }

    private static int puissanceModulo(int nombreDepart, int dernierePuissance, int puissance, int modulo) {
        if (puissance < 0) {
            throw new IllegalArgumentException("Cette fonction ne prend pas en " + "charge les valeurs négatives.");
        } else if (puissance == 1) {
            return dernierePuissance;
        } else {
            int res = (nombreDepart * dernierePuissance) % modulo;
            return puissanceModulo(nombreDepart, res, puissance - 1, modulo);
        }
    }

    /**
     * Calcule une puissance avec un modulo
     * 
     * @param nombre    le nombre à passer en puissance
     * @param puissance la puissance à laquelle on veut l'élever
     * @param modulo    le modulo auquel on effectue le calcul
     * @return nombre, élevé à puissance, au modulo modulo
     */
    public static int puissanceModulo(int nombre, int puissance, int modulo) {
        if (puissance < 0) {
            throw new IllegalArgumentException("Cette fonction ne prend pas en " + "charge les valeurs négatives.");
        } else if (puissance == 0) {
            return 1;
        } else if (puissance == 1) {
            return nombre;
        } else {
            int res = (nombre * nombre) % modulo;
            return puissanceModulo(nombre, res, puissance - 1, modulo);
        }
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
                return false;
            }
        }
        return true;
    }
}
