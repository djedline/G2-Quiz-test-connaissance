/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/** 
 * Représente le client dans les échanges de données via le réseau.
 */
public class Client {
    
    /**
     * La Socket client utilisée pour échanger.
     */
    private static Socket sock;
    
    /** 
     * Méthode de test des sockets.
     * @param args
     */
    public static void main(String[] args) {
            try {
                Scanner sc = new Scanner(System.in);
                creerClient("127.0.0.1", 6666);
         
                String message = "";
                File fichierATraiter;
                String s = "";
                
                while(s.isEmpty()) {
                	System.out.print("Saisissez le chemin absolu du fichier a envoyer : ");
                	fichierATraiter = new File(sc.nextLine());
                	message = contruireMessage(fichierATraiter);
                	envoyerMessage(message.getBytes());
                    s = recevoirMessage();
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
    public static void creerClient(String host, int port) throws IOException{
            System.out.println("CREATION DU CLIENT");
            try {
                sock = new Socket(host, port);
            } catch (IOException e) {
                throw new IOException("Impossible de créer la Socket client.");
            }
    }
    
    /**
     * Méthode qui crée le message a envoyer au serveur a partir d'un fichier
     * @param aEnvoyer fichier que l'on va traiter pour etre envoyer sous 
     * forme de chaine 
     * @return renvoie une chaine avec le contenu du fichier
     * @throws IOException si le message n'a pas pu être construit 
     */
    public static String contruireMessage(File aEnvoyer) throws IOException{
    	System.out.println("Chemin : " + aEnvoyer.getAbsolutePath());
    	FileReader fr = new FileReader(aEnvoyer);
    	String message = "";
    	while(fr.ready()){
    		message += Character.toString(fr.read());
    	}
    	return message;
    }
    
    /**
     * @param data les données à envoyer
     * @throws IOException si les données ne sont pas envoyées. 
     */
    public static void envoyerMessage(byte[] data) throws IOException {
            System.out.println("ENVOI DES DONNEES");
            try {
                OutputStream os = sock.getOutputStream();
                os.write(data);
                System.out.println("Le client a envoyé : " + data.toString());
            } catch (IOException e) {
                throw new IOException("Impossible d'envoyer le message au serveur.");
            }
    }
    
    /** 
     * @return le message reçu
     * @throws InterruptedException
     * @throws IOException 
     */
    public static String recevoirMessage() throws InterruptedException, IOException {
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
                String s = "";
                while (is.available() != 0) {
                        s += Character.toString(is.read());
                }
                System.out.println("Le client a reçu : " + s);
                return s;
            } catch (IOException e) {
                throw new IOException("Impossible de recevoir le message du serveur.");
            } catch (Exception e) {
                throw new InterruptedException("La connection a été interrompue");
            }
    }
    
    /**
     * Ferme la socket courante.
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
