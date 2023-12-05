package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

/** 
 * Fonction utilitaire qui permette de tout récupére facilement
 * @author djedline.boyer
 *
 */
public class ReseauUtils {
    private BufferedReader br;
    private BufferedWriter bw;

    /**
     * Crée les utilitaires réseau pour une socket.
     * @param sock la socket qui va envoyer et recevoir des messages.
     * @throws IOException si l'utilitaire ne peut être créé
     */
    public ReseauUtils(Socket sock) throws IOException {
        OutputStream os = sock.getOutputStream();
        InputStream is = sock.getInputStream();
        Charset encoding = Charset.forName("UTF-8");

        this.bw = new BufferedWriter(new OutputStreamWriter(os, encoding));
        this.br = new BufferedReader(new InputStreamReader(is, encoding));
    }



    /** TODO comment field role (attribute, association) */
    public static final int RECEIVE_TIMEOUT = 10;

    /** 
     * Lit les données dans une String depuis la socket tant qu'elles 
     * sont disponibles. Bloquant pendant 
     * @param sock 
     * @throws IOException si la lecture est impossible.
     * @return strRecu l'ensemble des données reçues
     */
    public String reception() throws IOException {
        String strRecu = "";
        for (int sec = 0 ; sec < RECEIVE_TIMEOUT && !br.ready(); sec++) {
            System.out.println("En attente...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (br.ready()) {
            strRecu += Character.toString(br.read());
        }
        System.out.println("Message reçu : " + strRecu);
        return strRecu;
    }

    /**
     * Envoie les données passées en paramètre dans la socket.
     * @param sock 
     * @param data les données à envoyer
     * @throws IOException si les données ne sont pas envoyées.
     */
    public void envoyerMessage(String data) throws IOException {
        try {
            bw.write(data);
            bw.flush();
            System.out.println("Message envoyé : " + data.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Impossible d'envoyer le message.");
        }
    }

}
