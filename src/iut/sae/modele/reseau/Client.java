/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

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

    /*
     * Méthode de test des sockets.
     * 
     * @param args
     *
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            creerLiaisonServeur("10.2.14.31", 6666);
            String reponse = "";
            //reponse = recevoirEtAnalyser();
            envoyerReponse(reponse);
            fermerSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


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
	        int gB = (int) Math.pow(g, b);
	        Thread.sleep(1000);
	        System.out.println("Envoi de GB : ");
	        util.envoyerMessage(Integer.toString(gB));
	        
	        int cle = (int) Math.pow(gA, b);
	        System.out.println("Clé générée : " + cle);
	        return cle;
        } catch (NumberFormatException e) {
        	throw new IOException("Données corrompues envoyées par le serveur.");
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
    	
    	String fichEncode = Cryptage.chiffrer(contenuFich, Integer.toString(cle));
    	util.envoyerMessage(fichEncode);
    }
    
    /**
     * Permet de recevoir la requete du client et de l'analyser pour construire 
     * le contenu de la reponse
     * Crypte le fichier et l'envoie
     * @param fichierEnvoyer 
     * 
     * @return text : la reponse a la requete
     */
    /*
    public String recevoirEtAnalyser(File fichierEnvoyer) {
        
        String cle = "";
        String fichLu = "";
        
        System.out.println("RECEPTION DE LA REPONSE");
        try {

            if (sock != null) {
                InputStream is = sock.getInputStream();
                boolean test = true;
                while (test) {
                    if (is.available() != 0) {
                        System.out.println("En attente...");
                        test = false;
                    }

                }
                
                // lis la clé en UTF-8
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(sock.getInputStream(), "UTF-8"));
                while (reader.ready()) {
                    cle += Character.toString(reader.read());
                }
                System.out.println("Le serveur a reçu la clé : " + cle);

                System.out.println(fichierEnvoyer.getAbsolutePath());
                if (!fichierEnvoyer.exists()) {
                    fichierEnvoyer.createNewFile();
                }

                FileReader fr = new FileReader(
                        fichierEnvoyer, Charset.forName("UTF-8"));
                while (fr.ready()) {
                    fichLu += Character.toString(fr.read());
                }
                System.out.println("Le fichier contient : " + fichLu);
                fichLu = Cryptage.chiffrer(fichLu, cle);
                fr.close();
                System.out.println("le serveur a écrit : " + fichLu);
            }

        } catch (Exception e) {
            System.err.println("Impossible de recevoir la requête.");
            e.printStackTrace();
        }
        return fichLu;
    }
    */
    
 /*   /**
     * Permet d'envoyer la reponse au client en retour d'une requete
     * 
     * @param rep : la reponse a envoyer
     
    public void envoyerReponse(String rep) {
        
        String msgx2 = "";
        String fichLu = "";
        
        int x2;
        int gx2;
        int gxe1;
        
        System.out.println("ENVOI DE LA REPONSE");
        System.out.println("Le serveur est : " + sock.getLocalSocketAddress());
        System.out.println("Le client est : " + sock.getRemoteSocketAddress());

        try {
            if (sock != null) {
                OutputStream os = sock.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(rep);
                System.out.println("Le client a envoyé " + rep);
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("Impossible de répondre au serveur.");
            e.printStackTrace();
        }
    }*/
    
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
}
