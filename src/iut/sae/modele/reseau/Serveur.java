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
            System.err.println("Impossible de trouver l'ip");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Impossible de créer la Socket serveur.");
            e.printStackTrace();
        }
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
     * Méthode qui crée la clé a envoyer au serveur a partir d'un fichier
     * 
     * @return renvoie une chaine avec la clé à envoyer
     * @throws IOException si le message n'a pas pu être construit
     */
    public static String genererCle() throws IOException {
        return Cryptage.genereCleDiffie();
    }
    
    /**
     * @param data les données à envoyer
     * @throws IOException si les données ne sont pas envoyées.
     */
    public void envoyerMessage(byte[] data) throws IOException {
        System.out.println("ENVOI DES DONNEES");
        try {
            OutputStream os = comm.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                                                  os, "UTF-8"));
            os.write(data);
            System.out.println("Le serveur a envoyé : " + data.toString());
        } catch (IOException e) {
            throw new IOException("Impossible d'envoyer le message au client.");
        }
    }


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