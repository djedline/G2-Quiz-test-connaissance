/*
 * FichierMalFormeException.java                                      30/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.IOException;

/**
 * Classe d'exception sur un fichier mal formé
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class FichierMalFormeException extends IOException {

    /** Serial Version UID de la classe */
    private static final long serialVersionUID = 4194471556836552495L;

    /**
     * Constructeur avec un message d'erreur
     * 
     * @param string
     */
    public FichierMalFormeException(String string) {
        super(string);
    }

    /**
     * constructeur sans message 
     */
    public FichierMalFormeException() {
        super();
    }

}
