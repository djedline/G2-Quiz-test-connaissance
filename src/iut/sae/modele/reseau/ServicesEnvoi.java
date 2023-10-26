/*
 * ServicesEnvoi.java                                    25 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.File;
import java.io.FileReader;

/** TODO comment class responsibility (SRP)
 * @author leila.baudroit
 *
 */
public class ServicesEnvoi {
    
    enum Role{
        CLIENT,
        SERVEUR
    }
    
    public void ouvrirConnexion(Role role) {
        
    }
    
    public void envoyerFichier(File f) {
        if (f.exists() && f.isFile() && f.canRead()) {
            FileReader fr = new FileReader(f);
        }
    }

}
