/*
 * FichierMalFormeException.java                                      30/11/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele;

import java.io.IOException;

/**
 * Exception qui se lève lorsqu'un fichier est mal formé
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class FichierMalFormeException extends IOException {

    /** Serial Version UID de la classe */
    private static final long serialVersionUID = 4194471556836552495L;

    /**
     * Constructeur avec un message d'erreur
     * @param messageErreur message qui s'affiche lorsque l'erreur est levé
     */
    public FichierMalFormeException(String messageErreur) {
        super(messageErreur);
    }

    /**
     * constructeur sans message 
     */
    public FichierMalFormeException() {
        super();
    }

}
