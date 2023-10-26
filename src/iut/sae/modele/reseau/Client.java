/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

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
                creerClient("localhost", 6666);
         
                String text = "";
                while(!text.equalsIgnoreCase("stop")) {
                	System.out.print("Saisissez un message : ");
                	text = sc.nextLine();
                	envoyerMessage(text.getBytes());
                    String s = recevoirMessage();
                    System.out.println("Le client a reçu : " + s);
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
                do {
                    System.out.println("Le client a recu " + is.available() + " octets.");
                    Thread.sleep(2000);
                } while (is.available() == 0);
                String s = "";
                while (is.available() != 0) {
                        s += is.read();
                }
                System.out.println("Le client a reçu : " + s);
                return s;
            } catch (IOException e) {
                throw new IOException("Impossible de recevoir le message du serveur.");
            } catch (InterruptedException e) {
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
