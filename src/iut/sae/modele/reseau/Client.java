/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente le client dans les échanges de données via le réseau.
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 * @version 1.0
 */
public class Client {

    /**
     * La Socket client utilisée pour échanger.
     */
    private Socket sock;
    
    private static final File FICHIER_RECEPTION = 
            new File("src/iut/sae/modele/reseau/tests/fichierRecu.txt");
    
    /**
     * Utilitaires réseaux pour envoyer et recevoir facilement les chaînes
     * de caractères. Lié à la socket.
     */
    private ReseauUtils util;

    /**
     * @param host l'adresse ou le nom du serveur
     * @param port le port du serveur
     * @throws IOException si la socket client ne peut être crée
     */
    public Client(String host, int port) throws IOException {
        System.out.println("CREATION SOCKET EN COURS");
        try {
            sock = new Socket(host, port);
            util = new ReseauUtils(sock);
            System.out.println("CREATION DU CLIENT");
        } catch (UnknownHostException e) {
            throw new IOException("Hôte inconnu : " + e.getMessage());
        } catch (ConnectException e) {
            throw new IOException("La connexion a été refusée : " 
            + e.getMessage());
        } catch (IOException e) {
            throw new IOException(
                    "Erreur lors de la création de la Socket client : " 
            + e.getMessage());
        }
    }
    
    /** 
     * Récupère les données et génère les données
     * @return gA^b
     * @throws IOException
     * @throws InterruptedException 
     */
    public int echangerDonneesCryptage() throws IOException, InterruptedException {
        try {
        	System.out.println("Réception de P : ");
        	String msgP = util.reception();
            System.out.println("Réception de G : ");
            String msgG = util.reception();
            
	        int p = Integer.parseInt(msgP);
	        int g = Integer.parseInt(msgG);
	        
	        System.out.println("Réception de GA : ");
	        String msgGA = util.reception();
	        int gA = Integer.parseInt(msgGA);
	        
	        int b = DiffieHellman.genererX();
	        System.out.println("Valeur de B : " + b);
	        int gB = DiffieHellman.puissanceModulo(g, b, p);
	        Thread.sleep(1000);
	        System.out.println("Envoi de GB : ");
	        util.envoyerMessage(Integer.toString(gB));
	        
	        int cle = DiffieHellman.puissanceModulo(gA, b, p);
	        System.out.println("Clé générée : " + cle);
	        return cle;
        } catch (NumberFormatException e) {
        	throw new IOException("Les données envoyées par le serveur sont vides ou incorrectes. Réessayez.");
        }
    }
    
    /** 
     * Envoyer le fichier et la clé au serveur
     * @param fich
     * @param cle 
     * @throws IOException
     */
    public void envoyer(File fich, int cle) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(fich));
    	String contenuFich = "";
    	while (br.ready()) {
    		contenuFich += br.readLine() + "\n";
    	}
    	br.close();
    	
    	Integer[] offset = Cryptage.convertirValeurEnOffset(cle);
    	String fichEncode = Cryptage.chiffrer(contenuFich, offset);
    	util.envoyerMessage(fichEncode);
    }   
    
    /**
     * Ferme la socket courante.
     * 
     * @throws IOException si la socket ne peut être fermée
     */
    public void fermerSocket() throws IOException {
        System.out.println("FERMETURE DU CLIENT");
        try {
            sock.close();
        } catch (IOException e) {
            throw new IOException("Impossible de fermer la Socket client.");
        }
    }

	/** TODO comment method role
	 * @param fich
	 * @param offset
	 * @throws IOException
	 */
	public void envoyer(File fich, Integer[] offset) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(fich));
    	String contenuFich = "";
    	while (br.ready()) {
    		contenuFich += br.readLine() + "\n";
    	}
    	br.close();
    	
    	String fichEncode = Cryptage.chiffrer(contenuFich, offset);
    	util.envoyerMessage(fichEncode);
	}
}
