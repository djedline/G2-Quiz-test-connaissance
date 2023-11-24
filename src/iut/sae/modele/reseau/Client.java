/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

import iut.sae.modele.reseau.Cryptage;

/*
 * Représente le client dans les échanges de données via le réseau.
 */
/**
 * TODO comment class responsibility (SRP)
 * 
 * @author leila.baudroit, djedline.boyer, nael.briot, tany.catala-bailly,
 *         leo.cheikh-boukal
 * @version 1.0
 */
public class Client {

    /**
     * La Socket client utilisée pour échanger.
     */
    private static Socket sock;
    /** socket qui permet la communication entre le serveur et le client */
    static Socket comm;
    
    private static final File FICHIER_RECEPTION = 
            new File("src/iut/sae/modele/reseau/tests/fichierRecu.txt");

    /**
     * Méthode de test des sockets.
     * 
     * @param args
     */
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
    }

    /**
     * @param host l'adresse ou le nom du serveur
     * @param port le port du serveur
     * @throws IOException si la socket client ne peut être crée
     */
    public static void creerLiaisonServeur(String host, int port) throws IOException {
        System.out.println("CREATION DU CLIENT");
        try {
            sock = new Socket(host, port);
        } catch (IOException e) {
            throw new IOException("Impossible de créer la Socket client.");
        }
    }

    /**
     * Permet de recevoir la requete du client et de l'analyser pour construire le
     * contenu de la reponse
     * Crypte le fichier et l'envoie
     * @param fichierEnvoyer 
     * 
     * @return text : la reponse a la requete
     */
     public static String recevoirEtAnalyser(File fichierEnvoyer) {
        String cle = "";
        String fichLu = "";


        System.out.println("RECEPTION DE LA REPONSE");
        try {

            if (comm != null) {
                InputStream is = comm.getInputStream();
                boolean test = true;
                while (test) {
                    if (is.available() != 0) {
                        System.out.println("En attente...");
                        test = false;
                    }

                }
                
                // lis la clé en UTF-8
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(comm.getInputStream(), "UTF-8"));
                while (reader.ready()) {
                    cle += Character.toString(reader.read());
                }
                System.out.println("Le serveur a reçu la clé : " + cle);

                System.out.println(fichierEnvoyer.getAbsolutePath());
                if (!fichierEnvoyer.exists()) {
                    fichierEnvoyer.createNewFile();
                }

                FileReader fr = new FileReader(fichierEnvoyer, Charset.forName("UTF-8"));
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
    
    /**
     * Permet d'envoyer la reponse au client en retour d'une requete
     * 
     * @param rep : la reponse a envoyer
     */
    public static void envoyerReponse(String rep) {
        System.out.println("ENVOI DE LA REPONSE");
        System.out.println("Le serveur est : " + comm.getLocalSocketAddress());
        System.out.println("Le client est : " + comm.getRemoteSocketAddress());

        try {
            if (comm != null) {
                OutputStream os = comm.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(rep);
                System.out.println("Le client a envoyé " + rep);
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("Impossible de répondre au serveur.");
            e.printStackTrace();
        }
    }
    
    /**
     * Ferme la socket courante.
     * 
     * @throws IOException si la socket ne peut être fermée
     */
    public static void fermerSocket() throws IOException {
        System.out.println("FERMETURE DU CLIENT");
        try {
            sock.close();
        } catch (IOException e) {
            throw new IOException("Impossible de fermer la Socket client.");
        }
    }
}
