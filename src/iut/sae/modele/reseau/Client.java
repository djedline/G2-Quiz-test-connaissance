/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import iut.sae.modele.reseau.Cryptage;

/*
 * Représente le client dans les échanges de données via le réseau.
 */
/** TODO comment class responsibility (SRP)
 * @author djedline.boyer
 *
 */
public class Client {

    /**
     * La Socket client utilisée pour échanger.
     */
    private static Socket sock;
    
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
            creerClient("10.2.14.31", 6666);
			String cle = "";
			String recu = "";
            while (recu.isEmpty()) {
                System.out.print("Génération et envoi de la clé");
                cle = construireMessage();
                System.out.println("Le client a envoyé : " + cle);
                envoyerMessage(cle.getBytes());
                recu = recevoirMessage(FICHIER_RECEPTION, cle);
            }
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
    public static void creerClient(String host, int port) throws IOException {
        System.out.println("CREATION DU CLIENT");
        try {
            sock = new Socket(host, port);
        } catch (IOException e) {
            throw new IOException("Impossible de créer la Socket client.");
        }
    }

    /**
     * Méthode qui crée la clé a envoyer au serveur a partir d'un fichier
     * 
     * @return renvoie une chaine avec la clé à envoyer
     * @throws IOException si le message n'a pas pu être construit
     */
    public static String construireMessage() throws IOException {
        return Cryptage.genereCle();
    }

    /**
     * @param data les données à envoyer
     * @throws IOException si les données ne sont pas envoyées.
     */
    public static void envoyerMessage(byte[] data) throws IOException {
        System.out.println("ENVOI DES DONNEES");
        try {
            OutputStream os = sock.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            os.write(data);
            System.out.println("Le client a envoyé : " + data.toString());
        } catch (IOException e) {
            throw new IOException("Impossible d'envoyer le message au serveur.");
        }
    }

    /**
     * Saisie le message reçu dans un fichier.
     * @param fichRecu le fichier dans lequel écrire
     * @param cle la clé de décryptage
     * @return le message reçu
     * @throws InterruptedException
     * @throws IOException
     */
    public static String recevoirMessage(File fichRecu, String cle) throws InterruptedException, IOException {
        String recu = "";
        try {
            System.out.println("RECEPTION DES DONNEES");
            InputStream is = sock.getInputStream();
            boolean test = true;
            while (test) {
                if (is.available() != 0) {
                    System.out.println("Le serveur a recu : " + is.available() + " octets.");
                    test = false;
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while (reader.ready()) {
                recu += Character.toString(reader.read());
            }
            System.out.println("Recu par le client : " + recu);
            
            if (!fichRecu.exists()) {
                fichRecu.createNewFile();
            }
            recu = Cryptage.dechiffrer(recu, cle);
        } catch (IOException e) {
            System.err.println("C'est la réception le problème.");
            e.printStackTrace();
        }
        
        try {
            FileWriter fw = new FileWriter(fichRecu);
            fw.append(recu);
            fw.flush();
            fw.close();

            System.out.println("Le client a reçu : " + recu);
            return recu;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("C'est le fichier le problème.");
        } catch (Exception e) {
            throw new InterruptedException("La connection a été interrompue");
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
