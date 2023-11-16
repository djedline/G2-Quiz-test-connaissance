package iut.sae.modele;

import java.io.IOException;

public class FichierMalFormeException extends IOException {

	public FichierMalFormeException(String string) {
		super(string);
	}
	
	public FichierMalFormeException() {
		super();
	}

}
