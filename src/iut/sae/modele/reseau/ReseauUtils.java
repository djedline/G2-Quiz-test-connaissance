package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ReseauUtils {
	
    /** 
     * Lit les données dans une String depuis la socket tant qu'elles 
     * sont disponibles.
     * @throws IOException si la lecture est impossible.
     * @return strRecu l'ensemble des données reçues
     */
    public static String reception(Socket sock) throws IOException {
        String strRecu = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(sock.getInputStream(), "UTF-8"));
        while (reader.ready()) {
            strRecu += Character.toString(reader.read());
        }
        return strRecu;
    }
	
    /**
     * Envoie les données passées en paramètre dans la socket.
     * @param data les données à envoyer
     * @throws IOException si les données ne sont pas envoyées.
     */
    public static void envoyerMessage(Socket sock, String data) throws IOException {
        System.out.println("ENVOI DES DONNEES");
        try {
            OutputStream os = sock.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                                                  os, "UTF-8"));
            bw.write(data);
            System.out.println("Le serveur a envoyé : " + data.toString());
        } catch (IOException e) {
            throw new IOException("Impossible d'envoyer le message au client.");
        }
    }

}
