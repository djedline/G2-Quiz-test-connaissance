/*
 * TestClient.java                                    25 oct. 2023
 * IUT Rodez, info2 2022-2023, pas de copyright ni "copyleft"
 */
package src.iut.sae.modele.reseau.tests;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import src.iut.sae.modele.reseau.Client;
/** 
 * @author leila.baudroit
 */
public class TestClient {
    
    /** 
     * Méthode de test des sockets.
     * @param args
     */
    public static void main(String[] args) {
        testerEcho();
    }
    
    /** 
     * Teste le fonctionnement d'un serveur Echo.
     * @return true si le echo a fonctionné, false sinon
     */
    public static boolean testerEcho() { 
        try {
            Scanner sc = new Scanner(System.in);
            Client.creerClient("localhost", 6666);
            File f = new File("src/iut/sae/modele/reseau/tests/fich.txt");
            FileReader fr = new FileReader(f);
            String envoye = "";
            while (fr.ready()) {
                envoye += Character.toString(fr.read());
            }
            Client.envoyerMessage(envoye.getBytes());
            System.out.println("Le client a envoyé : " +  envoye);
            
            String recu = Client.recevoirMessage();
            System.out.println("Le client a reçu : " + recu);
            File f2 = new File("src/iut/sae/modele/reseau/tests/fich.txt");
            FileWriter fw = new FileWriter(f2);
            fw.append(recu);
            
            Client.fermerSocket();
            return envoye == recu;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
