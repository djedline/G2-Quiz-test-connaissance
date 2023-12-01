/* Serveur.java												16/10/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe qui représente le serveur lors d'un échange d'information
 * 
 * @author Baudroit Leila, Boyer Djedline, Briot Nael, Catala-Bailly,
 *         Cheikh-Boukal Léo
 */
public class Serveur {

    private static final File FICHIER_A_ENVOYER = 
            new File("src/iut/sae/modele/reseau/tests/fichEnvoi.txt");

    /** socket de connexion lors du démarrage du client et serveur */
    public ServerSocket conn;

    /** socket qui permet la communication entre le serveur et le client */
    private Socket comm;

    /**
     * prépare le serveur en démarrant la socket conn
     * 
     */
    public Serveur() {
        System.out.println("CREATION DU SERVEUR");
        try {          
            conn = new ServerSocket(6666);
            System.out.println("coucou");
        } catch (UnknownHostException e) {
            System.err.println("Impossible de trouver l'adresse IP");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Impossible de créer la Socket serveur.");
            e.printStackTrace();
        }
    }
    
    /** 
     * Envoie les données initiales (P et G de Diffie-Hellman)
     * @throws IOException 
     * @throws InterruptedException 
     */
    public int envoiDonneesInitiale() throws IOException, InterruptedException {
        int p = DiffieHellman.genererModulo();
        int g = DiffieHellman.genererGenerateur();
        String msgP = Integer.toString(p);
        String msgG = Integer.toString(g);
        int b = DiffieHellman.genererX();
        
        ReseauUtils.envoyerMessage(comm, msgG);
        Thread.sleep(500);
        ReseauUtils.envoyerMessage(comm, msgP);
        Thread.sleep(500);
        System.out.println("Le serveur a envoyé : p et g)");
        
        String msgGB = Integer.toString((int) Math.pow(g, b));
        ReseauUtils.envoyerMessage(comm, msgGB);
        
        String msgGA = ReseauUtils.reception(comm);
        try {
        	int gA = Integer.parseInt(msgGA);
            return (int) Math.pow(gA, b);
        } catch (NumberFormatException e) {
        	throw new IOException("Données corrompues envoyées par le serveur");
        }
    }
    
    public String receptionFichier(int cle) throws IOException {
    	String contenuFichCrypte = ReseauUtils.reception(comm);
    	return Cryptage.dechiffrer(contenuFichCrypte, 
    			Integer.toString(cle));
    }
    
    /*
     * 
     * @param args
     * @throws InterruptedException
     *
    public void main(String[] args) throws InterruptedException {
        preparerServeur();
        accepterConnexion(); // bloquante : attend que le client se connecte
        String cle = "";
        String recu ="";
        while (recu.isEmpty()) {
            System.out.print("Génération et envoi de la clé");
            try {
                cle = genererCle();
                System.out.println("Le client a envoyé : la cle)");
                envoyerMessage(cle.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //recu = recevoirMessage(FICHIER_RECEPTION, cle);
        }

       // reponse = recevoirEtAnalyser();
        //envoyerReponse(reponse);

        Thread.sleep(1000);

        System.out.println("FERMETURE DU SERVEUR");
        try {
            conn.close();
        } catch (IOException e) {
            System.err.println("Impossible de fermer la socket serveur.");
            e.printStackTrace();
        }
    }*/


    /**
     * Ferme le serveur
     */
    public void fermetureServeur() {
        try {
            conn.close();
        } catch (IOException e) {
            System.err.println("Impossible de fermer la socket serveur.");
            e.printStackTrace();
        }
    }

    /**
     * attend qu'un client demande une connexion et l'accepte
     */
    public void accepterConnexion() {

        System.out.println("ACCEPTATION");
        try {
            comm = conn.accept();
            System.out.println("La inet Adress conn : " 
                                + conn.getInetAddress());
            System.out.println("La inet Adress comm : " 
                                + comm.getInetAddress());
        } catch (IOException e) {
            System.err.println("Impossible d'accepter la connection.");
            e.printStackTrace();
        }
    }
    

}