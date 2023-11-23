/* Serveur.java												16/10/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * Classe qui représente le serveur lors d'un échange d'information
 * 
 * @author Baudroit Leila, Boyer Djedline, Briot Nael, Catala-Bailly,
 *         Cheikh-Boukal Léo
 */
public class Serveur {

    private static final File FICHIER_A_ENVOYER = new File("src/iut/sae/modele/reseau/tests/fichEnvoi.txt");

    /** socket de connexion lors du démarrage du client et serveur */
    public static ServerSocket conn;

    /** socket qui permet la communication entre le serveur et le client */
    static Socket comm;

    /**
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        preparerServeur();
        accepterConnexion(); // bloquante : attend que le client se connecte
        String reponse = "";

        reponse = recevoirEtAnalyser();
        envoyerReponse(reponse);

        Thread.sleep(1000);

        System.out.println("FERMETURE DU SERVEUR");
        try {
            conn.close();
        } catch (IOException e) {
            System.err.println("Impossible de fermer la socket serveur.");
            e.printStackTrace();
        }

    }

    /**
     * prépare le serveur en démarrant la socket conn
     * 
     * @return l'adresse inet
     */
    public static String preparerServeur() {
        System.out.println("CREATION DU SERVEUR");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            conn = new ServerSocket(6666);
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Impossible de trouver l'ip");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Impossible de créer la Socket serveur.");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Ferme le serveur
     */
    public static void fermetureServeur() {
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
    public static void accepterConnexion() {
        System.out.println("ACCEPTATION");
        try {
            comm = conn.accept();
            System.out.println("La inet Adress conn : " + conn.getInetAddress());
            System.out.println("La inet Adress comm : " + comm.getInetAddress());
        } catch (IOException e) {
            System.err.println("Impossible d'accepter la connection.");
            e.printStackTrace();
        }
    }

    /**
     * Permet de recevoir la requete du client et de l'analyser pour construire le
     * contenu de la reponse
     * 
     * @return text : la reponse a la requete
     */
    public static String recevoirEtAnalyser() {
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(comm.getInputStream(), "UTF-8"));
                while (reader.ready()) {
                    cle += Character.toString(reader.read());
                }
                System.out.println("Le serveur a reçu la clé : " + cle);

                System.out.println(FICHIER_A_ENVOYER.getAbsolutePath());
                if (!FICHIER_A_ENVOYER.exists()) {
                    FICHIER_A_ENVOYER.createNewFile();
                }

                FileReader fr = new FileReader(FICHIER_A_ENVOYER, Charset.forName("UTF-8"));
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
                System.out.println("Le serveur a envoyé " + rep);
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("Impossible de répondre au client.");
            e.printStackTrace();
        }
    }
}