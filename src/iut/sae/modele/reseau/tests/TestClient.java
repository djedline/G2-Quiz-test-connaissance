/*
 * TestClient.java                                    25 oct. 2023
 * IUT Rodez, info2 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import iut.sae.modele.reseau.Client;
/** 
 * @author leila.baudroit
 */
public class TestClient {
    
    /** 
     * Méthode de test des sockets.
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Client.creerClient("localhost", 6666);
            File f = new File("src/iut/sae/modele/reseau/tests/fich.txt");
            FileReader fr = new FileReader(f);
            String s = "";
            while (fr.ready()) {
                s += Character.toString(fr.read());
            }
            Client.envoyerMessage(s.getBytes());
            System.out.println("Le client a envoyé : " +  s);
            
            s = Client.recevoirMessage();
            System.out.println("Le client a reçu : " + s);
            Client.fermerSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * Teste le serveur echo
     */
    public static void testerAvecString() {
        
        try {
            Scanner sc = new Scanner(System.in);
            Client.creerClient("localhost", 6666);
            System.out.print("Saisissez un message : ");
            String text = sc.nextLine();
            Client.envoyerMessage(text.getBytes());
            System.out.println("Le client a envoyé : " +  text);
            String s = Client.recevoirMessage();
            System.out.println("Le client a reçu : " + s);
            Client.fermerSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
