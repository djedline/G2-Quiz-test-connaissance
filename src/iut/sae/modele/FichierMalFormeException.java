package iut.sae.modele;

import java.io.IOException;

/**
 * TODO comment class responsibility (SRP)
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class FichierMalFormeException extends IOException {

    /**
     * TODO comment initial state
     * 
     * @param string
     */
    public FichierMalFormeException(String string) {
        super(string);
    }

    /**
     * TODO comment initial state
     * 
     */
    public FichierMalFormeException() {
        super();
    }

}
